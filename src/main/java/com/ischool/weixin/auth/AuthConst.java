package com.ischool.weixin.auth;

public class AuthConst {

	/**
	 * 登录用户类型
	 * 
	 * @author WXL
	 *
	 */
	public static enum AUTH_USER_TYPE {
		sysuser("1000", "系统用户"), customer("1001", "客户");
		private AUTH_USER_TYPE(String code, String name) {
			this.code = code;
			this.name = name;
		}

		private String code;
		private String name;

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	public static final String WEIXIN_AUTH_OPENID_KEY = "weixin_auth_openid";
}
