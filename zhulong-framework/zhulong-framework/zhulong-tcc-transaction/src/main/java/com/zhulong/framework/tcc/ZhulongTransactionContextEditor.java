package com.zhulong.framework.tcc;

import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.api.TransactionContextEditor;

import java.lang.reflect.Method;

/**
 * 通过TccContextStore进行获取和设置TransactionContext。
 * 远程调用时，tcc框架会设置到当前线程中TCC-CONTEXT-STORE，再通过远程调用框架（如feign）获取TCC-CONTEXT-STORE中的值，通过某种方式传递到远端。
 * 远端需要再通过某种技术获取到TCC-CONTEXT的值，重新知道到TCC-CONTEXT-STORE中。
 * 在远端，TCC框架，会通过get方法获取到TCC-CONTEXT值，用于进行操作
 * Created by shanb on 2019-1-25.
 */
public class ZhulongTransactionContextEditor implements TransactionContextEditor {

    @Override
    public TransactionContext get(Object target, Method method, Object[] args) {
        return TccContextStore.get();
    }

    @Override
    public void set(TransactionContext transactionContext, Object target, Method method, Object[] args) {
        TccContextStore.set(transactionContext);
    }
}