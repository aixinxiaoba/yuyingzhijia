package com.manage.crm.entity;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 附件类。
 * 
 * @author wei
 *
 */
@Entity(name = "Attachs")
@Table(name = "Attachs")
public class Attachs extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "nid")
	private News objNews;
	
	@Column(name = "pid")
	private Long projectID; // 项目id。
	
	@Column(name = "path")
	private String path; // 附件路径

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public News getObjNews() {
		return objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}
}