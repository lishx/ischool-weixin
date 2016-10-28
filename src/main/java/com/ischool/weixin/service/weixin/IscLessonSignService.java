package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscLessonSign;
import com.ischool.weixin.service.BaseService;

public interface IscLessonSignService extends BaseService<IscLessonSign> {

	/**
	 * 查询学生的签到信息
	 * @param userId
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	List<Map<String, Object>> queryStuSignInfo(Integer userId, Integer pageIndex, Integer pageSize);

	/**
	 * 查看签到详细信息
	 * @param lsid
	 * @return
	 */
	Map<String, Object> findSignInfo(Integer lsid);

	/**
	 * 查询课程班级指定课程的签到信息
	 * @param classId
	 * @param lessonno
	 * @return
	 */
	List<Map<String, Object>> queryStuSigns(Integer classId, Integer lessonno);
	/**
	 * 查询课程班级指定课程的未签到的学生信息
	 * @param classId
	 * @param lessonno
	 * @return
	 */
	List<Map<String, Object>> queryStuNotSigns(Integer classId, Integer lessonno);

	/**
	 * 查询指定班级今日已签到的学生
	 * @param classId
	 * @return
	 */
	List<Map<String, Object>> queryToDayStuSigns(Integer classId);

	/**
	 * 查询指定班级今日未签到的学生
	 * @param classId
	 * @return
	 */
	List<Map<String, Object>> queryToDayStuNotSigns(Integer classId);

	/**
	 * 查询指定课程的最小签到时间
	 * @param lessonid
	 * @return
	 */
	Long findMinSignTimeByLid(Long lessonid);

	
	Map<String, Object> findByLidAndSid(Long lessonId,Integer studentId);
	
}
