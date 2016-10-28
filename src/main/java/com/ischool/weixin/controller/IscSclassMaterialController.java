/*
 * IscSclassMaterialController.java
 * Created on 2016年10月17日 下午3:07:27
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ischool.weixin.constant.ConstantClassField.LM_TYPE;
import com.ischool.weixin.entity.IscSclassMaterial;
import com.ischool.weixin.service.weixin.IscSclassMaterialService;
import com.ischool.weixin.tool.Config;


/**
 * 
 * All rights reserved.<br>
 *
 * 文件名称：IscSclassMaterialController.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 */
@Controller
@RequestMapping("/resouce")
public class IscSclassMaterialController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IscSclassMaterialController.class);
	
	private static int currmaxId =0;
	@Autowired
	private IscSclassMaterialService iscSclassMaterialService;
	
	@RequestMapping("/resouceInfo")
	public String resouceInfo(Integer lmid,Model model){
		try {
			if(null == lmid){
				return null;
			}
			IscSclassMaterial entity = iscSclassMaterialService.findOne(lmid);
			//设置title
			if(entity.getLmtype() == LM_TYPE.expand_knowledge.getCode()){
				model.addAttribute("title", LM_TYPE.expand_knowledge.getName());
			}else{
				model.addAttribute("title", LM_TYPE.LEARNING_MATERIAL.getName());
			}
			model.addAttribute("entity", entity);
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			return "resouceInfo";
		} catch (Exception e) {
			LOGGER.error("数据查询失败", e);
			model.addAttribute("msg", "查询失败");
			return "err";
		}
		
	}
	
	@RequestMapping("/teacher/addresouce")
	public String toadd(IscSclassMaterial entity,Model model){
		try {
			currmaxId = iscSclassMaterialService.getMaxId("isc_sclass_material", "lmid");
			//设置title
			if(entity.getLmtype() == LM_TYPE.expand_knowledge.getCode()){
				model.addAttribute("title", LM_TYPE.expand_knowledge.getName());
			}else{
				model.addAttribute("title", LM_TYPE.LEARNING_MATERIAL.getName());
			}
			model.addAttribute("entity", entity);
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			model.addAttribute("maxId", currmaxId);
			return "teacher/addresouce";
		} catch (Exception e) {
			LOGGER.error("数据添加失败", e);
			model.addAttribute("msg", "添加失败");
			return "err";
		}
		
	}
	
	@RequestMapping("/doadd")
	public String doadd(IscSclassMaterial entity,Model model){
		try {
			//设置title
			if(entity.getLmtype() == LM_TYPE.expand_knowledge.getCode()){
				model.addAttribute("title", LM_TYPE.expand_knowledge.getName());
			}else{
				model.addAttribute("title", LM_TYPE.LEARNING_MATERIAL.getName());
			}
			entity.setState(1);
			iscSclassMaterialService.save(entity);
		//	model.addAttribute("entity", entity);
		//	model.addAttribute("fileServer", Config.getProperty("file_server_url"));
		//	model.addAttribute("maxId", currmaxId);
			return "redirect:teacher/tolist?lmtype="+entity.getLmtype()+"&scid="+entity.getScid();
		} catch (Exception e) {
			LOGGER.error("数据添加失败", e);
			model.addAttribute("msg", "添加失败");
			return "err";
		}
		
	}
	
	@RequestMapping("/teacher/tolist")
	public String toList(Integer lmtype,Integer scid,Model model){
		try {
			//设置title
			if(lmtype == LM_TYPE.expand_knowledge.getCode()){
				model.addAttribute("title", LM_TYPE.expand_knowledge.getName());
			}else{
				model.addAttribute("title", LM_TYPE.LEARNING_MATERIAL.getName());
			}
			model.addAttribute("lmtype", lmtype);
			model.addAttribute("scid", scid);
			
			return "teacher/docSharingList";
		} catch (Exception e) {
			LOGGER.error("转到资料列表出错（老师）", e);
			model.addAttribute("msg","加载列表出错");
			return "err";
		}
		
	}
	
	@RequestMapping("/student/tolist")
	public String tosList(Integer lmtype,Integer scid,Model model){
		try {
			//设置title
			if(lmtype == LM_TYPE.expand_knowledge.getCode()){
				model.addAttribute("title", LM_TYPE.expand_knowledge.getName());
			}else{
				model.addAttribute("title", LM_TYPE.LEARNING_MATERIAL.getName());
			}
			model.addAttribute("lmtype", lmtype);
			model.addAttribute("scid", scid);
			
			return "student/docSharingList";
		} catch (Exception e) {
			LOGGER.error("转到资料列表出错（学生）", e);
			model.addAttribute("msg","加载列表出错");
			return "err";
		}
		
	}
	
	/**
	 * 查询资料列表
	 * @date 2016年10月18日 下午3:38:38
	 * @author 李双文
	 * @param pageIndex
	 * @param pageSize
	 * @param lmtype
	 * @param scid
	 * @return 
	 */
	@RequestMapping("/doList")
	@ResponseBody
	public List<Map<String, Object>> doList(Integer pageIndex,Integer pageSize,Integer lmtype,Integer scid){
		try {
			if(null == scid || null == lmtype){
				return null;
			}
			
			if(null == pageIndex){
				pageIndex =1;
			}
			if(null == pageSize){
				pageSize = 7;
			}
			List<Map<String, Object>> list = iscSclassMaterialService.queryResouceByScid(pageIndex,pageSize,lmtype,scid);
			
			
			return list;
		} catch (Exception e) {
			LOGGER.error("列表查询失败", e);
			return null;
		}
		
	}
}
