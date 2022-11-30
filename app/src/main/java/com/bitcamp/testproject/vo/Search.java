package com.bitcamp.testproject.vo;

import java.util.Arrays;

public class Search {

  private String keyword;
  private String noticeKeyword = "";

  private String type = "T";
  private String[] typeArr = {"T"};

  private String typeName = "내용";

  @Override
  public String toString() {
    return "Search [keyword=" + keyword + ", noticeKeyword=" + noticeKeyword + ", type=" + type
        + ", typeArr=" + Arrays.toString(typeArr) + ", typeName=" + typeName + "]";
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getNoticeKeyword() {
    return noticeKeyword;
  }

  public void setNoticeKeyword(String noticeKeyword) {
    this.noticeKeyword = noticeKeyword;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
    this.typeArr = type.split("");
    switch (type) {
      case "T":   this.typeName = "제목"; break;
      case "C":   this.typeName = "내용"; break;
      case "W":   this.typeName = "작성자"; break;
      case "TC":  this.typeName = "제목 + 내용"; break;
      case "TW":  this.typeName = "제목 + 작성자"; break;
      case "TCW": this.typeName = "제목 + 내용 + 작성자";
    }
  }

  public String[] getTypeArr() {
    return typeArr;
  }

  public void setTypeArr(String[] typeArr) {
    this.typeArr = typeArr;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }



}
