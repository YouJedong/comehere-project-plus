package com.bitcamp.testproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.testproject.vo.Review;

@Mapper
public interface ReviewDao {

  List<Review> findAll(
      @Param("userNo")int userNo, 
      @Param("sportNo")int sportNo);

  Review findByNo(int reviewNo);

  int insert(Review review);

  int insertFiles(Review review);

  Double findStarByNo(
      @Param("userNo")int userNo, 
      @Param("sportNo")int sportNo);

}














