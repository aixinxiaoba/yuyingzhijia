package com.manage.crm.entity;

import java.util.Date;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 心情处理类。
 * 
 * @author wei
 */
@Entity(name = "MoodNewsRela")
@Table(name = "MoodNewsRela")
public class MoodNewsRela extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "mType")
	private int moodType; // 心情类型

	@Column(name = "moodName")
	private String moodName; // 心情名称

	@Column(name = "nid")
	private Long newsID; // 新闻id
	
	@Column(name = "num")
	private Long num; // 心情数量
	
	@Column(name = "startDate")
	private Date startDate; // 开始时间
	
	@Column(name = "operateTime")
	private String operateTime; // 操作时间
	
	@Column(name = "ip")
	private String ip; // id地址
	
	@Column(name = "cid")
	private Long customerID; // 用户id

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public int getMoodType() {
		return moodType;
	}

	public void setMoodType(int moodType) {
		this.moodType = moodType;
	}

	public String getMoodName() {
		return moodName;
	}

	public void setMoodName(String moodName) {
		this.moodName = moodName;
	}

	public Long getNewsID() {
		return newsID;
	}

	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

}