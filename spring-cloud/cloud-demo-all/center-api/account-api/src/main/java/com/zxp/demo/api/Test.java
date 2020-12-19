package com.zxp.demo.api;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class Test
{
    public static void main(String[] args) {
        Object object=new Object();
        System.out.println(object.hashCode());
        Object object2=new Object();
        System.out.println(object2.hashCode());
        Object object3=new Object();
        System.out.println(object3.hashCode());
        Object object4=new Object();
        System.out.println(object4.hashCode());
    }
}
