package com.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AUTHOR：lanchuanke on 17/10/18 15:53
 */
public class Depot {
    private static final int MAX_SIZE=100;
    private volatile int count;
    Lock lock;
    Condition emptyCondition;
    Condition fullCondition;
    private Depot(){
        lock=new ReentrantLock();
        emptyCondition=lock.newCondition();
        fullCondition=lock.newCondition();
    }

    public static final Depot newInstance(){
        return new Depot();
    }


    public void put(int num){
        lock.lock();
        try {
            while (num>0){
                if (count>=MAX_SIZE){
                    System.out.println("\n \n仓库放不下了，等着吧 \n \n");
                    fullCondition.await();
                }
                count++;
                num--;
                System.out.println(Thread.currentThread().getName()+"进货了1个");
                emptyCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void take(int num){
        lock.lock();
        try {
            while (num>0){
                if (count<=0){
                    System.out.println("\n \n仓库空了，等着吧 \n  \n");
                    emptyCondition.await();
                }
                count--;
                num--;
                System.out.println(Thread.currentThread().getName()+"取走了1个");
                fullCondition.signal();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }




}
