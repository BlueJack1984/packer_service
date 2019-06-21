package com.iot.controller;

import com.iot.service.interfaces.BipMsgHandleService;
import com.iot.util.RequestMessageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class BipController {
    private static final Log logger = LogFactory.getLog(BipController.class);
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected BipMsgHandleService bipMsgHandleService;
    @RequestMapping("/bipMsgHandle")
    @ResponseBody
    public String bipMsgHandle() {
        String retData = "";
        try{
            String reqStr = RequestMessageUtil.getMessage(request, response);
            if((null == reqStr) || ("".equals(reqStr))) {
                logger.info("bip请求报文为空！");
                return null;
            }
            logger.info("bip请求报文：" + reqStr);
            if(!checkProtocolVersionAndBipReq(reqStr)){
                logger.error("Protocol Version不匹配（协议版本需要6以上），退出！");
                return null;
            }
            retData = bipMsgHandleService.bipMsgHandleService(reqStr);
            logger.info("**************下行报文：" + retData);
            return retData;
        } catch (Exception e) {
            logger.error("接口调用异常！", e);
            return null;
        }
    }

    /**
     * 检查协议版本
     * @return
     * @throws IOException
     */
    boolean checkProtocolVersionAndBipReq(String reqStr){

        return true;
    }

    /**
     * 获取请求报文
     * @return
     * @throws IOException
     */
//    protected String getRequest() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
//        String line = null;
//        StringBuffer requestParams = new StringBuffer();
//        while ((line = br.readLine()) != null) {
//            requestParams.append(line);
//        }
//        if(requestParams.toString().trim().length()==0){
//            return null;
//        }
//        String paramStr=requestParams.toString();
//        logger.info("接收到的请求信息为:[" + paramStr.toString() + "]");
//        return paramStr;
//    }
}
