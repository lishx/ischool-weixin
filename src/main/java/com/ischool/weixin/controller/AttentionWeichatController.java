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

import com.ischool.weixin.constant.ConstantClassField.USER_TYPE;
import com.ischool.weixin.entity.IscAttentionWeichat;
import com.ischool.weixin.entity.IscClass;
import com.ischool.weixin.service.weixin.IscAttentionWeichatService;
import com.ischool.weixin.service.weixin.IscClassService;
import com.ischool.weixin.service.weixin.IscSchoolUserService;

@RequestMapping("/guanzu")
@Controller
public class AttentionWeichatController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AttentionWeichatController.class);
	@Autowired
	private IscSchoolUserService iscSchoolUserService;
	@Autowired
	private IscAttentionWeichatService iscAttentionWeichatService;
	
	@RequestMapping("/getStudents")
	@ResponseBody
	public List<Map<String, Object>> getStudentByClassId(String classId){
		try {
			List<Map<String, Object>> list = iscSchoolUserService.getStudentByClassId(classId);
			return list;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return null;
		}
		
	};
	
	/**
	 * bind用戶信息
	 * @return
	 */
	@RequestMapping("/dobind")
	public String bind(IscAttentionWeichat weichat,String phone,Model model) { 
		try {
			weichat.setAttentiontime(System.currentTimeMillis());
			iscAttentionWeichatService.save(weichat);
			
			Map<String, Object> map = iscSchoolUserService.findUserInfo(weichat.getWeichat());
			model.addAttribute("bean", map);
			if(USER_TYPE.teacher.getCode().equals(map.get("usertype"))){
				return "teacher/registered";
			}else{
				return "student/sturegistered";
			}
		} catch (Exception e) {
			LOGGER.error("用户绑定信息错误", e);
			model.addAttribute("msg", "绑定用户信息失败");
			return "err";
		}
		
	}
}
