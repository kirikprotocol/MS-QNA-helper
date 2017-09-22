package com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna.model;

/**
 * author: Denis Enenko
 * date: 14.09.17
 */
public class Request {

  private String question;
  private Integer top;


  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
  }

}
