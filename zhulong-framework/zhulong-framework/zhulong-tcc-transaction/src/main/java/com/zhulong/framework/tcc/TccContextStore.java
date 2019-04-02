package com.zhulong.framework.tcc;

import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 线程的方式存放TCC的事务上下文。用于框架获取上下文进行传递
 * 注意，因为是使用线程绑定的，需要在同一线程中进行设置和获取。
 * Created by shanb on 2019-1-25.
 */
public class TccContextStore {

    private static final ThreadLocal<TransactionContext> contextLocal = new ThreadLocal<>();

    public static TransactionContext get(){
        return contextLocal.get();
    }

    public static void set(TransactionContext context){
        contextLocal.set(context);
    }

    public static void remove(){
        contextLocal.remove();
    }
}