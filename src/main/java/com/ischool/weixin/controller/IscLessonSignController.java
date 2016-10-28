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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ischool.weixin.entity.IscLessonSign;
import com.ischool.weixin.service.weixin.IscLessonService;
import com.ischool.weixin.service.weixin.IscLessonSignService;
import com.ischool.weixin.tool.Config;

@RequestMapping("/sign")
@Controller
public class IscLessonSignController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IscLessonSignController.class);
	@Autowired
	private IscLessonSignService iscLessonSignService;
	@Autowired
	private IscLessonService iscLessonService;
	
	@RequestMapping("tosign")
	public String tosign(IscLessonSign iscLessonSign,Model model){
		try {
			Map<String, Object> lessInfo = iscLessonService.findLessInfo(iscLessonSign.getLessonid());
			model.addAttribute("lessInfo", lessInfo);
			model.addAttribute("entity", iscLessonSign);
			return "student/scansignIninfo";
		} catch (Exception e) {
			LOGGER.error("二维码扫描后转到信息页面出错了", e);
			model.addAttribute("msg", "未找到课程信息");
			return "err";
		}
		
		
	}
	@RequestMapping("dosign")
	@ResponseBody
	public Map<String, Object> dosign(IscLessonSign iscLessonSign){
		Map<String, Object> map = new HashMap<>();
		try {
			Map<String, Object> signinfo = iscLessonSignService.findByLidAndSid(iscLessonSign.getLessonid(),iscLessonSign.getStudentid());
			if(null != signinfo ){
				map.put("status", 0);
				map.put("msg","已经签过了");
				return map; 
			}
			
			//查询第一个签到的时间
			long minSignTime =	iscLessonSignService.findMinSignTimeByLid(iscLessonSign.getLessonid());
			String property = Config.getProperty("signTime");
			long signTime = Long.valueOf(property)*60*1000;
			long now = System.currentTimeMillis();
			
			if(signTime+minSignTime < now && 0 < minSignTime ){
				map.put("status", 0);
				map.put("msg","已过签到时间");
				return map; 
			}
			iscLessonSign.setAgent(0);
			iscLessonSign.setRemark("签到了");
			iscLessonSign.setState((byte)1);
			iscLessonSign.setSignintime(System.currentTimeMillis());
			iscLessonSignService.save(iscLessonSign);
			map.put("status",1);
			map.put("msg", "签到成功");
			//如果签到时间大于10分钟，后面签到视为无效
			return map;
		} catch (Exception e) {
			map.put("status",-1);
			map.put("msg","签到异常。未成功签到");
			LOGGER.error("dosign出错了",e);
			return map;
		}
		
	}
	@RequestMapping("dosignByTeacher")
	@ResponseBody
	public Map<String, Object> dosignByTeacher(IscLessonSign iscLessonSign){
		Map<String, Object> map = new HashMap<>();
		try {
			Map<String, Object> signinfo = iscLessonSignService.findByLidAndSid(iscLessonSign.getLessonid(),iscLessonSign.getStudentid());
			if(null != signinfo ){
				map.put("status", 0);
				map.put("msg","已经签过了");
				return map; 
			}
			iscLessonSign.setAgent(1);
			iscLessonSign.setRemark("老师修改");
			iscLessonSign.setState((byte)1);
			iscLessonSign.setSignintime(System.currentTimeMillis());
			iscLessonSignService.save(iscLessonSign);
			map.put("status",1);
			map.put("msg", "签到成功");
			return map;
		} catch (Exception e) {
			map.put("status",-1);
			map.put("msg","签到异常。未成功签到");
			LOGGER.error("dosignByTeacher出错了", e);
			return map;
		}
		
	}
	
	/**
	 * 老师查看今日签到
	 * @param classId
	 * @param lessonno
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkTodaySignIn")
	public String checkTodaySignIn(Integer classId,Model model){
		try {
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			List<Map<String, Object>> list = iscLessonSignService.queryToDayStuSigns(classId);
			for(Map<String, Object> map : list){
				long object =Long.parseLong(map.get("signintime").toString()) ;
				String format = sft.format(new Date(object));
				map.put("signintime", format);
			}
			
			List<Map<String, Object>> notlist = iscLessonSignService.queryToDayStuNotSigns(classId);
			/*for(Map<String, Object> map : notlist){
				long object =Long.parseLong(map.get("signintime").toString()) ;
				String format = sft.format(new Date(object));
				map.put("signintime", format);
			}*/
			model.addAttribute("list", list);
			model.addAttribute("notlist", notlist);
			model.addAttribute("controller", "checkTodaySignIn");
			model.addAttribute("classId", classId);
			return "teacher/checkSignIn";
		} catch (Exception e) {
			LOGGER.error("checkTodaySignIn出错了", e);
			model.addAttribute("msg", "查询签到信息失败");
			return "err";
		}
		
	}
	/**
	 * 老师查看签到
	 * @param classId
	 * @param lessonno
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkSignIn")
	public String checkSignIn(Integer classId,Integer lessonno,Model model){
		try {
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Map<String, Object>> list = iscLessonSignService.queryStuSigns(classId,lessonno);
			
			for(Map<String, Object> map : list){
				long object =Long.parseLong(map.get("signintime").toString()) ;
				String format = sft.format(new Date(object));
				map.put("signintime", format);
			}
			
			List<Map<String, Object>> notlist = iscLessonSignService.queryStuNotSigns(classId,lessonno);
			
			/*for(Map<String, Object> map : notlist){
				long object =Long.parseLong(map.get("signintime").toString()) ;
				String format = sft.format(new Date(object));
				map.put("signintime", format);
			}*/
			model.addAttribute("list", list);
			model.addAttribute("notlist", notlist);
			model.addAttribute("classId", classId);
			model.addAttribute("controller", "checkSignIn");
			return "teacher/checkSignIn";
		} catch (Exception e) {
			LOGGER.error("checkSignIn出错了", e);
			model.addAttribute("msg", "查询签到信息失败");
			return "err";
		}
		
	}
	
	
	/**
	 * 查看签到列表
	 */
	
	@RequestMapping("dogetsign")
	@ResponseBody
	public List<Map<String, Object>> dogetsignList(Integer pageIndex,Integer pageSize,Integer userId,Model model){
	try {
		if(null == pageIndex){
			pageIndex =1;
		}
		if(null == pageSize){
			pageSize = 7;
		}
		
		List<Map<String, Object>> list = iscLessonSignService.queryStuSignInfo(userId,pageIndex,pageSize);
		for(Map<String, Object> map :list){
			long signintime =Long.parseLong(map.get("signintime").toString()) ;
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sft.format(new Date(signintime));
			map.put("signintime", format);
		}
		
		return list;
	} catch (Exception e) {
		LOGGER.error("dogetsignList出错了", e);
		return null;
	}
		
	}
	
	/**
	 * 查询签到信息
	 * @param lsid
	 * @param model
	 * @return
	 */
	@RequestMapping("/getsignInfo")
	public String toSignInfo(Integer lsid,Model model){
		try {
			Map<String, Object> map = iscLessonSignService.findSignInfo(lsid);
			
			long signintime =Long.parseLong(map.get("signintime").toString()) ;
			SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sft.format(new Date(signintime));
			map.put("signintime", format);
			
			model.addAttribute("entity", map);
			return "student/viewsignIninfo";
		} catch (Exception e) {
			LOGGER.error("getsignInfo出错了", e);
			model.addAttribute("msg","查询签到信息失败");
			return "err";
		}
		
	}
	
}
