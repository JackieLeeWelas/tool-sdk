package com.jackie.lee.common;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
/**
 * Created by lxb on 2019/6/23.
 */
public class NumberUtils {
    /**
     * 将double转换为string，最多保留digits位的小数（小数末尾是0的需要去掉），四舍五入。
     */
    public static String doubleToString(double d, int digits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(digits);
        // 设置四舍五入
        nf.setRoundingMode(RoundingMode.HALF_UP);
        return nf.format(d);
    }

    /**
     * 将BigDecimal转换为string，最多保留digits位的小数（小数末尾是0的需要去掉），四舍五入。
     */
    public static String bigDecimalToString(BigDecimal d, int digits) {
        if (d == null || d.compareTo(BigDecimal.ZERO) <= 0) return "";
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(digits);
        // 设置四舍五入
        nf.setRoundingMode(RoundingMode.HALF_UP);
        return nf.format(d);
    }

    public static String bigDecimalToString(BigDecimal d, int digits, String formatStr) {
        String num = bigDecimalToString(d, digits);
        if (StringUtils.isNotEmpty(num) && StringUtils.isNotEmpty(formatStr)) {
            return String.format(formatStr, num);
        }
        return num;
    }

    /**
     * 将Integer转为字符串，空和0不展示，其余拼接format
     *
     * @param d
     * @param formatStr
     * @return
     */
    public static String integerToString(Integer d, String formatStr) {
        if (d == null || d <= 0 || StringUtils.isEmpty(formatStr)) {
            return null;
        }
        return String.format(formatStr, d);
    }

    public static boolean isIntegerPositive(Integer num) {
        if (num != null && num > 0) {
            return true;
        }
        return false;
    }

    public static boolean isAllIntegerPositive(Integer... nums) {
        if (ArrayUtils.isNotEmpty(nums)) {
            for (Integer num : nums) {
                if (!isIntegerPositive(num)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
