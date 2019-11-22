package com.baixin.service;

import com.alibaba.fastjson.JSONObject;
import com.baixin.common.PageInfo;
import com.baixin.mapper.OperateLogMapper;
import com.baixin.model.OperateLog;
import com.baixin.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc: 操作日志记录service
 * @ClassName: OperateLogService
 * @Author: liqz
 * @Date: 2019-11-22 16:16
 **/
@Slf4j
@Service
public class OperateLogService {
    
    @Autowired
    private OperateLogMapper operateLogMapper;
    
    /**
     * @desc 记录操作日志
     * @auther: liqz
     * @param: []
     * @return: void
     * @date: 2019-11-22 16:34
     */
    @Transactional
    public void recordOperateLog(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }
    
    /**
     * @desc 操作日志分页列表
     * @auther: liqz
     * @param: [item]
     * @return: java.util.List<com.baixin.model.OperateLog>
     * @date: 2019-11-22 21:30
     */
    public PageInfo listPage(Map<String, Object> paramMap) {
        PageInfo listPage = new PageInfo();
        List<OperateLog> operateLogList = operateLogMapper.listPage(paramMap);
        if(null != operateLogList && !operateLogList.isEmpty()) {
            operateLogList.stream().forEach(log -> log.setOperateTimeStr(DateUtil.getDateStr(log.getOperateTime())));
            int listPageCount = operateLogMapper.listPageCount(paramMap);
            listPage.setContentList(operateLogList);
            listPage.setPageNum(Integer.parseInt(paramMap.get("pageNum").toString()));
            listPage.setTotalSize(listPageCount);
        }
        return listPage;
    }
    
}
