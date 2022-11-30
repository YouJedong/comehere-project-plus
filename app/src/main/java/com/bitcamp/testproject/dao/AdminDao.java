package com.bitcamp.testproject.dao;

import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Member;

@Mapper
public interface AdminDao {

  Member findAdmin(Member idPwd);

}
