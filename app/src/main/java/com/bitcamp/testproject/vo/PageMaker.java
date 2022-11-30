package com.bitcamp.testproject.vo;

// https://to-dy.tistory.com/90
public class PageMaker {

  private Criteria cri;
  private int totalCount;
  private int startPage;
  private int endPage;
  private boolean prev;
  private boolean next;
  private int displayPageNum = 3;



  @Override
  public String toString() {
    return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage
        + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum="
        + displayPageNum + "]";
  }
  public Criteria getCri() {
    return cri;
  }
  public void setCri(Criteria cri) {
    this.cri = cri;
  }
  public int getTotalCount() {
    return totalCount;
  }
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    calcData(); // 총 게시글 수를 셋팅할 때 페이징 관련 버튼 계산을 한다.
  }

  private void calcData() {

    // 끝 페이지 번호 구하기 (ex. 현재 페이지 번호가 1,2,3,4,5 중에 하나라면 끝페이지는 5)
    endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

    // 시작 페이지의 번호 (ex. 현재 페이지 번호가 1,2,3,4,5 중에 하나라면 시작 페이지는 5)
    startPage = (endPage - displayPageNum) + 1;
    if(startPage <= 0) startPage = 1; // 모든 게시글을 출력했을 때 페이지 개수가 5보다 작다면 시작을 1로 만든다.

    // 한 페이지에 게시글을 20개 이상 출력할 시 끝 페이지 번호 예외 처리 
    int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
    if (endPage > tempEndPage) {
      endPage = tempEndPage;
    }

    // 이전 버튼 생성 여부
    prev = startPage == 1 ? false : true;

    // 다음 버튼 생성 여부
    next = endPage * cri.getPerPageNum() < totalCount ? true : false;

  }

  public int getStartPage() {
    return startPage;
  }
  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }
  public int getEndPage() {
    return endPage;
  }
  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }
  public boolean isPrev() {
    return prev;
  }
  public void setPrev(boolean prev) {
    this.prev = prev;
  }
  public boolean isNext() {
    return next;
  }
  public void setNext(boolean next) {
    this.next = next;
  }
  public int getDisplayPageNum() {
    return displayPageNum;
  }
  public void setDisplayPageNum(int displayPageNum) {
    this.displayPageNum = displayPageNum;
  }

}
