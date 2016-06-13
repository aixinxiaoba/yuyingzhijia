package com.manage.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javacommon.core.base.BaseEntity;
import javacommon.util.StringUtils;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity(name = "News")
@Table(name = "News")
public class News extends BaseEntity {
	private static final long serialVersionUID = 8759258670597170305L;
	public static final String ID = "lId";
	public static final String PROJECTID = "pid";

	@ManyToMany(cascade = { javax.persistence.CascadeType.REFRESH,
			javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "newstagrela", joinColumns = { @JoinColumn(name = "nid") }, inverseJoinColumns = { @JoinColumn(name = "ntid") })
	@OrderBy("lId")
	private Set<NewsTag> lstNewsTag = new LinkedHashSet();

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	private Project objProject;

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "MID")
	private ProjectMenu objProjectMenu;

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "UID")
	private Users objUsers;

	@OneToMany(cascade = { javax.persistence.CascadeType.ALL }, mappedBy = "objNews", fetch = FetchType.LAZY)
	private Set<Attachs> lstAttachs = new HashSet<Attachs>();

	@OneToMany(cascade = { javax.persistence.CascadeType.ALL }, mappedBy = "objNews", fetch = FetchType.LAZY)
	private Set<Comments> lstComments = new HashSet<Comments>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long lId;

	@Column(name = "NContent")
	private String strContent;

	@Column(name = "title")
	private String strTitle;

	@Column(name = "sendDate")
	private String strSendDate;

	@Column(name = "stick")
	private int nStick;

	/**
	 * 获取新闻摘要。
	 */
	@Column(name = "summary")
	private String strSummary;

	/**
	 * 类型。
	 */
	@Column(name = "type")
	private String type;

	@Column(name = "readnum")
	private int readNum; // 阅读量

	@Column(name = "addressFrom")
	private String addressFrom; // 来源

	@Column(name = "sendStatus")
	private int sendStatus; // 发送状态

	@Column(name = "shortURL")
	private String shortURL; // 短链接。

	@Column(name = "staticFlag")
	private int staticFlag; // 静态化标志。

	@Column(name = "imageURL")
	private String imageUrl; // 图片地址。

	@Transient
	private String strSenderName;

	@Transient
	private String strProjectMenuName;

	@Transient
	private long lProjectMenuID;

	/**
	 * 获取指定长度的标题。
	 */
	@Transient
	private String strFormatTitle;

	/**
	 * 获取指定长度的标题。
	 */
	@Transient
	private String strLongTitle;

	/**
	 * 获取指定长度的标题。
	 */
	@Transient
	private String strTitle13;

	/**
	 * 获取指定长度的标题。
	 */
	@Transient
	private String strLongTitleTwo;

	/**
	 * 发布时间（年月日）。
	 */
	@Transient
	private String strSendDateShort;

	/**
	 * 获取短新闻内容。
	 */
	@Transient
	private String strShortContent;

	/**
	 * 获取短新闻内容。
	 */
	@Transient
	private String strShortOneContent;

	public Users getObjUsers() {
		return this.objUsers;
	}

	public void setObjUsers(Users objUsers) {
		this.objUsers = objUsers;
	}

	public Long getlId() {
		return this.lId;
	}

	public void setLId(Long lId) {
		this.lId = lId;
	}

	public Long getLId() {
		return this.lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getStrContent() {
		return this.strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	public String getStrSendDate() {
		return this.strSendDate;
	}

	public void setStrSendDate(String strSendDate) {
		this.strSendDate = strSendDate;
	}

	public String getStrTitle() {
		return this.strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrSenderName() {
		return this.objUsers.getStrName();
	}

	public String getStrProjectMenuName() {
		return this.getObjProjectMenu().getStrMenuName();
	}

	public int getnStick() {
		return this.nStick;
	}

	public void setnStick(int nStick) {
		this.nStick = nStick;
	}

	public int getNStick() {
		return this.nStick;
	}

	public void setNStick(int nStick) {
		this.nStick = nStick;
	}

	public Project getObjProject() {
		return objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProjectMenu getObjProjectMenu() {
		return objProjectMenu;
	}

	public void setObjProjectMenu(ProjectMenu objProjectMenu) {
		this.objProjectMenu = objProjectMenu;
	}

	public long getLProjectMenuID() {
		return objProjectMenu.getlId();
	}

	public long getlProjectMenuID() {
		return objProjectMenu.getlId();
	}

	public String getStrFormatTitle() {
		strFormatTitle = this.strTitle;
		if (!StringUtils.isEmpty(this.strTitle)) {
			if (this.strTitle.length() > 15) {
				strFormatTitle = this.strTitle.substring(0, 15) + "...";
			}
		}
		return strFormatTitle;
	}

	public String getStrLongTitle() {
		this.strLongTitle = this.strTitle;
		if (!StringUtils.isEmpty(this.strTitle)) {
			if (this.strTitle.length() > 17) {
				strLongTitle = this.strTitle.substring(0, 17) + "...";
			}
		}
		return strLongTitle;
	}

	public String getStrLongTitleTwo() {
		this.strLongTitleTwo = this.strTitle;
		if (!StringUtils.isEmpty(this.strTitle)) {
			if (this.strTitle.length() > 40) {
				strLongTitleTwo = this.strTitle.substring(0, 40);
			}
		}
		return strLongTitleTwo;
	}

	public String getStrSendDateShort() {
		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd");
		strSendDateShort = this.strSendDate;

		if (!StringUtils.isEmpty(this.strSendDate)) {
			try {
				Date objSendDate = sdfFormat.parse(this.strSendDate);
				strSendDateShort = sdfFormat.format(objSendDate);
				Calendar objCalendar = Calendar.getInstance();
				objCalendar.setTime(objSendDate);
				strSendDateShort = (objCalendar.get(Calendar.MONTH) + 1) + "-"
						+ objCalendar.get(Calendar.DAY_OF_MONTH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strSendDateShort;
	}

	public void setStrSendDateShort(String strSendDateShort) {
		this.strSendDateShort = strSendDateShort;
	}

	public String getStrShortContent() {
		strShortContent = this.strContent;
		if (this.strContent.length() > 50) {
			strShortContent = strShortContent.substring(0, 50);
		}
		return strShortContent;
	}

	public String getStrShortOneContent() {
		strShortOneContent = this.getStrSummary();
		if (this.strSummary.length() > 50) {
			strShortOneContent = strShortOneContent.substring(0, 40);
		}
		return strShortOneContent;
	}

	public String getStrSummary() {
		return strSummary;
	}

	public void setStrSummary(String strSummary) {
		this.strSummary = strSummary;
	}

	public Set<NewsTag> getLstNewsTag() {
		return lstNewsTag;
	}

	public void setLstNewsTag(Set<NewsTag> lstNewsTag) {
		this.lstNewsTag = lstNewsTag;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public Set<Attachs> getLstAttachs() {
		return lstAttachs;
	}

	public void setLstAttachs(Set<Attachs> lstAttachs) {
		this.lstAttachs = lstAttachs;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getShortURL() {
		return shortURL;
	}

	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}

	public Set<Comments> getLstComments() {
		return lstComments;
	}

	public void setLstComments(Set<Comments> lstComments) {
		this.lstComments = lstComments;
	}

	public int getStaticFlag() {
		return staticFlag;
	}

	public void setStaticFlag(int staticFlag) {
		this.staticFlag = staticFlag;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStrTitle13() {
		this.strTitle13 = this.strTitle;
		if (!StringUtils.isEmpty(this.strTitle)) {
			if (this.strTitle.length() > 13) {
				strTitle13 = this.strTitle.substring(0, 13);
			}
		}
		return strTitle13;
	}

}