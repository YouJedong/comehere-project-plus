package com.bitcamp.testproject.dao;

public class MailDao {
  private String address;
  private String title;
  private String content;

  @Override
  public String toString() {
    return "MailDao [address=" + address + ", title=" + title + ", content=" + content + "]";
  }

  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

}