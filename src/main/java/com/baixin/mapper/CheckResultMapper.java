package com.baixin.mapper;

import com.baixin.model.CheckResult;
import com.baixin.model.Item;
import com.baixin.model.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by liqz on 2020/01/6
 */
@Mapper
public interface CheckResultMapper {
    
    CheckResult findById(CheckResult checkResult);
    
    int insert(CheckResult checkResult);
    
    int update(CheckResult checkResult);
    
    /**
     * @desc 检验报告信息分页列表
     *
     * @auther: liqz
     * @param: [paramMap]
     * @return: java.util.List<com.baixin.model.CheckResult>
     * @date: 2020-01-06 17:28
     *
     */
    List<CheckResult> listPage(Map<String, Object> paramMap);
    
    int listPageCount(Map<String, Object> paramMap);
    
    int deleteById(CheckResult checkResult);
}
