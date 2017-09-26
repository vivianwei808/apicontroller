package com.ww.web;

import com.ww.entity.ApiRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangwei on 2017/8/9.
 */
@Component
@Slf4j
public abstract class ApiHandler implements ApplicationContextAware{

    private ApplicationContext ctx ;

    @Autowired
    private ApiRegister apiRegister;

    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        String rootPath = req.getContextPath();
        String reqUrl = req.getRequestURI().substring(req.getRequestURI().indexOf(rootPath));
        ApiRunnable apiRunnable = apiRegister.getRegisterMap().get(reqUrl);
        if (StringUtils.hasLength(rootPath)){
            return;
        } if(null == apiRunnable){
            log.error(String.format("url{}未找到处理的controller",reqUrl));
            return;
        }
        Object controllerCls = ctx.getBean(apiRunnable.getControllerClass());
        Method method = apiRunnable.getMethod();
        try {
            //TODO 方法上加上参数 参见 InterfaceExecutorServiceImpl 接口平台
            Object obj = method.invoke(controllerCls);
            try {
                handlerResult(obj,resp);
            } catch (IOException e) {
                log.error("解析方法返回类型失败！");
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理方法执行返回的结果
     * @param obj
     * @param resp
     */
    protected abstract void handlerResult(Object obj, HttpServletResponse resp) throws IOException;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
