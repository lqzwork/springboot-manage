package com.baixin.model;

import lombok.Data;

import java.util.Date;

@Data
public class OperateLog extends BaseObject{
    private int id;
    private String itemId;
    private int modifyNum;
    private String operate;//操作：买入、卖出
    private String operateUserId;
    private Date operateTime;
    private String operateTimeStr;
    
    /** 以下为非数据库映射属性 */
    private String title;
    private String norm;
    private String price;
    private String unit;
    private String realName;
}
