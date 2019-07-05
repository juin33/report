package com.juin.report.controller;

import com.juin.report.core.UserContext;
import com.juin.report.core.UserContextHolder;
import com.juin.report.service.impl.AuthServiceImpl;
import com.juin.report.service.impl.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author kejun
 * @date 2019/7/4 下午4:46
 */
@RestController
public class MybatisPluginController {

    @Autowired
    private BusinessServiceImpl businessService;
    @Autowired
    private AuthServiceImpl authService;

    @ModelAttribute
    public void addAttributes(@RequestParam(required = false) Integer userId) {
        if(userId != null) {
            UserContext userContext = new UserContext();
            userContext.setUserId(userId);
            UserContextHolder.userContextThreadLocal.set(userContext);
        }
    }

    @RequestMapping(value = "/query",method = POST)
    public ResponseEntity<Object> query(@RequestParam(name = "userId") Integer userId){
        businessService.search(userId);
        return new ResponseEntity<Object>("success", HttpStatus.OK);
    }

}
