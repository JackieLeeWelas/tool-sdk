package com.jackie.lee.classloader;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by lxb on 2019/6/23.
 */
public class Test {

    public static void main(String[] args) throws Exception{
//        MyClassLoader loader = new MyClassLoader("/Users/lxb/Desktop",ClassLoader.getSystemClassLoader().getParent()); // ,ClassLoader.getSystemClassLoader().getParent()
//        try {
//            Class<?> clz = loader.loadClass("com.jackie.lee.object.Lxb");
//            Object o = clz.newInstance();
//            if (o instanceof Lxb){
//                System.out.println("the same");
//            }else {
//                ClassLoader classLoader1 = clz.getClassLoader();
//                ClassLoader classLoader2 = new Lxb().getClass().getClassLoader();
//                System.out.println(classLoader1 + "" + classLoader2);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        热部署
//        MyClassLoader loader = new MyClassLoader("/Users/lxb/Desktop",ClassLoader.getSystemClassLoader().getParent());
        List<Class> lxbs = Lists.newArrayList();
        for (int i = 0; i < 10; i++){
            MyClassLoader loader = new MyClassLoader("/Users/lxb/Desktop",ClassLoader.getSystemClassLoader().getParent());
            Class<?> clz = loader.loadClass("com.jackie.lee.object.Lxb");
            lxbs.add(clz);
        }
        for (int i = 0; i < 9; i++){
            if (lxbs.get(i) == lxbs.get(i + 1)){
                System.out.println("the same");
            }else {
                System.out.println("not same");
            }
        }
    }
}
