package com.jackie.lee.common;

/**
 * Created by lxb on 2019/6/23.
 */
public class StringUtils {
    /**
     * 判断一个字符串是否可表示成一个小数
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 怕段字符串是否不是空的
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return str != null && str.length() > 0;
    }

    /**
     * 判断字符串是否是空的
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }
}
