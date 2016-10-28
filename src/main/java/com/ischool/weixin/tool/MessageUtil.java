/*
 * MessageUtil.java
 * Created on 2016年10月11日 上午10:49:46
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.tool;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：MessageUtil.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李双文<br>
 * 完成日期：2016年10月11日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李双文<br>
 * 完成日期：2016年10月11日<br>
 */
public class MessageUtil {

	
	 public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
	        // 将解析结果存储在HashMap中
		 	Map<String,String> map = new HashMap<String,String>();
	 
	        // 从request中取得输入流
	        InputStream inputStream = request.getInputStream();
	        // 读取输入流
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(inputStream);
	        // 得到xml根元素
	        Element root = document.getRootElement();
	        // 得到根元素的所有子节点
	        @SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
	        
	      
	        // 遍历所有子节点
	        for (Element e : elementList){
	            map.put(e.getName(), e.getText());
	        }
	        // 释放资源
	        inputStream.close();
	        inputStream = null;
	 
	        return map;
	    }
}
