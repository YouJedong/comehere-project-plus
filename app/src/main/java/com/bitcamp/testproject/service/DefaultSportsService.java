package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.SportsDao;
import com.bitcamp.testproject.vo.Sports;

@Service
public class DefaultSportsService implements SportsService {

  @Autowired 
  SportsDao sportsDao;

  @Override
  public List<Sports> list() {
    return sportsDao.findAll();
  }


}








