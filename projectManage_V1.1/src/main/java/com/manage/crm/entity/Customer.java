package com.manage.crm.entity;

import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Customer")
@Table(name = "Customer")
public class Customer extends BaseEntity {
	public static final String INITIALPASSWORDS = "111111";
	public static final int LEVEL_ONE = 1;
	public static final int LEVEL_TWO = 2;
	private static final long serialVersionUID = -2255594410354745190L;
	public static final int IDCARDTYPE_ED = 1;
	public static final int IDCARDTYPE_JGZ = 2;
	public static final int IDCARDTYPE_HZ = 3;
	public static final int IDCARDTYPE_GATXZ = 4;
	public static final int IN_BLACKLIST = 1;
	public static final int NOT_IN_BLACKLIST = 0;
	public static final String ID = "lId";
	public static final String SNAME = "strSname";
	public static final String PROJECT = "objProject";
	public static final String QQ = "strQQ";
	public static final String IS_ACTIVE = "nIsActive";
	public static final String CUSTOMERTYPE = "objCustomerType";
	public static final String PARENT_CUSTOMER = "objParentCustomer";

	public static final String NICKNAME = "strNickName";

	@ManyToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "lstCustomer")
	@OrderBy("lId")
	private Set<Email> lstEmail = new LinkedHashSet();

	@ManyToMany(cascade = { javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "customer_project", joinColumns = { @JoinColumn(name = "CID") }, inverseJoinColumns = { @JoinColumn(name = "PID") })
	@OrderBy("lId")
	private Set<Project> lstProject = new LinkedHashSet();

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SParent_ID")
	private Customer objParentCustomer;

	@OneToMany(cascade = { javax.persistence.CascadeType.ALL }, mappedBy = "objParentCustomer", fetch = FetchType.LAZY)
	@OrderBy("lId")
	private Set<Customer> lstChildrenCustomer = new HashSet();

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTID")
	private CustomerType objCustomerType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long lId;

	@Column(name = "SName")
	private String strSname;

	@Column(name = "SNamePY")
	private String strSnamePY;

	@Column(name = "CURRENT_LEVEL")
	private int nCurrentLevel;

	@Column(name = "IS_LAST_LEVEL")
	private int nIsLastLevel;

	@Column(name = "IS_ACTIVE")
	private Integer nIsActive;

	@Column(name = "IDE")
	private String strIde;

	@Column(name = "IDE_Type")
	private int nIdeType;

	@Transient
	private String strIdeType;

	@Column(name = "SBrithday")
	private String strSbrithday;

	@Column(name = "GuoJi")
	private String strGuoJi;

	@Column(name = "Nation")
	private String strNation;

	@Column(name = "SDegree")
	private String strSdegree;

	@Column(name = "SEmail")
	private String strSemail;

	@Column(name = "STel")
	private String strStel;

	@Column(name = "SPhone")
	private String strSPhone;

	@Column(name = "SWork")
	private String strSWork;

	@Column(name = "SWORKY")
	private int nSworky;

	@Column(name = "SWorkUnit")
	private String strSWorkUnit;

	@Column(name = "SAddress")
	private String strSAddress;

	@Column(name = "SPIC")
	private String strSPic;

	@Column(name = "LiticalStatus")
	private String strLiticalStatus;

	@Column(name = "RegistTime", nullable = false)
	private String strRegistTime;

	@Column(name = "Sex")
	private String strSex;

	@Column(name = "SArea")
	private String strSArea;

	@Column(name = "SChuanzhen")
	private String strSChuanzhen;

	@Column(name = "JiGuan")
	private String strJiGuan;

	@Column(name = "HunYinStatus")
	private int nHunYinStatus;

	@Column(name = "Remarks")
	private String strRemarks;

	@Column(name = "SYWName")
	private String strYWName;

	@Column(name = "nickname")
	private String strNickName;

	@Column(name = "QQ")
	private String strQQ;

	@Column(name = "InternetExperience")
	private String strInterExperience;

	@Column(name = "Target")
	private String strTarget;

	@Column(name = "Password")
	private String strPassword;

	@Column(name = "REGCUSTOM1")
	private String strRegCustom1;

	@Column(name = "REGCUSTOM2")
	private String strRegCustom2;

	@Column(name = "REGCUSTOM3")
	private String strRegCustom3;

	@Column(name = "REGCUSTOM4")
	private String strRegCustom4;

	@Column(name = "REGCUSTOM5")
	private String strRegCustom5;

	@Column(name = "REGCUSTOM6")
	private String strRegCustom6;

	@Column(name = "REGCUSTOM7")
	private String strRegCustom7;

	@Column(name = "REGCUSTOM8")
	private String strRegCustom8;

	@Column(name = "REGCUSTOM9")
	private String strRegCustom9;

	@Column(name = "REGCUSTOM10")
	private String strRegCustom10;

	@Column(name = "REGCUSTOM11")
	private String strRegCustom11;

	@Column(name = "REGCUSTOM12")
	private String strRegCustom12;

	@Column(name = "REGCUSTOM13")
	private String strRegCustom13;

	@Column(name = "REGCUSTOM14")
	private String strRegCustom14;

	@Column(name = "REGCUSTOM15")
	private String strRegCustom15;

	@Column(name = "RESERVER1")
	private String strReserver1;

	@Column(name = "RESERVER2")
	private String strReserver2;

	@Column(name = "RESERVER3")
	private String strReserver3;

	@Column(name = "RESERVER4")
	private String strReserver4;

	@Column(name = "RESERVER5")
	private String strReserver5;

	@Column(name = "RESERVER6")
	private String strReserver6;

	@Column(name = "RESERVER7")
	private String strReserver7;

	@Column(name = "RESERVER8")
	private String strReserver8;

	@Column(name = "RESERVER9")
	private String strReserver9;

	@Column(name = "RESERVER10")
	private String strReserver10;

	@Column(name = "RESERVER11")
	private String strReserver11;

	@Column(name = "RESERVER12")
	private String strReserver12;

	@Column(name = "RESERVER13")
	private String strReserver13;

	@Column(name = "RESERVER14")
	private String strReserver14;

	@Column(name = "RESERVER15")
	private String strReserver15;

	@Column(name = "access_token")
	private String accessToken;

	@Column(name = "uid")
	private String uId;

	@Column(name = "avatarLarge")
	private String avatarLarge; // 用户头像地址（大图）

	@Transient
	private String strIsActive;

	@Transient
	private String strParentCustomerName;

	@Transient
	private String strCustomerTypeName;

	@Transient
	private String strParentCustomerQQ;

	@Transient
	private String strParentCustomerPhone;

	@Transient
	private String strCurrentLevel;

	@Transient
	private String strParentNickName;
	
	@Transient
	private String strRePassword;
	

	public Long getLId() {
		return this.lId;
	}

	public void setLId(Long lId) {
		this.lId = lId;
	}

	public String getStrSname() {
		return this.strSname;
	}

	public void setStrSname(String strSname) {
		this.strSname = strSname;
	}

	public String getStrSnamePY() {
		return this.strSnamePY;
	}

	public void setStrSnamePY(String strSnamePY) {
		this.strSnamePY = strSnamePY;
	}

	public String getStrIde() {
		return this.strIde;
	}

	public void setStrIde(String strIde) {
		this.strIde = strIde;
	}

	public int getNIdeType() {
		return this.nIdeType;
	}

	public void setNIdeType(int nIdeType) {
		this.nIdeType = nIdeType;
	}

	public String getStrSbrithday() {
		return this.strSbrithday;
	}

	public void setStrSbrithday(String strSbrithday) {
		this.strSbrithday = strSbrithday;
	}

	public String getStrGuoJi() {
		return this.strGuoJi;
	}

	public void setStrGuoJi(String strGuoJi) {
		this.strGuoJi = strGuoJi;
	}

	public String getStrNation() {
		return this.strNation;
	}

	public void setStrNation(String strNation) {
		this.strNation = strNation;
	}

	public String getStrSdegree() {
		return this.strSdegree;
	}

	public void setStrSdegree(String strSdegree) {
		this.strSdegree = strSdegree;
	}

	public String getStrSemail() {
		return this.strSemail;
	}

	public void setStrSemail(String strSemail) {
		this.strSemail = strSemail;
	}

	public String getStrStel() {
		return this.strStel;
	}

	public void setStrStel(String strStel) {
		this.strStel = strStel;
	}

	public String getStrSPhone() {
		return this.strSPhone;
	}

	public void setStrSPhone(String strSPhone) {
		this.strSPhone = strSPhone;
	}

	public String getStrSWork() {
		return this.strSWork;
	}

	public void setStrSWork(String strSWork) {
		this.strSWork = strSWork;
	}

	public int getNSworky() {
		return this.nSworky;
	}

	public void setNSworky(int nSworky) {
		this.nSworky = nSworky;
	}

	public String getStrSWorkUnit() {
		return this.strSWorkUnit;
	}

	public void setStrSWorkUnit(String strSWorkUnit) {
		this.strSWorkUnit = strSWorkUnit;
	}

	public String getStrSAddress() {
		return this.strSAddress;
	}

	public void setStrSAddress(String strSAddress) {
		this.strSAddress = strSAddress;
	}

	public String getStrSPic() {
		return this.strSPic;
	}

	public void setStrSPic(String strSPic) {
		this.strSPic = strSPic;
	}

	public String getStrLiticalStatus() {
		return this.strLiticalStatus;
	}

	public void setStrLiticalStatus(String strLiticalStatus) {
		this.strLiticalStatus = strLiticalStatus;
	}

	public String getStrRegistTime() {
		return this.strRegistTime;
	}

	public void setStrRegistTime(String strRegistTime) {
		this.strRegistTime = strRegistTime;
	}

	public String getStrSex() {
		return this.strSex;
	}

	public void setStrSex(String strSex) {
		this.strSex = strSex;
	}

	public String getStrSArea() {
		return this.strSArea;
	}

	public void setStrSArea(String strSArea) {
		this.strSArea = strSArea;
	}

	public String getStrSChuanzhen() {
		return this.strSChuanzhen;
	}

	public void setStrSChuanzhen(String strSChuanzhen) {
		this.strSChuanzhen = strSChuanzhen;
	}

	public String getStrJiGuan() {
		return this.strJiGuan;
	}

	public void setStrJiGuan(String strJiGuan) {
		this.strJiGuan = strJiGuan;
	}

	public int getNHunYinStatus() {
		return this.nHunYinStatus;
	}

	public void setNHunYinStatus(int nHunYinStatus) {
		this.nHunYinStatus = nHunYinStatus;
	}

	public String getStrRemarks() {
		return this.strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrReserver1() {
		return this.strReserver1;
	}

	public void setStrReserver1(String strReserver1) {
		this.strReserver1 = strReserver1;
	}

	public String getStrReserver2() {
		return this.strReserver2;
	}

	public void setStrReserver2(String strReserver2) {
		this.strReserver2 = strReserver2;
	}

	public String getStrReserver3() {
		return this.strReserver3;
	}

	public void setStrReserver3(String strReserver3) {
		this.strReserver3 = strReserver3;
	}

	public String getStrReserver4() {
		return this.strReserver4;
	}

	public void setStrReserver4(String strReserver4) {
		this.strReserver4 = strReserver4;
	}

	public String getStrReserver5() {
		return this.strReserver5;
	}

	public void setStrReserver5(String strReserver5) {
		this.strReserver5 = strReserver5;
	}

	public String getStrReserver6() {
		return this.strReserver6;
	}

	public void setStrReserver6(String strReserver6) {
		this.strReserver6 = strReserver6;
	}

	public String getStrReserver7() {
		return this.strReserver7;
	}

	public void setStrReserver7(String strReserver7) {
		this.strReserver7 = strReserver7;
	}

	public String getStrReserver8() {
		return this.strReserver8;
	}

	public void setStrReserver8(String strReserver8) {
		this.strReserver8 = strReserver8;
	}

	public String getStrReserver9() {
		return this.strReserver9;
	}

	public void setStrReserver9(String strReserver9) {
		this.strReserver9 = strReserver9;
	}

	public String getStrReserver10() {
		return this.strReserver10;
	}

	public void setStrReserver10(String strReserver10) {
		this.strReserver10 = strReserver10;
	}

	public String getStrReserver11() {
		return this.strReserver11;
	}

	public void setStrReserver11(String strReserver11) {
		this.strReserver11 = strReserver11;
	}

	public String getStrReserver12() {
		return this.strReserver12;
	}

	public void setStrReserver12(String strReserver12) {
		this.strReserver12 = strReserver12;
	}

	public String getStrReserver13() {
		return this.strReserver13;
	}

	public void setStrReserver13(String strReserver13) {
		this.strReserver13 = strReserver13;
	}

	public String getStrReserver14() {
		return this.strReserver14;
	}

	public void setStrReserver14(String strReserver14) {
		this.strReserver14 = strReserver14;
	}

	public String getStrReserver15() {
		return this.strReserver15;
	}

	public void setStrReserver15(String strReserver15) {
		this.strReserver15 = strReserver15;
	}

	public Long getlId() {
		return this.lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public int getnIdeType() {
		return this.nIdeType;
	}

	public void setnIdeType(int nIdeType) {
		this.nIdeType = nIdeType;
	}

	public int getnSworky() {
		return this.nSworky;
	}

	public void setnSworky(int nSworky) {
		this.nSworky = nSworky;
	}

	public int getnHunYinStatus() {
		return this.nHunYinStatus;
	}

	public void setnHunYinStatus(int nHunYinStatus) {
		this.nHunYinStatus = nHunYinStatus;
	}

	public String getStrIdeType() {
		switch (this.nIdeType) {
		case 1:
			this.strIdeType = "二代身份证";
			break;
		case 2:
			this.strIdeType = "军官证";
			break;
		case 3:
			this.strIdeType = "护照";
			break;
		case 4:
			this.strIdeType = "港澳台通行证";
			break;
		default:
			this.strIdeType = "未知";
		}

		return this.strIdeType;
	}

	public String getStrYWName() {
		return this.strYWName;
	}

	public void setStrYWName(String strYWName) {
		this.strYWName = strYWName;
	}

	public String getStrQQ() {
		return this.strQQ;
	}

	public void setStrQQ(String strQQ) {
		this.strQQ = strQQ;
	}

	public String getStrInterExperience() {
		return this.strInterExperience;
	}

	public void setStrInterExperience(String strInterExperience) {
		this.strInterExperience = strInterExperience;
	}

	public String getStrTarget() {
		return this.strTarget;
	}

	public void setStrTarget(String strTarget) {
		this.strTarget = strTarget;
	}

	public String getStrRegCustom1() {
		return this.strRegCustom1;
	}

	public void setStrRegCustom1(String strRegCustom1) {
		this.strRegCustom1 = strRegCustom1;
	}

	public String getStrRegCustom2() {
		return this.strRegCustom2;
	}

	public void setStrRegCustom2(String strRegCustom2) {
		this.strRegCustom2 = strRegCustom2;
	}

	public String getStrRegCustom3() {
		return this.strRegCustom3;
	}

	public void setStrRegCustom3(String strRegCustom3) {
		this.strRegCustom3 = strRegCustom3;
	}

	public String getStrRegCustom4() {
		return this.strRegCustom4;
	}

	public void setStrRegCustom4(String strRegCustom4) {
		this.strRegCustom4 = strRegCustom4;
	}

	public String getStrRegCustom5() {
		return this.strRegCustom5;
	}

	public void setStrRegCustom5(String strRegCustom5) {
		this.strRegCustom5 = strRegCustom5;
	}

	public String getStrRegCustom6() {
		return this.strRegCustom6;
	}

	public void setStrRegCustom6(String strRegCustom6) {
		this.strRegCustom6 = strRegCustom6;
	}

	public String getStrRegCustom7() {
		return this.strRegCustom7;
	}

	public void setStrRegCustom7(String strRegCustom7) {
		this.strRegCustom7 = strRegCustom7;
	}

	public String getStrRegCustom8() {
		return this.strRegCustom8;
	}

	public void setStrRegCustom8(String strRegCustom8) {
		this.strRegCustom8 = strRegCustom8;
	}

	public String getStrRegCustom9() {
		return this.strRegCustom9;
	}

	public void setStrRegCustom9(String strRegCustom9) {
		this.strRegCustom9 = strRegCustom9;
	}

	public String getStrRegCustom10() {
		return this.strRegCustom10;
	}

	public void setStrRegCustom10(String strRegCustom10) {
		this.strRegCustom10 = strRegCustom10;
	}

	public String getStrRegCustom11() {
		return this.strRegCustom11;
	}

	public void setStrRegCustom11(String strRegCustom11) {
		this.strRegCustom11 = strRegCustom11;
	}

	public String getStrRegCustom12() {
		return this.strRegCustom12;
	}

	public void setStrRegCustom12(String strRegCustom12) {
		this.strRegCustom12 = strRegCustom12;
	}

	public String getStrRegCustom13() {
		return this.strRegCustom13;
	}

	public void setStrRegCustom13(String strRegCustom13) {
		this.strRegCustom13 = strRegCustom13;
	}

	public String getStrRegCustom14() {
		return this.strRegCustom14;
	}

	public void setStrRegCustom14(String strRegCustom14) {
		this.strRegCustom14 = strRegCustom14;
	}

	public String getStrRegCustom15() {
		return this.strRegCustom15;
	}

	public void setStrRegCustom15(String strRegCustom15) {
		this.strRegCustom15 = strRegCustom15;
	}

	public void setStrIdeType(String strIdeType) {
		this.strIdeType = strIdeType;
	}

	public int getnCurrentLevel() {
		return this.nCurrentLevel;
	}

	public void setnCurrentLevel(int nCurrentLevel) {
		this.nCurrentLevel = nCurrentLevel;
	}

	public int getNCurrentLevel() {
		return this.nCurrentLevel;
	}

	public void setNCurrentLevel(int nCurrentLevel) {
		this.nCurrentLevel = nCurrentLevel;
	}

	public int getnIsLastLevel() {
		return this.nIsLastLevel;
	}

	public void setnIsLastLevel(int nIsLastLevel) {
		this.nIsLastLevel = nIsLastLevel;
	}

	public int getNIsLastLevel() {
		return this.nIsLastLevel;
	}

	public void setNIsLastLevel(int nIsLastLevel) {
		this.nIsLastLevel = nIsLastLevel;
	}

	public CustomerType getObjCustomerType() {
		return this.objCustomerType;
	}

	public void setObjCustomerType(CustomerType objCustomerType) {
		this.objCustomerType = objCustomerType;
	}

	public String getStrActiveStatus() {
		return this.nIsActive != null && this.nIsActive == 1 ? "已激活" : "未激活";
	}

	public String getStrPassword() {
		return this.strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public Customer getObjParentCustomer() {
		return this.objParentCustomer;
	}

	public void setObjParentCustomer(Customer objParentCustomer) {
		this.objParentCustomer = objParentCustomer;
	}

	public Set<Customer> getLstChildrenCustomer() {
		return this.lstChildrenCustomer;
	}

	public void setLstChildrenCustomer(Set<Customer> lstChildrenCustomer) {
		this.lstChildrenCustomer = lstChildrenCustomer;
	}

	public String getStrIsActive() {
		return this.nIsActive != null && this.nIsActive == 1 ? "已激活" : "未激活";
	}

	public String getStrParentCustomerName() {
		return this.objParentCustomer != null ? this.objParentCustomer.getStrSname() : "暂无";
	}

	public String getStrParentNickName() {
		return this.objParentCustomer != null ? this.objParentCustomer.getStrNickName() : "暂无";
	}

	public String getStrCustomerTypeName() {
		return this.objCustomerType != null ? this.objCustomerType.getStrName() : "暂无";
	}

	public String getStrParentCustomerQQ() {
		return this.objParentCustomer != null ? this.objParentCustomer.getStrQQ() : "暂无";
	}

	public String getStrParentCustomerPhone() {
		return this.objParentCustomer != null ? this.objParentCustomer.getStrSPhone() : "暂无";
	}

	public Set<Email> getLstEmail() {
		return this.lstEmail;
	}

	public void setLstEmail(Set<Email> lstEmail) {
		this.lstEmail = lstEmail;
	}

	public Set<Project> getLstProject() {
		return this.lstProject;
	}

	public void setLstProject(Set<Project> lstProject) {
		this.lstProject = lstProject;
	}

	public String getStrCurrentLevel() {
		return this.nCurrentLevel == 2 ? "项目管理员" : this.nCurrentLevel == 1 ? "初始等级" : "未知";
	}

	public String getStrNickName() {
		return strNickName;
	}

	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}

	public Integer getnIsActive() {
		return nIsActive;
	}

	public void setnIsActive(Integer nIsActive) {
		this.nIsActive = nIsActive;
	}

	public Integer getNIsActive() {
		return nIsActive;
	}

	public void setNIsActive(Integer nIsActive) {
		this.nIsActive = nIsActive;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getAvatarLarge() {
		return avatarLarge;
	}

	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}

	public String getStrRePassword() {
		return strRePassword;
	}

	public void setStrRePassword(String strRePassword) {
		this.strRePassword = strRePassword;
	}

}