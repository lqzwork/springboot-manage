package com.baixin.util;

import com.baixin.model.CheckResult;
import com.spire.doc.Document;

import java.io.InputStream;

/**
 * @desc: word文档工具类
 * @ClassName: WordUtil
 * @Author: liqz
 * @Date: 2020-01-06 14:04
 **/
public class WordUtil {
    public static Document process(InputStream inputStream, CheckResult checkResult, String fileName) {
        //加载测试文档
        Document doc = new Document(inputStream);
        //如只需替换文档中的第一个指定文本，替换前先调用以下方法，再进行下一步代码
        doc.setReplaceFirst(true);
        //如需替换文档中的所有指定文本，可省略上一步代码，直接调用以下方法
        doc.replace("姓名:", "姓名:" + checkResult.getPatientName(), true, true);
        doc.replace("性别:", "性别:" + checkResult.getPatientSex(), true, true);
        doc.replace("年龄:", "年龄:" + checkResult.getPatientAge(), true, true);
        doc.replace("住院号:", "住院号:" + checkResult.getPatientHostNum(), true, true);
        doc.replace("送检科室:", "送检科室:" + checkResult.getInspecDepart(), true, true);
        doc.replace("床号:", "床号:" + checkResult.getPatientBedNum(), true, true);
        // doc.replace("标本类型:", "标本类型:" + checkResult.getSpecimenType(), true, true);
        // doc.replace("标本类型:", "标本类型:" + (fileName.contains("尿") == true ? "尿液":checkResult.getSpecimenType()), true, true);
        doc.replace("门诊号:", "门诊号:" + checkResult.getOutpatService(), true, true);
        doc.replace("标本状态:", "标本状态:" + checkResult.getSpecimenStatus(), true, true);
        doc.replace("样本号:", "样本号:" + checkResult.getSampleNum(), true, true);
        doc.replace("送检日期:", "送检日期:" + checkResult.getInspecDate(), true, true);
        doc.replace("送检医师:", "送检医师:" + checkResult.getInspecDoctor(), true, true);
        doc.replace("检验师:", "检验师:" + checkResult.getCheckDoctor(),true, true);
        doc.replace("报告时间:", "报告时间:" + checkResult.getCheckReportDate(), true, true);
    
        doc.replace("姓名：", "姓名:" + checkResult.getPatientName(), true, true);
        doc.replace("性别：", "性别:" + checkResult.getPatientSex(), true, true);
        doc.replace("年龄：", "年龄:" + checkResult.getPatientAge(), true, true);
        doc.replace("住院号：", "住院号:" + checkResult.getPatientHostNum(), true, true);
        doc.replace("送检科室：", "送检科室:" + checkResult.getInspecDepart(), true, true);
        doc.replace("床号：", "床号:" + checkResult.getPatientBedNum(), true, true);
        // doc.replace("标本类型：", "标本类型:" + checkResult.getSpecimenType(), true, true);
        // doc.replace("标本类型：", "标本类型:" + (fileName.contains("尿") == true ? "尿液":checkResult.getSpecimenType()), true, true);
        doc.replace("门诊号：", "门诊号:" + checkResult.getOutpatService(), true, true);
        doc.replace("标本状态：", "标本状态:" + checkResult.getSpecimenStatus(), true, true);
        doc.replace("样本号：", "样本号:" + checkResult.getSampleNum(), true, true);
        doc.replace("送检日期：", "送检日期:" + checkResult.getInspecDate(), true, true);
        doc.replace("送检医师：", "送检医师:" + checkResult.getInspecDoctor(), true, true);
        doc.replace("检验师：", "检验师:" + checkResult.getCheckDoctor(),true, true);
        doc.replace("报告时间：", "报告时间:" + checkResult.getCheckReportDate(), true, true);
        //保存文档
        // doc.saveToFile("/Users/liqingzheng/Documents/测试报告单.docx", FileFormat.Docx_2010);
        
        return doc;
    }
}
