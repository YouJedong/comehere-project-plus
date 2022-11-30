package com.bitcamp.testproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.AdminDao;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.vo.Member;

@Service 
public class DefaultAdminService implements AdminService {

  @Autowired
  AdminDao adminDao;

  @Autowired
  BoardDao boardDao;

  @Override
  public Member getAdmin(Member idPwd) {
    return adminDao.findAdmin(idPwd);
  }
  //
  //  @Transactional
  //  @Override
  //  public void add(Member member) throws Exception {
  //    memberDao.insert(member);
  //    memberDao.insertRegion(member);
  //    memberDao.insertSports(member);
  //  }
  //
  //  @Override
  //  public boolean update(Member member) throws Exception {
  //    return memberDao.update(member) > 0;
  //  }
  //
  //  @Override
  //  public boolean updatePW(String password, String email, String id) throws Exception {
  //    if (memberDao.updatePW(password, email, id) == 0) {
  //      return false;
  //    }
  //    return true;
  //  }
  //  @Override
  //  public Member get(int no) throws Exception {
  //    return memberDao.findByNo(no);
  //  }
  //
  //  @Override
  //  public Member get(String id, String password) throws Exception {
  //    return memberDao.findByIdPassword(id, password);
  //  }
  //
  //
  //  @Override
  //  public Member getByPassword(String id, String email, String SecCode) throws Exception {
  //    return memberDao.findByPassword(id, email, SecCode);
  //  }
  //
  //  @Override
  //  public Member getId(String name, String email) throws Exception {
  //    return memberDao.findById(name, email);
  //  }
  //
  //
  //  @Transactional
  //  @Override
  //  public boolean delete(int no) throws Exception {
  //    //    boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
  //    //    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
  //    return memberDao.delete(no) > 0; // 회원 삭제
  //  }
  //
  //  @Override
  //  public List<Member> list() throws Exception {
  //    return memberDao.findAll();
  //  }
  //
  //
  //  //  @Override
  //  //  public String checkId(Member member) throws Exception {
  //  //    List<Member> memberList = memberDao.findAll();
  //  //    for(Member eachMember : memberList) {
  //  //      if(eachMember.getId().equals(member.getId())) return "fail";
  //  //    }
  //  //    return "succ";
  //  //  }
  //

}








