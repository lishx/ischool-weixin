package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.constant.ConstantClassField.USER_TYPE;
import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscSchoolUserDAO;
import com.ischool.weixin.entity.IscSchoolUser;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscSchoolUserService;

@Service
public class IscSchoolUserServiceImpl extends BaseServiceImpl<IscSchoolUser> implements IscSchoolUserService{

	private final Logger LOGGER = Logger.getLogger(IscSchoolUserServiceImpl.class);
	@Autowired
	private IscSchoolUserDAO iscSchoolUserDAO;
	
	@Override
	public BaseDAO<IscSchoolUser> getDao() {
		return iscSchoolUserDAO;
	}

	@Override
	public List<Map<String, Object>> queryAllTeachersInfo() {
		try{
			String sql ="SELECT u.*,t.codeinfo FROM sys_user u,isc_school_user  t WHERE u.userid = t.userid  and t.usertype ="+USER_TYPE.teacher.getCode()+" and  t.state=1 and u.state=1"+
				" and not EXISTS( SELECT 1 from isc_attention_weichat w WHERE u.userid = w.userid )";
			List<Map<String, Object>> queryForList = iscSchoolUserDAO.queryForList(sql, null);
			return queryForList;
		
		}catch(Exception e){
			LOGGER.error(e);
		}
		return null;
	}

	
	public Map<String, Object> findTeacherInfo(Integer userId) {
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		String sql= "SELECT u.*, t.codeinfo FROM sys_user u,isc_school_user t"+
				" WHERE u.userid = t.userid and t.usertype ="+USER_TYPE.teacher.getCode()+" AND t.state = 1 AND u.state = 1 AND su.suid = :userid";
		Map<String, Object> params = new HashMap<>();
		params.put("userid", userId);
		Map<String, Object> map = iscSchoolUserDAO.findOne(sql, params);
		return map;
	}

	/**
	 * 查询班级没有绑定过微信的学生
	 */
	@Override
	public List<Map<String, Object>> getStudentByClassId(String classId) {
		String sql ="SELECT u.username,u.phone,u.fullname,t.* from isc_school_user t,sys_user u where t.classid=:classId AND t.userid = u.userid  and t.usertype ="+USER_TYPE.student.getCode();
		sql +=" and not EXISTS (SELECT 1 from isc_attention_weichat w WHERE u.userid = w.userid )";
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		List<Map<String, Object>> list = iscSchoolUserDAO.queryForList(sql, params);
		return list;
	}

	
	/**
	 * 根据用户id 获取用户信息(学生)
	 * @param userId
	 * @return
	 */
	@Override
	public Map<String, Object> findUserInfo(int userId) {
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		String sql= "SELECT c.classname,u.usertype,u.userid,u.phone,u.username,su.codeinfo FROM isc_school_user su,sys_user u,isc_class c "+
				" WHERE su.userid = u.userid  AND u.state = 1 AND su.classid = c.classid AND su.suid = :userid";
		Map<String, Object> params = new HashMap<>();
		params.put("userid", userId);
		Map<String, Object> map = iscSchoolUserDAO.findOne(sql, params);
		return map;
	}

	/**
	 * 根据微信号获取用信息(学生)
	 * @param openId
	 * @return
	 */
	@Override
	public Map<String, Object> findUserInfo(String openId) {
		if(StringUtils.isEmpty(openId)){
			return null;
		}
		String sql= "SELECT su.usertype,w.weichat AS openid,u.userid,u.phone,u.username,su.codeinfo,su.suid FROM isc_school_user su,sys_user u,"
				+ " isc_attention_weichat w  "+
				" WHERE  w.userid = u.userid AND su.userid = u.userid  AND u.state = 1 AND w.weichat=:openid";
		Map<String, Object> params = new HashMap<>();
		params.put("openid", openId);
		Map<String, Object> map = iscSchoolUserDAO.findOne(sql, params);
		return map;
	}

	@Override
	public Map<String, Object> findBaseUserInfo(int suid) {
		String sql ="SELECT su.usertype,u.userid,u.phone,u.username,su.codeinfo,su.suid FROM "
				+ " isc_school_user su, sys_user u WHERE su.userid = u.userid AND u.state = 1 AND su.suid =:userId";
		Map<String, Object> params = new HashMap<>();
		params.put("userId", suid);
		Map<String, Object> map = iscSchoolUserDAO.findOne(sql, params);
		return map;
	}

}
