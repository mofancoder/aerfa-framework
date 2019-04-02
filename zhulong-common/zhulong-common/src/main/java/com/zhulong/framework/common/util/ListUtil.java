package com.zhulong.framework.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * list的工具类
 * @author xxc
 * @time 2019-1-7 16:01
 */
public class ListUtil {

    private ListUtil(){}

    /**
     * 获取list中空字符串数量
     * @author xxc
     * @return long
     * @param strings
     * @time 2019-1-7 15:32
     */
    public static long getNullStrOfNum(List<String> strings){

        return strings.parallelStream().filter(string->string.isEmpty()).count();

    }

    /**
     * 移除list中空字符串
     * @author xxc
     * @return java.util.List<java.lang.String>
     * @param strings
     * @time 2019-1-7 15:37
     */
    public static List<String> removeNullStr(List<String> strings){

        return strings.parallelStream().filter(string->!string.isEmpty()).collect(Collectors.toList());

    }

    /**
     * 获取list中字符串长度为length的数量
     * @author xxc
     * @return long
     * @param strings
     * @param length
     * @time 2019-1-7 15:34
     */
    public static long getlengOfNum(List<String> strings,int length){

        return strings.parallelStream().filter(string->string.length() == length).count();

    }

    /**
     * list中字符串排序
     * @author xxc
     * @return java.util.List<java.lang.String>
     * @param strings
     * @time 2019-1-7 15:50
     */
    public static List<String> compare(List<String> strings){

        Comparator<String> comparator=(String s1, String s2)-> (s2.compareTo(s1));

        Collections.sort(strings,comparator);

        return strings;
    }

    /**
     * 获取list中最大的数
     * @author xxc
     * @return long
     * @param integers
     * @time 2019-1-7 15:42
     */
    public static long getMaxNum(List<Integer> integers){

        return integers.stream().mapToInt((x) ->x).summaryStatistics().getMax();
    }

    /**
     * 获取list中最小的数
     * @author xxc
     * @return long
     * @param integers
     * @time 2019-1-7 15:42
     */
    public static long getMinNum(List<Integer> integers){

        return integers.stream().mapToInt((x) ->x).summaryStatistics().getMin();
    }

    /**
     * 获取list的总数
     * @author xxc
     * @return long
     * @param integers
     * @time 2019-1-7 15:42
     */
    public static long getSumNum(List<Integer> integers){

        return integers.stream().mapToInt((x) ->x).summaryStatistics().getSum();
    }

    /**
     * 获取list的平均数
     * @author xxc
     * @return double
     * @param integers
     * @time 2019-1-7 15:42
     */
    public static double getAverageNum(List<Integer> integers){

        return integers.stream().mapToInt((x) ->x).summaryStatistics().getAverage();
    }


    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        System.out.println("空字符串数量为: " + getNullStrOfNum(strings));

        System.out.println("筛选后的列表: " + removeNullStr(strings));

        System.out.println("字符串长度为 3 的数量为: " + getlengOfNum(strings,3));

        System.out.println("排序: " + compare(strings));

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        System.out.println("列表中最大的数 : " + getMaxNum(integers));
        System.out.println("列表中最小的数 : " + getMinNum(integers));
        System.out.println("所有数之和 : " + getSumNum(integers));
        System.out.println("平均数 : " + getAverageNum(integers));


    }
}
