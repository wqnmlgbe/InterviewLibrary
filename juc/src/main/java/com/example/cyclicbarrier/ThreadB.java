package com.example.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:50
 */
public class ThreadB extends Thread {
    CyclicBarrier cyclicBarrier;

    public ThreadB(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ThreadB 开始执行");
            Thread.sleep(3000);
            cyclicBarrier.await();
            System.out.println("ThreadB 执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
