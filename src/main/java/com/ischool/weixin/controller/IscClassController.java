package com.ischool.weixin.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ischool.weixin.constant.ConstantClassField.USER_TYPE;
import com.ischool.weixin.service.weixin.IscLessonService;
import com.ischool.weixin.service.weixin.IscSchoolUserService;
import com.ischool.weixin.service.weixin.IscSubjectClassService;

/**
 * 课程班级（学科班级定义）
 * @author Administrator
 *
 */
@RequestMapping("/class")
@Controller
public class IscClassController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IscClassController.class);
	@Autowired
	private IscLessonService iscLessonService;
	
	@Autowired
	private IscSchoolUserService iscSchoolUserService;
	@Autowired
	private IscSubjectClassService iscSubjectClassService;
	
	/**
	 * 转到teaching页面
	 * @param classId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toClass")
	public String toClass(Integer classId,Model model){
		try {
			if(StringUtils.isEmpty(classId)){
				model.addAttribute("msg", "课程班级未获取到");
				return "err";
			}
			List<Map<String, Object>> lessonNo =  iscLessonService.queryLessonNoByClassId(classId);
			model.addAttribute("classId", classId);
			model.addAttribute("lessonNo", new Gson().toJson(lessonNo));
			return "teacher/teaching";
		} catch (Exception e) {
			LOGGER.error("toClass方法出错了", e);
			model.addAttribute("msg", "查询出错了");
			return "err";
		}
		
	}
	
	/**
	 * 老师查看今日签到
	 * @param classId
	 * @param lessonno
	 * @param model
	 * @return
	 */
	@RequestMapping("/subjectClass")
	public String subjectClass(String suid,String lmtype,Model model){
		try {
			
			Map<String, Object> user = iscSchoolUserService.findBaseUserInfo(Integer.valueOf(suid));
			if(null == user ){
				model.addAttribute("msg", "未找到用户信息");
				return "err";
			}
			Object studentId = user.get("userid");
			//学生
			List<Map<String, Object>> queryClass = iscSubjectClassService.queryClassBySid((Integer)studentId);
			model.addAttribute("list", queryClass);	
			model.addAttribute("lmtype",lmtype);
			return "student/subjectClass";
			
		} catch (Exception e) {
			LOGGER.error("学生转到subjectClass方法出错了", e);
			model.addAttribute("msg","查询学生课程班级出错");
			return "err";
		}
		
	}
	
}
