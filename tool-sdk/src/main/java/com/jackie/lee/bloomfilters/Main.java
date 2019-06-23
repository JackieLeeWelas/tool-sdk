package com.jackie.lee.bloomfilters;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lxb on 2018/11/27.
 */
public class Main {
    private BloomFilter<String> filter = null;
    Main(int num, double fpp){
        this.filter = BloomFilter.create(Funnels.unencodedCharsFunnel(), num, fpp);
    }
    public static void main(String[] args) throws Exception{
        Main main = new Main(1000,0.001);
        main.put("JackieLee");
        System.out.println(main.isContain("JackieLee"));
        BloomFilters bloomFilters = new BloomFilters(100000000);
        for (int i = 0; i < 100000; i++){
            bloomFilters.add(i + "");
        }

        if (bloomFilters.check("9999")){
            System.out.println("contain");
        }else {
            System.out.println("not contain");
        }

    }

    private boolean put(String str){
        return filter.put(str);
    }

    private boolean isContain(String str){
        return filter.mightContain(str);
    }
}
