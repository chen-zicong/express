package com.logistics.express.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.logistics.express.entity.News;
import com.logistics.express.entity.NewsMessage;
import com.logistics.express.entity.TextMessage;
import com.logistics.express.service.WeChatService;
import com.thoughtworks.xstream.XStream;


@Service("wechatService")
public class WeChatServiceImpl implements WeChatService{
	
	@Override
	public boolean checkSignature(String signature,String timestamp,String nonce,String token){
		String[] arr=new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//sha1解码
		String temp = getSha1(content.toString());
		return temp.equals(signature);
		
	}
	public static String getSha1(String str){
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));
	         
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	
       //text 转为 xml
	@Override
	   public String textMessageToXml(TextMessage textMessage){
			XStream xstream=new XStream();
			xstream.alias("xml",textMessage.getClass());
			return xstream.toXML(textMessage);
		}
	
	
		//news 转为 xml	
	@Override
		public String NewsMessageToXml(NewsMessage newsMessage){
			XStream xstream=new XStream();
			xstream.alias("xml",newsMessage.getClass());
			xstream.alias("item",new News().getClass());
			return xstream.toXML(newsMessage);
		}
	
	// xml to map
	@Override
	 	public Map<String,String> xmlToMap(HttpServletRequest request)throws IOException,DocumentException{
	 			Map<String,String> map=new HashMap<String, String>();
	 			SAXReader reader=new SAXReader();
	 			
	 			InputStream ins=request.getInputStream();
	 			Document doc=reader.read(ins);
	 			Element root=doc.getRootElement();
	 			
	 			List<Element> list=root.elements();
	 			for(Element e:list){
	 				map.put(e.getName(),e.getText());
	 			}
	 			ins.close();
	 			return map;
	 		}
	
	

}
