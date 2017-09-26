package com.ww.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by wangwei on 2017/8/23.
 */
public class UTF8ResponseWrapper extends HttpServletResponseWrapper {

    public UTF8ResponseWrapper(HttpServletResponse response) {
        super(response);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }
}
