package com.bitcamp.testproject.vo;

import java.util.List;

public class PartyMember {

  private int memberNo;
  private int partyNo;
  private int statusNo;
  private boolean auth;
  private Party party;

  private Member member;

  private List<Member> members;

  @Override
  public String toString() {
    return "PartyMember [memberNo=" + memberNo + ", partyNo=" + partyNo + ", statusNo=" + statusNo
        + ", auth=" + auth + ", party=" + party + ", member=" + member + ", members=" + members
        + "]";
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public int getPartyNo() {
    return partyNo;
  }

  public void setPartyNo(int partyNo) {
    this.partyNo = partyNo;
  }

  public int getStatusNo() {
    return statusNo;
  }

  public void setStatusNo(int statusNo) {
    this.statusNo = statusNo;
  }

  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }

  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

}
