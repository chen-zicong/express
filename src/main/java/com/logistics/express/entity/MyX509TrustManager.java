package com.logistics.express.entity;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
 // https信任管理器
public class MyX509TrustManager implements X509TrustManager{

	@Override
	//检验客户端证书
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {		
	}

	@Override
	//检验服务器端证书
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	//返回受信任的x5090证书数组
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
