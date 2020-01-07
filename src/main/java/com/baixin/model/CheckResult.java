package com.baixin.model;

import lombok.Data;

@Data
public class CheckResult extends BaseObject {
    private int id;
    /**
     * 患者姓名
     */
    private String patientName;
    /**
     * 患者性别
     */
    private String patientSex;
    /**
     * 患者年龄
     */
    private String patientAge;
    /**
     * 患者住院号
     */
    private String patientHostNum;
    /**
     * 送检科室
     */
    private String inspecDepart;
    /**
     * 床号
     */
    private String patientBedNum;
    /**
     * 标本类型
     */
    private String specimenType;
    /**
     * 门诊号
     */
    private String outpatService;
    /**
     * 标本状态
     */
    private String specimenStatus;
    /**
     * 样本号
     */
    private String sampleNum;
    /**
     * 送检日期
     */
    private String inspecDate;
    /**
     * 送检医师
     */
    private String inspecDoctor;
    /**
     * 检验师
     */
    private String checkDoctor;
    /**
     * 报告时间
     */
    private String checkReportDate;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 创建人ID
     */
    private int createUserId;
    
}
