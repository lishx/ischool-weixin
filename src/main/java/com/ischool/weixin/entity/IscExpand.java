package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_expand database table.
 * 
 */
@Entity
@Table(name="isc_expand")
@NamedQuery(name="IscExpand.findAll", query="SELECT i FROM IscExpand i")
public class IscExpand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int expandid;

	public IscExpand() {
	}

	public int getExpandid() {
		return this.expandid;
	}

	public void setExpandid(int expandid) {
		this.expandid = expandid;
	}

}