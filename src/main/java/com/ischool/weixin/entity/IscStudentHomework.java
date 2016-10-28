package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_student_homework database table.
 * 
 */
@Entity
@Table(name="isc_student_homework")
@NamedQuery(name="IscStudentHomework.findAll", query="SELECT i FROM IscStudentHomework i")
public class IscStudentHomework implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int shid;

	private String correctinfo;

	private Long finishtime;
	
	private int homeworkid;

	private byte iscorrect;

	private byte isfinish;

	private int studentid;

	public IscStudentHomework() {
	}

	public int getShid() {
		return this.shid;
	}

	public void setShid(int shid) {
		this.shid = shid;
	}

	public String getCorrectinfo() {
		return this.correctinfo;
	}

	public void setCorrectinfo(String correctinfo) {
		this.correctinfo = correctinfo;
	}

	public int getHomeworkid() {
		return this.homeworkid;
	}

	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public byte getIscorrect() {
		return this.iscorrect;
	}

	public void setIscorrect(byte iscorrect) {
		this.iscorrect = iscorrect;
	}

	public byte getIsfinish() {
		return this.isfinish;
	}

	public void setIsfinish(byte isfinish) {
		this.isfinish = isfinish;
	}

	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public Long getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Long finishtime) {
		this.finishtime = finishtime;
	}

	
	
}