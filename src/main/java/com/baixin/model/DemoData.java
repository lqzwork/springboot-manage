package com.baixin.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.ExcelIgnore;

import java.util.Date;

/**
 * @desc: easyExcel 测试实体类
 * @ClassName: DemoData
 * @Author: liqz
 * @Date: 2020-06-23 16:28
 **/
@Data
public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("地市")
    private String city;
    @ExcelProperty("省份")
    private String province;
    @ExcelProperty("县分")
    private String county;
    @ExcelProperty("阿里")
    private String ali;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
