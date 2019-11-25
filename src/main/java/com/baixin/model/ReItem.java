package com.baixin.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReItem extends BaseObject{
    private int id;
    private String title;
    private String supplier;
    private String sellPoint;
    private String norm;
    private String unit;
    private Double price;
    private Double sellPrice;
    private int num;
    private String barcode;
    private String image;
    private int cid;
    private int status;
    private Date recovered;
    private String recoveredStr;
    private String createUserId;
    private String updteUserId;
}