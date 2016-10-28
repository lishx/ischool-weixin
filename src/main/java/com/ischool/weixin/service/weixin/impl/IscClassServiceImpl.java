package com.ischool.weixin.service.weixin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.dao.weixin.IscClassDAO;
import com.ischool.weixin.entity.IscClass;
import com.ischool.weixin.service.impl.BaseServiceImpl;
import com.ischool.weixin.service.weixin.IscClassService;
@Service
public class IscClassServiceImpl extends BaseServiceImpl<IscClass> implements IscClassService{

	@Autowired
	private IscClassDAO iscClassDAO;
	@Override
	public BaseDAO<IscClass> getDao() {
		return iscClassDAO;
	}
	/* (non-Javadoc)
	 * @see com.ischool.weixin.service.weixin.IscClassService#queryClass(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryClass(String teacherCode) {
		if(StringUtils.isEmpty(teacherCode)){
			return null;
		}
		String sql="SELECT l.*,c.classid,c.cmcode,c.classname,c.infocode,c.alias,c.year,c.classno FROM isc_lesson l,isc_class c WHERE l.classcode = c.classcode AND l.teachercode =:teachercode";
		Map<String, Object> params = new HashMap<>();
		params.put("teachercode", teacherCode);
		List<Map<String, Object>> forList = iscClassDAO.queryForList(sql, params);
		return forList;
	
	}

}
