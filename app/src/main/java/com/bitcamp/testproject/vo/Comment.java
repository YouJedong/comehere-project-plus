package com.bitcamp.testproject.vo;

import java.sql.Date;

public class Comment {

  private int no;
  private Member writer;
  private int objectNo;
  private String content;
  private Date createdDate;
  private boolean act;

  private Board board; // 댓글을 단 게시글의 정보를 가져오기 위해 정의

  public Comment() {}

  public Comment(String content, int objectNo, int memberNo) {
    this.content = content;
    this.objectNo = objectNo;
    this.writer = new Member(memberNo);
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  @Override
  public String toString() {
    return "Comment [no=" + no + ", writer=" + writer + ", objectNo=" + objectNo + ", content="
        + content + ", createdDate=" + createdDate + ", act=" + act + ", board=" + board + "]";
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
  public int getObjectNo() {
    return objectNo;
  }
  public void setObjectNo(int objectNo) {
    this.objectNo = objectNo;
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

  public boolean isAct() {
    return act;
  }

  public void setAct(boolean act) {
    this.act = act;
  }




}
