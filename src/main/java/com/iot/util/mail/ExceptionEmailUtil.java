package com.iot.util.mail;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import com.iot.util.StringHelper;
/**
 * 异常邮件发送工具类
 * @author asus
 *
 */
public class ExceptionEmailUtil {
    private static final String defaultPlatFormReceiver="yangning@linksfield.net";//管理平台默认邮件接收人
    private static final String defaultOperatorReceiver="panglinjuan@linksfield.net";//运营商支持默认邮件接收人
    /**
     * 异常邮件发送
     * @param systemName 系统名称
     * @param interfaceName 接口名称
     * @param exceptionDesc 异常描述
     * @param receiver 邮件接收人(多个接收人时,邮箱间用","(英文)分割)
     * @param department 归属部门 1-管理平台部门 2-运营商支持部门 （当填写的邮件接收无效时，根据部门标识来发送给该部门直属上级）
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendExceptionMail(String systemName,String interfaceName,String exceptionDesc,String receiver,String department) throws AddressException, MessagingException{
        boolean valid=true;
        //创建一封邮件
        //用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        Properties properties = new Properties();
        // 创建信件服务器
        properties.put("mail.smtp.host", "smtp.exmail.qq.com");//主机host，跟邮件发送者必须一致
        properties.put("mail.smtp.auth", "true"); // 通过验证，也就是用户名和密码的验证，必须要有这一条　
        properties.put("mail.smtp.port", 465);//加密服务端口465
//      properties.put("mail.smtp.ssl.enable", true);

        // 发送邮件协议名称
        properties.setProperty("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.ssl.enable", "true");//加密
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                //登陆邮箱，密码
                return new PasswordAuthentication("shengbin@linksfield.net","Links@2018");
            }
        });
        session.setDebug(true);
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //邮件几个必须的：发件人，收件人，邮件主题，邮件内容
        //1、from :发件人
        //其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress("shengbin@linksfield.net"));
        //2、TO :收件人
        //MimeMessage.RecipientType.TO  直接发送人
        //MimeMessage.RecipientType.CC  抄送人（可选）
        //MimeMessage.RecipientType.BCC  秘密发送人（可选）
        //验证收件人是否存在（不存在，默认发送给领导）
        if(!StringHelper.isEmpty(receiver)){
            String[] receivers = receiver.split(",");
            for(String receive:receivers){
                valid = valid(receive,"smtp.exmail.qq.com");
            }
            System.out.println("验证结果:"+valid);
            if(!valid){
                if("1".equals(department)){//归属管理平台
                    receiver=defaultPlatFormReceiver;
                }else if("2".equals(department)){
                    receiver=defaultOperatorReceiver;
                }
            }
        }
        InternetAddress[] internetAddressTo = new InternetAddress().parse(receiver);
        message.setRecipients(MimeMessage.RecipientType.TO, internetAddressTo);
        //3、Suject :邮件主题
        message.setSubject("系统"+systemName+"中接口"+interfaceName+"运行异常","UTF-8");
        //邮件内容
        ///邮件的内容
        //4、Content :邮件正文（可以使用html标签）
        message.setContent(exceptionDesc, "text/html;charset=UTF-8");
        //5、设置显示的发件时间
        message.setSentDate(new Date());
        //6、保存前面设置的
        message.saveChanges();
        //7、发送
        Transport.send(message);
        System.out.println("邮件已经发送完毕");
    }
    /**
     * 验证邮箱是否存在
     * <br>
     * 由于要读取IO，会造成线程阻塞
     *
     * @param toMail
     *         要验证的邮箱
     * @param domain
     *         发出验证请求的域名(是当前站点的域名，可以任意指定)
     * @return
     *         邮箱是否可达
     */
    public static boolean valid(String toMail, String domain) {
        if(StringUtils.isBlank(toMail) || StringUtils.isBlank(domain)) return false;
        if(!StringUtils.contains(toMail, '@')) return false;
        String host = toMail.substring(toMail.indexOf('@') + 1);
        if(host.equals(domain)) return false;
        Socket socket = new Socket();
        try {
            // 查找mx记录
            Record[] mxRecords = new Lookup(host, Type.MX).run();
            if(ArrayUtils.isEmpty(mxRecords)) return false;
            // 邮件服务器地址
            String mxHost = ((MXRecord)mxRecords[0]).getTarget().toString();
            if(mxRecords.length > 1) { // 优先级排序
                List<Record> arrRecords = new ArrayList<Record>();
                Collections.addAll(arrRecords, mxRecords);
                Collections.sort(arrRecords, new Comparator<Record>() {

                    public int compare(Record o1, Record o2) {
                        return new CompareToBuilder().append(((MXRecord)o1).getPriority(), ((MXRecord)o2).getPriority()).toComparison();
                    }

                });
                mxHost = ((MXRecord)arrRecords.get(0)).getTarget().toString();
            }
            // 开始smtp
            socket.connect(new InetSocketAddress(mxHost, 25));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 超时时间(毫秒)
            long timeout = 6000;
            // 睡眠时间片段(50毫秒)
            int sleepSect = 50;

            // 连接(服务器是否就绪)
            if(getResponseCode(timeout, sleepSect, bufferedReader) != 220) {
                return false;
            }

            // 握手
            bufferedWriter.write("HELO " + domain + "\r\n");
            bufferedWriter.flush();
            if(getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 身份
            bufferedWriter.write("MAIL FROM: <check@" + domain + ">\r\n");
            bufferedWriter.flush();
            if(getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 验证
            bufferedWriter.write("RCPT TO: <" + toMail + ">\r\n");
            bufferedWriter.flush();
            if(getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 断开
            bufferedWriter.write("QUIT\r\n");
            bufferedWriter.flush();
            return true;
        } catch (NumberFormatException e) {
        } catch (TextParseException e) {
        } catch (IOException e) {
        } catch (InterruptedException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        return false;
    }

    private static int getResponseCode(long timeout, int sleepSect, BufferedReader bufferedReader) throws InterruptedException, NumberFormatException, IOException {
        int code = 0;
        for(long i = sleepSect; i < timeout; i += sleepSect) {
            Thread.sleep(sleepSect);
            if(bufferedReader.ready()) {
                String outline = bufferedReader.readLine();
                // FIXME 读完……
                while(bufferedReader.ready())
                    /*System.out.println(*/bufferedReader.readLine()/*)*/;
                /*System.out.println(outline);*/
                code = Integer.parseInt(outline.substring(0, 3));
                break;
            }
        }
        return code;
    }
}
