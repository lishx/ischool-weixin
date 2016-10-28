package com.ischool.weixin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="isc_exam")
@NamedQuery(name="IscExam.findAll", query="SELECT i FROM IscExam i")
public class IscExam {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer examid;
	private Integer scid;
	private String examtitle;
	
	private Long examtime;
	private Long endtime;
	
	private String examreq;
	private String address;
	
	private Integer state;

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public Integer getScid() {
		return scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
	}

	public String getExamtitle() {
		return examtitle;
	}

	public void setExamtitle(String examtitle) {
		this.examtitle = examtitle;
	}

	public Long getExamtime() {
		return examtime;
	}

	public void setExamtime(Long examtime) {
		this.examtime = examtime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public String getExamreq() {
		return examreq;
	}

	public void setExamreq(String examreq) {
		this.examreq = examreq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
}
