package com.iot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串帮助类
 * @author lipei
 *
 */
public class StringHelper {

    /**
     * 判断是否为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        if(s==null||"".equals(s.trim())){
            return true;
        }
        return false;
    }
    /***
     * 根据指定的正则表达式替换字符串内容
     * @param content
     * @param pattern
     * @return
     */
    public static String replaceAll(String content,String pattern,String targetStr){
        if(content==null||pattern==null){
            return content;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        content = m.replaceAll(targetStr);
        return content;
    }

}
