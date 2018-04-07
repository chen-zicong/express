package com.logistics.express.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessOrder;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.logistics.express.entity.MyX509TrustManager;
import com.thoughtworks.xstream.XStream;
import com.logistics.express.wechat.util.Button;
import com.logistics.express.wechat.util.ClickButton;
import com.logistics.express.wechat.util.Menu;
import com.logistics.express.wechat.util.ViewButton;
import com.logistics.express.entity.AccessToken;
import com.logistics.express.entity.News;
import com.logistics.express.entity.NewsMessage;
import com.logistics.express.entity.TextMessage;

public class WeChatUtil {

    private static final String APPID="wx6a6fb29fafbb8255";
      private static final String APPSECRET="3712865442aee5ae623a3b24223d0533";
  //  private static final String APPID = "wxdc9fecea689cd801";
  //  private static final String APPSECRET = "a1408ff1e5231e90ec60e3d9a3311106";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String CREAT_MENU_URL = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static final String POST_FOR_OPENID_ONE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=";
    private static final String POST_FOR_OPENID_TWO = "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    private static final String oldUrl = "http://www.zjhr1688.com";

    private static final String newUrl = "http://118.190.209.162:8080";
    //private static final String newUrl = "http://zx5a92.natappfree.cc";

    //发送请求
    public static HttpsURLConnection getLink(String url, String way) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        HttpsURLConnection http = null;
        try {
            URL urlget = new URL(url);
            http = (HttpsURLConnection) urlget.openConnection();
            //创建sslContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext ss = SSLContext.getInstance("SSL", "SunJSSE");
            ss.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = ss.getSocketFactory();
            //设置链接参数与要求
            http.setSSLSocketFactory(ssf);
            http.setRequestMethod(way);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//链接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");//读取超时
            http.connect();//链接
        } catch (MalformedURLException e) {
            System.out.println("链接公众平台失败");
            e.printStackTrace();
        }
        return http;
    }

    //自定义类型发送请求，返回json格式
    public JSONObject HttpsURLConnectionOfJson(String url, String way) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        InputStream is = null;
        JSONObject json = null;
        try {
            HttpsURLConnection http = getLink(url, way);
            is = http.getInputStream();
            int size = is.available();
            byte[] jsonSize = new byte[size];
            is.read(jsonSize);
            String js = new String(jsonSize, "UTF-8");
            json = (JSONObject) JSONObject.parse(js);
            is.close();
        } catch (IOException e1) {
            System.out.println("获取json失败");
            e1.printStackTrace();
        }
        return json;
    }

    //获取access_taken
    public static AccessToken getAccess_token() throws DocumentException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
 			/*String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
 					+ "&appid="+appid
 					+ "&secret="+AppSecret;*/
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        AccessToken access_taken = new AccessToken();
        InputStream is = null;
        JSONObject json = null;
        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            HttpsURLConnection http = getLink(url, "GET");
            is = http.getInputStream();
            int size = is.available();
            byte[] jsonSize = new byte[size];
            is.read(jsonSize);
            String js = new String(jsonSize, "UTF-8");
            json = (JSONObject) JSONObject.parse(js);
            System.out.println(js);
            access_taken.setToken(json.getString("access_token"));
            access_taken.setTimeString(format.format(date));
            //access_taken.setExpiresIn(json.getInteger("expires_in"));
            is.close();
        } catch (IOException e1) {
            System.out.println("获取access_taken失败");
            e1.printStackTrace();
        }
        return access_taken;
    }

    // xml to map
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();

        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

// 		 text 转为 xml

    public static String textMessageToXml(TextMessage textMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    //news 转为 xml
    public static String NewsMessageToXml(NewsMessage newsMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

    //创建菜单
    public static int createMenu(String token, String Menu) throws ClientProtocolException, IOException {
        int result = 100;
        String url = CREAT_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, Menu);
        if (jsonObject != null)
            result = jsonObject.getInteger("errcode");
        return result;
    }

    //发送post请求
    public static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        // HttpPost httpPost=new HttpPost(url);
        HttpPost httpPost = null;
        try {
            URL url1 = new URL(url);
            URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
            httpPost = new HttpPost(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject jsonObjet = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObjet = (JSONObject) JSONObject.parse(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return jsonObjet;

    }

    //组装菜单
    public static Menu initMenu() {
        Menu menu = new Menu();
        ViewButton button11 = new ViewButton();
        button11.setName("查询单号");
        button11.setType("view");
        button11.setUrl(newUrl + "/express/Base/goURL/home/weixin_requireies?identify=wechat".replace(oldUrl, newUrl));

        ViewButton button12 = new ViewButton();
        button12.setName("寄出物件");
        button12.setType("view");
        button12.setUrl(newUrl + "/express/Base/goURL/home/weixin_sendGoods?identify=wechat");

        ViewButton button13 = new ViewButton();
        button13.setName("司机注册");
        button13.setType("view");
        button13.setUrl(newUrl + "/express/Base/goURL/home/weixin_personalRegister?identify=wechat");
        //https://www.xwh511.cc/express/wechat
        ViewButton button32 = new ViewButton();
        button32.setName("发送位置");
        button32.setType("view");
        button32.setUrl(newUrl + "/express/Base/goURL/home/driverLocation?identify=wechat");

        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        button1.setName("查询单号");
        button2.setName("寄出物件");
        button3.setName("司机专用");
        button1.setSub_button(new Button[]{button11});
        button2.setSub_button(new Button[]{button12});
        button3.setSub_button(new Button[]{button13, button32});

        menu.setButton(new Button[]{button1, button2, button3});

        return menu;
    }

    public static AccessToken getAcessToken() {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        System.out.println(url);
        System.out.println(jsonObject.toString());
        if (jsonObject != null) {
            token.setToken(jsonObject.getString("access_token"));
            //token.setExpiresIn(jsonObject.getInt("expires_in"));
            System.out.println("token>>>>>>>>>>" + jsonObject.toString());
        }


        return token;
    }

    public static JSONObject doGetStr(String url) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObjet = null;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObjet = (JSONObject) JSONObject.parse(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*httpClient.close();*/
        return jsonObjet;
    }

    public static JSONArray doGetStrArray(String url) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONArray jsonObjet = null;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                System.out.println("结果：" + result);
                jsonObjet = JSONArray.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObjet;
    }


    //发送请求去获取个人信息
    public static JSONObject getPersonalInformaion(HttpServletRequest request) {
        //需要请求的链接String code;
        //在一键投简历的页面需要获取state，因为在连接中将设置参数，根据参数跳转到不同公司的介绍页面
        String state;
        String code;
        code = request.getParameter("code");
        state = request.getParameter("state");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>code :" + code);
        System.out.println(">>>>>>>>>>>>>>>>>>>>state" + state);
        System.out.println(">>>>>>>>>>>>>>>>>this is a test one");
        //获取openid 需要修改appid和appsecret

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        //生成一个获取的json的参数
        JSONObject json;
        json = doGetStr(url);
        return json;
    }

}
