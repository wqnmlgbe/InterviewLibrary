package com.example.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * AUTHOR：lanchuanke on 17/10/17 17:54
 */
public class ThreadTest {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
        ThreadA threadA=new ThreadA(cyclicBarrier);
        ThreadB threadB=new ThreadB(cyclicBarrier);
        ThreadC threadC=new ThreadC(cyclicBarrier);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
