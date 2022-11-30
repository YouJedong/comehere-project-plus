package com.bitcamp.testproject.vo;

import lombok.Data;

@Data
public class Scrap {

  private int boardNo;
  private int memberNo;

  @Override
  public String toString() {
    return "Scrap [boardNo=" + boardNo + ", memberNo=" + memberNo + "]";
  }

  public int getBoardNo() {
    return boardNo;
  }
  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }
  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }


}






