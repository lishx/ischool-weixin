/*
 * IscSclassMaterialService.java
 * Created on 2016年10月17日 下午2:10:42
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscSclassMaterial;
import com.ischool.weixin.service.BaseService;


/**
 * All rights reserved.<br>
 *
 * 文件名称：IscSclassMaterialService.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 */
public interface IscSclassMaterialService extends
		BaseService<IscSclassMaterial> {

	/**
	 * 根据课程id查询
	 * @date 2016年10月18日 下午3:32:02
	 * @author 李双文
	 * @param pageIndex
	 * @param pageSize
	 * @param scid
	 * @return 
	 */
	List<Map<String, Object>> queryResouceByScid(Integer pageIndex,
			Integer pageSize,Integer lmtype ,Integer scid);

}
