package com.juin.report.controller;

import com.alibaba.fastjson.JSON;
import com.juin.report.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    private final static Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<Object> importExcel(@RequestParam(value = "filePath") String filePath) throws Exception {
        logger.info("filePath:{}",filePath);
        uploadFileService.importExcel(filePath);
        return new ResponseEntity<Object>("success",HttpStatus.OK);
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ResponseEntity<Object> hello(){
        return new ResponseEntity<Object>("hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/query",method= {RequestMethod.GET,RequestMethod.POST})
    @CrossOrigin
    public ResponseEntity<Object> query(@RequestParam("order") String order,
                                        @RequestParam("offset") String offset,
                                        @RequestParam("limit") String limit){
        logger.info("order:{},offset:{},limit:{}",order,offset,limit);
        return new ResponseEntity<Object>(JSON.toJSONString(uploadFileService.queryFlow()),HttpStatus.OK);
    }
}
