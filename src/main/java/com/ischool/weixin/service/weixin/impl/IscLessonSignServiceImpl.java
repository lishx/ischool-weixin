package com.ischool.weixin.service.weixin.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscLessonSignDAO;
import com.ischool.weixin.entity.IscLessonSign;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscLessonSignService;
@Service
public class IscLessonSignServiceImpl extends BaseServiceImpl<IscLessonSign> implements IscLessonSignService{
	
	@Autowired
	private IscLessonSignDAO iscLessonSignDAO;
	@Override
	public BaseDAO<IscLessonSign> getDao() {
		
		return iscLessonSignDAO;
	}
	@Override
	public List<Map<String, Object>> queryStuSignInfo(Integer studentid, Integer pageIndex, Integer pageSize) {
		/*String sql ="SELECT l.lessonname,ls.* FROM isc_lesson_student ls,isc_lesson l where ls.lessonid = l.lessonid";
		sql +=" AND ls.studentid =:userId";*/
		String sql =" SELECT ts.subname,sc.scname,l.lessonno,ls.* FROM isc_lesson_sign ls,isc_lesson l,isc_subject_class sc,isc_term_subject ts where"
				+ " ls.lessonid = l.lessonid and sc.scid = l.scid  and ts.tsid = sc.tsid"
				+ " AND ls.studentid =:studentid order by ls.signintime desc";
		
		Map<String, Object> params = new HashMap<>();
		params.put("studentid", studentid);
		
		List<Map<String, Object>> queryForList = iscLessonSignDAO.queryForList(sql,pageIndex,pageSize ,params);
		
		return queryForList;
	}
	@Override
	public Map<String, Object> findSignInfo(Integer lsid) {
		/*String sql ="SELECT l.lessonname,u.username,su.codeinfo,ls.* FROM";
		sql +=" isc_lesson_student ls, isc_lesson l,isc_school_user su,sys_user u";
		sql +=" WHERE ls.lessonid = l.lessonid AND su.userid = u.userid AND l.teachercode = su.codeinfo AND ls.lsid = 1";
		sql +=" AND ls.lsid=:lsid";*/
		String sql =" SELECT u.username,u.fullname,l.lessonno,ts.subname,ts.subcode, su.codeinfo,ls.* FROM  "
				+ " isc_lesson_sign ls, isc_lesson l,isc_school_user su,sys_user u,isc_subject_class sc,isc_term_subject ts"
				+ " WHERE ls.lessonid = l.lessonid AND su.userid = u.userid AND sc.teacherid = su.userid AND sc.tsid =ts.tsid"
				+ " AND sc.scid = l.scid  AND ls.lsid = :lsid";
		
		Map<String, Object> params = new HashMap<>();
		params.put("lsid", lsid);
		Map<String, Object> entity = iscLessonSignDAO.findEntity(sql, params);
		return entity;
	}
	@Override
	public List<Map<String, Object>> queryStuSigns(Integer classId, Integer lessonno) {
		
		String sql ="SELECT u.username,su.codeinfo,c.classname,ls.* FROM isc_lesson_sign ls, isc_lesson l,isc_school_user su,isc_class c,sys_user u "
				+ " WHERE ls.lessonid=l.lessonid "
				+ " AND su.classid = c.classid "
				+ " AND su.userid = u.userid "
				+ " AND su.suid = ls.studentid "
				+ " AND l.scid=:classId and l.lessonno =:lessonno  ORDER BY l.lessonid DESC";
		
		Map<String, Object> params = new HashMap<>();
		params.put("lessonno", lessonno);
		params.put("classId", classId);
		List<Map<String, Object>> queryForList = iscLessonSignDAO.queryForList(sql, params);
		return queryForList;
	}
	@Override
	public List<Map<String, Object>> queryStuNotSigns(Integer classId, Integer lessonno) {
		String sql1="SELECT * FROM isc_lesson l WHERE l.scid =:classId AND l.lessonno =:lessonno";
		Map<String, Object> params = new HashMap<>();
		params.put("lessonno", lessonno);
		params.put("classId", classId);
		Map<String, Object> one = iscLessonSignDAO.findOne(sql1, params);
		if(null == one){
			return null;
		}
		Object object = one.get("lessonid");
		String lessonId = object.toString();
		
		String sql=" SELECT u.username,u.userid,'"+lessonId+"' lessonid,su.codeinfo,c.classname,su.suid from  isc_class_student s,isc_school_user su,isc_class c,sys_user u"
				+ " where su.classid = c.classid AND su.userid = u.userid"
				+ " AND su.userid = s.studentid"
				+ " AND su.suid NOT IN("
				+ " SELECT ls.studentid  FROM isc_lesson_sign ls WHERE ls.lessonid =:lessonid"
				+ ") and s.scid = :classId";
		Map<String, Object> params1 = new HashMap<>();
		params1.put("classId", classId);
		params1.put("lessonid", lessonId);
		List<Map<String, Object>> queryForList = iscLessonSignDAO.queryForList(sql, params1);
		return queryForList;
	}
	/**
	 * 查询指定班级今日签到学生
	 */
	@Override
	public List<Map<String, Object>> queryToDayStuSigns(Integer classId) {
		//查询最新签到的一节课程
				String newlesson = "SELECT * FROM isc_lesson_sign s WHERE s.signintime ="
						+ "	 (SELECT max(signintime) FROM isc_lesson_sign ls,isc_lesson l WHERE ls.lessonid = l.lessonid AND l.scid = :classId)";
				
				Map<String, Object> params1 = new HashMap<>();
				params1.put("classId", classId);
				List<Map<String, Object>> list = iscLessonSignDAO.queryForList(newlesson,params1);
				if(null != list && !list.isEmpty()){
					Map<String, Object> m = list.get(0);
					String lessonId = m.get("lessonid").toString();
					//查询课程签到的学生
					String sql ="SELECT u.username,su.codeinfo,c.classname,ls.* FROM isc_lesson_sign ls, "
							+ " isc_lesson l,isc_school_user su,isc_class c,sys_user u"
							+ " WHERE ls.lessonid=l.lessonid AND su.classid = c.classid AND su.userid = u.userid"
							+ " AND su.suid = ls.studentid AND l.lessonid=:lessonid  ORDER BY l.lessonid DESC";
					Map<String, Object> params = new HashMap<>();
					params.put("lessonid", lessonId);
					List<Map<String, Object>> queryForList = iscLessonSignDAO.queryForList(sql, params);
					
					return queryForList;
				}else{
					return new ArrayList<Map<String, Object>>();
				}
	
		
	}
	
	/**
	 * 查询指定班级今日未签到学生
	 * */
	@Override
	public List<Map<String, Object>> queryToDayStuNotSigns(Integer classId) {
		
		String newlesson = "SELECT * FROM isc_lesson_sign s WHERE s.signintime ="
				+ "	 (SELECT max(signintime) FROM isc_lesson_sign ls,isc_lesson l WHERE ls.lessonid = l.lessonid AND l.scid = :classId)";
		
		Map<String, Object> params1 = new HashMap<>();
		params1.put("classId", classId);
		List<Map<String, Object>> list = iscLessonSignDAO.queryForList(newlesson,params1);
		if(null != list && !list.isEmpty()){
			Map<String, Object> m = list.get(0);
			String lessonId = m.get("lessonid").toString();

			String sql=" SELECT u.username,u.userid,'"+lessonId+"' lessonid,su.codeinfo,c.classname,su.suid from  isc_class_student s,isc_school_user su,isc_class c,sys_user u"
					+ " where su.classid = c.classid AND su.userid = u.userid"
					+ " AND su.userid = s.studentid"
					+ " AND su.suid NOT IN("
					+ " SELECT ls.studentid  FROM isc_lesson_sign ls WHERE ls.lessonid = :lessonid"
					+ ") and s.scid = :classId";
			Map<String, Object> params = new HashMap<>();
			params.put("lessonid", lessonId);
			params.put("classId", classId);
			List<Map<String, Object>> queryForList = iscLessonSignDAO.queryForList(sql, params);
			return queryForList;
		}else{
			return null;
		}
		
	
	}
	
	/**
	 * 查询指定课程的最小签到时间
	 * @param lessonid
	 * @return
	 */
	@Override
	public Long findMinSignTimeByLid(Long lessonid) {
		if(StringUtils.isEmpty(lessonid)){
			return null;
		}
		String sql ="SELECT min(s.signintime) signintime  FROM isc_lesson_sign s WHERE lessonid =:lessonid";
		
		Map<String, Object> params = new HashMap<>();
		params.put("lessonid", lessonid);
		Map<String, Object> one = iscLessonSignDAO.findOne(sql, params);
		if(null == one ){
			return 0l;
		}
		Object object = one.get("signintime");
		if(null == object ){
			return 0l;
		}
		long minTime = Long.parseLong(object.toString());
		return minTime;
	}
	@Override
	public Map<String, Object> findByLidAndSid(Long lessonId, Integer studentId) {
		String sql ="SELECT * FROM isc_lesson_sign ls WHERE ls.lessonid=:lessonId AND ls.studentid=:studentId";
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("lessonId", lessonId);
			params.put("studentId", studentId);
			
			Map<String, Object> findOne = iscLessonSignDAO.findOne(sql, params);
			if(null != findOne){
				return findOne;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	

	
}
