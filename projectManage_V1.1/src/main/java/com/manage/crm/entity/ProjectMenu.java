package com.manage.crm.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javacommon.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "ProjectMenu")
@Table(name = "ProjectMenu")
public class ProjectMenu extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8969988919017551585L;

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	private Project objProject;
	
	@ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="ParID")
	private ProjectMenu objParentProjectMenu;

	@OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objParentProjectMenu", fetch=FetchType.LAZY)
	@OrderBy("lId")
	private Set<ProjectMenu> lstChildrenProjectMenu = new HashSet<ProjectMenu>();
	  
	@OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objProjectMenu", fetch=FetchType.LAZY)
	@OrderBy("lId desc")
	private Set<News> lstNews = new HashSet<News>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "menuname")
	private String strMenuName;

	@Column(name = "validate")
	private int validate;

	@Column(name = "menuKey")
	private String menuKey;
	
	@Column(name = "level")
	private int level;
	
	@Column(name = "url")
	private String menuURL; // 菜单链接
	
	@Column(name = "showIndex")
	private int showIndex; // 是否在首页显示
	
	@Column(name = "m_menuName")
	private String mobileMenuName; // 移动端显示名称
	
	@Transient
	private String strParentMenuKey;
	
	@Transient
	private String strParID;
	
	@Transient
	private List<News> lstImageNews;
	
	/**
	 * 手动的
	 */
	@Transient
	private List<News> lstManualNews = new ArrayList<News>();
	
	public Long getlId() {
		return this.lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public Long getLId() {
		return lId;
	}

	public void setLId(Long id) {
		lId = id;
	}

	public void setStrMenuName(String strMenuName) {
		this.strMenuName = strMenuName;
	}

	public void setValidate(int validate) {
		this.validate = validate;
	}

	public Project getObjProject() {
		return objProject;
	}

	public String getStrMenuName() {
		return strMenuName;
	}

	public int getValidate() {
		return validate;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public Set<News> getLstNews() {
		return lstNews;
	}

	public void setLstNews(Set<News> lstNews) {
		this.lstNews = lstNews;
	}

	public ProjectMenu getObjParentProjectMenu() {
		return objParentProjectMenu;
	}

	public void setObjParentProjectMenu(ProjectMenu objParentProjectMenu) {
		this.objParentProjectMenu = objParentProjectMenu;
	}

	public Set<ProjectMenu> getLstChildrenProjectMenu() {
		return lstChildrenProjectMenu;
	}

	public void setLstChildrenProjectMenu(Set<ProjectMenu> lstChildrenProjectMenu) {
		this.lstChildrenProjectMenu = lstChildrenProjectMenu;
	}

	public String getStrParentMenuKey() {
		return this.objParentProjectMenu != null ? this.objParentProjectMenu.getMenuKey() : "";
	}

	public String getStrParID() {
		return (this.objParentProjectMenu != null ? this.objParentProjectMenu.getLId() : -1L) + "";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public int getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}

	public String getMobileMenuName() {
		return mobileMenuName;
	}

	public void setMobileMenuName(String mobileMenuName) {
		this.mobileMenuName = mobileMenuName;
	}

	public List<News> getLstImageNews() {
		return lstImageNews;
	}

	public void setLstImageNews(List<News> lstImageNews) {
		this.lstImageNews = lstImageNews;
	}

	public List<News> getLstManualNews() {
		return lstManualNews;
	}

	public void setLstManualNews(List<News> lstManualNews) {
		this.lstManualNews = lstManualNews;
	}

	
	
}