package com.baixin.mapper;

import com.baixin.model.Item;
import com.baixin.model.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangyunxiong on 2018/3/10.
 */
@Mapper
public interface OperateLogMapper {
    
    int insert(OperateLog operateLog);
    
    /**
     * @desc 操作日志分页列表
     *
     * @auther: liqz
     * @param: [item]
     * @return: java.util.List<com.baixin.model.OperateLog>
     * @date: 2019-11-22 21:27
     *
     */
    List<OperateLog> listPage(Map<String, Object> paramMap);
    
    int listPageCount(Map<String, Object> paramMap);
}
