package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_subject_class database table.
 * 
 */
@Entity
@Table(name="isc_subject_class")
@NamedQuery(name="IscSubjectClass.findAll", query="SELECT i FROM IscSubjectClass i")
public class IscSubjectClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private int scid;
	private String scname;
	private byte state;
	private int teacherid;
	private int year;
	private int tsid;

	public IscSubjectClass() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getScid() {
		return this.scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}


	public String getScname() {
		return this.scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}


	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}


	public int getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}


	public int getTsid() {
		return this.tsid;
	}

	public void setTsid(int tsid) {
		this.tsid = tsid;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

}