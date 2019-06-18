package com.iot.controller;

import com.iot.otaBean.mo.BaseMo;
import com.iot.otaBean.mt.CascadePushCommandCMD;
import com.iot.otaBean.mt.MtData;
import com.iot.otaBean.mt.PlainDataMt;
import com.iot.service.interfaces.USSDPackService;
import com.iot.service.interfaces.USSDUnpackService;
import com.iot.util.JsonUtil;
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
import java.util.HashMap;
import java.util.Map;

@RestController
public class USSDController {
        private static final Log logger = LogFactory.getLog(com.iot.controller.USSDController.class);
        @Autowired
        protected HttpServletRequest request;
        @Autowired
        protected HttpServletResponse response;
        @Autowired
        protected USSDUnpackService ussdBusiServiceUnpack;
        @Autowired
        protected USSDPackService ussdBusiServicePack;

        @RequestMapping("/ussdMsgUnpack")
        @ResponseBody
        public BaseMo ussdMsgUnpack() {
            BaseMo baseMo = new BaseMo();
            try {
                String reqStr = getRequest();
                if ((null == reqStr) || ("".equals(reqStr))) {
                    logger.info("ussd请求报文为空！");
                    return null;
                }
                logger.info("ussd请求报文：" + reqStr);
                if (!checkProtocolVersionAndUSSDReq(reqStr)) {
                    logger.error("Protocol Version不匹配（协议版本需要6以上），退出！");
                    return null;
                }
                baseMo = ussdBusiServiceUnpack.ussdBusiServiceUnpack(reqStr);
            } catch (Exception e) {
                logger.error("解析ussd失败！", e);
                return null;
            }
            return baseMo;
        }
        @RequestMapping("/ussdMsgPack")
        @ResponseBody
        public String ussdMsgPack(){
            String resData = null;
            try {
                String reqStr = getRequest();
                if ((null == reqStr) || ("".equals(reqStr))) {
                    logger.info("ussd请求报文为空！");
                    return null;
                }
                MtData mtData = new MtData();
/*                cmd.setTag("1111");
                cmd.setValue("11156670");
                cmds2.add(cmd);
                cmdParamData.setOtaTradeNo("000000000000");
                cmdParamData.setCmds(cmds2);
                cmdParamData.setCallControl("1234");
                cmdParamData.setDataKeyIndex("2");
                cmdParamData.setImsi("123456789123");
                cmdParamData.setpIccid("78454123687456231454");
                cmdParamData.setOldIccid("56560154574650021");
                cmdParamData.setKeyData("74f84a49309d849b85395091488a6b395297209a9c651524326589");
//		CmdParamData cmdParamData = new CmdParamData();

                List<PlainDataMt> plainDatas = new ArrayList<>();
                PlainDataMt plainDataMt = new PlainDataMt();
                plainDataMt.setCmdLength("10");
                plainDataMt.setCmdParams(cmdParamData);
                plainDataMt.setCmdType("31");

                plainDatas.add(plainDataMt);


                mtData.setBatchNumber("0000");
                mtData.setBusiType("2");
                mtData.setCheckNum("3");
                mtData.setKeyIndex("2");
                mtData.setMac("1257adfc");
                mtData.setManuFlag("00077");

                mtData.setPlainDatas(plainDatas);
                //String reqStr = JsonUtil.getJSONString(mtData);
                reqStr = JsonUtil.getJSONString(mtData);*/
                logger.info("ussd请求报文：" + reqStr);
                Map<String,Class> map = new HashMap<String, Class>();
                map.put("plainDatas", PlainDataMt.class);
                map.put("cmds", CascadePushCommandCMD.class);
                mtData = (MtData) JsonUtil.getDTO(reqStr, MtData.class,map);
                resData = ussdBusiServicePack.ussdBusiServicePack(mtData);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resData;
        }
        /**
         * 检查协议版本
         * @return
         * @throws IOException
         */
        boolean checkProtocolVersionAndUSSDReq(String reqStr){

            return true;
        }

        /**
         * 获取请求报文
         * @return
         * @throws IOException
         */
        protected String getRequest() throws IOException {
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
