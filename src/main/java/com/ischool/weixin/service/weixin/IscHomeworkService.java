package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscHomework;
import com.ischool.weixin.service.BaseService;

public interface IscHomeworkService extends BaseService<IscHomework>{

	/**
	 * 根据班级code查询作业信息
	 * @param classcode
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	List<Map<String, Object>> queryHomeWorkByClassCode(String classcode, Integer pageIndex, Integer pageSize);

	/**
	 * 查询作业相关的详细信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryHomeWorkById(Integer homeworkid,Integer studentid);

	/**
	 * 老师查看作业安排
	 * @param classId
	 * @param teacherId
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	List<Map<String, Object>> queryWorkList(Integer classId, Integer pageIndex, Integer pageSize);

	/**
	 * 查询作业相关信息
	 * @param homeworkid
	 * @return
	 */
	Map<String, Object> findWorkInfo(Integer homeworkid);

	/**
	 * 根据学生id 查询作业信息
	 * @date 2016年9月18日 下午12:45:29
	 * @author 李双文
	 * @param studentId
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 */
	List<Map<String, Object>> queryHomeWorkByStid(Integer studentId,
			Integer pageIndex, Integer pageSize);

}
