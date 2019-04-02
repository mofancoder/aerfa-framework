package com.zhulong.framework.lock;

import java.util.Optional;

/**
 * Created by shanb on 2019-1-22.
 */
public class ZhuLongLockTest {

    public static void main(String[] args) {
        ZkLockProvide zkLockProvide = new ZkLockProvide("10.30.30.60","zhulong-lock");
        ZhulongLock zhulongLock = new ZhulongLock(zkLockProvide);
        Runnable lockThread = ()->{
            System.out.println("enter "+Thread.currentThread().getName());
            Optional<LockHold> lockHold = zhulongLock.getLock("shanb",10*1000L);
            if(lockHold.isPresent()){
                //执行业务操作
                try {
                    //线程休息1s
                    System.out.println(Thread.currentThread().getName()+":sleep 1000ms");
                    Thread.sleep(10*1000L);
                    System.out.println(Thread.currentThread().getName()+":wake up");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    zhulongLock.releasLock(lockHold.get());
                }
            }else{
                System.out.println(Thread.currentThread().getName()+" get lock failure");
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(lockThread).start();
        }
        try {
            Thread.sleep(100*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}