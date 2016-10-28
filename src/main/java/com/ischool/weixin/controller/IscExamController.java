package com.ischool.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ischool.weixin.entity.IscExam;
import com.ischool.weixin.service.weixin.IscExamService;
import com.ischool.weixin.service.weixin.IscLessonService;
import com.ischool.weixin.service.weixin.IscSchoolUserService;

@RequestMapping("/exam")
@Controller
public class IscExamController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IscExamController.class);
	@Autowired
	private IscExamService iscExamService;
	@Autowired
	private IscLessonService iscLessonService;
	@Autowired
	private IscSchoolUserService iscSchoolUserService;
	/*@Autowired
	private IscSubjectClassService iscSubjectClassService;*/

	/**
	 * 老师查看历史考试安排
	 * @param classId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toksap")
	public String toksap(String classId,Model model){
		
		model.addAttribute("classId", classId);
		return "teacher/exam_Arrangement";
	}
	
	
	
	/**
	 * 学生查看考试安排
	 * @param classId
	 * @param model
	 * @return
	 */
	@RequestMapping("student/examarrangement")
	public String examArrangement(Integer studentId ,Model model){
	
		
		model.addAttribute("studentId", studentId);
		return "student/examArrangement";
	}
	
	/**
	 * 老师查看考试安排
	 * @date 2016年9月18日 上午10:35:31
	 * @author 李双文
	 * @param classId
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 */
	@RequestMapping("/dolist")
	@ResponseBody
	public List<Map<String, Object>> getArrangement(String classId,Integer pageIndex,Integer pageSize){
		try {
			if(null == pageIndex){
				pageIndex = 0;
			}
			
			if(null == pageSize){
				pageSize = 10;
			}
			Map<String, Object> params = new HashMap<>();
			params.put("classId", classId);
			List<Map<String, Object>> list = iscExamService.queryPageForMap(params,pageIndex,pageSize);
			return list;
		} catch (Exception e) {
			LOGGER.error("dolist方法出错了", e);
			return null;
		}
		
	}
	
	@RequestMapping("student/dolist")
	@ResponseBody
	public List<Map<String, Object>> queryArrangement(String studentId,Integer pageIndex,Integer pageSize){
		
		try {
			if(null == pageIndex){
				pageIndex = 0;
			}
			if(null == pageSize){
				pageSize = 10;
			}
			Map<String, Object> params = new HashMap<>();
			Map<String, Object> findBaseUserInfo = iscSchoolUserService.findBaseUserInfo(Integer.valueOf(studentId));
			
			if(null != findBaseUserInfo){
				Object object = findBaseUserInfo.get("userid");
				if(null != object){
					studentId = object.toString();	
				}
				
			}
			params.put("studentId", studentId);
			List<Map<String, Object>> list = iscExamService.queryArrangementList(params,pageIndex,pageSize);
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(Map<String, Object> map : list){
				long object =Long.parseLong(map.get("examtime").toString()) ;
				String format = sft.format(new Date(object));
				map.put("examtime", format);
			}
			
			return list;
		} catch (Exception e) {
			LOGGER.error("queryArrangement方法出错了", e);
			return null;
		}
		
	}
	
	@RequestMapping("aboutExam")
	public String aboutArrangement(Integer examid,Model model){
		try {
			Map<String, Object>	entity = iscExamService.findForMap(examid);
			Object object = entity.get("examtime");
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(null != object){
				long examtime =Long.parseLong(object.toString()) ;
				
				String format = sft.format(new Date(examtime));
				entity.put("examtime", format);
			}
			
			Object object2 = entity.get("endtime");
			if(null != object2){
				long endtime =Long.parseLong(object2.toString()) ;
				String format1 = sft.format(new Date(endtime));
				entity.put("endtime", format1);
			}
			
			model.addAttribute("entity", entity);
			return "exam_AboutArrangement";
		} catch (Exception e) {
			LOGGER.error("aboutArrangement方法出错了",e);
			model.addAttribute("msg", "查询考试相关信息失败");
			return "err";
		}
		
	}
	
	@RequestMapping("toadd")
	public String toadd(String classId,Model model){
		try {
			Map<String, Object> lesson = iscLessonService.findLessonByScId(classId);
			model.addAttribute("lesson", lesson);
			model.addAttribute("classId", classId);
			return "teacher/addexam";
		} catch (Exception e) {
			LOGGER.error("toadd方法出错了",e);
			model.addAttribute("msg","课程信息未获取到");
			return "err";
		}
		
	}
	
	@RequestMapping("doadd")
	public String doadd(IscExam exam, String examtimeVo, String endtimeVo, Model model) {
		try {
			if (StringUtils.isEmpty(endtimeVo) || StringUtils.isEmpty(endtimeVo)) {
				return "err";
			}
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long examtime = sft.parse(examtimeVo).getTime();
			long endtime = sft.parse(endtimeVo).getTime();
			exam.setEndtime(endtime);
			exam.setExamtime(examtime);
			exam.setState(1);
			iscExamService.save(exam);

			return "redirect:/class/toClass?classId="+exam.getScid();
		} catch (Exception e) {
			LOGGER.error("doadd方法出错了", e);
			return "err";
		}

	}
}
