package com.reactive.parkey;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class SpringTypeReference {
    public static void main(String[] args) {

        //익명클래스가 상속한 슈퍼 클래스의 타입 파라미터 정보를 전달하기 위해 super type token
        // List<String> list = new ArrayList<>(); 제네릭 사용할때 런타임시 타입 지워짐.
        ParameterizedTypeReference<?> parameterizedTypeReference = new ParameterizedTypeReference<List<String>>() {};
        System.out.println(parameterizedTypeReference.getType());

    }


}
