package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the isc_lesson database table.
 * 
 */
@Entity
@Table(name="isc_lesson")
@NamedQuery(name="IscLesson.findAll", query="SELECT i FROM IscLesson i")
public class IscLesson implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long lessonid;
	private String address;
	private String content;
	private BigInteger endtime;
	private byte lessoninfo;
	private short lessonno;
	private String remark;
	private int scid;
	private BigInteger starttime;
	private byte state;

	public IscLesson() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Long lessonid) {
		this.lessonid = lessonid;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public BigInteger getEndtime() {
		return this.endtime;
	}

	public void setEndtime(BigInteger endtime) {
		this.endtime = endtime;
	}


	public byte getLessoninfo() {
		return this.lessoninfo;
	}

	public void setLessoninfo(byte lessoninfo) {
		this.lessoninfo = lessoninfo;
	}


	public short getLessonno() {
		return this.lessonno;
	}

	public void setLessonno(short lessonno) {
		this.lessonno = lessonno;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getScid() {
		return this.scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}


	public BigInteger getStarttime() {
		return this.starttime;
	}

	public void setStarttime(BigInteger starttime) {
		this.starttime = starttime;
	}


	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

}