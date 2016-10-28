package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_subject database table.
 * 
 */
@Entity
@Table(name="isc_subject")
@NamedQuery(name="IscSubject.findAll", query="SELECT i FROM IscSubject i")
public class IscSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int subjectid;

	private String brief;

	private String subcode;

	private String subname;

	public IscSubject() {
	}

	public int getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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