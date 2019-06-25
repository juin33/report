package com.juin.report.service;

import com.juin.report.dao.domain.Flow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author kejun
 * @date 2019/6/24 下午4:06
 */
public interface UploadFileService {
    void importExcel(String filePath) throws ParseException, IOException;

    List<Flow> queryFlow();
}
