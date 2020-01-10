package com.baixin.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @desc: 汉子转拼音工具
 * @ClassName: PinYinUtil
 * @Author: liqz
 * @Date: 2020-01-10 17:17
 **/
public class PinYinUtil {
    
    /**
     * @desc 汉字转为拼音
     *
     * @auther: liqz
     * @param: [chinese]
     * @return: java.lang.String
     * @date: 2020-01-10 17:23
     *
     */
    public static String ToPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
    
    /**
     * @desc 获取字符串拼音的第一个字母
     *
     * @auther: liqz
     * @param: [chinese]
     * @return: java.lang.String
     * @date: 2020-01-10 17:24
     *
     */
    public static String ToFirstChar(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}
