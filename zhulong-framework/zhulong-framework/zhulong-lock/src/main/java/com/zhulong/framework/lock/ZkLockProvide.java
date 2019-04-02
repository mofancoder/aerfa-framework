package com.zhulong.framework.lock;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 使用curator提供的基于zk的分布式锁的实现。
 * 当前需要手动进行释放，否则会一直占用资源。希望确保在代码中忘记了调用释放方法，在线程结束后，能自动释放。
 * Created by shanb on 2019-1-18.
 */
public class ZkLockProvide implements LockProvider {

    @NonNull
    private String zkAddress;

    @NonNull
    private String namespace;

    private static CuratorFramework curatorFramework;

    public ZkLockProvide(String zkAddress,String namespace){
        this.zkAddress = zkAddress;
        this.namespace = namespace;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000,3);
        curatorFramework = CuratorFrameworkFactory.builder().connectString(zkAddress)
                .retryPolicy(retryPolicy).namespace(namespace).build();
        curatorFramework.start();
    }


    @Override
    public Optional<LockHold> lock(String key, long timeout) {
        InterProcessLock lock = new InterProcessMutex(curatorFramework,"/"+key);
        try {
            boolean acquired = lock.acquire(timeout, TimeUnit.MILLISECONDS);
            if(acquired){
                return Optional.of(new LockHold(lock));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("get lock failure",e);
        }
    }

    @Override
    public void releasLock(LockHold lockHold) {
        InterProcessLock lock = (InterProcessLock) lockHold.getRealLock();
        try {
            lock.release();
        } catch (Exception e) {
            throw new RuntimeException("release lock failure",e);
        }
    }
}