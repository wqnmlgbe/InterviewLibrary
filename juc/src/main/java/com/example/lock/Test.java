package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AUTHOR：lanchuanke on 17/10/18 16:02
 */
public class Test {
    private static Depot depot=Depot.newInstance();
    private static ExecutorService executorService= Executors.newFixedThreadPool(100);
    public static void main(String[] args){
        Producer producer=new Producer();
        Consumer consumer=new Consumer();
        producer.produce(11);
        consumer.consume(5);
        producer.produce(3);
        consumer.consume(8);
    }

    public static class Producer {
        public void produce(final int num){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    depot.put(num);
                }
            });

        }
    }

    public static class Consumer{
       public void consume(final int num){
           executorService.submit(new Runnable() {
               @Override
               public void run() {
                   depot.take(num);
               }
           });

       }
    }
}
