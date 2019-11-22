package com.baixin.mapper;

import com.baixin.model.Item;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jiangyunxiong on 2018/3/10.
 */
@Mapper
public interface OperateLogMapper {

    Item findById(Item item);
}
