package com.ww.web;

import com.google.gson.Gson;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * todo 如何做到组装各个类
 * Created by wangwei on 2017/8/23.
 */
@Component
public class CommonResultHandler extends ApiHandler{

    @Override
    protected void handlerResult(Object obj, HttpServletResponse resp) throws IOException {
        String valOut;
        if (obj instanceof String){
            valOut = (String)obj;
        }else {
            valOut = new Gson().toJson(obj);
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(valOut);
        printWriter.flush();
        printWriter.close();
    }
}
