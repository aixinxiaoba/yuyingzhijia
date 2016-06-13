package com.manage.crm.entity;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 问题反馈。
 * 
 * @author wei
 */
@Entity(name = "Suggestion")
@Table(name = "Suggestion")
public class Suggestion extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "content")
	private String content; // 内容
	
	@Column(name = "connectWay")
	private String connectWay; // 联系方式
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "createDate")
	private String createDate;

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getConnectWay() {
		return connectWay;
	}

	public void setConnectWay(String connectWay) {
		this.connectWay = connectWay;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}