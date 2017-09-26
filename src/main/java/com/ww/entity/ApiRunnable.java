package com.ww.entity;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * apiStore里Map的value
 * Created by wangwei on 2017/8/9.
 */
@Data
public class ApiRunnable {

    /**
     * Class类
     */
    private Class controllerClass;

    /**
     * 对应的方法
     */
    private Method method;
}
