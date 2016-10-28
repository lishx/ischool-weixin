package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the isc_college_major database table.
 * 
 */
@Entity
@Table(name="isc_college_major")
@NamedQuery(name="IscCollegeMajor.findAll", query="SELECT i FROM IscCollegeMajor i")
public class IscCollegeMajor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cmid;

	private String cmcode;

	private String collcode;

	private BigInteger inittime;

	private String majcode;

	private String schcode;

	private byte state;

	private BigInteger versiontime;

	public IscCollegeMajor() {
	}

	public int getCmid() {
		return this.cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public String getCmcode() {
		return this.cmcode;
	}

	public void setCmcode(String cmcode) {
		this.cmcode = cmcode;
	}

	public String getCollcode() {
		return this.collcode;
	}

	public void setCollcode(String collcode) {
		this.collcode = collcode;
	}

	public BigInteger getInittime() {
		return this.inittime;
	}

	public void setInittime(BigInteger inittime) {
		this.inittime = inittime;
	}

	public String getMajcode() {
		return this.majcode;
	}

	public void setMajcode(String majcode) {
		this.majcode = majcode;
	}

	public String getSchcode() {
		return this.schcode;
	}

	public void setSchcode(String schcode) {
		this.schcode = schcode;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public BigInteger getVersiontime() {
		return this.versiontime;
	}

	public void setVersiontime(BigInteger versiontime) {
		this.versiontime = versiontime;
	}

}