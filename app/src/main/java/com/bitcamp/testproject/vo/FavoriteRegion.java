package com.bitcamp.testproject.vo;

public class FavoriteRegion {

  private int rno;
  private int mno;
  private Region region;

  public FavoriteRegion(int rno) {
    this.rno = rno;
  }

  @Override
  public String toString() {
    return "FavoriteRegion [rno=" + rno + ", mno=" + mno + ", region=" + region + "]";
  }

  public int getRno() {
    return rno;
  }

  public void setRno(int rno) {
    this.rno = rno;
  }

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }


}

