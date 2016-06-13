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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="Users")
@Table(name="Users")
public class Users extends BaseEntity
{
  public static final String ID = "lId";
  public static final String CURRENT_LEVEL = "nCurrentLevel";
  public static final String IS_LAST_LEVEL = "nIsLastLevel";
  public static final String IS_ACTIVE = "nIsActive";
  public static final String AID = "lAreaId";
  public static final String ONAME = "strName";
  public static final String OSHORTNAME = "strShortName";
  public static final String LOGINNAME = "strLoginName";
  public static final String OPASSWORD = "strPassword";
  public static final String LASTMODIFYBY = "strLastModifyPerson";
  public static final String XGSJ = "strLastModifyTime";
  public static final String OTYPE = "strType";
  public static final String ONUMBER = "strNumber";
  public static final String PINCHARGE = "strChargePerson";
  public static final String PTEL = "strPersonTel";
  public static final String PPHONE = "strPersonPhone";
  public static final String PFAX = "strPersonFax";
  public static final String OVALIDTIME = "strValidTime";
  public static final String OADDRESS = "strAddress";
  public static final String KSADDRESS = "strKSAddress";
  public static final String ADVISEWAY = "strAdviseWay";
  public static final String EMAIL = "strEmail";
  public static final String POSTCODE = "strPostcode";
  public static final String WEBSITE = "strWebSite";
  public static final String ISALLOWCREATEJDJH = "nIsAllowCreateJDJH";
  public static final String KDTYPE = "strKDType";
  public static final String KSLEVEL = "strKSLevel";
  public static final String OCODE = "strCode";
  public static final String OLEVEL = "nLevel";
  public static final String EPAYLOGIN = "strEPAYLogin";
  public static final String EPAYFENZHANG = "strEPAYFenZhang";
  public static final String PICPATH = "strPicPath";
  public static final String VALIDCOUNT = "nValidCount";
  public static final String XKZ = "strXKZ";
  public static final String SPYJ = "strSPYJ";
  public static final String OLAIYUAN = "strLaiyuan";
  public static final String EXAM_NUM = "nExamNum";
  public static final String EACH_ERN = "nEachERN";
  public static final String LAYOUT_COM = "nLayoutCom";
  public static final String LOGIN_STATUS = "nLoginStatus";
  public static final String REMARKS = "strRemarks";
  public static final String BEGINTIME = "strBeginTime";
  public static final String ENDTIME = "strEndTime";
  public static final String RESERVER1 = "strReserver1";
  public static final String RESERVER2 = "strReserver2";
  public static final String RESERVER3 = "strReserver3";
  public static final String RESERVER4 = "strReserver4";
  public static final String RESERVER5 = "strReserver5";
  public static final String RESERVER6 = "strReserver6";
  public static final String RESERVER7 = "strReserver7";
  public static final String RESERVER8 = "strReserver8";
  public static final String RESERVER9 = "strReserver9";
  public static final String RESERVER10 = "strReserver10";
  public static final String JGCUSTOM1 = "strJGCustom1";
  public static final String JGCUSTOM2 = "strJGCustom2";
  public static final String JGCUSTOM3 = "strJGCustom3";
  public static final String JGCUSTOM4 = "strJGCustom4";
  public static final String JGCUSTOM5 = "strJGCustom5";
  public static final String JGCUSTOM6 = "strJGCustom6";
  public static final String JGCUSTOM7 = "strJGCustom7";
  public static final String JGCUSTOM8 = "strJGCustom8";
  public static final String JGCUSTOM9 = "strJGCustom9";
  public static final String JGCUSTOM10 = "strJGCustom10";
  private static final long serialVersionUID = -2146640575878339990L;

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
  @JoinColumn(name="ParID")
  private Users objParentUsers;

  @OneToMany(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, mappedBy="objParentUsers", fetch=FetchType.LAZY)
  @Fetch(FetchMode.SUBSELECT)
  @OrderBy("lId")
  private Set<Users> lstChildUsers = new LinkedHashSet();

  @ManyToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
  @JoinTable(name="Users_Project", joinColumns={@JoinColumn(name="UID")}, inverseJoinColumns={@JoinColumn(name="PID")})
  @OrderBy("lId")
  private Set<Project> lstProject = new LinkedHashSet();

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
  @JoinColumn(name="AID")
  private Area objArea;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID")
  private Long lId;

  @Column(name="CURRENT_LEVEL", nullable=false)
  private Integer nCurrentLevel;

  @Column(name="IS_LAST_LEVEL", nullable=false)
  private Integer nIsLastLevel;

  @Column(name="IS_ACTIVE", nullable=false)
  private Integer nIsActive;

  @Transient
  private String strStatus;

  @Column(name="OName", length=100, nullable=false)
  private String strName;

  @Column(name="OShortName", length=100)
  private String strShortName;

  @Column(name="LoginName", length=100)
  private String strLoginName;

  @Column(name="OPassword", length=100)
  private String strPassword;

  @Column(name="LastModifyBy", length=100)
  private String strLastModifyPerson;

  @Column(name="XGSJ", length=100)
  private String strLastModifyTime;

  @Column(name="OType", length=100)
  private String strType;

  @Transient
  private String strTypeDesc;

  @Transient
  private String strKDTypeDesc;

  @Column(name="ONumber", length=100)
  private String strNumber;

  @Column(name="PTel", length=100)
  private String strPersonTel;

  @Column(name="PPhone", length=100)
  private String strPersonPhone;

  @Column(name="PFax", length=100)
  private String strPersonFax;

  @Column(name="OAddress", length=255)
  private String strAddress;

  @Column(name="Email", length=100)
  private String strEmail;

  @Column(name="Postcode", length=100)
  private String strPostcode;

  @Column(name="OLevel")
  private Integer nLevel;

  @Column(name="ValidCount")
  private Integer nValidCount;

  @Column(name="SPYJ", length=100)
  private String strSPYJ;

  @Column(name="OLaiyuan", length=100)
  private String strLaiyuan;

  @Column(name="Exam_Num")
  private Integer nExamNum;

  @Column(name="Each_ERN")
  private Integer nEachERN;

  @Column(name="Layout_Com")
  private Integer nLayoutCom;

  @Column(name="Login_Status")
  private Integer nLoginStatus;

  @Column(name="Remarks", length=100)
  private String strRemarks;

  @Column(name="reserver1", length=255)
  private String strReserver1;

  @Column(name="reserver2", length=255)
  private String strReserver2;

  @Column(name="reserver3", length=255)
  private String strReserver3;

  @Column(name="reserver4", length=255)
  private String strReserver4;

  @Column(name="reserver5", length=255)
  private String strReserver5;

  @Column(name="reserver6", length=255)
  private String strReserver6;

  @Column(name="reserver7", length=255)
  private String strReserver7;

  @Column(name="reserver8", length=255)
  private String strReserver8;

  @Column(name="reserver9", length=255)
  private String strReserver9;

  @Column(name="reserver10", length=255)
  private String strReserver10;

  public Users getObjParentUsers()
  {
    return this.objParentUsers;
  }

  public void setObjParentUsers(Users objParentUsers)
  {
    this.objParentUsers = objParentUsers;
  }

  public Set<Users> getLstChildUsers()
  {
    return this.lstChildUsers;
  }

  public void setLstChildUsers(Set<Users> lstChildUsers)
  {
    this.lstChildUsers = lstChildUsers;
  }

  public Integer getnCurrentLevel()
  {
    return this.nCurrentLevel;
  }

  public void setnCurrentLevel(Integer nCurrentLevel)
  {
    this.nCurrentLevel = nCurrentLevel;
  }

  public Integer getNCurrentLevel()
  {
    return this.nCurrentLevel;
  }

  public void setNCurrentLevel(Integer nCurrentLevel)
  {
    this.nCurrentLevel = nCurrentLevel;
  }

  public Integer getNIsLastLevel()
  {
    return this.nIsLastLevel;
  }

  public void setNIsLastLevel(Integer nIsLastLevel)
  {
    this.nIsLastLevel = nIsLastLevel;
  }

  public Integer getNIsActive()
  {
    return this.nIsActive;
  }

  public void setNIsActive(Integer nIsActive)
  {
    this.nIsActive = nIsActive;
  }

  public String getStrName()
  {
    return this.strName;
  }

  public void setStrName(String strName)
  {
    this.strName = strName;
  }

  public String getStrShortName()
  {
    return this.strShortName;
  }

  public void setStrShortName(String strShortName)
  {
    this.strShortName = strShortName;
  }

  public String getStrLoginName()
  {
    return this.strLoginName;
  }

  public void setStrLoginName(String strLoginName)
  {
    this.strLoginName = strLoginName;
  }

  public String getStrPassword()
  {
    return this.strPassword;
  }

  public void setStrPassword(String strPassword)
  {
    this.strPassword = strPassword;
  }

  public String getStrLastModifyPerson()
  {
    return this.strLastModifyPerson;
  }

  public void setStrLastModifyPerson(String strLastModifyPerson)
  {
    this.strLastModifyPerson = strLastModifyPerson;
  }

  public String getStrLastModifyTime()
  {
    return this.strLastModifyTime;
  }

  public void setStrLastModifyTime(String strLastModifyTime)
  {
    this.strLastModifyTime = strLastModifyTime;
  }

  public String getStrType()
  {
    return this.strType;
  }

  public void setStrType(String strType)
  {
    this.strType = strType;
  }

  public String getStrNumber()
  {
    return this.strNumber;
  }

  public void setStrNumber(String strNumber)
  {
    this.strNumber = strNumber;
  }

  public String getStrPersonTel()
  {
    return this.strPersonTel;
  }

  public void setStrPersonTel(String strPersonTel)
  {
    this.strPersonTel = strPersonTel;
  }

  public String getStrPersonPhone()
  {
    return this.strPersonPhone;
  }

  public void setStrPersonPhone(String strPersonPhone)
  {
    this.strPersonPhone = strPersonPhone;
  }

  public String getStrPersonFax()
  {
    return this.strPersonFax;
  }

  public void setStrPersonFax(String strPersonFax)
  {
    this.strPersonFax = strPersonFax;
  }

  public String getStrAddress()
  {
    return this.strAddress;
  }

  public void setStrAddress(String strAddress)
  {
    this.strAddress = strAddress;
  }

  public String getStrEmail()
  {
    return this.strEmail;
  }

  public void setStrEmail(String strEmail)
  {
    this.strEmail = strEmail;
  }

  public String getStrPostcode()
  {
    return this.strPostcode;
  }

  public void setStrPostcode(String strPostcode)
  {
    this.strPostcode = strPostcode;
  }

  public Integer getNLevel()
  {
    return this.nLevel;
  }

  public void setNLevel(Integer nLevel)
  {
    this.nLevel = nLevel;
  }

  public Integer getNValidCount()
  {
    return this.nValidCount;
  }

  public void setNValidCount(Integer nValidCount)
  {
    this.nValidCount = nValidCount;
  }

  public String getStrSPYJ()
  {
    return this.strSPYJ;
  }

  public void setStrSPYJ(String strSPYJ)
  {
    this.strSPYJ = strSPYJ;
  }

  public String getStrLaiyuan()
  {
    return this.strLaiyuan;
  }

  public void setStrLaiyuan(String strLaiyuan)
  {
    this.strLaiyuan = strLaiyuan;
  }

  public Integer getNExamNum()
  {
    return this.nExamNum;
  }

  public void setNExamNum(Integer nExamNum)
  {
    this.nExamNum = nExamNum;
  }

  public Integer getNEachERN()
  {
    return this.nEachERN;
  }

  public void setNEachERN(Integer nEachERN)
  {
    this.nEachERN = nEachERN;
  }

  public Integer getNLayoutCom()
  {
    return this.nLayoutCom;
  }

  public void setNLayoutCom(Integer nLayoutCom)
  {
    this.nLayoutCom = nLayoutCom;
  }

  public Integer getNLoginStatus()
  {
    return this.nLoginStatus;
  }

  public void setNLoginStatus(Integer nLoginStatus)
  {
    this.nLoginStatus = nLoginStatus;
  }

  public String getStrRemarks()
  {
    return this.strRemarks;
  }

  public void setStrRemarks(String strRemarks)
  {
    this.strRemarks = strRemarks;
  }

  public Set<Project> getLstProject()
  {
    return this.lstProject;
  }

  public void setLstProject(Set<Project> lstProject)
  {
    this.lstProject = lstProject;
  }

  public String getStrReserver1()
  {
    return this.strReserver1;
  }

  public void setStrReserver1(String strReserver1)
  {
    this.strReserver1 = strReserver1;
  }

  public String getStrReserver2()
  {
    return this.strReserver2;
  }

  public void setStrReserver2(String strReserver2)
  {
    this.strReserver2 = strReserver2;
  }

  public String getStrReserver3()
  {
    return this.strReserver3;
  }

  public void setStrReserver3(String strReserver3)
  {
    this.strReserver3 = strReserver3;
  }

  public String getStrReserver4()
  {
    return this.strReserver4;
  }

  public void setStrReserver4(String strReserver4)
  {
    this.strReserver4 = strReserver4;
  }

  public String getStrReserver5()
  {
    return this.strReserver5;
  }

  public void setStrReserver5(String strReserver5)
  {
    this.strReserver5 = strReserver5;
  }

  public String getStrReserver6()
  {
    return this.strReserver6;
  }

  public void setStrReserver6(String strReserver6)
  {
    this.strReserver6 = strReserver6;
  }

  public String getStrReserver7()
  {
    return this.strReserver7;
  }

  public void setStrReserver7(String strReserver7)
  {
    this.strReserver7 = strReserver7;
  }

  public String getStrReserver8()
  {
    return this.strReserver8;
  }

  public void setStrReserver8(String strReserver8)
  {
    this.strReserver8 = strReserver8;
  }

  public String getStrReserver9()
  {
    return this.strReserver9;
  }

  public void setStrReserver9(String strReserver9)
  {
    this.strReserver9 = strReserver9;
  }

  public String getStrReserver10()
  {
    return this.strReserver10;
  }

  public void setStrReserver10(String strReserver10)
  {
    this.strReserver10 = strReserver10;
  }

  public boolean isAdmin()
  {
    return "PTHLAdmin".equals(this.strLoginName);
  }

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public String getStrStatus()
  {
    if (this.nIsActive.intValue() == 0)
    {
      return "已禁用";
    }

    return "已激活";
  }

  public void setStrStatus(String strStatus)
  {
    this.strStatus = strStatus;
  }

  public Long getlId()
  {
    return this.lId;
  }

  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public Integer getnIsLastLevel()
  {
    return this.nIsLastLevel;
  }

  public void setnIsLastLevel(Integer nIsLastLevel)
  {
    this.nIsLastLevel = nIsLastLevel;
  }

  public Integer getnIsActive()
  {
    return this.nIsActive;
  }

  public void setnIsActive(Integer nIsActive)
  {
    this.nIsActive = nIsActive;
  }

  public Integer getnLevel()
  {
    return this.nLevel;
  }

  public void setnLevel(Integer nLevel)
  {
    this.nLevel = nLevel;
  }

  public Integer getnValidCount()
  {
    return this.nValidCount;
  }

  public void setnValidCount(Integer nValidCount)
  {
    this.nValidCount = nValidCount;
  }

  public Integer getnExamNum()
  {
    return this.nExamNum;
  }

  public void setnExamNum(Integer nExamNum)
  {
    this.nExamNum = nExamNum;
  }

  public Integer getnEachERN()
  {
    return this.nEachERN;
  }

  public void setnEachERN(Integer nEachERN)
  {
    this.nEachERN = nEachERN;
  }

  public Integer getnLayoutCom()
  {
    return this.nLayoutCom;
  }

  public void setnLayoutCom(Integer nLayoutCom)
  {
    this.nLayoutCom = nLayoutCom;
  }

  public Integer getnLoginStatus()
  {
    return this.nLoginStatus;
  }

  public void setnLoginStatus(Integer nLoginStatus)
  {
    this.nLoginStatus = nLoginStatus;
  }

  public Area getObjArea()
  {
    return this.objArea;
  }

  public void setObjArea(Area objArea)
  {
    this.objArea = objArea;
  }

  public void setStrTypeDesc(String strTypeDesc)
  {
    this.strTypeDesc = strTypeDesc;
  }

  public void setStrKDTypeDesc(String strKDTypeDesc)
  {
    this.strKDTypeDesc = strKDTypeDesc;
  }

  public String getStrActiveStatu()
  {
    return this.nIsActive.intValue() == 1 ? "已激活" : "未激活";
  }
}