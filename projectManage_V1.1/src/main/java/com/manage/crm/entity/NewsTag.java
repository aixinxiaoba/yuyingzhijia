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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity(name = "NewsTag")
@Table(name = "NewsTag")
public class NewsTag extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";
	
	public static final Long G_SUGGESTIONREADING = 1L; // 推荐阅读。
	
	public static final Long G_TOPOFREADING = 2L; // 阅读排行榜。
	
	public static final Long G_HOTSUGGESTION = 3L; // 热点推荐。
	
	public static final Long G_YUYINGSKILL = 4L; // 育婴技能。

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	private Project objProject;

	@ManyToMany(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "lstNewsTag")
	@OrderBy("lId desc")
	private Set<News> lstNews = new LinkedHashSet();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "tagname")
	private String strTagName; // 标签名

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getStrTagName() {
		return strTagName;
	}

	public void setStrTagName(String strTagName) {
		this.strTagName = strTagName;
	}

	public Project getObjProject() {
		return objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public Set<News> getLstNews() {
		return lstNews;
	}

	public void setLstNews(Set<News> lstNews) {
		this.lstNews = lstNews;
	}

}