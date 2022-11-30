package com.bitcamp.testproject.vo;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class Board {

  private int no;
  private int cateno;
  private Member writer;
  private String title;
  private boolean active;
  private String content;
  private Date createdDate;
  private int viewCount;
  private List<String> report;
  private String thumbnail;
  private boolean act;

  private int commentCount; // 게시글에 달린 댓글 개수

  @Override
  public String toString() {
    return "Board [no=" + no + ", cateno=" + cateno + ", writer=" + writer + ", title=" + title
        + ", active=" + active + ", content=" + content + ", createdDate=" + createdDate
        + ", viewCount=" + viewCount + ", report=" + report + ", thumbnail=" + thumbnail + ", act="
        + act + ", commentCount=" + commentCount + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public List<String> getReport() {
    return report;
  }

  public void setReport(List<String> report) {
    this.report = report;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public boolean isAct() {
    return act;
  }

  public void setAct(boolean act) {
    this.act = act;
  } 




}






