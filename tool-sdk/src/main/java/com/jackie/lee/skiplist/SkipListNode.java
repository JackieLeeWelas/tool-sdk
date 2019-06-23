package com.jackie.lee.skiplist;

import java.util.Arrays;

/**
 * Created by lxb on 2018/10/14.
 */
public class SkipListNode<T> {
    SkipListNode pre;
    SkipListNode<T>[] next;
    T key;
    int level;

    SkipListNode(T key,int level){
        this.key = key;
        this.level = level;
        next = new SkipListNode[level];
    }

    @Override
    public String toString() {
        return "SkipListNode{" +
                "pre=" + pre +
                ", next=" + Arrays.toString(next) +
                ", key=" + key +
                ", level=" + level +
                '}';
    }
}
