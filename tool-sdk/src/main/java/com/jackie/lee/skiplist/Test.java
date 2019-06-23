package com.jackie.lee.skiplist;

/**
 * Created by lxb on 2018/10/14.
 */
public class Test {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>(5);
        skipList.insert(2);
        skipList.insert(9);
        skipList.insert(11);
        skipList.insert(4);
        skipList.insert(21);
        skipList.insert(17);

        Integer result = skipList.search(17);
        System.out.println(result);
    }
}
