package com.reactive.parkey;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class SuperTypeToken {
    static class Sup<T> {
        T value;
    }

    //nested static class = inner class??
    static class Sub extends Sup<String> {
    }

    static class Sub2 extends Sup<List<String>> {
    }

    //TypeToken
    static class TypesafeMap {
        Map<TypeReference<?>, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr, value);
        }

        <T> T get(TypeReference<T> tr) {
            if (tr.type instanceof Class<?>)
                return ((Class<T>)tr.type).cast(map.get(tr)); // typeReference<String> o typeReference<List<String>> X
            else
                return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr)); // typeReference<String> o typeReference<List<String>> X
        }
    }

    //TypeToken
    static class TypeReference<T> {
        Type type; //type을 저장

        public TypeReference() {
            Type stype = getClass().getGenericSuperclass();
            if(stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType)stype).getActualTypeArguments()[0];
            } else throw new RuntimeException();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;
            TypeReference<?> that = (TypeReference<?>) o;
            return type.equals(that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
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

//        TypeReference<String> typeReference = new TypeReference<>(); // runtime 익셉션 발생

        TypeReference typeReference = new TypeReference<String>() {};

        TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<String>() {}, "value");
        m.put(new TypeReference<Integer>() {}, 1);
        m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1,2,3));
        m.put(new TypeReference<List<String>>() {}, Arrays.asList("a","b","c"));

        System.out.println(m.get(new TypeReference<Integer>() {}));
        System.out.println(m.get(new TypeReference<String>() {}));
        System.out.println(m.get(new TypeReference<List<Integer>>() {}));
        System.out.println(m.get(new TypeReference<List<String>>() {}));

    }
}
