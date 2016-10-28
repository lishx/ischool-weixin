package com.ischool.weixin.service;



import java.io.Serializable;
import java.util.List;

import com.ischool.weixin.dao.BaseDAO;



public interface BaseService<T> {
	
	BaseDAO<T> getDao();
	
	T save(T t);
	
	T findOne(Serializable id);
	
	public List<T> queryAllList();

	void delete(Serializable id);
	
	void delete(T t);
	
	int getMaxId(String tableName,String columnName);
	
}
