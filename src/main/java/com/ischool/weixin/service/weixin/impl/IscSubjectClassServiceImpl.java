package com.ischool.weixin.service.weixin.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscSubjectClassDAO;
import com.ischool.weixin.entity.IscSubjectClass;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscSubjectClassService;
@Service
public class IscSubjectClassServiceImpl extends BaseServiceImpl<IscSubjectClass> implements IscSubjectClassService {
	@Autowired
	private IscSubjectClassDAO iscSubjectClassDAO;
	
	@Override
	public BaseDAO<IscSubjectClass> getDao() {
		return iscSubjectClassDAO;
	}

	@Override
	public List<Map<String, Object>> queryClassByTid(Integer teacherId) {
		
		String sql ="SELECT su.suid,sc.*,ts.cmcode,ts.subname FROM isc_subject_class sc,isc_school_user su,isc_term_subject ts WHERE sc.tsid = ts.tsid AND	sc.teacherid = su.userid AND sc.teacherid=:teacherid AND sc.year=:year";
		Map<String, Object> params = new HashMap<>();
		params.put("teacherid", teacherId);
		Calendar cal = Calendar.getInstance();
		params.put("year", cal.get(Calendar.YEAR));
		
		List<Map<String, Object>> list = iscSubjectClassDAO.queryForList(sql, params);
		
		return list;
	}

	/**
	 * 查询学生所在课程班级
	 * @param studentId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryClassBySid(Integer studentId) {
		
		String sql ="SELECT su.suid,cs.studentid,sc.*, ts.cmcode,ts.subname FROM isc_subject_class sc,isc_school_user su,isc_term_subject ts,isc_class_student cs "
				+ " WHERE sc.tsid = ts.tsid AND cs.studentid = su.userid and cs.scid = sc.scid AND studentid =:studentId";
		Map<String, Object> params = new HashMap<>();
		params.put("studentId", studentId);
		
		List<Map<String, Object>> list = iscSubjectClassDAO.queryForList(sql, params);
		
		return list;
	}

	
}
