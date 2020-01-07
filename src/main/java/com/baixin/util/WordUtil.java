package com.baixin.util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

/**
 * @desc: word文档工具类
 * @ClassName: WordUtil
 * @Author: liqz
 * @Date: 2020-01-06 14:04
 **/
public class WordUtil {
    public static void main(String[] args) {
        //加载测试文档
        Document doc = new Document("/Users/liqingzheng/Documents/测试报告单.docx");
        //如只需替换文档中的第一个指定文本，替换前先调用以下方法，再进行下一步代码
        doc.setReplaceFirst(true);
        //如需替换文档中的所有指定文本，可省略上一步代码，直接调用以下方法
        doc.replace("姓名:", "姓名:杨树国", true, true);
        doc.replace("性别:", "性别:男", true, true);
        doc.replace("年龄:", "年龄:47岁", true, true);
        doc.replace("住院号:", "住院号:101", true, true);
        doc.replace("送检科室:", "送检科室:内科", true, true);
        doc.replace("床号:", "床号:10床", true, true);
        doc.replace("标本类型:", "标本类型:静脉血", true, true);
        doc.replace("门诊号:", "门诊号:1123", true, true);
        doc.replace("标本状态:", "标本状态:正常", true, true);
        doc.replace("样本号:", "样本号:5", true, true);
        doc.replace("送检日期:", "送检日期:2019-12-01", true, true);
        doc.replace("送检医师:", "送检医师:赵金星", true, true);
        doc.replace("检验师:", "检验师:叶芝萍", true, true);
        doc.replace("报告时间:", "报告时间:2020-1-5", true, true);

        doc.replace("姓名：", "姓名:杨树国", true, true);
        doc.replace("性别：", "性别:男", true, true);
        doc.replace("年龄：", "年龄:47岁", true, true);
        doc.replace("住院号：", "住院号:101", true, true);
        doc.replace("送检科室：", "送检科室:内科", true, true);
        doc.replace("床号：", "床号:10床", true, true);
        doc.replace("标本类型：", "标本类型:静脉血", true, true);
        doc.replace("门诊号：", "门诊号:1123", true, true);
        doc.replace("标本状态：", "标本状态:正常", true, true);
        doc.replace("样本号：", "样本号:5", true, true);
        doc.replace("送检日期：", "送检日期:2019-12-01", true, true);
        doc.replace("送检医师：", "送检医师:赵金星", true, true);
        doc.replace("检验师：", "检验师:叶芝萍", true, true);
        doc.replace("报告时间：", "报告时间:2020-1-5", true, true);
        //保存文档
        doc.saveToFile("/Users/liqingzheng/Documents/测试报告单.docx", FileFormat.Docx_2010);

    }
}
