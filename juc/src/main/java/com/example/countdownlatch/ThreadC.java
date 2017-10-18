package com.example.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadC extends Thread {
    CountDownLatch countDownLatch;

    public ThreadC(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ThreadC 开始执行");
            Thread.sleep(5000);
            System.out.println("ThreadC 执行完毕");
            countDownLatch.countDown();
        }catch (Exception e){}

    }
}
