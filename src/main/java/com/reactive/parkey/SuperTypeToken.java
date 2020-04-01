package com.reactive.parkey;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
    static class Sup<T> {
        T value;
    }

    //nested static class = inner class??
    static class Sub extends Sup<String> {
    }

    static class Sub2 extends Sup<List<String>> {
    }

    public static void main(String[] args) throws NoSuchFieldException {

        //인스턴스 생성시 타입을 준것
        Sup<String> sup = new Sup<>();
        //실행시 타입 파라미터 정보 지워지고 오브젝트로 변환됨
        System.out.println(sup.getClass().getDeclaredField("value").getType());

        //슈퍼클래스 상속을 통해 타입파라미터 지정
        //제네릭 선언한 슈퍼클래스에서 타입을 가지고 올수 있음
        Sub sub = new Sub();
        Type t = sub.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);

//        Sub2 sub2 = new Sub2();
//        Sup sub2 = new Sup<List<String>>() {};
        Type t2 = new Sup<List<String>>() {}.getClass().getGenericSuperclass();
        ParameterizedType ptype2 = (ParameterizedType) t2;
        System.out.println(ptype2.getActualTypeArguments()[0]);

    }
}
