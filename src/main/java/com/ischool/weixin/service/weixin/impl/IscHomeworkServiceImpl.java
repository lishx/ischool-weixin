package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscHomeworkDAO;
import com.ischool.weixin.entity.IscHomework;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscHomeworkService;

@Service
public class IscHomeworkServiceImpl extends BaseServiceImpl<IscHomework> implements IscHomeworkService{
	@Autowired
	private IscHomeworkDAO IscHomeworkDAO;
	
	@Override
	public BaseDAO<IscHomework> getDao() {
		return IscHomeworkDAO;
	}

	@Override
	public List<Map<String, Object>> queryHomeWorkByClassCode(String classcode, Integer pageIndex, Integer pageSize) {
		String sql ="SELECT a.*,sh.correctinfo,sh.finishtime,sh.iscorrect,sh.isfinish,sh.shid,sh.studentid FROM(SELECT l.*,h.createtime,h.homeworkid,h.limittime,h.workinfo,h.versiontime FROM"
				+ " isc_lesson l, isc_homework h where l.lessonid = h.lessonid"
				+ " AND l.classcode=:classcode) a LEFT JOIN isc_student_homework sh "
				+ " ON sh.homeworkid = a.homeworkid";
		Map<String, Object> params = new HashMap<>();
		params.put("classcode", classcode);
		List<Map<String, Object>> queryForList = IscHomeworkDAO.queryForList(sql, pageIndex, pageSize, params);
		
		return queryForList;
	}

	@Override
	public Map<String, Object> queryHomeWorkById(Integer homeworkid,Integer studentid) {
		/*String sql ="SELECT a.*,sh.correctinfo,sh.finishtime,sh.iscorrect,sh.isfinish,sh.shid,sh.studentid FROM(SELECT l.*,h.createtime,h.homeworkid,h.limittime,h.workinfo,h.versiontime FROM"
				+ " isc_lesson l, isc_homework h where l.lessonid = h.lessonid"
				+ " AND h.homeworkid=:homeworkid) a LEFT JOIN isc_student_homework sh "
				+ " ON sh.homeworkid = a.homeworkid AND studentid= :studentid";*/
		String sql ="SELECT sh.correctinfo,sh.finishtime,sh.iscorrect,sh.isfinish,sh.shid,a.* FROM "
				+ " ( SELECT h.*,cs.studentid,ts.subname,sc.scname FROM isc_homework h ,isc_class_student cs,"
				+ "		isc_subject_class sc,isc_term_subject ts WHERE sc.tsid = ts.tsid AND"
				+ " sc.scid = cs.scid 	 AND h.scid = sc.scid AND  cs.studentid =:studentid AND h.homeworkid =:homeworkid"
				+ " ) a LEFT JOIN isc_student_homework sh ON a.homeworkid = sh.homeworkid AND sh.studentid = a.studentid"
				+ "";
		Map<String, Object> params = new HashMap<>();
		params.put("homeworkid", homeworkid);
		params.put("studentid", studentid);
		
		Map<String, Object> map = IscHomeworkDAO.findEntity(sql, params);
		
		return map;
	}

	/*
	 * 老师查看作业历史 (non-Javadoc)
	 * @see com.ischool.weixin.service.weixin.IscHomeworkService#queryWorkList(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> queryWorkList(Integer classId, Integer pageIndex,
			Integer pageSize) {
		
		String sql="SELECT sc.*,w.homeworkid,ts.subname,w.createtime FROM isc_subject_class sc,isc_homework w,isc_term_subject ts "
				+ " WHERE sc.tsid = ts.tsid and sc.scid = w.scid AND sc.scid=:classId ORDER BY w.createtime desc";
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		List<Map<String, Object>> queryForList = IscHomeworkDAO.queryForList(sql, pageIndex, pageSize, params);
		return queryForList;
	}

	@Override
	public Map<String, Object> findWorkInfo(Integer homeworkid) {
		String sql ="SELECT h.*,ts.subname FROM isc_homework h,isc_subject_class sc,isc_term_subject ts WHERE"
				+ " h.scid = sc.scid and sc.tsid = ts.tsid AND h.homeworkid=:homeworkid";
		
		Map<String, Object> params = new HashMap<>();
		params.put("homeworkid", homeworkid);
		Map<String, Object> findOne = IscHomeworkDAO.findOne(sql, params);
		
		return findOne;
	}

	/**
	 * 根据学生id 查询作业信息 
	 * @date 2016年9月18日 下午12:45:54
	 * @author 李双文
	 * @param studentId
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 */
	@Override
	public List<Map<String, Object>> queryHomeWorkByStid(Integer studentId,
			Integer pageIndex, Integer pageSize) {
		
		String sql ="SELECT sh.correctinfo,sh.finishtime,sh.iscorrect,sh.isfinish,sh.shid,a.* FROM "
				+ " ( SELECT h.*,cs.studentid,ts.subname,sc.scname FROM isc_homework h ,isc_class_student cs,"
				+ "		isc_subject_class sc,isc_term_subject ts WHERE sc.tsid = ts.tsid AND"
				+ " sc.scid = cs.scid 	 AND h.scid = sc.scid AND  cs.studentid =:studentId"
				+ " ) a LEFT JOIN isc_student_homework sh ON a.homeworkid = sh.homeworkid AND sh.studentid = a.studentid"
				+ " order by a.createtime desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("studentId", studentId);
		List<Map<String, Object>> list = IscHomeworkDAO.queryForList(sql, pageIndex, pageSize, params);
	
		return list;
	}

	
	
	

}
