package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscLesson;
import com.ischool.weixin.service.BaseService;

public interface IscLessonService extends BaseService<IscLesson>{
	
	
	public Map<String, Object> findLessonByScId(String classId);

	/**
	 * 查询课程号
	 * @param classId
	 * @return
	 */
	public List<Map<String, Object>> queryLessonNoByClassId(Integer classId);

	/**
	 * 查询课程信息
	 * @param lessonid
	 * @return
	 */
	public Map<String, Object> findLessInfo(Long lessonid);

	
	
}
