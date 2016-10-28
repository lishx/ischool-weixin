package com.ischool.weixin.hander;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ischool.weixin.tool.WeiXinConfigTools;

public class WeChatFilter implements Filter {
	private final Logger logger = Logger.getLogger(WeChatFilter.class);
	private String defaultHandler = "com.gson.inf.DefaultMessageProcessingHandlerImpl";
	private Properties p;

	public void destroy() {
		this.logger.info("WeChatFilter已经销毁");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Boolean isGet = Boolean.valueOf(request.getMethod().equals("GET"));
		if (isGet.booleanValue())
			doGet(request, response);
		else
			doPost(request, response);
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (WeiXinConfigTools.getInstance().checkSignature(signature,
				timestamp, nonce, echostr)) {
			response.getWriter().write(echostr);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.logger.info("WeChatFilter已经启动！");
	}

}