package com.ischool.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ischool.weixin.entity.IscHomework;
import com.ischool.weixin.service.weixin.IscHomeworkService;
import com.ischool.weixin.service.weixin.IscLessonService;
import com.ischool.weixin.service.weixin.IscSchoolUserService;
import com.ischool.weixin.tool.Config;

@Controller
@RequestMapping("/work")
public class IscHomeworkController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IscHomeworkController.class);
	@Autowired
	private IscHomeworkService iscHomeworkService;
	@Autowired
	private IscLessonService iscLessonService;
	@Autowired
	private IscSchoolUserService iscSchoolUserService;
	
	private static int currmaxId =0;
	
	/**
	 * 老师查看作业历史
	 * @return
	 */
	@RequestMapping("/toworklist")
	public String toworkList(Integer classId,Model model){
		
		model.addAttribute("classId", classId);
		return "teacher/workList";
	}
	
	/**
	 * 老师查看作业历史
	 * @return
	 */
	@RequestMapping("/doworklist")
	@ResponseBody
	public List<Map<String, Object>> doworkList(Integer classId,Integer pageIndex,Integer pageSize){
		try {
			List<Map<String, Object>> list = iscHomeworkService.queryWorkList(classId,pageIndex,pageSize);
			
			for(Map<String, Object> entity : list){
				long createtime =Long.parseLong(entity.get("createtime").toString()) ;
				SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = sft.format(new Date(createtime));
				entity.put("createtime", format);
			}
			return list;
		} catch (Exception e) {
			LOGGER.error("doworklist方法出错了", e);
			return null;
		}
		
	}
	
	@RequestMapping("/teacher/workInfo")
	public String findworkInfo(Integer homeworkid,Model model){
		try {
			Map<String, Object> findOne = iscHomeworkService.findWorkInfo(homeworkid);
			long createtime =Long.parseLong(findOne.get("createtime").toString()) ;
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sft.format(new Date(createtime));
			findOne.put("createtime", format);
			Object olimittime = findOne.get("limittime");
			if(null != olimittime){
				long limittime =Long.parseLong(olimittime.toString()) ;
				String format1 = sft.format(new Date(limittime));
				findOne.put("limittime", format1);
			}
			
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			
			model.addAttribute("entity",findOne);
			
			return "teacher/workInfo";
		} catch (Exception e) {
			LOGGER.error("findworkInfo方法出错了", e);
			model.addAttribute("msg", "未找到作业相关信息");
			return "err";
		}
		
	}
	
	@RequestMapping("/addwork")
	public String toadd(String classId,Model model){
		try {
			Map<String, Object> lesson = iscLessonService.findLessonByScId(classId);
			currmaxId = iscHomeworkService.getMaxId("isc_homework", "homeworkid");
			model.addAttribute("maxId", currmaxId);
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			model.addAttribute("lesson", lesson);
			model.addAttribute("classId", classId);
			return "teacher/newJob";
		} catch (Exception e) {
			LOGGER.error("addwork出错了",e);
			model.addAttribute("msg", "未找到课程相关信息");
			return "err";
		}
		
	}
	
	@RequestMapping("/doadd")
	public String doadd(IscHomework homework,String limittimeVo,String classId,Model model){
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			long time = sft.parse(limittimeVo).getTime();
			homework.setLimittime(time);
			homework.setState(1);
			homework.setScid(Integer.valueOf(classId));
			homework.setVersiontime(System.currentTimeMillis());
			homework.setCreatetime(System.currentTimeMillis());
			iscHomeworkService.save(homework);
			Map<String, Object> lesson = iscLessonService.findLessonByScId(classId);
			model.addAttribute("lesson", lesson);
			return "redirect:/class/toClass?classId="+classId;
		} catch (Exception e) {
			LOGGER.error("doadd出错了",e);
			model.addAttribute("msg", "添加作业失败");
			return "err";
		}
		
	}
	
	/**
	 * 转到查看学生的课程作业
	 * 
	 */
	@RequestMapping("student/homework")
	public String toStuHomeWork(Integer studentId,Model model){
		model.addAttribute("studentId", studentId);
		return "student/stuHomeWorks";
	}
	
	@RequestMapping("student/dohomework")
	@ResponseBody
	public List<Map<String, Object>> doStuHomeWork(Integer studentId,Integer pageIndex,Integer pageSize){
		try {
			Map<String, Object> findBaseUserInfo = iscSchoolUserService.findBaseUserInfo(studentId);
			if(null != findBaseUserInfo){
				Object object1 = findBaseUserInfo.get("userid");
				List<Map<String, Object>> list= iscHomeworkService.queryHomeWorkByStid(Integer.valueOf(object1.toString()),pageIndex,pageSize);
				for(Map<String, Object> entity : list){
					Object object = entity.get("createtime");
					if(null != object){
						long createtime =Long.parseLong(entity.get("createtime").toString()) ;
						SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String format = sft.format(new Date(createtime));
						entity.put("createtime", format);
					}
					
				}
				return list;
			}
			return null;
		} catch (Exception e) {
			LOGGER.error("doStuHomeWork方法出错了",e);
			return null;
		}
		
	}
	@RequestMapping("/student/workInfo")
	public String getworkInfo(Integer homeworkid,Integer studentId,Model model){
		try {
			Map<String, Object> findBaseUserInfo = iscSchoolUserService.findBaseUserInfo(studentId);
			if(null != findBaseUserInfo){
				Object object1 = findBaseUserInfo.get("userid");
				Map<String, Object> map = iscHomeworkService.queryHomeWorkById(homeworkid, Integer.valueOf(object1.toString()));
				
				long createtime =Long.parseLong(map.get("createtime").toString()) ;
				SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = sft.format(new Date(createtime));
				map.put("createtime", format);
				Object olimittime = map.get("limittime");
				if(null != olimittime){
					long limittime =Long.parseLong(map.get("limittime").toString()) ;
					String format1 = sft.format(new Date(limittime));
					map.put("limittime", format1);
				}
				
				Object oftime = map.get("finishtime");
				if(null != oftime){
					long finishtime =Long.parseLong(oftime.toString()) ;
					String format2 = sft.format(new Date(finishtime));
					map.put("finishtime", format2);
				}
				Object ocorrecttime = map.get("correcttime");
				if(null != ocorrecttime){
					long correcttime =Long.parseLong(ocorrecttime.toString()) ;
					String format2 = sft.format(new Date(correcttime));
					map.put("correcttime", format2);
				}
				model.addAttribute("entity",map);
			}	
			
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			return "student/stuHomeWorkInfo";
		} catch (Exception e) {
			LOGGER.error("/student/workInfo出错了", e);
			model.addAttribute("msg","查询作业信息失败");
			return "err";
		}
		
	}
	
}
