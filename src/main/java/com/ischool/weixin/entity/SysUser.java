package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the sys_user database table.
 * 
 */
@Entity
@Table(name="sys_user")
@NamedQuery(name="SysUser.findAll", query="SELECT s FROM SysUser s")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;

	private String address;

	private String birthday;

	private String city;

	private String email;

	private String extensions;

	private String fullname;

	private String idcard;

	private BigInteger inittime;

	private String nationality;

	private String password;

	private String phone;

	private String province;

	private String sex;

	private byte state;

	private String usercode;

	private String username;

	private byte usertype;

	private BigInteger validity;

	private BigInteger versiontime;

	private String weichat;

	public SysUser() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtensions() {
		return this.extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public BigInteger getInittime() {
		return this.inittime;
	}

	public void setInittime(BigInteger inittime) {
		this.inittime = inittime;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte getUsertype() {
		return this.usertype;
	}

	public void setUsertype(byte usertype) {
		this.usertype = usertype;
	}

	public BigInteger getValidity() {
		return this.validity;
	}

	public void setValidity(BigInteger validity) {
		this.validity = validity;
	}

	public BigInteger getVersiontime() {
		return this.versiontime;
	}

	public void setVersiontime(BigInteger versiontime) {
		this.versiontime = versiontime;
	}

	public String getWeichat() {
		return this.weichat;
	}

	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

}