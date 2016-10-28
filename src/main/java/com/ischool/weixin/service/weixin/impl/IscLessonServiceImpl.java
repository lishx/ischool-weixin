package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscLessonDAO;
import com.ischool.weixin.entity.IscLesson;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscLessonService;

@Service
public class IscLessonServiceImpl extends BaseServiceImpl<IscLesson> implements IscLessonService{
	@Autowired
	private IscLessonDAO iscLessonDAO;
	
	@Override
	public BaseDAO<IscLesson> getDao() {
		return iscLessonDAO;
	}

	@Override
	public Map<String, Object> findLessonByScId(String classId) {
		if(StringUtils.isEmpty(classId)){
			return null;
		}
		String sql ="SELECT ts.* FROM isc_term_subject ts,isc_subject_class sc where ts.tsid = sc.tsid AND sc.scid=:classId";
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		Map<String, Object> lesson = iscLessonDAO.findOne(sql, params);
		return lesson;
	}

	@Override
	public List<Map<String, Object>> queryLessonNoByClassId(Integer classId) {
		String sql ="SELECT lessonno FROM isc_lesson l where l.scid=:classId";
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		List<Map<String, Object>> queryForList = iscLessonDAO.queryForList(sql, params);
		return queryForList;
	}

	@Override
	public Map<String, Object> findLessInfo(Long lessonid) {
		String sql ="SELECT u.username,u.fullname,l.lessonno,ts.subname,ts.subcode,su.codeinfo"
				+ " FROM isc_lesson l,isc_school_user su,sys_user u,isc_subject_class sc,isc_term_subject ts"
				+ " WHERE  su.userid = u.userid AND sc.teacherid = su.userid AND sc.tsid = ts.tsid"
				+ " AND sc.scid = l.scid AND l.lessonid =:lessonid";
		Map<String, Object> params = new HashMap<>();
		params.put("lessonid", lessonid);
		try {
			Map<String, Object> findOne = iscLessonDAO.findOne(sql, params);
			return findOne;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
