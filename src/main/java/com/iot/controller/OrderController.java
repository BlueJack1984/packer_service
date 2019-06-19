package com.iot.controller;

import com.iot.service.interfaces.IOrderService;
import com.iot.util.RequestMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单业务
 * @author lushusheng
 * @date 2019-06-18
 * @description
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    //private static final Log logger = LogFactory.getLog(BusinessController.class);
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final IOrderService orderService;

    @PostMapping("/handle")
    public String orderHandle() {

        String requestMessage = "";
        try {
            //首先接收终端设备的位置更新信息LU（Location Update）
            requestMessage = RequestMessageUtil.getMessage(request, response);
            if((null == requestMessage) || ("".equals(requestMessage))) {
                log.info("bip请求报文为空！");
                return null;
            }
            log.info("bip请求报文：" + requestMessage);
            //检查通信协议版本号应该在6以上
            if(!checkProtocolVersionAndBipReq(requestMessage)){
                log.error("Protocol Version不匹配（协议版本需要6以上），退出！");
                return null;
            }
            String retData = orderService.handle(requestMessage);
            return retData;
        }catch (Exception ex) {
            log.error("接口调用异常！", ex);
            return null;
        }
    }

    /**
     * @description 检查协议版本
     * @return 返回协议版本为6及以上的布尔值true，反之返回false
     * @throws IOException
     */
    private boolean checkProtocolVersionAndBipReq(String requestMessage)throws Exception{
        //这里根据请求信息判断协议版本号是否为6及以上
        return true;
    }
}
