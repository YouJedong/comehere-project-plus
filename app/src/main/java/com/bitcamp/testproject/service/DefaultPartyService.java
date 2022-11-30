package com.bitcamp.testproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.PartyCommentDao;
import com.bitcamp.testproject.dao.PartyDao;
import com.bitcamp.testproject.dao.PartyMemberDao;
import com.bitcamp.testproject.dao.ReviewDao;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Party;
import com.bitcamp.testproject.vo.PartyMember;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired 
  PartyDao partyDao;
  @Autowired 
  PartyMemberDao partyMemberDao;
  @Autowired
  PartyCommentDao partyCommentDao;
  @Autowired
  ReviewDao reviewDao;

  @Transactional
  @Override
  public void add(Party party, PartyMember partyMember, Part file) throws Exception {
    // 1) 모임 등록
    if (partyDao.insert(party) == 0) {
      throw new Exception("모임 등록 실패!");
    }
    // 3) 파티멤버 생성(주최자 등록)
    if (party.getUserNo() == partyMember.getMemberNo()) {
      partyMember.setPartyNo(partyDao.findNowPartyNo());
      if (partyMemberDao.insertUser(partyMember) == 0) {
        throw new Exception("모임 멤버 생성 실패!");
      }
    } else {
      throw new Exception("주최자 설정 실패!");
    }
  }

  @Transactional
  @Override
  public void attend(Party party, PartyMember partyMember) throws Exception {

    System.out.println(party);
    System.out.println(partyMember);

    if (party.getUserNo() == partyMember.getMemberNo()) {
      partyMember.setPartyNo(party.getNo());
      if (partyMemberDao.insertMember(partyMember) == 0) {
        throw new Exception("모임 멤버 생성 실패!");
      }
    } else {
      throw new Exception("참여자 설정 실패!");
    }
  }

  @Override
  public boolean update(Party party) throws Exception {

    if (party.getThumbnail() == null) {
      String originThumb = partyDao.getThumbnailByPartyNo(party.getNo());
      party.setThumbnail(originThumb);
    }

    // 1) 모임 변경
    if (partyDao.update(party) == 0) {
      return false;
    }
    return true;
  }

  @Transactional
  @Override
  public Party get(int no) throws Exception {
    Party party = partyDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
    int userNo = party.getUser().getNo();
    int sportNo = party.getSports().getNo();
    if (reviewDao.findStarByNo(userNo, sportNo) != null) {
      party.setStar(reviewDao.findStarByNo(userNo, sportNo));
    }
    return party;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 1) 첨부파일 삭제
    partyCommentDao.deleteCommentAll(no);

    partyMemberDao.delete(no);

    // 2) 모임 삭제
    return partyDao.delete(no) > 0;
  }

  @Override
  public List<Party> list(Criteria cri) throws Exception {
    List<Party> list = partyDao.findAll(cri);
    List<Party> list2 = new ArrayList<>();
    for (Party party : list) {
      int userNo = party.getUser().getNo();
      int sportNo = party.getSports().getNo();
      party.setStar(reviewDao.findStarByNo(userNo, sportNo));

      list2.add(party);
    }

    return list2;

  }

  @Override
  public int listCount() throws Exception {
    return partyDao.findAllCount();
  }

  @Override
  public int listCount2(
      String gu, 
      String sports, 
      String partyDate, 
      String partyTime,
      String searchText
      ) throws Exception {
    return partyDao.findAllCount2(gu, sports, partyTime, partyDate, searchText
        );
  }

  @Override
  public List<Party> list2(
      String gu, 
      String sports, 
      String partyDate, 
      String partyTime,
      String searchText,
      String listStar,
      String listCreate,
      String listPartyDate,
      Criteria cri) throws Exception {
    int pagesStart = cri.getPagesStart();
    int perPageNum = cri.getPerPageNum();
    List<Party> result = partyDao.findAll2(
        gu, sports, partyTime, partyDate, searchText, listStar, listCreate, listPartyDate, pagesStart, perPageNum);
    List<Party> list2 = new ArrayList<>();
    for (Party party : result) {
      int userNo = party.getUser().getNo();
      int sportNo = party.getSports().getNo();
      party.setStar(reviewDao.findStarByNo(userNo, sportNo));
      list2.add(party);
    }

    return list2;

  }

  @Override
  public int checkOwner(int partyNo) {
    return partyDao.checkOwner(partyNo);
  }


  @Override
  public List<Party> findByMyParty(Map<String, Object> paramMap) throws Exception {
    return partyDao.findByMyParty(paramMap);
  }

  @Override
  public int countMyParty(int memberNo) throws Exception {
    return partyDao.countMyParty(memberNo);
  }

  @Override
  public List<Party> findByJoinParty(Map<String, Object> paramMap) throws Exception {
    return partyDao.findByJoinParty(paramMap);
  }

  @Override
  public int countJoinParty(int memberNo) throws Exception {
    return partyDao.countJoinParty(memberNo);
  }

  @Override
  public List<Party> findByEndParty(Map<String, Object> paramMap) throws Exception {
    List<Party> endPartyListTotal = new ArrayList<>();

    List<Party> endPartyList = partyDao.findByEndParty(paramMap);

    int[] partyNumbers = new int[endPartyList.size()];

    for (int i=0; i<endPartyList.size(); i++) {
      Party endParty = endPartyList.get(i);
      endParty.setReviewAct("작성완료");
      partyNumbers[i] = endParty.getNo();
      endPartyListTotal.add(endParty);
    }

    List<Party> endPartyList2 = partyDao.findByEndParty2(paramMap);

    for (Party endParty : endPartyList2) {

      if (endParty.getReviewNo() == 0) {
        endParty.setReviewAct("작성하기");
        endPartyListTotal.add(endParty);
      }
      else {
        int check = 0;

        for (int k=0; k<partyNumbers.length; k++) {
          int count = 0;

          for (int j=0; j <partyNumbers.length; j++) {
            if (partyNumbers[j] == endParty.getNo()) {
              continue;
            }
            count++;
          }

          if (count == partyNumbers.length) {
            if (check == 0) {
              endParty.setReviewAct("작성하기");
              endPartyListTotal.add(endParty);
              check++;
            }
          }
        }
      }

    }
    return endPartyListTotal;
  }

  @Override
  public int countEndParty(int memberNo) throws Exception {
    return partyDao.countEndParty(memberNo);
  }

  @Override
  public boolean partyEnd(int partyNo) throws Exception {
    if (partyDao.partyEnd(partyNo) == 0) {
      return false;
    }
    return true;
  }

}








