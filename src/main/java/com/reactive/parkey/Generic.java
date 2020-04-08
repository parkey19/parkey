package com.reactive.parkey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generic {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 3, 2);
        System.out.println(max(list));
        System.out.println(Collections.max(list));
        List<String> ns = Arrays.asList("1","2");
        System.out.println(getSize(ns));
    }

    /**
     * List<? extends T> 메소드 내부에서 사용시 upper bound 사용
     * <T extends Comparable<? super T>> 메서드 밖에서 사용시 lower bound 사용
     * <T extends Comparable<T>> 은 “자신과 비교될 수 있는 모든 타입 T”라고 읽을 수 있다.
     * @param list
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).get();
    }

    /**
     *  <? extends T> T를 포함하고 T를 상속한 어떤 하위 타입 (upper bound)
     *  <? super T2> T2를 포함하고 T2의 슈퍼 타입 (lower bound)
     * @param list
     * @param <T>
     * @return
     */
    private static <T> int getSize(List<? extends Number> list) {
        return list.size();
    }

}
