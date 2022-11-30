package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Party;

@Mapper
public interface PartyDao {

  // 모임 전체 조회 
  // - 운동 종류, 지역 분류, 모임일자(기간), 모임시간(기간)
  // - 검색어 기준 
  // - 한 페이지 만 프론트, 페이지 이동 시 
  List<Party> findAll(Criteria cri);

  int findAllCount();

  int findAllCount2(
      @Param("gu") String gu, 
      @Param("sports") String sports,
      @Param("partyTime") String partyTime,
      @Param("partyDate") String partyDate,
      @Param("searchText") String searchText
      );

  List<Party> findAll2(
      @Param("gu") String gu, 
      @Param("sports") String sports,
      @Param("partyTime") String partyTime,
      @Param("partyDate") String partyDate,
      @Param("searchText") String searchText,
      @Param("listStar")String listStar,
      @Param("listCreate")String listCreate,
      @Param("listPartyDate")String listPartyDate,
      @Param("pagesStart")int pagesStart,
      @Param("perPageNum")int perPageNum
      );

  Party findByNo(int no);

  int insert(Party party);

  int update(Party party);

  int delete(int no);

  int checkOwner(int partyNo);

  // 모임 주최자 추가를 위한 현재 생성 모임 pno 구하기
  int findNowPartyNo();

  String getThumbnailByPartyNo(int no);


  List<Party> findByMyParty(Map<String, Object> paramMap);

  int countMyParty(int memberNo);

  List<Party> findByJoinParty(Map<String, Object> paramMap);

  int countJoinParty(int memberNo);

  List<Party> findByEndParty(Map<String, Object> paramMap);

  List<Party> findByEndParty2(Map<String, Object> paramMap);

  int countEndParty(int memberNo);

  int partyEnd(int partyNo);
}














