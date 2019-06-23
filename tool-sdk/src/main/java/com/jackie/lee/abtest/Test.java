package com.jackie.lee.abtest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jackie.lee.abtest.bo.AbtestConfig;

import java.util.List;
import java.util.UUID;

/**
 * Created by lxb on 2019/6/23.
 */
public class Test {
    private static String config = "{\n" +
            "    \"experiments\": [\n" +
            "        {\n" +
            "            \"isValide\": \"true\",\n" +
            "            \"experimentName\": \"test1\",\n" +
            "            \"strategys\": [\n" +
            "                {\n" +
            "                    \"strategyName\": \"s1\",\n" +
            "                    \"weight\": 10,\n" +
            "                    \"whiteList\": \"\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"strategyName\": \"s2\",\n" +
            "                    \"weight\": 10,\n" +
            "                    \"whiteList\": \"D9AB75C02EBF31FA0056F4B7222587ED80BD1A9C03F8701BC3BF80D7518FEF9D,1269751559\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"strategyName\": \"s3\",\n" +
            "                    \"weight\": 10,\n" +
            "                    \"whiteList\": \"\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"isValide\": \"true\",\n" +
            "            \"experimentName\": \"test2\",\n" +
            "            \"strategys\": [\n" +
            "                {\n" +
            "                    \"strategyName\": \"s1\",\n" +
            "                    \"weight\": 50\n" +
            "                },\n" +
            "                {\n" +
            "                    \"strategyName\": \"s2\",\n" +
            "                    \"weight\": 50\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"isValide\": \"true\",\n" +
            "            \"experimentName\": \"test3\",\n" +
            "            \"strategys\": [\n" +
            "                {\n" +
            "                    \"strategyName\": \"s1\",\n" +
            "                    \"weight\": 40\n" +
            "                },\n" +
            "                {\n" +
            "                    \"strategyName\": \"s2\",\n" +
            "                    \"weight\": 60\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args) {
        AbtestUtil.setAbtestConfig(JSON.parseObject(config, AbtestConfig.class));

        List<Integer> nums = Lists.newArrayList(100, 1000, 10000, 100000, 1000000, 10000000);
        for (Integer num : nums) {
            int s1 = 0;
            int s2 = 0;
            int s3 = 0;
            for (int i = 0; i < num; i++) {
                String uuid = UUID.randomUUID().toString();
                String hitStrategy = AbtestUtil.hit("test1",uuid,"s1");
                if ("s1".equals(hitStrategy)) {
                    s1++;
                } else if ("s2".equals(hitStrategy)){
                    s2++;
                }else if ("s3".equals(hitStrategy)){
                    s3++;
                }
            }
            System.out.println("实验" + num + "次,s1=" + s1 + ",s2=" + s2 + ",s3=" + s3);
        }
    }
}
