package com.ischool.weixin.service.weixin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.SysUserDAO;
import com.ischool.weixin.entity.SysUser;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.SysUserService;
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService{
	
	@Autowired
	private SysUserDAO sysUserDAO;
	
	@Override
	public BaseDAO<SysUser> getDao() {
		return sysUserDAO;
	}

}
