package com.reactive.parkey;

import java.util.Arrays;
import java.util.List;

public class Dispatch {

    interface Post { void postOn(SNS sns);}
    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            System.out.println("text ->" + sns.getClass().getSimpleName());
        }
    }

    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            System.out.println("picture ->" + sns.getClass().getSimpleName());
        }
    }
    
    interface SNS {}
    static class Facebook implements SNS {

    }
    static class Twitter implements SNS {
    }

    public static void main(String[] args) {
        List<Post> postList = Arrays.asList(new Text(), new Picture());
        List<SNS> snsList = Arrays.asList(new Facebook(), new Twitter());


        //runtime 시점에 post에 어떤 클래스가 실행될지 결정 다이나믹 디스패치
        postList.forEach(post -> snsList.forEach(sns -> post.postOn(sns)));

    }
}
