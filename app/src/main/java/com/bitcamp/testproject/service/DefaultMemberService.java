package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.dao.FavoriteRegionDao;
import com.bitcamp.testproject.dao.FavoriteSportsDao;
import com.bitcamp.testproject.dao.MemberDao;
import com.bitcamp.testproject.vo.Member;

@Service 
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Autowired
  BoardDao boardDao;

  @Autowired
  FavoriteRegionDao favoriteRegionDao;

  @Autowired
  FavoriteSportsDao favoriteSprotsDao;

  @Transactional
  @Override
  public void add(Member member) throws Exception {
    // 1) 회원등록
    memberDao.insert(member);

    // 2) 관심지역 등록
    favoriteRegionDao.insertRegion(member);

    //  3) 관심운동 등록
    favoriteSprotsDao.insertSports(member);
  }

  @Transactional
  @Override
  public boolean update(Member member) throws Exception {

    if (member.getFilepath() == null) {
      String originFile = memberDao.getFileByMemberNo(member.getNo());
      member.setFilepath(originFile);
    }
    return memberDao.update(member) > 0;
  }

  @Override
  public boolean updatePW(String password, String email, String id) throws Exception {
    if (memberDao.updatePW(password, email, id) == 0) {
      return false;
    }
    return true;
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByIdPassword(id, password);
  }


  @Override
  public Member getByPassword(String id, String email, String SecCode) throws Exception {
    return memberDao.findByPassword(id, email, SecCode);
  }

  @Override
  public Member getId(String name, String email) throws Exception {
    return memberDao.findById(name, email);
  }


  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    //    boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
    //    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
    return memberDao.delete(no) > 0; // 회원 삭제
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public int idCheck(String id) throws Exception {
    return memberDao.idCheck(id);
  }

  @Override
  public int verificationPw(String password, int no) throws Exception {
    return memberDao.verificationPw(password, no);
  }

  @Override
  public int nickCheck(String nickname) throws Exception {
    return memberDao.nickCheck(nickname);
  }

  @Override
  public int emailCheck(String email) throws Exception {
    return memberDao.emailCheck(email);
  }

  @Override
  public Member idEmailCheck(String id, String email) throws Exception {
    return memberDao.idEmailCheck(id, email);
  }

  @Override
  public Member idPasswordCheck(String id, String password) throws Exception {
    return memberDao.idPasswordCheck(id, password);
  }

  @Override
  public Member findIdCheck(String name, String email) throws Exception {
    return memberDao.findIdCheck(name, email);
  }
}








