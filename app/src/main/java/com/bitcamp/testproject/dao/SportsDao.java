package com.bitcamp.testproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Sports;

@Mapper
public interface SportsDao {

  List<Sports> findAll();


}














