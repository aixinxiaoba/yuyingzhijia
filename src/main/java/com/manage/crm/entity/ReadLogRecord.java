package com.manage.crm.entity;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 访问日志表。
 * 
 * @author wei
 *
 */
@Entity(name = "ReadLogRecord")
@Table(name = "ReadLogRecord")
public class ReadLogRecord extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

//	@Column(name = "nid")
//	private Long nId; // 新闻id
	
	@Column(name = "readnum")
	private Long lReadNum; // 阅读量
	
	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public Long getlReadNum() {
		return lReadNum;
	}

	public void setlReadNum(Long lReadNum) {
		this.lReadNum = lReadNum;
	}
}