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
package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscSclassMaterialDAO;
import com.ischool.weixin.entity.IscSclassMaterial;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscSclassMaterialService;


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
@Service
public class IscSclassMaterialServiceImpl extends
		BaseServiceImpl<IscSclassMaterial> implements IscSclassMaterialService{
	@Autowired
	private IscSclassMaterialDAO iscSclassMaterialDAO;
	/**
	 * @date 2016年10月17日 下午2:14:16
	 * @author 李双文
	 * @return 
	 */
	@Override
	public BaseDAO<IscSclassMaterial> getDao() {
		return iscSclassMaterialDAO;
	}
	/**
	 * @date 2016年10月18日 下午3:33:19
	 * @author 李双文
	 * @param pageIndex
	 * @param pageSize
	 * @param lmtype
	 * @param scid
	 * @return 
	 */
	@Override
	public List<Map<String, Object>> queryResouceByScid(Integer pageIndex,
			Integer pageSize, Integer lmtype, Integer scid) {
		
		String sql = "SELECT sm.*,sc.scname FROM isc_sclass_material sm,isc_subject_class sc where sm.scid = sc.scid AND sm.scid =:scid AND sm.lmtype=:lmtype order by sm.lmid desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("scid",scid);
		params.put("lmtype",lmtype);
		List<Map<String, Object>> list = iscSclassMaterialDAO.queryForList(sql, pageIndex, pageSize, params);
		
		return list;
	}

}
