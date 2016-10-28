package com.ischool.weixin.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischool.weixin.dao.BaseDAO;
import com.ischool.weixin.service.BaseService;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@Override
	public T save(T t) {
		return getDao().save(t);
	}
	
	@Override
	public void delete(Serializable id) {
		getDao().delete(id);
	}



	@Override
	public void delete(T t) {
		getDao().delete(t);
	}



	public T findOne(Serializable id) {
		return getDao().findOne(id);
	}

	public List<T> queryAllList() {
		return getDao().findAll();
	}

	

	/**
	 * @date 2016年10月14日 下午2:18:02
	 * @author 李双文
	 * @param tableName
	 * @param columnName
	 * @return 
	 */
	public int getMaxId(String tableName, String columnName) {
		
		String sql ="SELECT max("+columnName+") maxid FROM "+tableName;
		
		Map<String, Object> map = getDao().findOne(sql, new HashMap<String, Object>());
		if(null != map){
			Object object = map.get("maxid");
			if(null != object){
				return Integer.valueOf(object.toString())+1;
			}
		}
		return 0;
	}
	
	
	

	
}
