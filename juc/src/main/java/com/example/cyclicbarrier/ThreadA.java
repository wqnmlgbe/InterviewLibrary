package com.example.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadA extends Thread {
    CyclicBarrier cyclicBarrier;

    public ThreadA(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ThreadA 开始执行");
            Thread.sleep(1000);
            cyclicBarrier.await();
            System.out.println("ThreadA 执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
