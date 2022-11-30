package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.PartyDao;
import com.bitcamp.testproject.dao.PartyMemberDao;
import com.bitcamp.testproject.vo.PartyMember;

@Service
public class DefaultPartyMemberService implements PartyMemberService {

  @Autowired 
  PartyDao partyDao;
  @Autowired 
  PartyMemberDao partyMemberDao;

  @Override
  public int countMyPartyMember(int partyNo) throws Exception {
    return partyMemberDao.countMyPartyMember(partyNo);
  }

  @Override
  public List<PartyMember> findByMyPartyList(Map<String, Object> paramMap) throws Exception {
    return partyMemberDao.findMyPartyMemberAll(paramMap);
  }

  @Override
  public int partyMemberCheck(int memberNo, int partyNo) throws Exception {
    return partyMemberDao.partyMemberCheck(memberNo, partyNo);
  }

  @Override
  public boolean partyMemberOk(int status, int memberNo, int partyNo) throws Exception {
    if (partyMemberDao.updateOk(status, memberNo, partyNo) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean partyMemberNo(int status, int memberNo, int partyNo) throws Exception {
    if (partyMemberDao.updateNo(status, memberNo, partyNo) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public boolean partyMemberSecession(int status, int memberNo, int partyNo) throws Exception {
    if (partyMemberDao.updateSecession(status, memberNo, partyNo) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public int countPartyMember(int partyNo) throws Exception {
    return partyMemberDao.countPartyMember(partyNo);
  }

}








