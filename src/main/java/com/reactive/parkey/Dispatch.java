package com.reactive.parkey;

import java.util.Arrays;
import java.util.List;

public class Dispatch {

    interface Post {
        void postOn(SNS sns);
    }
    static class Text implements Post {
        public void postOn(SNS sns) {
            sns.post(this);
        }
    }

    static class Picture implements Post {
        public void postOn(SNS sns) {
            sns.post(this);
        }
    }
    
    interface SNS {
        void post(Text text);
        void post(Picture picture);
    }
    static class Facebook implements SNS {
        @Override
        public void post(Text text) {
            System.out.println("text Facebook");
        }

        @Override
        public void post(Picture picture) {
            System.out.println("picture Facebook");
        }
    }
    static class Twitter implements SNS {
        @Override
        public void post(Text text) {
            System.out.println("text Twit");
        }

        @Override
        public void post(Picture picture) {
            System.out.println("picture Twit");
        }
    }
    static class Googleplus implements SNS {
        @Override
        public void post(Text text) {
            System.out.println("text Google");
        }

        @Override
        public void post(Picture picture) {
            System.out.println("picture Google");
        }
    }

    public static void main(String[] args) {
        List<Post> postList = Arrays.asList(new Text(), new Picture());
        List<SNS> snsList = Arrays.asList(new Facebook(), new Twitter(), new Googleplus());

        //runtime 시점에 post에 어떤 클래스가 실행될지 결정 다이나믹 디스패치
        //메서드 오버로딩은 컴파일 시점에 파라미터 타입이 정해져야한다
        //현재 코드는 정적으로 정해지지 않고 런타임에 정해짐.
        postList.forEach(post -> snsList.forEach(sns -> post.postOn(sns)));

    }
}
