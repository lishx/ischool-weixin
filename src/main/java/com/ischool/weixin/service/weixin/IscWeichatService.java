/*
 * IscWeichatService.java
 * Created on 2016年10月11日 下午1:20:10
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.service.weixin;

import com.ischool.weixin.entity.IscWeichat;
import com.ischool.weixin.service.BaseService;


/**
 * 
 * All rights reserved.<br>
 *
 * 文件名称：IscWeichatService.java<br>
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
public interface IscWeichatService extends BaseService<IscWeichat>{

	/**
	 * @date 2016年10月11日 下午1:39:47
	 * @author 李双文
	 * @param fromUserName 
	 */
	boolean deleteByOpenId(String openid);

}
