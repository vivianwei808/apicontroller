package com.ww.web;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangwei on 2017/8/9.
 */
public class DispacherServlet extends HttpServlet {

    ApplicationContext applicationContext;

    private CommonResultHandler apiHandler;

    /**
     * 初始化，方法1 ：得到spring扫描包得到的所有bean
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        apiHandler = applicationContext.getBean(CommonResultHandler.class);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiHandler.handle(req,resp);
    }

}
