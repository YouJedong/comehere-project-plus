package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.RegionDao;
import com.bitcamp.testproject.vo.Region;

@Service
public class DefaultRegionService implements RegionService {

  @Autowired 
  RegionDao regionDao;

  @Override
  public List<Region> list() {
    return regionDao.findAll();
  }
}








