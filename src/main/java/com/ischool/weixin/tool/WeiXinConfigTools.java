package com.ischool.weixin.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class WeiXinConfigTools {
	private final Logger logger = LoggerFactory.getLogger(WeiXinConfigTools.class);
	private static WeiXinConfigTools instance;
	private String conf = "classPath:wechat.properties";
	private Properties p = null;
	private String token = null;
	private String appId = null;
	private String appsecret = null;

	private String accessToken = null;
	private long accessTokenFailTime = 0;

	private String jsapiTicket = null;
	private long jsapiTicketFailTime = 0;
	private String jsapiTicketSignature = null;

	private String signature = null;
	private String timestamp = null;
	private String nonce = null;
	private String echostr = null;

	private WeiXinConfigTools() {
		init();
	}

	public static WeiXinConfigTools getInstance() {
		if (instance == null) {
			instance = new WeiXinConfigTools();
		}
		return instance;
	}

	private void init() {
		String classPath = getClass().getResource("/").getPath().replaceAll("%20", " ");
		this.conf = this.conf.replace("classPath:", classPath);
		this.p = new Properties();
		File pfile = new File(this.conf);
		if (pfile.exists()) {
			try {
				this.p.load(new FileInputStream(pfile));
				this.token = this.p.getProperty("Token");
				this.appId = this.p.getProperty("appID");
				this.appsecret = this.p.getProperty("appsecret");
			} catch (FileNotFoundException e) {
				this.logger.error("未找到wechat.properties", e);
			} catch (IOException e) {
				this.logger.error("wechat.properties读取异常", e);
			}
		}
	}

	public String getToken() {
		return token;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public String getSignature() {
		return signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	@SuppressWarnings("unchecked")
	public String getAccessToken() {
		if (StringUtils.isBlank(accessToken) || System.currentTimeMillis() >= accessTokenFailTime - (10 * 1000)) {
			try {
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+getAppId()+"&secret="
						+ getAppsecret();
				String result = client(url, null);
				Map<String, Object> data = new Gson().fromJson(result, Map.class);

				if (data.containsKey("errcode")) {
					int errcode = MapUtils.getIntValue(data, "errcode");
				}
				int expiresIn = MapUtils.getInteger(data, "expires_in");
				accessTokenFailTime = System.currentTimeMillis() + (expiresIn * 1000);
				accessToken = MapUtils.getString(data, "access_token");
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return accessToken;
	}

	/**
	 * oauth2获取AccessToken及openid
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getAccessToken(String code) {

			try {
				String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+getAppId()+"&secret="
						+ getAppsecret()+"&code="+code+"&grant_type=authorization_code";
				String result = client(url, null);
				Map<String, String> data = new Gson().fromJson(result, Map.class);

//				if (data.containsKey("errcode")) {
//					int errcode = MapUtils.getIntValue(data, "errcode");
//				}
				/*
				int expiresIn = MapUtils.getInteger(data, "expires_in");
				accessTokenFailTime = System.currentTimeMillis() + (expiresIn * 1000);
				accessToken = MapUtils.getString(data, "access_token");
				*/
				return data;
			}catch (Exception e) {
				throw new RuntimeException(e);
			} 
		
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public String getJsapiTicket() {
		if (StringUtils.isBlank(jsapiTicket) || System.currentTimeMillis() >= jsapiTicketFailTime - (10 * 1000)) {
			try {
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi";
				String result = client(url, null);
				Map<String, Object> data = new Gson().fromJson(result, Map.class);
				if (data.containsKey("errcode")) {
					int errcode = MapUtils.getIntValue(data, "errcode");
				}

				int expiresIn = MapUtils.getInteger(data, "expires_in");
				jsapiTicketFailTime = System.currentTimeMillis() + (expiresIn * 1000);
				jsapiTicket = MapUtils.getString(data, "ticket");

			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return jsapiTicket;
	}

	public String getJsapiTicketSignatureUrl(String url, String noncestr, String timestamp) {
		String jsapiTicket = this.getJsapiTicket();
		List<String> params = new ArrayList<String>();
		params.add("noncestr=" + noncestr);
		params.add("jsapi_ticket=" + jsapiTicket);
		params.add("timestamp=" + timestamp);
		params.add("url=" + url);
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return SHA1.encode(StringUtils.join(params, "&"));
	}

	@SuppressWarnings("unchecked")
	public String getOpenId(String code) {
		String result = null;
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token");
		url.append("?appid=");
		url.append(WeiXinConfigTools.getInstance().getAppId());
		url.append("&secret=");
		url.append(WeiXinConfigTools.getInstance().getAppsecret());
		url.append("&response_type=code");
		url.append("&code=");
		url.append(code);
		url.append("&grant_type=authorization_code");
		try {
			result = client(url.toString(), null);
		} catch (HttpException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (result != null) {
			Map<String, String> resultMap = new Gson().fromJson(result, Map.class);
			return resultMap.get("openid");
		}
		return "";
	}

	/**
	 * 获取授权的链接(静默授权)
	 * 
	 * @date 2014年12月17日 上午11:45:55
	 * 
	 */
	public String getOAuth2Url(String redirectUrl) {
		StringBuffer oatuhUrl = new StringBuffer();
		//https://open.weixin.qq.com/connect/oauth2/authorize
		//?appid=APPID&redirect_uri=REDIRECT_URI&
		//response_type=code&scope=SCOPE&state=STATE#wechat_redirect
		oatuhUrl.append("https://open.weixin.qq.com/connect/oauth2/authorize");
		oatuhUrl.append("?appid=");
		oatuhUrl.append(getAppId());
		oatuhUrl.append("&redirect_uri=");
		oatuhUrl.append(redirectUrl);
		oatuhUrl.append("&response_type=code");
		oatuhUrl.append("&scope=snsapi_base");
		oatuhUrl.append("&state=STATE");
		oatuhUrl.append("#wechat_redirect");
		return oatuhUrl.toString();
	}

	public String client(String url, Map<String, Object> params) throws HttpException, IOException {
		String result = null;
		HttpClient client = new HttpClient();
		HttpMethod getMethod = new GetMethod(url.toString());
		try {
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			
			result = getMethod.getResponseBodyAsString();
			getMethod.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	/**
	 * 根据用户授权code 查询用户基本信息
	 * @param access_token
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
/*
 *  
 * 	public Map<String, String> getUserinfo(String code){
		try {
			Map<String, String> accessTokenMap = getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			String access_token=  MapUtils.getString(accessTokenMap, "access_token");
			String client = client("https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid,null);
			Map<String,String> parseObject = JSON.parseObject(client, Map.class);
			return parseObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		
	}*/
	public Map<String, String> getUserinfo(String access_token,String openid){
		try {
			String client = client("https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid,null);
			
			
			/*Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(client);
			*/
			
			Map<String,String> parseObject = JSON.parseObject(client, Map.class);
			/*System.out.println(client);
			client = client.replaceAll("\\\\","\\");
			System.out.println(client);
			Map<String, String> fromJson = gson.fromJson(client, new TypeToken<Map<String, String>>() {}.getType());*/
			return parseObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		
	}

	public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = (String) params.get(0) + (String) params.get(1) + (String) params.get(2);
		boolean result = SHA1.encode(temp).equals(signature);
		if (result) {
			this.signature = signature;
			this.timestamp = timestamp;
			this.nonce = nonce;
			this.echostr = echostr;
		}
		return result;
	}

	public File mediaDownload(String mediaId) throws Exception {
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + this.getAccessToken() + "&media_id=" + mediaId;
		HttpClient client = new HttpClient();
		HttpMethod getMethod = new GetMethod(url.toString());
		try {
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			Header contentTypeHeader = getMethod.getResponseHeader("Content-Type");
			if (contentTypeHeader != null) {
				if ("application/json".equals(contentTypeHeader.getValue())) {
					String responseContent = getMethod.getResponseBodyAsString();
					throw new Exception(responseContent);
				}
			}
			InputStream inputStream = getMethod.getResponseBodyAsStream();
			String fileName = getFileName(getMethod);
			if (StringUtils.isBlank(fileName)) {
				return null;
			}
			String[] name_ext = fileName.split("\\.");
			File localFile = FileUtils.createTmpFile(inputStream, name_ext[0], name_ext[1]);
			return localFile;
		} catch (HttpException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected String getFileName(HttpMethod getMethod) {
		Header[] contentDispositionHeader = getMethod.getResponseHeaders("Content-disposition");
		Pattern p = Pattern.compile(".*filename=\"(.*)\"");
		Matcher m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String fileName = m.group(1);
		return fileName;
	}
}
