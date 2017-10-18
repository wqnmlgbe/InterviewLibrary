package com.example.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:54
 */
public class ThreadTest {
    public static void main(String[] args){
        CountDownLatch countDownLatch=new CountDownLatch(3);
        ThreadA threadA=new ThreadA(countDownLatch);
        ThreadB threadB=new ThreadB(countDownLatch);
        ThreadC threadC=new ThreadC(countDownLatch);
        ThreadD threadD=new ThreadD(countDownLatch);
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
