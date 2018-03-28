package com.logistics.express.wechat.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;

import net.sf.json.JSONObject;



import com.logistics.express.entity.AccessToken;
import com.logistics.express.wechat.util.WeChatUtil;


public class WeiXinTest {
	
	public static void main(String[]args) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, DocumentException{
		System.out.println(">>>>>>>>>>");
		AccessToken token=WeChatUtil.getAcessToken();
		System.out.println("票据"+token.getToken());
		System.out.println("有效时间"+token.getExpiresIn());
		
		String menu=JSONObject.fromObject(WeChatUtil.initMenu()).toString();
		int result=1;
		result=WeChatUtil.createMenu(token.getToken(), menu);
		if(result==0){
			System.out.println("success");
		}else{
			System.out.println("fail:"+result);
		}
	
	}
	
	

}
