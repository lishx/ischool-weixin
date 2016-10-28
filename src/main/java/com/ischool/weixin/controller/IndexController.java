package com.ischool.weixin.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ischool.weixin.constant.ConstantClassField;
import com.ischool.weixin.constant.ConstantClassField.USER_TYPE;
import com.ischool.weixin.entity.IscClass;
import com.ischool.weixin.entity.IscWeichat;
import com.ischool.weixin.service.weixin.IscClassService;
import com.ischool.weixin.service.weixin.IscSchoolUserService;
import com.ischool.weixin.service.weixin.IscSubjectClassService;
import com.ischool.weixin.service.weixin.IscWeichatService;
import com.ischool.weixin.tool.Config;
import com.ischool.weixin.tool.MessageUtil;
import com.ischool.weixin.tool.WeiXinConfigTools;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IndexController.class);
	@Autowired
	private IscSchoolUserService iscSchoolUserService;
	@Autowired
	private IscSubjectClassService iscSubjectClassService;
	@Autowired
	private IscClassService iscClassService;
	@Autowired
	private IscWeichatService iscWeichatService;
	WeiXinConfigTools  wt = WeiXinConfigTools.getInstance();
	
	@RequestMapping("/handEvent")
	public void handEvent(HttpServletRequest request){
		try {
			Map<String, String> map = MessageUtil.parseXml(request);
			
			 // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
          //  String toUserName = map.get("ToUserName");
            // 消息类型
            String msgType = map.get("MsgType");
            
			if (msgType.equals(ConstantClassField.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = map.get("Event");
                // 关注
                if (eventType.equals(ConstantClassField.EVENT_TYPE_SUBSCRIBE)) {
                	
                	WeiXinConfigTools wt = WeiXinConfigTools.getInstance();
                	String access_token = wt.getAccessToken();
                	Map<String, String> userinfo = wt.getUserinfo(access_token, fromUserName);
                	
                	IscWeichat weichat = new IscWeichat();
                	
                	weichat.setCity(MapUtils.getString(userinfo, "city"));
                	//weichat.setFullname(fullname);
                	weichat.setInittime(MapUtils.getLongValue(userinfo, "subscribe_time"));
                	weichat.setNationality(MapUtils.getString(userinfo, "country"));
                	weichat.setNickname(MapUtils.getString(userinfo,"nickname"));
                	weichat.setProvince(MapUtils.getString(userinfo,"province"));
                	String sex =MapUtils.getString(userinfo, "sex");
                	switch (sex) {
					case "1":
						weichat.setSex("男");
						break;
					case "2":
						weichat.setSex("女");
						break;
					default:
						weichat.setSex("未知");
						break;
					}
                	weichat.setWeichat(MapUtils.getString(userinfo,"openid"));
                	iscWeichatService.save(weichat);
                	
                }
                // 取消关注
                else if (eventType.equals(ConstantClassField.EVENT_TYPE_UNSUBSCRIBE)) {
                    //  取消订阅后用户不会再收到公众账号发送的消息
                	iscWeichatService.deleteByOpenId(fromUserName);
                }
               
            }
		} catch (Exception e) {
			LOGGER.error("接收事件处理失败",e);
		}
		
	}
	
	/*@RequestMapping("/createmenu")
	public void createMenu(){
		String accessToken = wt.getAccessToken();
		
		String result = null;
		HttpClient client = new HttpClient();
		String url="";
		HttpMethod getMethod = new PostMethod(url.toString());
		try {
			getMethod.getParams()
			.setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			
			result = getMethod.getResponseBodyAsString();
			getMethod.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	@RequestMapping("/valid")
	@ResponseBody
	public String valid(Long timestamp,String nonce,String Signature,String echostr){
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(Signature);
		System.out.println(echostr);
		return echostr;
	}
	/**
	 * 需要微信授权接口统一入口
	 * @param code
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getcode")
	public static String getcode(String code,HttpServletRequest request,Model model){
		try {
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
			WeiXinConfigTools  wt = WeiXinConfigTools.getInstance();
			
			String oAuth2Url = wt.getOAuth2Url(tempContextUrl+code);
		
			return "redirect:"+oAuth2Url;
		} catch (Exception e) {
			LOGGER.error("getcode方法出错了", e);
			model.addAttribute("msg", "获取授权URL失败");
			return "err";
		}
		
	}
	
	/**
	 * 转到学生查看签到
	 */
	@RequestMapping("togetsign")
	public String togetsign(String code,HttpServletRequest request,Model model){
		try {
			WeiXinConfigTools configTools = WeiXinConfigTools.getInstance();
			
			model.addAttribute("appId", configTools.getAppId());// 必填，公众号的唯一标识
			String timestamp = System.currentTimeMillis() + "";
			model.addAttribute("timestamp", timestamp); // 必填，生成签名的时间戳
			String nonceStr = UUID.randomUUID().toString();
			model.addAttribute("nonceStr", nonceStr); // 必填，生成签名的随机串
			String QueryString = request.getQueryString();
			if (QueryString != null && QueryString != "null") {
				model.addAttribute("signature",
						configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString() + "?" + request.getQueryString(), nonceStr, timestamp));// 必填，签名，见附录1
			} else {
				model.addAttribute("signature", configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString(), nonceStr, timestamp));// 必填，签名，见附录1
			}
			
			Map<String, String> accessTokenMap = configTools.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账户基本信息失败");
				return "err";
			}
			
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null == findUserInfo){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
 			Object object = findUserInfo.get("usertype");
			if(!USER_TYPE.student.getCode().equals(object.toString())){
				model.addAttribute("msg", "你还不是学生");
				return "err";
			}
			model.addAttribute("userId", findUserInfo.get("suid"));
			return "student/signinlist";
		} catch (Exception e) {
			LOGGER.error("togetsign方法出错了", e);
			model.addAttribute("msg", "获取用户信息失败");
			return "err";
		}
		
	}
	
	
	@RequestMapping("/oauth")
	public String oauth(String type,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			if(null == type){
				type =USER_TYPE.student.getCode();
			}
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
			
			String oAuth2Url = wt.getOAuth2Url(tempContextUrl+"bind?type="+type);
			return "redirect:"+oAuth2Url;
		} catch (Exception e) {
			LOGGER.error("oauth方法出错了", e);
			model.addAttribute("msg", "获取权限Url失败");
			return "err";
		}
		
	}
	/**
	 * 转到用户信息页面
	 * @param code 微信授权code
	 * @param state state
	 * @param type 标示老师还是学生
	 * @param model
	 * @return
	 */
	@RequestMapping("/scan")
	public String scan(String code,String state,String type,HttpServletRequest request,Model model){
		try {
			WeiXinConfigTools configTools = WeiXinConfigTools.getInstance();
			
			model.addAttribute("appId", configTools.getAppId());// 必填，公众号的唯一标识
			String timestamp = System.currentTimeMillis() + "";
			model.addAttribute("timestamp", timestamp); // 必填，生成签名的时间戳
			String nonceStr = UUID.randomUUID().toString();
			model.addAttribute("nonceStr", nonceStr); // 必填，生成签名的随机串
			String QueryString = request.getQueryString();
			if (QueryString != null && QueryString != "null") {
				model.addAttribute("signature",
						configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString() + "?" + request.getQueryString(), nonceStr, timestamp));// 必填，签名，见附录1
			} else {
				model.addAttribute("signature", configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString(), nonceStr, timestamp));// 必填，签名，见附录1
			}
			
			Map<String, String> accessTokenMap = configTools.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账户基本信息失败");
				return "err";
			}
			
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null == findUserInfo || !findUserInfo.containsKey("suid")){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
			Object object = findUserInfo.get("usertype");
			if(!USER_TYPE.student.getCode().equals(object.toString())){
				model.addAttribute("msg", "你还不是学生");
				return "err";
			}
			
			model.addAttribute("userId", findUserInfo.get("suid"));
			return "student/signIn";
		} catch (Exception e) {
			LOGGER.error("签到失败", e);
			model.addAttribute("msg", "获取用户信息失败");
			return "err";
		}
		
	}
	
	/**
	 * 转到用户信息页面
	 * @param code 微信授权code
	 * @param state state
	 * @param type 标示老师还是学生
	 * @param model
	 * @return
	 */
	@RequestMapping("/bind")
	public String bind(String code,String state,String type,Model model){
		try {
		//String openId = wt.getOpenId(code);
		Map<String, String>  accessTokenMap = wt.getAccessToken(code);
		String openid =MapUtils.getString(accessTokenMap, "openid");
		if(StringUtils.isEmpty(openid)){
			model.addAttribute("msg","获取微信账号基本信息失败");
			return "err";
		}
		model.addAttribute("OpenID", openid);
		if(USER_TYPE.teacher.getCode().equals(type) || "0".equals(type)){
			//查询是否绑定
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null != findUserInfo && !findUserInfo.isEmpty()){
				model.addAttribute("bean", findUserInfo);
				return "teacher/registered";
			}
			List<Map<String, Object>> queryTeacherList = iscSchoolUserService.queryAllTeachersInfo();
			model.addAttribute("queryTeacherList", queryTeacherList);
			return "teacher/info_Binding";
		}else{
			//查询是否绑定
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null != findUserInfo && !findUserInfo.isEmpty()){
				model.addAttribute("bean", findUserInfo);
				return "student/sturegistered";
			}
			
			List<IscClass> list = iscClassService.queryAllList();
			model.addAttribute("classList", list);
			return "student/stubinding";
		}
		
		} catch (Exception e) {
			if(USER_TYPE.teacher.getCode().equals(type)){
				model.addAttribute("msg", "获取老师列表数据失败");
			}else{
				model.addAttribute("msg", "获取班级列表数据失败");
			}
			LOGGER.error("bind方法出错了", e);
		}
		return "err";
	}
	/**
	 * 学生教学教务入口
	 * @param code 微信授权code
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/studentMain")
	public String toStuMain(String code,HttpServletRequest request,Model model){
		try {
			WeiXinConfigTools configTools = WeiXinConfigTools.getInstance();
			Map<String, String>  accessTokenMap = wt.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账号基本信息失败");
				return "err";
			}
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null == findUserInfo || !findUserInfo.containsKey("suid")){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
			Object object = findUserInfo.get("usertype");
			if(!USER_TYPE.student.getCode().equals(object.toString())){
				model.addAttribute("msg", "你还不是学生");
				return "err";
			}
			Object studentId = findUserInfo.get("suid");
			
			Map<String, Object> user = iscSchoolUserService.findUserInfo((Integer) studentId);
			if(null == user){
				model.addAttribute("msg", "查询学生信息失败");
				return "err";
			}
			
			model.addAttribute("studentId", studentId);
			
			model.addAttribute("appId", wt.getAppId());// 必填，公众号的唯一标识
			String timestamp = System.currentTimeMillis() + "";
			model.addAttribute("timestamp", timestamp); // 必填，生成签名的时间戳
			String nonceStr = UUID.randomUUID().toString();
			model.addAttribute("nonceStr", nonceStr); // 必填，生成签名的随机串
			String QueryString = request.getQueryString();
			if (QueryString != null && QueryString != "null") {
				model.addAttribute("signature",
						configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString() + "?" + request.getQueryString(), nonceStr, timestamp));// 必填，签名，见附录1
			} else {
				model.addAttribute("signature", configTools.getJsapiTicketSignatureUrl(request.getRequestURL().toString(), nonceStr, timestamp));// 必填，签名，见附录1
			}
			return "student/main";
		} catch (Exception e) {
			LOGGER.error("toStuMain方法出错了", e);
			model.addAttribute("msg", "查询学生信息失败");
			return "err";
		}
		
	}
	
	
	/**
	 * 查询老师教的班级 教学教务
	 * @param model
	 * @return
	 */
	@RequestMapping("/teaMain")
	public String teaMain(String code ,Model model){
		try {
			Map<String, String>  accessTokenMap = wt.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账号基本信息失败");
				return "err";
			}
			
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			if(null == findUserInfo || !findUserInfo.containsKey("suid")){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
			Object suid = findUserInfo.get("suid");
			
			Map<String, Object> user = iscSchoolUserService.findBaseUserInfo((Integer) suid);
			if(null == user || !USER_TYPE.teacher.getCode().equals(user.get("usertype").toString())){
				model.addAttribute("msg", "你还不是老师");
				return "err";
			}
			Object teacherId = findUserInfo.get("userid");
			model.addAttribute("teacherId", teacherId);
			
			List<Map<String, Object>> queryClass = iscSubjectClassService.queryClassByTid((Integer)teacherId);
			model.addAttribute("list", queryClass);
			return "teacher/teaMain";
		} catch (Exception e) {
			LOGGER.error("teaMain方法出错了", e);
			model.addAttribute("msg", "查询课程班级出错了");
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
	@RequestMapping("/checkTodaySignIn")
	public String checkTodaySignIn(String code ,Model model){
		try {
			Map<String, String>  accessTokenMap = wt.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账号基本信息失败");
				return "err";
			}
			
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			
			if(null == findUserInfo || !findUserInfo.containsKey("suid")){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
			
			Object suid = findUserInfo.get("suid");
			
			Map<String, Object> user = iscSchoolUserService.findBaseUserInfo((Integer) suid);
			if(null == user || !USER_TYPE.teacher.getCode().equals(user.get("usertype").toString())){
				model.addAttribute("msg", "你还不是老师");
				return "err";
			}
			Object teacherId = user.get("userid");
			model.addAttribute("teacherId", teacherId);
			
			List<Map<String, Object>> queryClass = iscSubjectClassService.queryClassByTid((Integer)teacherId);
			model.addAttribute("list", queryClass);
			return "teacher/checktodaysignin";
		} catch (Exception e) {
			LOGGER.error("checkTodaySignIn方法出错了", e);
			model.addAttribute("msg","查询课程班级出错");
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
	public String subjectClass(String code ,Model model){
		try {
			Map<String, String>  accessTokenMap = wt.getAccessToken(code);
			String openid =MapUtils.getString(accessTokenMap, "openid");
			if(StringUtils.isEmpty(openid)){
				model.addAttribute("msg","获取微信账号基本信息失败");
				return "err";
			}
			
			Map<String, Object> findUserInfo = iscSchoolUserService.findUserInfo(openid);
			
			if(null == findUserInfo || !findUserInfo.containsKey("suid")){
				model.addAttribute("msg", "未绑定用户信息");
				return "err";
			}
			
			Object suid = findUserInfo.get("suid");
			
			Map<String, Object> user = iscSchoolUserService.findBaseUserInfo((Integer) suid);
			if(null == user ){
				model.addAttribute("msg", "未找到用户信息");
				return "err";
			}
			//老师
			if(USER_TYPE.teacher.getCode().equals(user.get("usertype").toString())){
				Object teacherId = user.get("userid");
				List<Map<String, Object>> queryClass = iscSubjectClassService.queryClassByTid((Integer)teacherId);
				model.addAttribute("list", queryClass);
				
			}else if(USER_TYPE.student.getCode().equals(user.get("usertype").toString())){
				Object studentId = user.get("userid");
				//学生
				List<Map<String, Object>> queryClass = iscSubjectClassService.queryClassBySid((Integer)studentId);
				model.addAttribute("list", queryClass);	
				
			}
			return "subjectClass";
			
			
		} catch (Exception e) {
			LOGGER.error("subjectClass方法出错了", e);
			model.addAttribute("msg","查询课程班级出错");
			return "err";
		}
		
	}
	
	@RequestMapping("/tellmsg")
	public String tellmsg(Integer scid,Integer suid,Model model) {
		try {
			Map<String, Object> user = iscSchoolUserService.findBaseUserInfo(suid);
			
			model.addAttribute("scid", scid);
			model.addAttribute("suid", suid);
			model.addAttribute("user", user);
			model.addAttribute("fileServer", Config.getProperty("file_server_url"));
			model.addAttribute("server", Config.getProperty("xmpp.server"));
			model.addAttribute("bosh_service", Config.getProperty("xmpp.bosh_service"));
			model.addAttribute("room", Config.getProperty("xmpp.room"));
			
			return "tellmsg";
		} catch (Exception e) {
			LOGGER.error("tellmsg方法出错了", e);
			model.addAttribute("msg","查询课程班级出错");
			return "err";
		}
		
		
	}
	
	
}
