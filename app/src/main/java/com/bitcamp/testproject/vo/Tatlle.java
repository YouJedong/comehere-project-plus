package com.bitcamp.testproject.vo;

import java.sql.Date;

public class Tatlle {

  private int no;
  private Member writer;
  private int decbno;
  private int id;//trno 신고사유 번호
  private Date tdt;
  private String cont;
  private Date comdt;


  @Override
  public String toString() {
    return "Tatlle [no=" + no + ", writer=" + writer + ", decbno=" + decbno + ", id=" + id
        + ", tdt=" + tdt + ", cont=" + cont + ", comdt=" + comdt + "]";
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
  public int getDecbno() {
    return decbno;
  }
  public void setDecbno(int decbno) {
    this.decbno = decbno;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Date getTdt() {
    return tdt;
  }
  public void setTdt(Date tdt) {
    this.tdt = tdt;
  }
  public String getCont() {
    return cont;
  }
  public void setCont(String cont) {
    this.cont = cont;
  }
  public Date getComdt() {
    return comdt;
  }
  public void setComdt(Date comdt) {
    this.comdt = comdt;
  }


}
