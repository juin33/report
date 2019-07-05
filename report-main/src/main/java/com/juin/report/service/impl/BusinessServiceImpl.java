package com.juin.report.service.impl;

import com.juin.report.core.DataAuthentication;
import com.juin.report.core.SqlSignature;
import com.juin.report.dao.domain.User;
import com.juin.report.dao.domain.UserCriteria;
import com.juin.report.dao.mapper.UserMapper;
import com.juin.report.dao.mapper.ext.UserExtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExtMapper userExtMapper;

    @DataAuthentication(@SqlSignature(field = "userId", tableName = "t_user"))
    public void search(Integer userId) {
        List<Integer> userIds = new ArrayList<>();
        List<User> users = userExtMapper.query(userId);
        for(User user : users){
            userIds.add(user.getUserId());
        }
        logger.info("userIds:{}",userIds);
    }

}
