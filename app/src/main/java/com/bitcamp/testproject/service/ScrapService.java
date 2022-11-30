package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Scrap;

public interface ScrapService {

  int insert(Scrap scrap);

  int delete(Scrap scrap);

  int countTotalScrapOfMember(int no);

  List<Board> getScrapsOfMember(Map<String, Object> paramMap);


}
