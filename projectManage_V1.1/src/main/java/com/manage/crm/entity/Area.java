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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Area")
@Table(name="Area")
public class Area extends BaseEntity
{
  private static final long serialVersionUID = 8759258670597170305L;
  public static final String ID = "lId";
  public static final String PARID = "lParId";
  public static final String ANUM = "lAreaNum";
  public static final String ANAME = "strName";
  public static final String XGSJ = "strXGSJ";
  public static final String RESERVER1 = "strReserver1";
  public static final String RESERVER2 = "strReserver2";
  public static final String RESERVER3 = "strReserver3";
  public static final String RESERVER4 = "strReserver4";

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
  @JoinColumn(name="ParID", referencedColumnName="ANum", insertable=false, updatable=false)
  private Area objParentArea;

  @OneToMany(cascade={javax.persistence.CascadeType.MERGE}, mappedBy="objParentArea", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Area> lstChildrenArea = new HashSet();

  @OneToMany(cascade={javax.persistence.CascadeType.MERGE}, mappedBy="objArea", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Users> lstUsers = new HashSet();

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="ParID", nullable=false)
  private long lParId;

  @Column(name="ANum", nullable=false)
  private long lAreaNum;

  @Column(name="AName", nullable=false)
  private String strName;

  @Column(name="XGSJ")
  private String strXGSJ;

  @Transient
  private String strFullName;

  @Column(name="reserver1")
  private String strReserver1;

  @Column(name="reserver2")
  private String strReserver2;

  @Column(name="reserver3")
  private String strReserver3;

  @Column(name="reserver4")
  private String strReserver4;

  public Set<Area> getLstChildrenNode()
  {
    return this.lstChildrenArea;
  }

  public void setLstChildrenNode(Set<Area> lstChildrenNode)
  {
    this.lstChildrenArea = lstChildrenNode;
  }

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public long getLParId()
  {
    return this.lParId;
  }

  public void setLParId(long lParId)
  {
    this.lParId = lParId;
  }

  public long getLAreaNum()
  {
    return this.lAreaNum;
  }

  public void setLAreaNum(long lAreaNum)
  {
    this.lAreaNum = lAreaNum;
  }

  public String getStrName()
  {
    return this.strName;
  }

  public void setStrName(String strName)
  {
    this.strName = strName;
  }

  public String getStrXGSJ()
  {
    return this.strXGSJ;
  }

  public void setStrXGSJ(String strXGSJ)
  {
    this.strXGSJ = strXGSJ;
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

  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public long getlParId()
  {
    return this.lParId;
  }

  public void setlParId(long lParId)
  {
    this.lParId = lParId;
  }

  public long getlAreaNum()
  {
    return this.lAreaNum;
  }

  public void setlAreaNum(long lAreaNum)
  {
    this.lAreaNum = lAreaNum;
  }

  public Area getObjParentArea()
  {
    return this.objParentArea;
  }

  public void setObjParentArea(Area objParentArea)
  {
    this.objParentArea = objParentArea;
  }

  public Set<Area> getLstChildrenArea()
  {
    return this.lstChildrenArea;
  }

  public void setLstChildrenArea(Set<Area> lstChildrenArea)
  {
    this.lstChildrenArea = lstChildrenArea;
  }

  public String getStrFullName()
  {
    return getFullName(this, "");
  }

  private String getFullName(Area objArea, String strNameCol)
  {
    if (objArea.getLParId() == -1L)
    {
      return objArea.getStrName() + strNameCol;
    }

    return getFullName(objArea.objParentArea, objArea.getStrName() + strNameCol);
  }

  public void setStrFullName(String strFullName)
  {
    this.strFullName = strFullName;
  }

  public Set<Users> getLstUsers()
  {
    return this.lstUsers;
  }

  public void setLstUsers(Set<Users> lstUsers)
  {
    this.lstUsers = lstUsers;
  }
}