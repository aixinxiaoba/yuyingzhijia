package com.manage.crm.entity;

import javacommon.core.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Pro_FiledInfo")
@Table(name="Pro_FiledInfo")
public class ProFiledInfo extends BaseEntity
{
  private static final long serialVersionUID = -7834936503090605179L;
  public static final int FIELD_NEED = 1;
  public static final int FIELD_DEFAULT = 2;
  public static final int FIELD_ACTIVE = 1;
  public static final int FIELD_NOT_ACTIVE = 0;
  public static final String ID = "lId";
  public static final String PID = "lPid";
  public static final String FIID = "lFiid";
  public static final String SEQUENCE = "nSequence";
  public static final String PFNAME = "strPfname";
  public static final String PFIDENTITY = "strPfidentity";
  public static final String PFHTMLTYPE = "nPfhtmlType";
  public static final String PFNATURE = "nPfnature";
  public static final String PFDEFAULT = "strPfdefault";
  public static final String PFSTATUS = "nStatus";
  public static final String IS_ADD = "nIsAdd";
  public static final String IS_MODIFY = "nIsModify";
  public static final String STRHTMLTYPE = "strHTMLType";
  public static final String STRNATURE = "strNature";
  public static final String RESERVER1 = "strReserver1";
  public static final String RESERVER2 = "strReserver2";
  public static final String RESERVER3 = "strReserver3";
  public static final String RESERVER4 = "strReserver4";

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="PID", nullable=false)
  private Long lPid;

  @Column(name="FIID", nullable=false)
  private Long lFiid;

  @Column(name="Sequence", nullable=false)
  private int nSequence;

  @Column(name="IS_ADD", nullable=false)
  private int nIsAdd;

  @Column(name="IS_Modify", nullable=false)
  private int nIsModify;

  @Column(name="PFName")
  private String strPfname;

  @Column(name="PFIdentity", nullable=false)
  private String strPfidentity;

  @Column(name="PFHTMLType")
  private int nPfhtmlType;

  @Column(name="PFNature")
  private int nPfnature;

  @Column(name="PFDefault")
  private String strPfdefault;

  @Column(name="PFStatus")
  private int nStatus;

  @Transient
  private String strHTMLType;

  @Transient
  private String strNature;

  @Transient
  private String strStatus;

  @Transient
  private String strIsAdd;

  @Transient
  private String strIsModify;

  @Column(name="RESERVER1")
  private String strReserver1;

  @Column(name="RESERVER2")
  private String strReserver2;

  @Column(name="RESERVER3")
  private String strReserver3;

  @Column(name="RESERVER4")
  private String strReserver4;

  public String getStrPfname()
  {
    return this.strPfname;
  }

  public void setStrPfname(String strPfname)
  {
    this.strPfname = strPfname;
  }

  public String getStrPfidentity()
  {
    return this.strPfidentity;
  }

  public void setStrPfidentity(String strPfidentity)
  {
    this.strPfidentity = strPfidentity;
  }

  public String getStrPfdefault()
  {
    return this.strPfdefault;
  }

  public void setStrPfdefault(String strPfdefault)
  {
    this.strPfdefault = strPfdefault;
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

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public Long getLPid()
  {
    return this.lPid;
  }

  public void setLPid(Long lPid)
  {
    this.lPid = lPid;
  }

  public Long getLFiid()
  {
    return this.lFiid;
  }

  public void setLFiid(Long lFiid)
  {
    this.lFiid = lFiid;
  }

  public int getNSequence()
  {
    return this.nSequence;
  }

  public void setNSequence(int nSequence)
  {
    this.nSequence = nSequence;
  }

  public int getNPfhtmlType()
  {
    return this.nPfhtmlType;
  }

  public void setNPfhtmlType(int nPfhtmlType)
  {
    this.nPfhtmlType = nPfhtmlType;
  }

  public int getNPfnature()
  {
    return this.nPfnature;
  }

  public void setNPfnature(int nPfnature)
  {
    this.nPfnature = nPfnature;
  }

  public Long getlId()
  {
    return this.lId;
  }

  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public Long getlPid()
  {
    return this.lPid;
  }

  public void setlPid(Long lPid)
  {
    this.lPid = lPid;
  }

  public Long getlFiid()
  {
    return this.lFiid;
  }

  public void setlFiid(Long lFiid)
  {
    this.lFiid = lFiid;
  }

  public int getnSequence()
  {
    return this.nSequence;
  }

  public void setnSequence(int nSequence)
  {
    this.nSequence = nSequence;
  }

  public int getnPfhtmlType()
  {
    return this.nPfhtmlType;
  }

  public void setnPfhtmlType(int nPfhtmlType)
  {
    this.nPfhtmlType = nPfhtmlType;
  }

  public int getnPfnature()
  {
    return this.nPfnature;
  }

  public void setnPfnature(int nPfnature)
  {
    this.nPfnature = nPfnature;
  }

  public String getStrHTMLType()
  {
    return this.strHTMLType;
  }

  public void setStrHTMLType(String strHTMLType)
  {
    this.strHTMLType = strHTMLType;
  }

  public String getStrNature()
  {
    return this.nPfnature == 1 ? "必填" : "默认";
  }

  public void setStrNature(String strNature)
  {
    this.strNature = strNature;
  }

  public int getnStatus()
  {
    return this.nStatus;
  }

  public void setnStatus(int nStatus)
  {
    this.nStatus = nStatus;
  }

  public int getNStatus()
  {
    return this.nStatus;
  }

  public void setNStatus(int nStatus)
  {
    this.nStatus = nStatus;
  }

  public int getnIsAdd()
  {
    return this.nIsAdd;
  }

  public void setNIsAdd(int nIsAdd)
  {
    this.nIsAdd = nIsAdd;
  }

  public int getNIsAdd()
  {
    return this.nIsAdd;
  }

  public void setnIsAdd(int nIsAdd)
  {
    this.nIsAdd = nIsAdd;
  }

  public int getnIsModify()
  {
    return this.nIsModify;
  }

  public void setnIsModify(int nIsModify)
  {
    this.nIsModify = nIsModify;
  }

  public int getNIsModify()
  {
    return this.nIsModify;
  }

  public void setNIsModify(int nIsModify)
  {
    this.nIsModify = nIsModify;
  }

  public String getStrStatus()
  {
    return this.nStatus == 1 ? "启用" : "未启用";
  }

  public String getStrIsAdd()
  {
    return this.nIsAdd == 1 ? "是" : "否";
  }

  public String getStrIsModify()
  {
    return this.nIsModify == 1 ? "是" : "否";
  }
}