package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_address database table.
 * 
 */
@Entity
@Table(name="isc_address")
@NamedQuery(name="IscAddress.findAll", query="SELECT i FROM IscAddress i")
public class IscAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressid;

	private String address;

	private String addresscode;

	private byte addresstype;

	private String altitude;

	private String latitude;

	private String layer;

	private String longitude;

	private short pid;

	private byte state;

	public IscAddress() {
	}

	public int getAddressid() {
		return this.addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddresscode() {
		return this.addresscode;
	}

	public void setAddresscode(String addresscode) {
		this.addresscode = addresscode;
	}

	public byte getAddresstype() {
		return this.addresstype;
	}

	public void setAddresstype(byte addresstype) {
		this.addresstype = addresstype;
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

	public String getLayer() {
		return this.layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public short getPid() {
		return this.pid;
	}

	public void setPid(short pid) {
		this.pid = pid;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

}