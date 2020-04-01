package com.reactive.parkey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {

    static class Generic<T> {
        T value;
        void set(T t) {

        }
        T get() {return value;}
    }

    static <T> T create(Class<T> clazz) throws Exception{
        return clazz.newInstance();
    }

    //TypeToken
    static class TypesafeMap {
        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }
    }

    public static void main(String[] args) throws Exception {
        String o = create(String.class);
        System.out.println(o.getClass());

        //type parameter
        //type eraser run time 시점엔 타입 삭제되고 오브젝트로 캐스팅
        Generic<String> stringGeneric = new Generic<>();
        stringGeneric.value = "String";

        Generic<Integer> i = new Generic<>();
        i.value = 1;
        i.set(10);

        //문제점 : 한타입에 하나의 값
        TypesafeMap m = new TypesafeMap();
        m.put(String.class, "value");
        m.put(Integer.class, 1);

        m.put(List.class, Arrays.asList(1,2,3));
        // 이런코드가 가능하도록 하는것 타입 eraser Object로
//        m.put(List<Integer>.class, Arrays.asList(1,2,3)); //리스트의 제네릭 타입 토큰 사용 불가능

        System.out.println(m.get(Integer.class));
        System.out.println(m.get(String.class));
        System.out.println(m.get(List.class));

    }
}
