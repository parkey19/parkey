package com.reactive.parkey;

import com.reactive.parkey.ParkeyApplication.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SpringRestCall {
    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();
        /**
         * 타겟 모델은 User 인데 rest템플릿은 이 모델에 대한 정보가 없음 발생
         *  아래의 api는 json key value 데이터를 리턴하므로 스프링에서는 LinkedHashMap 변환됨
         * //Exception in thread "main" java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to com.reactive.parkey.ParkeyApplication$User
         *         //	at com.reactive.parkey.SpringRestCall.main(SpringRestCall.java:11)
         *
         */
//        List<ParkeyApplication.User> forObject = rt.getForObject("http://localhost:8080", List.class);
//        System.out.println(forObject.get(0).getName());

        List<User> users = rt.exchange("http://localhost:8080",
                HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {})
                .getBody();
        users.forEach(System.out::println);
    }
}
