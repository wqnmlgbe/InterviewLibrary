package com.example.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadB extends Thread {
    CountDownLatch countDownLatch;

    public ThreadB(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ThreadB 开始执行");
            Thread.sleep(3000);
            System.out.println("ThreadB 执行完毕");
            countDownLatch.countDown();
        }catch (Exception e){}

    }
}
