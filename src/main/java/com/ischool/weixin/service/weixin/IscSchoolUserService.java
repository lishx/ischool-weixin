package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscSchoolUser;
import com.ischool.weixin.service.BaseService;

public interface IscSchoolUserService extends BaseService<IscSchoolUser>{

	/**
	 * 查询所有未bind的教师信息
	 * @return
	 */
	public List<Map<String, Object>> queryAllTeachersInfo();
	
	/**
	 * 查询老师的详细信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> findTeacherInfo(Integer userId);
	
	
	/**
	 * 查询班级的学生信息
	 * @param classId
	 * @return
	 */
	public List<Map<String, Object>> getStudentByClassId(String classId);

	/**
	 * 查询用户信息，不分老师和学生
	 * @param userid
	 * @return
	 */
	public Map<String, Object> findUserInfo(int userid);
	
	/**
	 * 根据微信openId获取用户
	 * @param openId
	 * @return
	 */
	public Map<String, Object> findUserInfo(String openId);

	/**
	 *  查询用户基本信息
	 * @param userid
	 * @return
	 */
	public Map<String, Object> findBaseUserInfo(int suid);
	
	
}
