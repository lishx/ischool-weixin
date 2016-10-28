package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscExamDAO;
import com.ischool.weixin.entity.IscExam;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscExamService;
@Service
public class IscExamServiceImpl extends BaseServiceImpl<IscExam> implements IscExamService{

	@Autowired
	private IscExamDAO iscExamDAO;
	@Override
	public BaseDAO<IscExam> getDao() {
		
		return iscExamDAO;
	}
	@Override
	public List<Map<String, Object>> queryPageForMap(Map<String, Object> params, Integer pageIndex, Integer pageSize) {
		String sql="SELECT ts.subname,e.*,sc.scname FROM isc_exam e,isc_subject_class sc,isc_term_subject ts"
				+ "  where ts.tsid = sc.tsid and e.scid = sc.scid  and  sc.scid=:classId";
		List<Map<String, Object>> queryForList = iscExamDAO.queryForList(sql, pageIndex, pageSize, params);
		return queryForList;
	}
	@Override
	public Map<String, Object> findForMap(Integer examid) {
		if(StringUtils.isEmpty(examid)){
			return null;
		}
		//String sql ="SELECT e.*,s.alias,s.schcode,s.cmcode,a.address FROM isc_exam e,isc_major_subject s,isc_address a where e.msid = s.msid AND e.addresscode = a.addresscode AND e.examid=:examid";
		String sql ="SELECT e.*,s.subname,s.subcode,s.cmcode FROM isc_exam e,isc_subject_class c,isc_term_subject s where c.tsid = s.tsid and e.scid = c.scid  AND e.examid=:examid";
		Map<String, Object> params = new HashMap<>();
		params.put("examid", examid);
		Map<String, Object> entity = iscExamDAO.findEntity(sql, params);
		return entity;
	}
	/**
	 * @date 2016年9月18日 上午10:40:39
	 * @author 李双文
	 * @param params
	 * @param pageIndex
	 * @param pageSzie
	 * @return 
	 */
	@Override
	public List<Map<String, Object>> queryArrangementList(
			Map<String, Object> params, Integer pageIndex, Integer pageSize) {
		String sql="SELECT ts.subname,sc.scname,e.* FROM isc_term_subject ts,isc_class_student cs,isc_subject_class sc,isc_exam e where cs.scid = sc.scid  AND e.scid = cs.scid"
				+ " AND ts.tsid = sc.tsid AND cs.studentid =:studentId order by e.examtime desc";
		List<Map<String, Object>> list = iscExamDAO.queryForList(sql, pageIndex, pageSize, params);
		return list;
	}

}
