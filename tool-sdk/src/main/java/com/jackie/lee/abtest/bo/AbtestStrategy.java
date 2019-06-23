package com.jackie.lee.abtest.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lxb on 2019/5/17.
 */
@Data
public class AbtestStrategy implements Serializable{
    /**
     * 策略名
     */
    private String strategyName;
    /**
     * 权重，策略的分流比例
     */
    private int weight;
    /**
     * 白名单，多个Id用逗号分隔
     */
    private String whiteList;
}
