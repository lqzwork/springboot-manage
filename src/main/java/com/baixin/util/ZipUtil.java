package com.baixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @desc: 多个文件打包成zip
 * @ClassName: ZipUtil
 * @Author: liqz
 * @Date: 2020-01-09 18:09
 **/
public class ZipUtil {
    /**
     * @desc 多文件打包zip方法
     *
     * @auther: liqz
     * @param: [fileEntrys, zipName]
     * @return: java.io.File
     * @date: 2020-01-09 18:13
     *
     */
    public static File zipFiles(List<Map<String, Object>> fileEntrys, String toPath, String zipName) throws Exception {
        File resultFile = null;
        Properties properties = null;
        if(!fileEntrys.isEmpty() && fileEntrys.size() > 0) {
            
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(toPath + zipName));
            InputStream input = null;
            
            for(Map<String, Object> map : fileEntrys) {
                String fullPath = (String) map.get("fullPahth");
                String originalFileName = (String) map.get("originalFileName");
                input = new FileInputStream(new File(fullPath));
                zipOut.putNextEntry(new ZipEntry(originalFileName));
                int temp = 0;
                while((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
            }
            zipOut.close();
            resultFile = new File(toPath + zipName);
        }
        
        return resultFile;
    }
}
