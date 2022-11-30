package com.bitcamp.testproject.vo;

public class AttachedFile {
  private int no;
  private String filepath;
  private int objectNo;

  public AttachedFile() {}

  public AttachedFile(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public String toString() {
    return "AttachedFile [no=" + no + ", filepath=" + filepath + ", objectNo=" + objectNo + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public int getObjectNo() {
    return objectNo;
  }

  public void setObjectNo(int objectNo) {
    this.objectNo = objectNo;
  }

}
