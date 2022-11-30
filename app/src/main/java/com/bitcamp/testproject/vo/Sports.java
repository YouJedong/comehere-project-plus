package com.bitcamp.testproject.vo;

import java.util.List;

public class Sports {

  private int no;
  private String name;
  private List<FavoriteSports> favoritesports;


  @Override
  public String toString() {
    return "Sports [no=" + no + ", name=" + name + ", favoritesports=" + favoritesports + "]";
  }


  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public List<FavoriteSports> getFavoritesports() {
    return favoritesports;
  }


  public void setFavoritesports(List<FavoriteSports> favoritesports) {
    this.favoritesports = favoritesports;
  }


}
