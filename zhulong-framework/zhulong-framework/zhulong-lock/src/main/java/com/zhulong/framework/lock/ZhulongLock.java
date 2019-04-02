package com.zhulong.framework.lock;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * 提供分布式锁的机制，实现者为LockProvider。
 * Created by shanb on 2019-1-18.
 */
@RequiredArgsConstructor
public class ZhulongLock {

    /**
     * 锁实现的提供者，可注入不同的实现着，如zk、redis、consule等
     */
    @NonNull
    private LockProvider lockProvider;

    /**
     * 获取锁
     * @param key 需加锁的键
     * @param timeout 获取锁等待超时时间
     * @return 获取到锁，返回LockHold对象，未获取到，则Optional中值为空
     */
    public Optional<LockHold> getLock(String key, long timeout){
        Optional<LockHold> lockHold = lockProvider.lock(key,timeout);
        return lockHold;
    }

    /**
     * 根据锁的持有对象，释放锁
     * @param lockHold 锁的持有对象
     */
    public void releasLock(LockHold lockHold){
        lockProvider.releasLock(lockHold);
    }


}
