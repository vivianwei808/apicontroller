package com.ww.service;

import org.springframework.stereotype.Component;

/**
 * Created by wangwei on 2017/8/23.
 */
@Component
public class DemoService {

    public String test(){
        String retStr = "DemoService test()方法执行。。。。。。。";
        return retStr;
    }
}
