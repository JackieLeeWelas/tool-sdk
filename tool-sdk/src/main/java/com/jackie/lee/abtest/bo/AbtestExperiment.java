package com.jackie.lee.abtest.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxb on 2019/5/17.
 */
@Data
public class AbtestExperiment implements Serializable {
    /**
     * 实验是否生效
     */
    private boolean isValide;
    /**
     * 实验名称
     */
    private String experimentName;
    /**
     * 实验策略
     */
    private List<AbtestStrategy> strategys;
}
