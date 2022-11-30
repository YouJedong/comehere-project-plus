package com.bitcamp.testproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.PartyDao;
import com.bitcamp.testproject.dao.ReviewDao;
import com.bitcamp.testproject.vo.Review;

@Service
public class DefaultReviewService implements ReviewService {

  @Autowired 
  ReviewDao reviewDao;
  @Autowired 
  PartyDao partyDao;

  @Override
  public List<Review> list(int userNo, int sportNo) throws Exception {
    System.out.println(reviewDao.findAll(userNo, sportNo));
    return reviewDao.findAll(userNo, sportNo);
  }

  @Override
  public Review get(int reviewNo) throws Exception {
    return reviewDao.findByNo(reviewNo);
  }

  @Transactional
  @Override
  public void add(Review review) throws Exception {
    // 1) 게시글 등록
    if (reviewDao.insert(review) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    // 2) 첨부파일 등록
    if (review.getAttachedFiles().size() > 0) {
      reviewDao.insertFiles(review);
    }
  }

}








