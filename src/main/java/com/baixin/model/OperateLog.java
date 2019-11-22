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
}
