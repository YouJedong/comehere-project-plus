package com.bitcamp.testproject.vo;

import java.sql.Date;

public class Notice {

  private int no;
  private String title;
  private String content;
  private Date createdDate;
  private int viewCount;
  private String thumbnail;

  @Override
  public String toString() {
    return "Notice [no=" + no + ", title=" + title + ", content=" + content + ", createdDate="
        + createdDate + ", viewCount=" + viewCount + ", thumbnail=" + thumbnail + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }


}
