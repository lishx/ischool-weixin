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
import org.apache.xml.security.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class Config {
	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	
	private static  String conf = "classPath:default.properties";
	private static Properties p = null;
	
	private Config() {

	}


	static {
		String classPath = Config.class.getResource("/").getPath().replaceAll("%20", " ");
		conf = conf.replace("classPath:", classPath);
		p = new Properties();
		File pfile = new File(conf);
		if (pfile.exists()) {
			try {
				p.load(new FileInputStream(pfile));
			
			} catch (FileNotFoundException e) {
				logger.error("未找到default.properties", e);
			} catch (IOException e) {
				logger.error("default.properties读取异常", e);
			}
		}
	}
	
	public static String getProperty(String key){
		
		return p.getProperty(key);
	}

	
}
