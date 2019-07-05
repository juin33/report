package com.juin.report.service.impl;

import com.juin.report.core.DataAuthentication;
import com.juin.report.core.SqlSignature;
import com.juin.report.dao.domain.User;
import com.juin.report.dao.domain.UserCriteria;
import com.juin.report.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public List<Integer> getIds(Integer userId) {
        List<Integer> userIds = new ArrayList<>();
        UserCriteria criteria = new UserCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(criteria);
        for(User user : users){
            userIds.add(user.getUserId());
        }
        return userIds;
    }
}
