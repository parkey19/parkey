package com.reactive.parkey;

import java.util.Arrays;
import java.util.List;

public class Dispatch {

    interface Post { void postOn(SNS sns);}
    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) {
                System.out.println("text ->" + "facebook");
            }
            if (sns instanceof Twitter) {
                System.out.println("text ->" + "twitter");
            }
            if (sns instanceof Googleplus) {
                System.out.println("text ->" + "Googleplus");
            }
        }
    }

    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) {
                System.out.println("picture ->" + "facebook");
            }
            if (sns instanceof Twitter) {
                System.out.println("picture ->" + "twitter");
            }
            if (sns instanceof Googleplus) {
                System.out.println("picture ->" + "googlePlus");
            }
        }
    }
    
    interface SNS {}
    static class Facebook implements SNS {
    }
    static class Twitter implements SNS {
    }
    static class Googleplus implements SNS {
    }

    public static void main(String[] args) {
        List<Post> postList = Arrays.asList(new Text(), new Picture());
        List<SNS> snsList = Arrays.asList(new Facebook(), new Twitter(), new Googleplus());


        //runtime 시점에 post에 어떤 클래스가 실행될지 결정 다이나믹 디스패치
        postList.forEach(post -> snsList.forEach(sns -> post.postOn(sns)));

    }
}
