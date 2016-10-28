package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_invigilator database table.
 * 
 */
@Entity
@Table(name="isc_invigilator")
@NamedQuery(name="IscInvigilator.findAll", query="SELECT i FROM IscInvigilator i")
public class IscInvigilator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int invigilatorid;

	public IscInvigilator() {
	}

	public int getInvigilatorid() {
		return this.invigilatorid;
	}

	public void setInvigilatorid(int invigilatorid) {
		this.invigilatorid = invigilatorid;
	}

}