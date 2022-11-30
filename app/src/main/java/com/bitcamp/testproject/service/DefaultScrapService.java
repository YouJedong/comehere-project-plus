package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.dao.ScrapDao;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Scrap;

@Service
public class DefaultScrapService implements ScrapService {

  @Autowired 
  ScrapDao scrapDao;
  @Autowired 
  BoardDao boardDao;

  @Override
  public int insert(Scrap scrap) {
    return scrapDao.insert(scrap);
  }

  @Override
  public int delete(Scrap scrap) {
    return scrapDao.delete(scrap);
  }

  @Override
  public int countTotalScrapOfMember(int memberNo) {
    return scrapDao.findScrapsCount(memberNo);
  }

  @Override
  public List<Board> getScrapsOfMember(Map<String, Object> paramMap) {
    return boardDao.findBoardsOfScrap(paramMap);
  }
}








