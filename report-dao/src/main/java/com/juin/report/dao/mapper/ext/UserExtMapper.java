package com.juin.report.dao.mapper.ext;

import com.juin.report.dao.domain.User;
import com.juin.report.dao.domain.UserCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserExtMapper {
    List<User> query(Integer userId);
}