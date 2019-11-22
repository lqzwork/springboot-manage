package com.baixin.mapper;

import com.baixin.model.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jiangyunxiong on 2018/3/10.
 */
@Mapper
public interface OperateLogMapper {
    
    int insert(OperateLog operateLog);
}
