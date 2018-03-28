package com.logistics.express.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

import com.logistics.express.entity.NewsMessage;
import com.logistics.express.entity.TextMessage;

public interface WeChatService {
	
	
    //微信加密验证
	public boolean checkSignature(String signature,String timestamp,String nonce,String token);
	
	//text 转为 xml
	public String textMessageToXml(TextMessage textMessage);

	//news 转为 xml	
	public String NewsMessageToXml(NewsMessage newsMessage);
	
	// xml to map
	public Map<String,String> xmlToMap(HttpServletRequest request)throws IOException,DocumentException;
	
	
}
