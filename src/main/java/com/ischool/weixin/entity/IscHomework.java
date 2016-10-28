package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_homework database table.
 * 
 */
@Entity
@Table(name="isc_homework")
@NamedQuery(name="IscHomework.findAll", query="SELECT i FROM IscHomework i")
public class IscHomework implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int homeworkid; 

	private Long createtime;

	private Integer scid;

	private Integer state;

	private Long versiontime;

	private String workinfo;
	
	private Long limittime;

	public IscHomework() {
	}

	public int getHomeworkid() {
		return this.homeworkid;
	}

	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public Long getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Integer getScid() {
		return scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getVersiontime() {
		return this.versiontime;
	}

	public void setVersiontime(Long versiontime) {
		this.versiontime = versiontime;
	}

	public String getWorkinfo() {
		return this.workinfo;
	}

	public void setWorkinfo(String workinfo) {
		this.workinfo = workinfo;
	}

	public Long getLimittime() {
		return limittime;
	}

	public void setLimittime(Long limittime) {
		this.limittime = limittime;
	}
	
	

}