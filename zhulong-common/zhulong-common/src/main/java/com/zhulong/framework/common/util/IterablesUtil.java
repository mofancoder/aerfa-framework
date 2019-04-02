package com.zhulong.framework.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Iterable 的工具类
 * @author xxc
 * @time 2018-12-27 14:35
 */
public class IterablesUtil {

    private IterablesUtil(){}

    /**
     * 带索引的迭代方法
     * @author xxc
     * @time 2018-12-27 16:19
     */
    public static <E> void forEach(Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);

        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }


    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1,2,6,8,9);

        integers.forEach(val-> {

            System.out.println("当前值："+val);

        });

        forEach(integers,(index, val)-> {

            System.out.println("当前索引:"+index+";值："+val);

        });
    }

}
