package com.iot.service.serviceImpl;

import com.iot.otaBean.mo.BaseMo;
import com.iot.service.interfaces.IOrderService;
import com.iot.service.interfaces.USSDPackService;
import com.iot.service.interfaces.USSDUnpackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description 订单业务扩展
 * @author lushusheng
 * @date 2019-06-18
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final USSDUnpackService ussdUnpackService;
    private final USSDPackService ussdBusiServicePack;
    /**
     * @description
     * @param requestMessage
     * @return
     */
    @Override
    public String handle(String requestMessage) {
        String sms = "";
        //解析request请求携带的信息requestMessage
        BaseMo baseMo = ussdUnpackService.ussdBusiServiceUnpack(requestMessage);

        return sms;
    }
}
