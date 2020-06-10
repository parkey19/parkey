package com.reactive.parkey;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;

public class PubSub {
    public static void main(String[] args) {
        Iterable<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Publisher publisher = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {

                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        Subscriber subscriber = new Subscriber<Integer>() {
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
            }

            @Override
            public void onNext(Integer item) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        publisher.subscribe(subscriber);

    }
}
