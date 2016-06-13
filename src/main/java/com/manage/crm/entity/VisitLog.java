package com.manage.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 访问日志表。
 * 
 * @author wei
 *
 */
@Entity(name = "VisitLog")
@Table(name = "VisitLog")
public class VisitLog extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "ip")
	private String strIP; // ip地址
	
	@Column(name = "refer")
	private String strRefer; // 来源
	
	@Column(name = "startdate")
	private String strStartDate; // 开始访问时间
	
	@Column(name = "thisPage")
	private String strThisPage; // 当前访问页面
	

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getStrIP() {
		return strIP;
	}

	public void setStrIP(String strIP) {
		this.strIP = strIP;
	}

	public String getStrRefer() {
		return strRefer;
	}

	public void setStrRefer(String strRefer) {
		this.strRefer = strRefer;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrThisPage() {
		return strThisPage;
	}

	public void setStrThisPage(String strThisPage) {
		this.strThisPage = strThisPage;
	}
	
	
}