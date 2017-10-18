package com.example.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadD extends Thread {
    CountDownLatch countDownLatch;

    public ThreadD(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            countDownLatch.await();
            System.out.println("ThreadD 开始执行");
            Thread.sleep(1000);
            System.out.println("ThreadD 执行完毕");
            countDownLatch.countDown();
        }catch (Exception e){}

    }
}
