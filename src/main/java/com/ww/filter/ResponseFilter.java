package com.ww.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangwei on 2017/8/23.
 */
public class ResponseFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, new UTF8ResponseWrapper((HttpServletResponse)resp));
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
