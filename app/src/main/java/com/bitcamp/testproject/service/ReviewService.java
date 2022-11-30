package com.bitcamp.testproject.service;

import java.util.List;
import com.bitcamp.testproject.vo.Review;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface ReviewService {

  List<Review> list(int userNo, int sportNo) throws Exception ;

  Review get(int reviewNo) throws Exception ;

  void add(Review review) throws Exception ;

}








