package com.ischool.weixin.auth;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ischool.weixin.tool.WeiXinConfigTools;

public class WeiXinAuthInterceptor implements HandlerInterceptor {

	private static final String PREV_REQ_URL_KEY = "prev_req_url";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!isAuth(request)) {
			String resultUrl = getRequestURL(request, true);
			String redirectUrl = WeiXinConfigTools.getInstance().getOAuth2Url(
					resultUrl);
			// redirectUrl = redirectUrl + prevReqURLKey + "=" + resultUrl;
			response.sendRedirect(redirectUrl);
			return false;
		}
		return true;
	}

	private String getRequestURL(HttpServletRequest request, boolean encode) {
		String resultUrl = request.getRequestURL().toString();
		String param = request.getQueryString();
		if (param != null) {
			resultUrl += "?" + param;
		}
		if (encode) {
			try {
				resultUrl = java.net.URLEncoder.encode(resultUrl, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return resultUrl;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (isConstCode(request)) {
			String code = request.getParameter("code");
			String openId = WeiXinConfigTools.getInstance().getOpenId(code);
			HttpSession session = request.getSession();
			session.setAttribute(AuthConst.WEIXIN_AUTH_OPENID_KEY, openId);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private boolean isAuth(HttpServletRequest request) {
		return isConstOpenId(request) || isConstCode(request);
	}

	private boolean isConstOpenId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object openIdObj = session
				.getAttribute(AuthConst.WEIXIN_AUTH_OPENID_KEY);
		if (openIdObj != null && StringUtils.isNoneBlank(openIdObj.toString())) {
			return true;
		}
		return false;
	}

	private boolean isConstCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (StringUtils.isNotBlank(code) && StringUtils.equals("auth", state)) {
			return true;
		}
		return false;
	}
}
