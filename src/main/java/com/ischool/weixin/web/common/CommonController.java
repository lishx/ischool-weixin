package com.ischool.weixin.web.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CommonController.PR_FORWORD)
public class CommonController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommonController.class);

	public static final String PR_FORWORD = "/ep/weixin/common";
	private static final String PR_JSP = "/ep/weixin/common";
	public static final String PR_REDIRECT = "redirect:".concat(PR_FORWORD);

	public static final String URL_SUCCESS = PR_REDIRECT.concat("/toSuccess");
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_NEXT_URL = "next_url";
	public static final String KEY_BACK_URL = "back_url";

	@RequestMapping("/toSuccess")
	public String toSuccess(ModelMap map, HttpServletRequest request) {
		map.addAttribute("KEY_MESSAGE", KEY_MESSAGE);
		map.addAttribute("KEY_NEXT_URL", KEY_NEXT_URL);
		map.addAttribute("KEY_BACK_URL", KEY_BACK_URL);
		return PR_JSP.concat("/success");
	}
}
