package com.jackie.lee.common;

import com.google.common.cache.*;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxb on 2019/6/23.
 */
@Component
public class ReflectUtil {
    /**
     * Map中键值Object的type与model中对应字段的type,必须一致
     */
    public static <T> void buildModel(T model, Map<String, Object> value) {
        try {
            Class clazz = model.getClass();
            Object cache = templateCache.get(clazz.getName());
            if (cache != null) {
                Map<String, Method> methodMap = (Map<String, Method>) cache;
                for (String key : value.keySet()) {
                    Method method = methodMap.get(key);
                    if (method == null || value.get(key) == null) continue;
                    method.invoke(model, value.get(key));
                }
            }
        } catch (Exception e) {
        }
    }

    //guava cache
    private static LoadingCache<String, Object> templateCache = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置写缓存后3小时过期
            .expireAfterWrite(3, TimeUnit.HOURS)
            //设置缓存容器的初始容量为10
            .initialCapacity(10)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(100)
            //设置要统计缓存的命中率
            .recordStats()
            //设置缓存的移除通知
            .removalListener(notification -> { //log
            })
            //build方法中可以指定CacheLoader，在缓存的内容不存在时通过CacheLoader的实现自动加载缓存。
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    // 处理value的值
                    return buildMethodMap(key);
                }
            });

    private static Map<String, Method> buildMethodMap(String clazzName) {
        Map<String, Method> methodMap = Maps.newHashMap();
        try {
            Class clazz = Class.forName(clazzName);
            Field[] fs = clazz.getDeclaredFields();
            for (Field f : fs) {
                String fieldName = f.getName();
                String fieldNameBig = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                if (StringUtils.isNotEmpty(fieldName)) {
                    f.setAccessible(true);
                    Method m = clazz.getMethod("set" + fieldNameBig, f.getType());
                    methodMap.put(fieldName, m);
                }
            }
        } catch (Exception e) {
        }
        return methodMap;
    }
}
