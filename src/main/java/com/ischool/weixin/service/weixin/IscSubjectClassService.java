package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscSubjectClass;
import com.ischool.weixin.service.BaseService;

public interface IscSubjectClassService extends BaseService<IscSubjectClass> {

	/**
	 * 查询老师教的课程班级
	 * @param teacherId
	 * @return
	 */
	List<Map<String, Object>> queryClassByTid(Integer teacherId);

	/**
	 * 查询学生所在课程班级
	 * @param studentId
	 * @return
	 */
	List<Map<String, Object>> queryClassBySid(Integer studentId);

}
