package com.bitcamp.testproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Region;

@Mapper
public interface RegionDao {

  List<Region> findAll();

}














