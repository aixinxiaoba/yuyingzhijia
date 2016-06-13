package com.manage.crm.entity;

import javacommon.core.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "UserMessage")
@Table(name = "UserMessage")
public class UserMessage extends BaseEntity {
	private static final long serialVersionUID = 1753220786713148295L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "CURRENT_LEVEL", nullable = false)
	private int nCurrentLevel;

	@Column(name = "IS_LAST_LEVEL", nullable = false)
	private int nIsLastLevel;

	@Column(name = "IS_ACTIVE", nullable = false)
	private int nIsActive;

	public Long getLId() {
		return lId;
	}

	public void setLId(Long id) {
		lId = id;
	}

	public int getNCurrentLevel() {
		return nCurrentLevel;
	}

	public void setNCurrentLevel(int currentLevel) {
		nCurrentLevel = currentLevel;
	}

	public int getNIsLastLevel() {
		return nIsLastLevel;
	}

	public void setNIsLastLevel(int isLastLevel) {
		nIsLastLevel = isLastLevel;
	}

	public int getNIsActive() {
		return nIsActive;
	}

	public void setNIsActive(int isActive) {
		nIsActive = isActive;
	}

}