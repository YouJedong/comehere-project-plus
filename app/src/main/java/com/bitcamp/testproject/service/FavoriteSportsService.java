package com.bitcamp.testproject.service;

import java.util.List;
import com.bitcamp.testproject.vo.FavoriteSports;
import com.bitcamp.testproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface FavoriteSportsService {

  List<FavoriteSports> getSportsNos(int memberNo);

  boolean updateSportsDomain(Member member);

  boolean updateSportsDomain(int mno, int sportsDomain);

  boolean deleteFavoriteSports(int memeberNo);

  void addFavoriteSports(Member member);

}








