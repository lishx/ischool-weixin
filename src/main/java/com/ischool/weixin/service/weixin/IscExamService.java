package com.ischool.weixin.service.weixin;

import java.util.List;
import java.util.Map;

import com.ischool.weixin.entity.IscExam;
import com.ischool.weixin.service.BaseService;

public interface IscExamService extends BaseService<IscExam> {

	/**
	 * 老师查看考试安排
	 * @date 2016年9月18日 上午10:39:02
	 * @author 李双文
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 */
	List<Map<String, Object>> queryPageForMap(Map<String, Object> params, Integer pageIndex, Integer pageSize);

	Map<String, Object> findForMap(Integer examid);

	/**
	 * 学生查看考试安排
	 * @date 2016年9月18日 上午10:40:24
	 * @author 李双文
	 * @param params
	 * @param pageIndex
	 * @param pageSzie
	 * @return 
	 */
	List<Map<String, Object>> queryArrangementList(Map<String, Object> params,
			Integer pageIndex, Integer pageSzie);

}
