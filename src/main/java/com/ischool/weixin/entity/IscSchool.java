package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the isc_school database table.
 * 
 */
@Entity
@Table(name="isc_school")
@NamedQuery(name="IscSchool.findAll", query="SELECT i FROM IscSchool i")
public class IscSchool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int schoolid;

	private String address;

	private String brief;

	private String homepage;

	private String infocode;

	private BigInteger inittime;

	private String schcode;

	private String schname;

	private byte state;

	private String tel;

	private BigInteger versiontime;

	public IscSchool() {
	}

	public int getSchoolid() {
		return this.schoolid;
	}

	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getInfocode() {
		return this.infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public BigInteger getInittime() {
		return this.inittime;
	}

	public void setInittime(BigInteger inittime) {
		this.inittime = inittime;
	}

	public String getSchcode() {
		return this.schcode;
	}

	public void setSchcode(String schcode) {
		this.schcode = schcode;
	}

	public String getSchname() {
		return this.schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public BigInteger getVersiontime() {
		return this.versiontime;
	}

	public void setVersiontime(BigInteger versiontime) {
		this.versiontime = versiontime;
	}

}