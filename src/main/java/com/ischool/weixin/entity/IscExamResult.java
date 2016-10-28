package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_exam_result database table.
 * 
 */
@Entity
@Table(name="isc_exam_result")
@NamedQuery(name="IscExamResult.findAll", query="SELECT i FROM IscExamResult i")
public class IscExamResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int erid;

	private String level;

	private byte score;

	private int studentid;

	public IscExamResult() {
	}

	public int getErid() {
		return this.erid;
	}

	public void setErid(int erid) {
		this.erid = erid;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public byte getScore() {
		return this.score;
	}

	public void setScore(byte score) {
		this.score = score;
	}

	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

}