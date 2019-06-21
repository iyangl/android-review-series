package com.lemayn.review;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: ly
 * date  : 2019/6/20 16:46
 * desc  :
 */
public class LoginCheckHandler implements InvocationHandler {

    private Object mSource;

    public static <S, T extends S> T proxy(S source, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(MainActivity.class.getClassLoader(), new Class[]{clazz}, new LoginCheckHandler(source));
    }

    private LoginCheckHandler(Object source) {
        this.mSource = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(mSource, args);
    }

}
