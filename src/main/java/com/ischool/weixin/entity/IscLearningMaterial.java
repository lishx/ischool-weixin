package com.ischool.weixin.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the isc_learning_material database table.
 * 
 */
@Entity
@Table(name="isc_learning_material")
@NamedQuery(name="IscLearningMaterial.findAll", query="SELECT i FROM IscLearningMaterial i")
public class IscLearningMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lmid;

	public IscLearningMaterial() {
	}

	public int getLmid() {
		return this.lmid;
	}

	public void setLmid(int lmid) {
		this.lmid = lmid;
	}

}