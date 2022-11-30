package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.FavoriteRegionDao;
import com.bitcamp.testproject.vo.FavoriteRegion;
import com.bitcamp.testproject.vo.Member;

@Service
public class DefaultFavoriteRegionService implements FavoriteRegionService {

  @Autowired 
  FavoriteRegionDao favoriteRegionDao;

  @Override
  public List<FavoriteRegion> getRegionNos(int memberNo) {
    return favoriteRegionDao.findAllRegionNosByMemberNo(memberNo);
  }

  @Override
  public boolean updateRegionDomain(Member member) {
    return favoriteRegionDao.updateRegion(member);
  }

  @Override
  public boolean updateRegionDomain(int mno, int regionDomain) {
    return favoriteRegionDao.updateRegion(mno, regionDomain);
  }

  @Override
  public boolean deleteFavoriteRegion(int memberNo) {
    return favoriteRegionDao.deleteFavoriteRegion(memberNo);
  }

  @Override
  public void addFavoriteRegion(Member member) {
    // 2) 관심지역 등록
    favoriteRegionDao.insertRegion(member);
  }
}








