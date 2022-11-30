package com.bitcamp.testproject.vo;

public class BoardCategory {

  private int cateno;
  private String name;

  @Override
  public String toString() {
    return "BoardCategory [cateno=" + cateno + ", category=" + name + "]";
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}

