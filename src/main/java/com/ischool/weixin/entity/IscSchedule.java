package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_schedule database table.
 * 
 */
@Entity
@Table(name="isc_schedule")
@NamedQuery(name="IscSchedule.findAll", query="SELECT i FROM IscSchedule i")
public class IscSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int scheduleid;

	private String addresscode;

	private short endcycle;

	private byte endsection;

	private byte grade;

	private byte semester;

	private short startcycle;

	private byte startsection;

	private byte state;

	private String subcode;

	private String teachercode;

	private byte week;

	public IscSchedule() {
	}

	public int getScheduleid() {
		return this.scheduleid;
	}

	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}

	public String getAddresscode() {
		return this.addresscode;
	}

	public void setAddresscode(String addresscode) {
		this.addresscode = addresscode;
	}

	public short getEndcycle() {
		return this.endcycle;
	}

	public void setEndcycle(short endcycle) {
		this.endcycle = endcycle;
	}

	public byte getEndsection() {
		return this.endsection;
	}

	public void setEndsection(byte endsection) {
		this.endsection = endsection;
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

	public short getStartcycle() {
		return this.startcycle;
	}

	public void setStartcycle(short startcycle) {
		this.startcycle = startcycle;
	}

	public byte getStartsection() {
		return this.startsection;
	}

	public void setStartsection(byte startsection) {
		this.startsection = startsection;
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

	public String getTeachercode() {
		return this.teachercode;
	}

	public void setTeachercode(String teachercode) {
		this.teachercode = teachercode;
	}

	public byte getWeek() {
		return this.week;
	}

	public void setWeek(byte week) {
		this.week = week;
	}

}