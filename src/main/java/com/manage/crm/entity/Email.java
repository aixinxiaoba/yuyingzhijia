package com.manage.crm.entity;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Email")
@Table(name="Email")
public class Email extends BaseEntity
{
  private static final long serialVersionUID = 8759258670597170305L;
  public static final String ID = "lId";

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinColumn(name="UID")
  private Users objUsers;

  @ManyToMany(cascade={javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinTable(name="email_customer", joinColumns={@JoinColumn(name="EID")}, inverseJoinColumns={@JoinColumn(name="CID")})
  @OrderBy("lId")
  private Set<Customer> lstCustomer = new LinkedHashSet();

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinColumn(name="PID")
  private Project objProject;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="EContent")
  private String strContent;

  @Column(name="subject")
  private String strSubject;

  @Column(name="sendDate")
  private String strSendDate;

  @Column(name="sendType")
  private String strSendType;

  @Transient
  private String strSenderName;

  @Transient
  private String strReciverName;

  public Users getObjUsers() { return this.objUsers; }


  public void setObjUsers(Users objUsers)
  {
    this.objUsers = objUsers;
  }

  public Long getlId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public Long getLId()
  {
    return this.lId;
  }

  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public String getStrContent()
  {
    return this.strContent;
  }

  public void setStrContent(String strContent)
  {
    this.strContent = strContent;
  }

  public String getStrSendDate()
  {
    return this.strSendDate;
  }

  public void setStrSendDate(String strSendDate)
  {
    this.strSendDate = strSendDate;
  }

  public Project getObjProject()
  {
    return this.objProject;
  }

  public void setObjProject(Project objProject)
  {
    this.objProject = objProject;
  }

  public String getStrSendType()
  {
    return this.strSendType;
  }

  public void setStrSendType(String strSendType)
  {
    this.strSendType = strSendType;
  }

  public String getStrSubject()
  {
    return this.strSubject;
  }

  public void setStrSubject(String strSubject)
  {
    this.strSubject = strSubject;
  }

  public Set<Customer> getLstCustomer()
  {
    return this.lstCustomer;
  }

  public void setLstCustomer(Set<Customer> lstCustomer)
  {
    this.lstCustomer = lstCustomer;
  }

  public String getStrSenderName()
  {
    return this.objUsers.getStrName();
  }

  public String getStrReciverName()
  {
    if ((this.lstCustomer != null) && (this.lstCustomer.size() > 0))
    {
      this.strReciverName = "";
      for (Customer objCustomer : this.lstCustomer)
      {
        if (!StringUtils.isEmpty(this.strReciverName))
        {
          this.strReciverName = (this.strReciverName + "," + objCustomer.getStrSname());
        }
        else
        {
          this.strReciverName += objCustomer.getStrSname();
        }

      }

    }

    return this.strReciverName;
  }
}