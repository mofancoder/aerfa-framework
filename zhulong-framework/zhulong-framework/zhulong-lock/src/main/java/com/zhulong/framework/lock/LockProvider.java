package com.zhulong.framework.lock;

import java.util.Optional;

/**
 * Created by shanb on 2019-1-18.
 * 分布式锁的实现的提供者，可采用redis、zk等
 */
public interface LockProvider {

    /**
     * 提供加锁功能
     */
    Optional<LockHold> lock(String key, long timeout);

    /**
     * 提供释放锁的功能
     */
    void releasLock(LockHold lockHold);
}