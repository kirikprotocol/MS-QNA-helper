package com.eyelinecom.whoisd.sads2.msqna.bot;

import com.eyeline.utils.config.ConfigException;
import com.eyeline.utils.config.xml.XmlConfig;
import com.eyeline.utils.config.xml.XmlConfigSection;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * author: Denis Enenko
 * date: 11.09.17
 */
public class InitListener implements ServletContextListener {

  private final static String CONFIG_DIR = "sads-msqna-bot.config.dir";
  private final static String DEFAULT_CONFIG_DIR = "conf";
  private final static String CONFIG_FILE_NAME = "config.xml";


  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    final File configDir = getConfigDir();

    initLog4j(configDir);

    XmlConfig cfg = loadXmlConfig(configDir);

    XmlConfigSection botCfg = getBotConfig(cfg);
    XmlConfigSection msQnaSection = getMsQnaSection(botCfg);

    String pushUrl = getPushUrl(botCfg);
    String botAskCommandName = getBotAskCommandName(botCfg);

    String msQnaKnowledgeBaseID = getMsQnaKnowledgeBaseID(msQnaSection);
    String msQnaSubscriptionKey = getMsQnaSubscriptionKey(msQnaSection);
    int minAcceptableScoreLevel = getMinAcceptableScoreLevel(msQnaSection);

    WebContext.init(msQnaKnowledgeBaseID, msQnaSubscriptionKey, minAcceptableScoreLevel, botAskCommandName, pushUrl);
  }

  private File getConfigDir() {
    String configDir = System.getProperty(CONFIG_DIR);

    if(configDir == null) {
      configDir = DEFAULT_CONFIG_DIR;
      System.err.println("System property '" + CONFIG_DIR + "' is not set. Using default value: " + configDir);
    }

    File cfgDir = new File(configDir);

    if(!cfgDir.exists())
      throw new RuntimeException("Config directory '" + cfgDir.getAbsolutePath() + "' does not exist");

    System.out.println("Using config directory '" + cfgDir.getAbsolutePath() + "'");

    return cfgDir;
  }

  private XmlConfig loadXmlConfig(File configDir) {
    final File cfgFile = new File(configDir, CONFIG_FILE_NAME);
    XmlConfig cfg = new XmlConfig();

    try {
      cfg.load(cfgFile);
    }
    catch(ConfigException e) {
      throw new RuntimeException("Unable to load config.xml", e);
    }

    return cfg;
  }

  private XmlConfigSection getBotConfig(XmlConfig config) {
    try {
      return config.getSection("ms.qna.bot");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Section ms.qna.bot is not found in config.", e);
    }
  }

  private XmlConfigSection getMsQnaSection(XmlConfigSection botSection) {
    try {
      return botSection.getSection("ms.qna");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Section ms.qna is not found in config.", e);
    }
  }

  private String getMsQnaKnowledgeBaseID(XmlConfigSection msQnaSection) {
    try {
      return msQnaSection.getString("knowledge.base.id");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Parameter knowledge.base.id is not found in ms.qna section.", e);
    }
  }

  private String getMsQnaSubscriptionKey(XmlConfigSection msQnaSection) {
    try {
      return msQnaSection.getString("subscription.key");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Parameter subscription.key is not found in ms.qna section.", e);
    }
  }

  private int getMinAcceptableScoreLevel(XmlConfigSection msQnaSection) {
    try {
      return msQnaSection.getInt("min.acceptable.score.level");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Parameter min.acceptable.score.level is not found in ms.qna section.", e);
    }
  }

  private String getPushUrl(XmlConfigSection botCfg) {
    try {
      return botCfg.getString("push.url");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Parameter push.url is not found in ms.qna.bot section.", e);
    }
  }

  private String getBotAskCommandName(XmlConfigSection botCfg) {
    try {
      return botCfg.getString("bot.ask.command.name");
    }
    catch(ConfigException e) {
      throw new RuntimeException("Parameter bot.ask.command.name is not found in ms.qna.bot section.", e);
    }
  }

  private void initLog4j(File configDir) {
    final File log4jProps = new File(configDir, "log4j.properties");
    System.out.println("Log4j conf file: " + log4jProps.getAbsolutePath() + ", exists: " + log4jProps.exists());
    PropertyConfigurator.configureAndWatch(log4jProps.getAbsolutePath(), TimeUnit.MINUTES.toMillis(1));
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
  }

}