package com.juin.report.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author kejun
 * @date 2019/6/24 下午4:06
 */
public interface UploadFileService {
    void importExcel(String filePath) throws ParseException, IOException;
}
