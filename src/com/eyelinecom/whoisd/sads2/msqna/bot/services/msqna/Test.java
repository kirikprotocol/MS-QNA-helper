package com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna;

import com.eyelinecom.whoisd.sads2.common.HttpDataLoader;
import com.eyelinecom.whoisd.sads2.common.Loader;
import com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna.model.Response;

/**
 * author: Denis Enenko
 * date: 14.09.17
 */
public class Test {

  public static void main(String[] args) throws Exception {
    Loader<Loader.Entity> loader = new HttpDataLoader();

    MicrosoftQnaMaker api = new MicrosoftQnaMaker(loader, "TOKEN", "TOKEN");

    Response response = api.query("Как я могу узнать свой тариф?", 2);
    System.out.println(response.getRaw());
  }

}
