package com.jackie.lee.abtest.bo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxb on 2019/5/17.
 */
@Data
public class AbtestConfig implements Serializable {
    List<AbtestExperiment> experiments = Lists.newArrayList();
}
