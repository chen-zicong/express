package com.logistics.express.unity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class GaodeUtil {
    private static String GAODE_KEY = "3d1a867fb9f6d03686f98f5d81b24ef0";


    /**
     * 输入地址 返回地址的经纬度
     */

    public static String getLonLat(String address) {
        //返回输入地址address的经纬度信息, 格式是 经度,纬度
        String queryUrl = "http://restapi.amap.com/v3/geocode/geo?key=" + GAODE_KEY + "&address=" + address;
        String queryResult = null;  //高德接品返回的是JSON格式的字符串
        try {
            queryResult = httpGet(queryUrl, "UTF-8");
        } catch (HttpException | IOException e) {
            e.printStackTrace();
        }

        JSONObject jo = JSONObject.fromObject(queryResult);

        JSONArray ja = jo.getJSONArray("geocodes");
        return ja.getJSONObject(0).get("location").toString();
    }

    /**
     * 输入两个地址的经纬度, 然后返回两地之间的距离
     */
    public static Long getDistance(String startLonLat, String endLonLat) {
        Long result = 0L;
        String queryUrl = "http://restapi.amap.com/v3/distance?key=" + GAODE_KEY + "&origins=" + startLonLat + "&destination=" + endLonLat;
        String response = getResponse(queryUrl);
        JSONObject jo = JSONObject.fromObject(response);
        JSONArray ja = jo.getJSONArray("results");

        result = Long.parseLong(ja.getJSONObject(0).get("distance").toString());
        return result;
    }

    public static String httpGet(String url, String charset)
            throws HttpException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String json = null;
        HttpGet httpGet = new HttpGet();
        // 设置参数
        try {

            httpGet.setURI(new URI(url));
        } catch (URISyntaxException e) {
            throw new HttpException("请求url格式错误。" + e.getMessage());
        }
        // 发送请求
        HttpResponse httpResponse = httpClient.execute(httpGet);
        // 获取返回的数据
        HttpEntity entity = httpResponse.getEntity();
        byte[] body = EntityUtils.toByteArray(entity);
        StatusLine sL = httpResponse.getStatusLine();
        int statusCode = sL.getStatusCode();
        if (statusCode == 200) {
            json = new String(body, charset);
            entity.consumeContent();
        } else {
            throw new HttpException("statusCode=" + statusCode);
        }
        return json;
    }

    private static String getResponse(String serverUrl) {

        //用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);

            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    public  static void  main(String  [] args) {
         String start = "上海";
        String lonLat = GaodeUtil.getLonLat(start);
        System.out.println("------------"+lonLat+"-----------------------------------------------------------");

    }
}