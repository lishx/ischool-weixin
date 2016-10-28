package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscAttentionWeichatDAO;
import com.ischool.weixin.entity.IscAttentionWeichat;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscAttentionWeichatService;
@Service
public class IscAttentionWeichatServiceImpl extends BaseServiceImpl<IscAttentionWeichat> implements IscAttentionWeichatService {

	@Autowired
	private IscAttentionWeichatDAO iscAttentionWeichatDAO;
	
	@Override
	public BaseDAO<IscAttentionWeichat> getDao() {
		return iscAttentionWeichatDAO;
	}

	@Override
	public Map<String, Object> findUserByOpenId(String openId) {
		String sql ="SELECT u.*,w.nickname,w.attentiontime,su.schoolid,su.collegeid,su.cmid,su.classid,su.codeinfo FROM isc_attention_weichat w,isc_school_user su,sys_user u WHERE"
				+ " w.userid =su.userid AND u.userid = su.suid AND w.weichat=openId";
		Map<String, Object> params= new HashMap<>();
		params.put("openId", openId);
		Map<String, Object> findEntity = iscAttentionWeichatDAO.findEntity(sql, params);
		return findEntity;
	}

}
