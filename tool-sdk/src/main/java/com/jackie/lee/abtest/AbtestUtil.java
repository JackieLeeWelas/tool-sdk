package com.jackie.lee.abtest;

import com.jackie.lee.abtest.bo.AbtestConfig;
import com.jackie.lee.abtest.bo.AbtestExperiment;
import com.jackie.lee.abtest.bo.AbtestStrategy;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 对流量进行分流的Abtest工具，AbtestConfig可以放在在任何可配置的地方配置
 * Created by lxb on 2019/5/17.
 */
public class AbtestUtil {
    private static AbtestConfig abtestConfig = null;

    public static void setAbtestConfig(AbtestConfig abtestConfig) {
        AbtestUtil.abtestConfig = abtestConfig;
    }

    /**
     *
     * @param experimentName 实验名称，实验的唯一标识
     * @param toAbtestId 任何可唯一标识进行Abtest的元素的字符串
     * @param defaultHitStrategy
     * @return
     */
    public static String hit(String experimentName, String toAbtestId, String defaultHitStrategy){
        if (StringUtils.isEmpty(toAbtestId)){
            toAbtestId = UUID.randomUUID().toString();
        }
        try {
            if (StringUtils.isNotBlank(experimentName) && StringUtils.isNotBlank(toAbtestId) &&
                    abtestConfig != null && CollectionUtils.isNotEmpty(abtestConfig.getExperiments())) {
                List<AbtestExperiment> experimentList = abtestConfig.getExperiments();
                for (AbtestExperiment experiment : experimentList) {
                    String configedExperimentName = experiment.getExperimentName();
                    if (experimentName.equals(configedExperimentName) && experiment.isValide()) {
                        String hitStrategy = hitStrategyInWhiteList(toAbtestId, experiment);
                        if (StringUtils.isNotBlank(hitStrategy)) {
                            return hitStrategy;
                        } else {
                            List<AbtestStrategy> strategies = experiment.getStrategys();
                            if (CollectionUtils.isNotEmpty(strategies)) {
                                String key = experimentName + "_" + toAbtestId;
                                int totalWeight = 0;
                                for (AbtestStrategy strategy : strategies) {
                                    totalWeight += strategy.getWeight();
                                }
                                int hitScope = Math.abs(DigestUtils.md5Hex(key).hashCode()) % totalWeight;
                                int sum = 0;
                                for (AbtestStrategy strategy : strategies) {
                                    sum += strategy.getWeight();
                                    if (hitScope < sum) {
                                        return strategy.getStrategyName();
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }catch (Exception e){
            String msg = String.format("NavAbtest异常，实验：%s 对Id：%s 将使用默认策略：%s",experimentName,toAbtestId,defaultHitStrategy);
//            log.error(msg, e);
        }
        return defaultHitStrategy;
    }

    /**
     * 判断是否命中某个实验的某个策略
     * @param experimentName
     * @param toAbtestId
     * @param strategyName
     * @return
     */
    public static boolean isHit(String experimentName, String toAbtestId, String strategyName){
        String hitedStrategy = hit(experimentName,toAbtestId,null);
        if (StringUtils.isNotBlank(hitedStrategy) && StringUtils.isNotBlank(strategyName) && hitedStrategy.equals(strategyName)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 是否命中白名单
     * @param toAbtestId
     * @param experiment
     * @return
     */
    private static String hitStrategyInWhiteList(String toAbtestId, AbtestExperiment experiment){
        List<AbtestStrategy> strategies = experiment.getStrategys();
        if (CollectionUtils.isNotEmpty(strategies)){
            for (AbtestStrategy strategy : strategies){
                if (StringUtils.isNotEmpty(strategy.getWhiteList())){
                    List<String> whiteList = Arrays.asList(strategy.getWhiteList().split(","));
                    if (CollectionUtils.isNotEmpty(whiteList) && whiteList.contains(toAbtestId)) {
                        return strategy.getStrategyName();
                    }
                }
            }
        }

        return null;
    }

}
