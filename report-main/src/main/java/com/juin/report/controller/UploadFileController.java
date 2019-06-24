package com.juin.report.controller;

import com.juin.report.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    private final static Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/import/{filePath}", method = RequestMethod.GET)
    public ResponseEntity<Object> importExcel(@PathVariable("filePath") String filePath) throws Exception {
        logger.info("filePath:{}",filePath);
        uploadFileService.importExcel(filePath);
        return new ResponseEntity<Object>("success",HttpStatus.OK);
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ResponseEntity<Object> hello(){
        return new ResponseEntity<Object>("hello", HttpStatus.OK);
    }
}
