/*package com.test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ischool.weixin.dao.weixin.SysUserDAO;
import com.ischool.weixin.entity.SysUser;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringTest extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SysUserDAO sysUserDAO;
	
	@Test
	public void testA(){
		List<SysUser> list = sysUserDAO.queryListBySql("select * from sys_user");
		System.out.println(list.isEmpty()+"\t"+list.size());
	}
	
	
	
	
}
*/