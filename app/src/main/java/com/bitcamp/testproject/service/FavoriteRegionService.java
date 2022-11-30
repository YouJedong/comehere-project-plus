package com.bitcamp.testproject.service;

import java.util.List;
import com.bitcamp.testproject.vo.FavoriteRegion;
import com.bitcamp.testproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface FavoriteRegionService {

  List<FavoriteRegion> getRegionNos(int memberNo);

  boolean updateRegionDomain(Member member);

  boolean updateRegionDomain(int mno, int regionDomain);

  boolean deleteFavoriteRegion(int memeberNo);

  void addFavoriteRegion(Member member);
}
