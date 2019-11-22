package com.baixin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangyunxiong on 2018/4/2.
 */
public class DateUtil {
    
    /**
     * 格式
     */
    public final static String YYYYMMDDHHmmSS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String YYYYMM = "yyyyMM";
    public final static String yyMd = "yyyyMMdd";
    public final static String yyM = "yyyy-MM";

    public static String  getDateStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmSS);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static Date strToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmSS);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
