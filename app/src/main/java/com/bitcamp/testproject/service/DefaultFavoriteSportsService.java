package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.FavoriteSportsDao;
import com.bitcamp.testproject.vo.FavoriteSports;
import com.bitcamp.testproject.vo.Member;

@Service
public class DefaultFavoriteSportsService implements FavoriteSportsService {

  @Autowired 
  FavoriteSportsDao favoriteSportsDao;

  @Override
  public List<FavoriteSports> getSportsNos(int memberNo) {
    return favoriteSportsDao.findAllSportsNosByMemberNo(memberNo);
  }

  @Override
  public boolean updateSportsDomain(Member member) {
    return favoriteSportsDao.updateSports(member);
  }

  @Override
  public boolean updateSportsDomain(int mno, int sportsDomain) {
    return favoriteSportsDao.updateSports(mno, sportsDomain);
  }

  @Override
  public boolean deleteFavoriteSports(int memberNo) {
    return favoriteSportsDao.deleteFavoriteSports(memberNo);
  }

  @Override
  public void addFavoriteSports(Member member) {
    //    3) 관심운동 등록
    favoriteSportsDao.insertSports(member);
  }
}








