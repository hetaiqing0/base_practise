package test;

import reflect.extend.People;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        // 开始执行
        long start = System.currentTimeMillis();
        /*=========================================================*/

        /*=========================================================*/
        // 结束执行
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");
    }
}
