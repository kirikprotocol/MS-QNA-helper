package com.eyelinecom.whoisd.sads2.msqna.bot;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CountDownLatch;

/**
 * author: Denis Enenko
 * date: 11.09.17
 */
@ApplicationScoped
public class WebContext {

  private final static CountDownLatch initLatch = new CountDownLatch(1);

  private static String msQnaKnowledgeBaseID;
  private static String msQnaSubscriptionKey;
  private static Integer minAcceptableScoreLevel;
  private static String botAskCommandName;
  private static String pushUrl;


  static synchronized void init(String msQnaKnowledgeBaseID,
                                String msQnaSubscriptionKey,
                                int minAcceptableScoreLevel,
                                String botAskCommandName,
                                String pushUrl) {

    if(WebContext.msQnaKnowledgeBaseID == null)
      WebContext.msQnaKnowledgeBaseID = msQnaKnowledgeBaseID;

    if(WebContext.msQnaSubscriptionKey == null)
      WebContext.msQnaSubscriptionKey = msQnaSubscriptionKey;

    if(WebContext.minAcceptableScoreLevel == null)
      WebContext.minAcceptableScoreLevel = minAcceptableScoreLevel;

    if(WebContext.botAskCommandName == null)
      WebContext.botAskCommandName = botAskCommandName;

    if(WebContext.pushUrl == null)
      WebContext.pushUrl = pushUrl;

    initLatch.countDown();
  }

  public static String getMsQnaKnowledgeBaseID() {
    try {
      initLatch.await();
      return msQnaKnowledgeBaseID;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static String getMsQnaSubscriptionKey() {
    try {
      initLatch.await();
      return msQnaSubscriptionKey;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static int getMinAcceptableScoreLevel() {
    try {
      initLatch.await();
      return minAcceptableScoreLevel;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static String getBotAskCommandName() {
    try {
      initLatch.await();
      return botAskCommandName;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static String getPushUrl() {
    try {
      initLatch.await();
      return pushUrl;
    }
    catch(InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}