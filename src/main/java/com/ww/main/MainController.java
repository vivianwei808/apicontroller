package com.ww.main;

import com.ww.annotation.APIController;
import com.ww.annotation.APIRequestMapping;
import com.ww.entity.Response;
import com.ww.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * todo 扫描包 读取配置文件
 * Created by wangwei on 2017/8/9.
 */
@APIController
@APIRequestMapping("/main")
@Configuration
public class MainController {

    @Autowired
    private DemoService demoService;

    @APIRequestMapping("/test1")
    public String test1() {
        String retStr = "简单测试1.........";
        return retStr;
    }

    @APIRequestMapping("/test2")
    public String test2(String str) {
        String retStr = "测试2.........这是一个有参数的测试，传入的参数："+str;
        return retStr;
    }

    @APIRequestMapping("/test3")
    public String test3() {
        String retStr = demoService.test();
        return retStr;
    }

    @APIRequestMapping("/test4")
    public Response test4() {
        Response response = new Response();
        response.setAge(11);
        response.setName("xiaohong");
        return response;
    }

    @APIRequestMapping({"/test5","/test6"})
    public Response test5() {
        Response response = new Response();
        response.setAge(555555555);
        response.setName("test555555555555");
        return response;
    }


}
