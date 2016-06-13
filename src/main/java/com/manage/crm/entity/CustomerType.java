package com.manage.crm.entity;

import java.util.HashSet;
import java.util.Set;
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

@Entity(name="CustomerType")
@Table(name="CustomerType")
public class CustomerType
{
  public static final String ID = "lId";

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinColumn(name="PID")
  private Project objProject;

  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objCustomerType", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Project> lstProject = new HashSet();

  @OneToMany(cascade={javax.persistence.CascadeType.MERGE}, mappedBy="objCustomerType", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Customer> lstCustomer = new HashSet();

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="name")
  private String strName;

  @Column(name="describle")
  private String strDescrible;

  @Column(name="isDefaultValue")
  private int nIsDefaultValue;

  @Column(name="remark")
  private String strRemark;

  public Long getlId() { return this.lId; }


  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public String getStrName()
  {
    return this.strName;
  }

  public void setStrName(String strName)
  {
    this.strName = strName;
  }

  public String getStrDescrible()
  {
    return this.strDescrible;
  }

  public void setStrDescrible(String strDescrible)
  {
    this.strDescrible = strDescrible;
  }

  public String getStrRemark()
  {
    return this.strRemark;
  }

  public void setStrRemark(String strRemark)
  {
    this.strRemark = strRemark;
  }

  public Project getObjProject()
  {
    return this.objProject;
  }

  public void setObjProject(Project objProject)
  {
    this.objProject = objProject;
  }

  public Set<Customer> getLstCustomer()
  {
    return this.lstCustomer;
  }

  public void setLstCustomer(Set<Customer> lstCustomer)
  {
    this.lstCustomer = lstCustomer;
  }

  public int getnIsDefaultValue()
  {
    return this.nIsDefaultValue;
  }

  public void setnIsDefaultValue(int nIsDefaultValue)
  {
    this.nIsDefaultValue = nIsDefaultValue;
  }

  public int getNIsDefaultValue()
  {
    return this.nIsDefaultValue;
  }

  public void setNIsDefaultValue(int nIsDefaultValue)
  {
    this.nIsDefaultValue = nIsDefaultValue;
  }

  public Set<Project> getLstProject() {
    return this.lstProject;
  }

  public void setLstProject(Set<Project> lstProject) {
    this.lstProject = lstProject;
  }
}