package com.baixin.service;

import com.baixin.mapper.OperateLogMapper;
import com.baixin.model.Item;
import com.baixin.model.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     *
     * @auther: liqz
     * @param: []
     * @return: void
     * @date: 2019-11-22 16:34
     *
     */
    @Transactional
    public void recordOperateLog(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }
    
}
