package com.bitcamp.testproject.vo;

import java.sql.Date;
import java.util.List;

public class Review {

  private int no;
  private Member writer;
  private int partyNo;
  private String title;
  private String content;
  private double star;
  private Date createdDate;

  private List<AttachedFile> attachedFiles;

  @Override
  public String toString() {
    return "Review [no=" + no + ", writer=" + writer + ", partyNo=" + partyNo + ", title=" + title
        + ", content=" + content + ", star=" + star + ", createdDate=" + createdDate
        + ", attachedFiles=" + attachedFiles + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public int getPartyNo() {
    return partyNo;
  }

  public void setPartyNo(int partyNo) {
    this.partyNo = partyNo;
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

  public double getStar() {
    return star;
  }

  public void setStar(double star) {
    this.star = star;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }


}
