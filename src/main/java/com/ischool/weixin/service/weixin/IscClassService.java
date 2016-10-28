package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscClass;
import com.ischool.weixin.service.BaseService;

public interface IscClassService extends BaseService<IscClass> {

	/**
	 * 查询老师所教的课程（及班级信息）
	 * @param teacherCode
	 * @return
	 */
	List<Map<String, Object>> queryClass(String teacherCode);

}
