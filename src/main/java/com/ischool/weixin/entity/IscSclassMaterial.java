/*
 * IscSclassMaterial.java
 * Created on 2016年10月17日 下午2:02:29
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
 * All rights reserved.<br>
 *
 * 文件名称：IscSclassMaterial.java<br>
 * 摘要：课程资料（isc_sclass_material）<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李双文<br>
 * 完成日期：2016年10月17日<br>
 */
@Entity
@Table(name="isc_sclass_material")
@NamedQuery(name="IscSclassMaterial.findAll", query="SELECT i FROM IscSclassMaterial i")
public class IscSclassMaterial implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer lmid;
	
	private Long scid;
	
	private String lmtitle;
	
	private String lminfo;
	
	private Integer lmtype;
	
	private Integer state;

	/**
	 * @return the lmid
	 */
	public Integer getLmid() {
		return lmid;
	}

	/**
	 * @param lmid the lmid to set
	 */
	public void setLmid(Integer lmid) {
		this.lmid = lmid;
	}

	/**
	 * @return the scid
	 */
	public Long getScid() {
		return scid;
	}

	/**
	 * @param scid the scid to set
	 */
	public void setScid(Long scid) {
		this.scid = scid;
	}

	/**
	 * @return the lmtitle
	 */
	public String getLmtitle() {
		return lmtitle;
	}

	/**
	 * @param lmtitle the lmtitle to set
	 */
	public void setLmtitle(String lmtitle) {
		this.lmtitle = lmtitle;
	}

	/**
	 * @return the lminfo
	 */
	public String getLminfo() {
		return lminfo;
	}

	/**
	 * @param lminfo the lminfo to set
	 */
	public void setLminfo(String lminfo) {
		this.lminfo = lminfo;
	}

	/**
	 * @return the lmtype
	 */
	public Integer getLmtype() {
		return lmtype;
	}

	/**
	 * @param lmtype the lmtype to set
	 */
	public void setLmtype(Integer lmtype) {
		this.lmtype = lmtype;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
	
	
}
