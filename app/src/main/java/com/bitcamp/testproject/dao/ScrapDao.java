package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Scrap;

@Mapper
public interface ScrapDao {

  int insert(Scrap scrap);

  int delete(Scrap scrap);

  int deleteAll(int no);

  int findScrapsCount(int memberNo);

  List<Scrap> findScrapsByMemberNo(Map<String, Object> paramMap);

}
