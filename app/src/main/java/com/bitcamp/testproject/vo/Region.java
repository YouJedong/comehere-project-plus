package com.bitcamp.testproject.vo;

import java.util.List;

public class Region {

  private int no;
  private String sido;
  private String sigu;
  private List<FavoriteRegion> favoriteRegion;


  @Override
  public String toString() {
    return "Region [no=" + no + ", sido=" + sido + ", sigu=" + sigu + ", favoriteRegion="
        + favoriteRegion + "]";
  }


  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getSido() {
    return sido;
  }


  public void setSido(String sido) {
    this.sido = sido;
  }


  public String getSigu() {
    return sigu;
  }


  public void setSigu(String sigu) {
    this.sigu = sigu;
  }


  public List<FavoriteRegion> getFavoriteRegion() {
    return favoriteRegion;
  }


  public void setFavoriteRegion(List<FavoriteRegion> favoriteRegion) {
    this.favoriteRegion = favoriteRegion;
  }



}
