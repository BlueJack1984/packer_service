package com.iot.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 请求工具类
 * @author lushusheng
 * @date 2019-06-18
 */
public class RequestMessageUtil {

    private static final Log logger = LogFactory.getLog(RequestMessageUtil.class);
    /**
     * @description 获取请求报文
     * @param
     * @return  IOException
     */
    public static String getMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuffer requestParams = new StringBuffer();
        while ((line = br.readLine()) != null) {
            requestParams.append(line);
        }
        if(requestParams.toString().trim().length()==0){
            return null;
        }
        String paramStr=requestParams.toString();
        logger.info("接收到的请求信息为:[" + paramStr.toString() + "]");
        return paramStr;
    }

}

