package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_lesson_sign database table.
 * 
 */
@Entity
@Table(name="isc_lesson_sign")
@NamedQuery(name="IscLessonSign.findAll", query="SELECT i FROM IscLessonSign i")
public class IscLessonSign implements Serializable {
	private static final long serialVersionUID = 1L;
	private int lsid;
	private int agent;
	private String altitude;
	private String latitude;
	private Long lessonid;
	private String longitude;
	private String remark;
	private String signaddress;
	private Long signintime;
	private Long signouttime;
	private byte state;
	private int studentid;
	private byte studyinfo;

	public IscLessonSign() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLsid() {
		return this.lsid;
	}

	public void setLsid(int lsid) {
		this.lsid = lsid;
	}


	public int getAgent() {
		return this.agent;
	}

	public void setAgent(int agent) {
		this.agent = agent;
	}


	public String getAltitude() {
		return this.altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}


	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public Long getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Long lessonid) {
		this.lessonid = lessonid;
	}


	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getSignaddress() {
		return this.signaddress;
	}

	public void setSignaddress(String signaddress) {
		this.signaddress = signaddress;
	}


	public Long getSignintime() {
		return this.signintime;
	}

	public void setSignintime(Long signintime) {
		this.signintime = signintime;
	}


	public Long getSignouttime() {
		return this.signouttime;
	}

	public void setSignouttime(Long signouttime) {
		this.signouttime = signouttime;
	}


	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}


	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}


	public byte getStudyinfo() {
		return this.studyinfo;
	}

	public void setStudyinfo(byte studyinfo) {
		this.studyinfo = studyinfo;
	}

}