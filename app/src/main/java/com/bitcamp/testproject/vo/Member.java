package com.bitcamp.testproject.vo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class Member {

  private int no;
  private String name;
  private String id;
  private String email;
  private String nickname;
  private String password;
  private String tel;
  private Date birthday;
  private boolean sex;
  private String filepath;
  private Date createdDate;
  private int active;
  private boolean black;
  private List<FavoriteRegion> favoriteRegion;
  private List<FavoriteSports> favoriteSports;
  private int[] regionDomain;
  private int[] sportsDomain;

  // 모임참여 명단에서 주최자 여부
  private boolean auth;

  // 모임 참여 명단에서 나이대
  private String ageGroup;

  public Member() {}

  public Member(int no) {
    this.no = no;
  }


  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", id=" + id + ", email=" + email + ", nickname="
        + nickname + ", password=" + password + ", tel=" + tel + ", birthday=" + birthday + ", sex="
        + sex + ", filepath=" + filepath + ", createdDate=" + createdDate + ", active=" + active
        + ", black=" + black + ", favoriteRegion=" + favoriteRegion + ", favoriteSports="
        + favoriteSports + ", regionDomain=" + Arrays.toString(regionDomain) + ", sportsDomain="
        + Arrays.toString(sportsDomain) + ", auth=" + auth + ", ageGroup=" + ageGroup + "]";
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public boolean getSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public boolean getBlack() {
    return black;
  }

  public void setBlack(boolean black) {
    this.black = black;
  }

  public List<FavoriteRegion> getFavoriteRegion() {
    return favoriteRegion;
  }

  public void setFavoriteRegion(List<FavoriteRegion> favoriteRegion) {
    this.favoriteRegion = favoriteRegion;
  }

  public List<FavoriteSports> getFavoriteSports() {
    return favoriteSports;
  }

  public void setFavoriteSports(List<FavoriteSports> favoriteSports) {
    this.favoriteSports = favoriteSports;
  }

  public int[] getRegionDomain() {
    return regionDomain;
  }

  public void setRegionDomain(int[] regionDomain) {
    this.regionDomain = regionDomain;
  }

  public int[] getSportsDomain() {
    return sportsDomain;
  }

  public void setSportsDomain(int[] sportsDomain) {
    this.sportsDomain = sportsDomain;
  }

  public boolean getAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }

  public String getAgeGroup() {
    return ageGroup;
  }

  public void setAgeGroup(String ageGroup) {
    this.ageGroup = ageGroup;
  }


}
