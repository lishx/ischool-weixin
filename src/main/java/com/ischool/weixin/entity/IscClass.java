package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_class database table.
 * 
 */
@Entity
@Table(name="isc_class")
@NamedQuery(name="IscClass.findAll", query="SELECT i FROM IscClass i")
public class IscClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int classid;

	private String alias;

	private String classcode;

	private String classname;

	private byte classno;

	private String cmcode;

	private String infocode;

	private String year;

	public IscClass() {
	}

	public int getClassid() {
		return this.classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClasscode() {
		return this.classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public byte getClassno() {
		return this.classno;
	}

	public void setClassno(byte classno) {
		this.classno = classno;
	}

	public String getCmcode() {
		return this.cmcode;
	}

	public void setCmcode(String cmcode) {
		this.cmcode = cmcode;
	}

	public String getInfocode() {
		return this.infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}