package com.example.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadA extends Thread {
    CountDownLatch countDownLatch;

    public ThreadA(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ThreadA 开始执行");
            Thread.sleep(1000);
            System.out.println("ThreadA 执行完毕");
            countDownLatch.countDown();
        }catch (Exception e){}

    }
}
