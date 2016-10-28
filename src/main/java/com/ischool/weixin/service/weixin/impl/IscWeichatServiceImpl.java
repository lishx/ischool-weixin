/*
 * IscWeichatServiceImpl.java
 * Created on 2016年10月11日 下午1:20:43
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.service.weixin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscWeichatDAO;
import com.ischool.weixin.entity.IscWeichat;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscWeichatService;


/**
 * <br>
 * All rights reserved.<br>
 *
 * 文件名称：IscWeichatServiceImpl.java<br>
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
@Service
public class IscWeichatServiceImpl extends BaseServiceImpl<IscWeichat> implements IscWeichatService{
	@Autowired
	private IscWeichatDAO iscWeichatDAO;
	/**
	 * @date 2016年10月11日 下午1:21:24
	 * @author 李双文
	 * @return 
	 */
	@Override
	public BaseDAO<IscWeichat> getDao() {
		return iscWeichatDAO;
	}
	/**
	 * 取消关注
	 * @date 2016年10月11日 下午1:40:11
	 * @author 李双文
	 * @param fromUserName
	 * @return 
	 */
	@Override
	public boolean deleteByOpenId(String openId) {
		if(StringUtils.isEmpty(openId)){
			return false;
		}
		
		try {
			String sql =" delete isc_weichat where weichat ='"+openId+"'";
			Integer count = iscWeichatDAO.updateSql(sql);
			return 0<count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
