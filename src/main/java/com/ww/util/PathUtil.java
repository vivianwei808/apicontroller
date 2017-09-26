package com.ww.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.net.URI;
import java.net.URL;

/**
 * Created by wangwei on 2017/8/9.
 */
public abstract class PathUtil {

    public static String getClassRootPath(){
        URL url = PathUtil.class.getClassLoader().getResource("");// /D:/vivi/workspace/apicontroller/target/classes/
        String path = url.getPath();
        System.out.println(path);
        return path;
    }

    public static void main(String[] args) {
        PathUtil.getClassRootPath();
    }
}
