package com.baixin.service;

import com.baixin.common.PageInfo;
import com.baixin.mapper.CheckResultMapper;
import com.baixin.model.CheckResult;
import com.baixin.properties.ConfigProperties;
import com.baixin.util.WordUtil;
import com.baixin.util.ZipUtil;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc: 检验报告service
 * @ClassName: CheckResultService
 * @Author: liqz
 * @Date: 2020-01-06 16:16
 **/
@Slf4j
@Service
public class CheckResultService {
    
    @Autowired
    private CheckResultMapper checkResultMapper;
    
    @Autowired
    private ConfigProperties configProperties;
    
    public CheckResult findById(CheckResult checkResult) {
        return checkResultMapper.findById(checkResult);
    }
    
    /**
     * @desc 记录检验报告相关信息
     * @auther: liqz
     * @param: []
     * @return: void
     * @date: 2020-01-06 16:34
     */
    @Transactional(readOnly = false)
    public void saveCheckResult(CheckResult checkResult) {
        if(checkResult.getId() != 0) {
            checkResultMapper.update(checkResult);
        } else {
            checkResultMapper.insert(checkResult);
        }
    }
    
    /**
     * @desc 检验报告信息分页列表
     * @auther: liqz
     * @param: [item]
     * @return: java.util.List<com.baixin.model.OperateLog>
     * @date: 2020-01-06 21:30
     */
    public PageInfo listPage(Map<String, Object> paramMap) {
        PageInfo listPage = new PageInfo();
        List<CheckResult> checkResultList = checkResultMapper.listPage(paramMap);
        if(null != checkResultList && !checkResultList.isEmpty()) {
            int listPageCount = checkResultMapper.listPageCount(paramMap);
            listPage.setContentList2(checkResultList);
            listPage.setPageNum(Integer.parseInt(paramMap.get("pageNum").toString()));
            listPage.setTotalSize(listPageCount);
        }
        return listPage;
    }
    
    public int checkResultDelete(CheckResult checkResult) {
        return checkResultMapper.deleteById(checkResult);
    }
    
    /**
     * @desc 替换word中指定文本
     * @auther: liqz
     * @param: [checkResultId, files]
     * @return: void
     * @date: 2020-01-08 14:40
     */
    public File detailCheckReports(int checkResultId, MultipartFile[] files) {
        File zipFiles = null;
        if(0 != checkResultId && null != files && files.length > 0) {
            CheckResult checkResult = checkResultMapper.findById(new CheckResult(checkResultId));
            File direc = new File(configProperties.getToPath());
            if(!direc.exists()) {
                direc.mkdirs();
            }
            try {
                File resultFile = null;
                List<Map<String, Object>> fileEntrys = new ArrayList<>();
                Map<String, Object> map = null;
                for(MultipartFile file : files) {
                    map = new HashMap<>();
                    
                    InputStream inputStream = file.getInputStream();
                    //替换文本并返回最新文档
                    Document document = WordUtil.process(inputStream, checkResult);
                    resultFile =
                            new File(configProperties.getToPath() + checkResult.getPatientName() + "_" + file.getOriginalFilename());
                    resultFile.deleteOnExit();//先删除存在的文件
                    //保存文档
                    document.saveToFile(resultFile.getAbsolutePath(), FileFormat.Docx_2010);
                    map.put("fullPahth", resultFile.getAbsolutePath());
                    map.put("originalFileName", resultFile.getName());
                    fileEntrys.add(map);
                    
                }
                zipFiles = ZipUtil.zipFiles(fileEntrys, configProperties.getToPath(),
                        checkResult.getPatientName() + "_" + configProperties.getZipName());
                System.out.println(configProperties.getZipName() + "6666666");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return zipFiles;
    }
    
}
