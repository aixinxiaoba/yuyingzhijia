package com.manage.crm.entity;

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
 * 问题反馈。
 * 
 * @author wei
 */
@Entity(name = "Comments")
@Table(name = "Comments")
public class Comments extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	// 与customer多对一
	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CID")
	private Customer objCustomer;
	
//	// 与新闻id多对多。
//	@ManyToMany(cascade = { javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
//	@JoinTable(name = "news_comment", joinColumns = { @JoinColumn(name = "CID") }, inverseJoinColumns = { @JoinColumn(name = "NID") })
//	@OrderBy("lId")
//	private Set<News> lstNews = new LinkedHashSet();
	
	// 与customer多对一
	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "NID")
	private News objNews;
	

	@Column(name = "content")
	private String content; // 内容

	@Column(name = "createTime")
	private String createTime; // 创建时间

	@Transient
	private String strNickName; // 用户昵称
	
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Customer getObjCustomer() {
		return objCustomer;
	}

	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}

	public News getObjNews() {
		return objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}

	public String getStrNickName() {
		
		return this.objCustomer != null ? this.objCustomer.getStrNickName() : "游客";
	}
	

}