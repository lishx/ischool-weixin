package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the isc_college database table.
 * 
 */
@Entity
@Table(name="isc_college")
@NamedQuery(name="IscCollege.findAll", query="SELECT i FROM IscCollege i")
public class IscCollege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int collegeid;

	private String address;

	private String brief;

	private String collcode;

	private String collname;

	private String homepage;

	private String infocode;

	private BigInteger inittime;

	private String schcode;

	private byte state;

	private String tel;

	private BigInteger versiontime;

	public IscCollege() {
	}

	public int getCollegeid() {
		return this.collegeid;
	}

	public void setCollegeid(int collegeid) {
		this.collegeid = collegeid;
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

	public String getCollcode() {
		return this.collcode;
	}

	public void setCollcode(String collcode) {
		this.collcode = collcode;
	}

	public String getCollname() {
		return this.collname;
	}

	public void setCollname(String collname) {
		this.collname = collname;
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