package com.jackie.lee.beanutils;

public class CastUtil {
    /**
     * 类型转换
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T cast(Object obj) {
        if (obj != null) {
            try {
                return (T) obj;
            } catch (ClassCastException e) {

            }
        }
        return null;
    }
}
