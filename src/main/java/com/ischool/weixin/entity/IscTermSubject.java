package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_term_subject database table.
 * 
 */
@Entity
@Table(name="isc_term_subject")
@NamedQuery(name="IscTermSubject.findAll", query="SELECT i FROM IscTermSubject i")
public class IscTermSubject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tsid;
	private String cmcode;
	private String desc;
	private byte grade;
	private byte semester;
	private String shortname;
	private byte state;
	private String subcode;
	private String subname;

	public IscTermSubject() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getTsid() {
		return this.tsid;
	}

	public void setTsid(int tsid) {
		this.tsid = tsid;
	}


	public String getCmcode() {
		return this.cmcode;
	}

	public void setCmcode(String cmcode) {
		this.cmcode = cmcode;
	}


	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public byte getGrade() {
		return this.grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}


	public byte getSemester() {
		return this.semester;
	}

	public void setSemester(byte semester) {
		this.semester = semester;
	}


	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}


	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}


	public String getSubcode() {
		return this.subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}


	public String getSubname() {
		return this.subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}


}