/*
 * IscWeichat.java
 * Created on 2016年10月11日 上午11:19:24
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.ischool.weixin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：IscWeichat.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李双文<br>
 * 完成日期：2016年10月11日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李双文<br>
 * 完成日期：2016年10月11日<br>
 */
@Entity
@Table(name="isc_weichat")
@NamedQuery(name="IscWeichat.findAll", query="SELECT i FROM IscWeichat i")
public class IscWeichat implements Serializable{

	private static final long serialVersionUID = -3123463828690855349L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int weichatid;
	
	private String weichat;
	
	private String nickname;
	
	private long inittime;
	
	private String sex;
	
	private String nationality;
	
	private String province;
	
	private String city;
	
	private String fullname;
	
	private long phone;

	public IscWeichat() {
	}

	/**
	 * @return the weichatid
	 */
	public int getWeichatid() {
		return weichatid;
	}

	/**
	 * @param weichatid the weichatid to set
	 */
	public void setWeichatid(int weichatid) {
		this.weichatid = weichatid;
	}

	/**
	 * @return the weichat
	 */
	public String getWeichat() {
		return weichat;
	}

	/**
	 * @param weichat the weichat to set
	 */
	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the inittime
	 */
	public long getInittime() {
		return inittime;
	}

	/**
	 * @param inittime the inittime to set
	 */
	public void setInittime(long inittime) {
		this.inittime = inittime;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		this.phone = phone;
	}

	

	

}
