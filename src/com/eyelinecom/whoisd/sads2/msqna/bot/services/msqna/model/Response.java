package com.eyelinecom.whoisd.sads2.msqna.bot.services.msqna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * author: Denis Enenko
 * date: 14.09.17
 */
public class Response {

  @JsonIgnore
  private String raw;
  private Error error;
  private List<Answer> answers;


  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public String getRaw() {
    return raw;
  }

  public void setRaw(String raw) {
    this.raw = raw;
  }

  @Override
  public String toString() {
    return "Response{" +
        "error=" + error +
        ", answers=" + answers +
        '}';
  }

}
