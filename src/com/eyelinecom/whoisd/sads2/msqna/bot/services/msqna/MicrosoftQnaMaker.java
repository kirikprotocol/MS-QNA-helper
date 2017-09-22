package com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna;


import com.eyelinecom.whoisd.sads2.common.Loader;
import com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna.model.Request;
import com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna.model.Response;
import com.eyelinecom.whoisd.sads2.msqna.bot.utils.MarshalUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: Denis Enenko
 * date: 14.09.17
 */
public class MicrosoftQnaMaker {

  private final static String KNOWLEDGE_BASE_URL = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0/knowledgebases";

  private final String knowledgeBaseID;
  private final String subscriptionKey;
  private final Loader<Loader.Entity> loader;


  public MicrosoftQnaMaker(Loader<Loader.Entity> loader, String knowledgeBaseID, String subscriptionKey) {
    this.knowledgeBaseID = knowledgeBaseID;
    this.subscriptionKey = subscriptionKey;
    this.loader = loader;
  }

  public Response query(String question, int top) throws Exception {
    final String url = KNOWLEDGE_BASE_URL + "/" + knowledgeBaseID + "/generateAnswer";

    final Request request = new Request();
    request.setQuestion(question);
    request.setTop(top);

    String jsonRequest = MarshalUtils.marshal(request);

    Map<String,String> headers = new HashMap<>();
    headers.put("Ocp-Apim-Subscription-Key", subscriptionKey);

    Loader.Entity response = loader.load(url, jsonRequest, "application/json", "utf-8", headers, "post");

    if(response.getBuffer() != null) {
      String data = new String(response.getBuffer(), "utf-8");
      Response res = MarshalUtils.unmarshal(MarshalUtils.parse(data), Response.class);
      res.setRaw(data);
      return res;
    }

    return null;
  }

}
