package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_school_user database table.
 * 
 */
@Entity
@Table(name="isc_school_user")
@NamedQuery(name="IscSchoolUser.findAll", query="SELECT i FROM IscSchoolUser i")
public class IscSchoolUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int suid;

	private int classid;

	private int cmid;

	private String codeinfo;

	private int collegeid;

	private String data1;

	private String data2;

	private String data3;

	private int schoolid;

	private byte state;

	private int userid;

	private byte usertype;

	public IscSchoolUser() {
	}

	public int getSuid() {
		return this.suid;
	}

	public void setSuid(int suid) {
		this.suid = suid;
	}

	public int getClassid() {
		return this.classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public int getCmid() {
		return this.cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public String getCodeinfo() {
		return this.codeinfo;
	}

	public void setCodeinfo(String codeinfo) {
		this.codeinfo = codeinfo;
	}

	public int getCollegeid() {
		return this.collegeid;
	}

	public void setCollegeid(int collegeid) {
		this.collegeid = collegeid;
	}

	public String getData1() {
		return this.data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return this.data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return this.data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public int getSchoolid() {
		return this.schoolid;
	}

	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public byte getUsertype() {
		return this.usertype;
	}

	public void setUsertype(byte usertype) {
		this.usertype = usertype;
	}

}