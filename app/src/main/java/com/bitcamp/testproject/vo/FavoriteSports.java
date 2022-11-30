package com.bitcamp.testproject.vo;

public class FavoriteSports {

  private int sno;
  private int mno;
  private Sports sports;

  public FavoriteSports(int sno) {
    this.sno = sno;
  }

  @Override
  public String toString() {
    return "FavoriteSports [sno=" + sno + ", mno=" + mno + ", sports=" + sports + "]";
  }

  public int getSno() {
    return sno;
  }

  public void setSno(int sno) {
    this.sno = sno;
  }

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
  }

  public Sports getSports() {
    return sports;
  }

  public void setSports(Sports sports) {
    this.sports = sports;
  }


}
