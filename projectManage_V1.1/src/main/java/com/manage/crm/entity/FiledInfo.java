package com.manage.crm.entity;

import java.util.HashSet;
import java.util.Set;
import javacommon.core.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="FiledInfo")
@Table(name="FiledInfo")
public class FiledInfo extends BaseEntity
{
  private static final long serialVersionUID = 8400001807118190473L;
  public static final String FILED_BM_NEW = "1";
  public static final String FILED_BM_OLD = "-1";
  public static final String FILED_JG_NEW = "1";
  public static final String ID = "lId";
  public static final String FNAME = "strFName";
  public static final String FIDENTITY = "strFIdentity";
  public static final String FHTMLTYPE = "nFHtmlType";
  public static final String IS_JDJH = "nIsJDJH";
  public static final String IS_JG = "nIsJg";
  public static final String IS_BM = "nIsBM";
  public static final String IS_ARRAGE = "nIsArrage";
  public static final String FNATURE = "nNature";
  public static final String DEFALUT = "strDefalut";
  public static final String RESERVE1 = "strReserver1";
  public static final String RESERVE2 = "strReserver2";
  public static final String RESERVE3 = "strReserver3";
  public static final String RESERVE4 = "strReserver4";

  @ManyToMany(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REFRESH}, mappedBy="lstFiledInfo", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Project> lstProject = new HashSet();

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="FName", nullable=false)
  private String strFName;

  @Column(name="FIdentity", nullable=false)
  private String strFIdentity;

  @Column(name="FHTMLType", nullable=false)
  private int nFHtmlType;

  @Column(name="FNature", nullable=false)
  private int nNature;

  @Column(name="Defalut")
  private String strDefalut;

  @Transient
  private String strHTMLType;

  @Transient
  private String strNature;

  @Column(name="reserver1")
  private String strReserver1;

  @Column(name="reserver2")
  private String strReserver2;

  @Column(name="reserver3")
  private String strReserver3;

  @Column(name="reserver4")
  private String strReserver4;

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(long lId)
  {
    this.lId = lId;
  }

  public String getStrFName()
  {
    return this.strFName;
  }

  public void setStrFName(String strFName)
  {
    this.strFName = strFName;
  }

  public String getStrFIdentity()
  {
    return this.strFIdentity;
  }

  public void setStrFIdentity(String strFIdentity)
  {
    this.strFIdentity = strFIdentity;
  }

  public int getNFHtmlType()
  {
    return this.nFHtmlType;
  }

  public void setNFHtmlType(int nFHtmlType)
  {
    this.nFHtmlType = nFHtmlType;
  }

  public int getNNature()
  {
    return this.nNature;
  }

  public void setNNature(int nNature)
  {
    this.nNature = nNature;
  }

  public String getStrDefalut()
  {
    return this.strDefalut;
  }

  public void setStrDefalut(String strDefalut)
  {
    this.strDefalut = strDefalut;
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

  public Long getlId()
  {
    return this.lId;
  }

  public void setlId(long lId)
  {
    this.lId = lId;
  }

  public int getnFHtmlType()
  {
    return this.nFHtmlType;
  }

  public void setnFHtmlType(int nFHtmlType)
  {
    this.nFHtmlType = nFHtmlType;
  }

  public int getnNature()
  {
    return this.nNature;
  }

  public void setnNature(int nNature)
  {
    this.nNature = nNature;
  }

  public void setStrHTMLType(String strHTMLType)
  {
    this.strHTMLType = strHTMLType;
  }

  public String getStrNature()
  {
    return this.nNature == 1 ? "必填" : "默认";
  }

  public void setStrNature(String strNature)
  {
    this.strNature = strNature;
  }

  public Set<Project> getLstProject()
  {
    return this.lstProject;
  }

  public void setLstProject(Set<Project> lstProject)
  {
    this.lstProject = lstProject;
  }

  public String getStrHTMLType()
  {
    return this.strHTMLType;
  }
}