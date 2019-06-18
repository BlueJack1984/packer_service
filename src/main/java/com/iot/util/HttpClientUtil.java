package com.iot.util;

//import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
//import sun.net.www.http.HttpClient;

public class HttpClientUtil {

    @SuppressWarnings("resource")
    public static String doGet(String url,String charset,String authorization){
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            if(authorization != null){
                httpGet.addHeader("Authorization", "Basic " + authorization);
            }
            httpGet.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpGet);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("resource")
    public static String doPost(String url,String requestStr,String charset,String authorization){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();

            httpPost = new HttpPost(url);
            if(authorization != null){
                httpPost.addHeader("Authorization", "Basic " + authorization);
            }
            httpPost.addHeader("Content-Type", "application/json");

            StringEntity se = new StringEntity(requestStr,charset);
            se.setContentType("application/json");
            httpPost.setEntity(se);

            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("resource")
    public static String getToken(String url,String username ,String password,String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        StringBuilder authUserStr = new StringBuilder();
        authUserStr.append("username=");
        authUserStr.append(username);
        authUserStr.append("&password=");
        authUserStr.append(password);
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            StringEntity se = new StringEntity(authUserStr.toString());
            se.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(se);

            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
