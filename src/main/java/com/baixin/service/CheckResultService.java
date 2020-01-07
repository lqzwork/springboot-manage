package com.baixin.service;

import com.baixin.common.PageInfo;
import com.baixin.mapper.CheckResultMapper;
import com.baixin.model.CheckResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    public int checkResultDelete (CheckResult checkResult) {
        return checkResultMapper.deleteById(checkResult);
    }
    
}
