package com.ww.web;

import com.ww.annotation.APIController;
import com.ww.annotation.APIRequestMapping;
import com.ww.entity.ApiRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO 泛型的改造
 * Created by wangwei on 2017/8/9.
 */
@Component
@Slf4j
public class ApiRegister implements InitializingBean, ApplicationContextAware {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<String, ApiRunnable> apiRegisterMap = new HashMap<String, ApiRunnable>();
    ApplicationContext applicationContext;

    /**
     * 初始化注解，把相应的内容放入apiStoreMap 页面跳转的url为key
     * 处理HandlerMapping
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        initHandlerMapping();
    }

    private void initHandlerMapping() {
        this.readWriteLock.writeLock().lock();
        try {
            Map<String, Object> maps = applicationContext.getBeansWithAnnotation(APIController.class);

            for (Object obj : maps.values()) {
                Class type = obj.getClass();
                String[] baseUrl = {};
                if (type.isAnnotationPresent(APIRequestMapping.class)) {
                    baseUrl = ((APIRequestMapping) type.getAnnotation(APIRequestMapping.class)).value();
                }
                this.combineMethodUrl(baseUrl, type);
            }
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    private void combineMethodUrl(String[] baseUrl, Class type) {
        for (String baseStr : baseUrl) {
            for (Method method : type.getDeclaredMethods()) {
                if (method.isAnnotationPresent(APIRequestMapping.class)) {
                    APIRequestMapping apiRequestMapping = method.getAnnotation(APIRequestMapping.class);
                    for (String methodUrl : apiRequestMapping.value()) {
                        String url = baseStr + methodUrl;
                        this.assertUniqueMethodMapping(url);
                        ApiRunnable apiRunnable = new ApiRunnable();
                        apiRunnable.setControllerClass(type);
                        apiRunnable.setMethod(method);
                        apiRegisterMap.put(url, apiRunnable);
                    }
                }
            }
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Map<String, ApiRunnable> getRegisterMap() {
        return Collections.unmodifiableMap(apiRegisterMap);
    }

    private void assertUniqueMethodMapping(String url) {
        ApiRunnable runnable = apiRegisterMap.get(url);
        if (runnable != null) {
            log.error("已经有重复的url：{}", url);
            throw new IllegalStateException("已经有重复的url：" + url);
        }
    }

}
