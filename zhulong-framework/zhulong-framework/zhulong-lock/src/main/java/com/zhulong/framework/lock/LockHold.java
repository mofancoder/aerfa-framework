package com.zhulong.framework.lock;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by shanb on 2019-1-21.
 */
@RequiredArgsConstructor
@Getter
public class LockHold {

    @NonNull
    private Object realLock;
}