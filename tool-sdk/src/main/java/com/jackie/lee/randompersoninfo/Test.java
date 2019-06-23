package com.jackie.lee.randompersoninfo;

import java.io.FileWriter;

/**
 * Created by lxb on 2019/6/23.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        RandomPersonInfo personInfo = new RandomPersonInfo();
        FileWriter fw = new FileWriter("/Users/lxb/Desktop/test.csv");
        String header = "name,tel\r\n";
        fw.write(header);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 500; i++) {
            str.append(personInfo.getChineseName() + "," + personInfo.getTelephone() + "\r\n");
        }
        fw.write(str.toString());
        fw.flush();
        fw.close();
    }
}
