package com.baixin.util;

/**
 * @desc: 房贷计算
 * @ClassName: HouseLoan
 * @Author: liqz
 * @Date: 2020-08-25 09:30
 **/
public class HouseLoan {
    
    /**
     * @desc 计算月供
     *
     * @auther: liqz
     * @param: [totalMonth 万, yearRate 年利率, tatalYear 总年数]
     * @return: java.lang.String
     * @date: 2020-08-25 09:34
     *
     */
    public static Double monthPay(Double totalMoney, Double yearRate, int totalYear) {
        /**
         * 月利率
         **/
        double monthRate = yearRate/12;
        /**
         * 总月数
         **/
        int totalMonth = totalYear * 12;
        /**
         * 贷款总额，元
         **/
        double totalMoneys = totalMoney * 10000;
        
        double rateN = monthRate + 1;
        
        for(int i = 1; i <= totalMonth - 1; i++) {
            rateN = rateN * (monthRate + 1);
        }
        
        return (totalMoneys * monthRate * rateN)/(rateN - 1);
    }
    
    public static void main(String[] args) {
        Double monthPay = monthPay(60.0, 0.041, 30);
        System.out.println(monthPay);
    }
}
