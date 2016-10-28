package com.ischool.weixin.service.weixin;

import java.util.Map;

import com.ischool.weixin.entity.IscAttentionWeichat;
import com.ischool.weixin.service.BaseService;

public interface IscAttentionWeichatService extends BaseService<IscAttentionWeichat> {

	
	/**
	 * 根据openid查询用户信息
	 * @param openId
	 * @return
	 */
	Map<String, Object> findUserByOpenId(String openId);

}
