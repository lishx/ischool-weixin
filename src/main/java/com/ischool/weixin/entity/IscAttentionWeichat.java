package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the isc_attention_weichat database table.
 * 
 */
@Entity
@Table(name="isc_attention_weichat")
@NamedQuery(name="IscAttentionWeichat.findAll", query="SELECT i FROM IscAttentionWeichat i")
public class IscAttentionWeichat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attentionid;

	private long attentiontime;

	private String nickname;

	private int userid;

	private String weichat;

	public IscAttentionWeichat() {
	}

	public int getAttentionid() {
		return this.attentionid;
	}

	public void setAttentionid(int attentionid) {
		this.attentionid = attentionid;
	}

	public long getAttentiontime() {
		return this.attentiontime;
	}

	public void setAttentiontime(long attentiontime) {
		this.attentiontime = attentiontime;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getWeichat() {
		return this.weichat;
	}

	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

}