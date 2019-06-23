package com.jackie.lee.common;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 字符串分割成List<Integer>
     * @param str
     * @param splitter
     * @return
     */
    public static List<Integer> splitToIntegerList(String str, String splitter){
        List<Integer> ints = null;
        try {
            List<String> strs = splitToStringList(str, splitter);
            if (strs != null) {
                CollectionUtils.collect(strs, input -> new Integer(input), ints);
            }
        }catch (Exception e){
            //
        }
        return ints;
    }

    /**
     * 字符串分割成List<String>
     * @param str
     * @param splitter
     * @return
     */
    public static List<String> splitToStringList(String str, String splitter){
        if (isNotEmpty(str)){
            String []strs = str.split(splitter);
            return Arrays.asList(strs);
        }
        return null;
    }
}
