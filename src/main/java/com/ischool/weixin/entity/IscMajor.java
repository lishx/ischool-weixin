package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_major database table.
 * 
 */
@Entity
@Table(name="isc_major")
@NamedQuery(name="IscMajor.findAll", query="SELECT i FROM IscMajor i")
public class IscMajor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int majorid;

	private String brief;

	private byte isleaf;

	private String layer;

	private String majcode;

	private String majname;

	private String pcode;

	private String property;

	public IscMajor() {
	}

	public int getMajorid() {
		return this.majorid;
	}

	public void setMajorid(int majorid) {
		this.majorid = majorid;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public byte getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(byte isleaf) {
		this.isleaf = isleaf;
	}

	public String getLayer() {
		return this.layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getMajcode() {
		return this.majcode;
	}

	public void setMajcode(String majcode) {
		this.majcode = majcode;
	}

	public String getMajname() {
		return this.majname;
	}

	public void setMajname(String majname) {
		this.majname = majname;
	}

	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}