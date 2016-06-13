package com.manage.crm.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Project")
@Table(name="Project")
public class Project extends BaseEntity
{
  private static final long serialVersionUID = -5777628392574297028L;
  public static final int BMTYPE_KD = 0;
  public static final int BMTYPE_AREA = 1;
  public static final String ID = "lId";
  public static final String CURRENT_LEVEL = "nCurrentLevel";
  public static final String IS_LAST_LEVEL = "nIsLastLevel";
  public static final String IS_ACTIVE = "nIsActive";
  public static final String PNAME = "strPname";
  public static final String PENNAME = "strPenname";
  public static final String PNUM = "nPnum";
  public static final String PTYPE = "nPtype";
  public static final String BEGIN_TIME = "strBeginTime";
  public static final String END_TIME = "strEndTime";
  public static final String BM_TYPE = "nBmType";
  public static final String TP_TKZ = "strTpTkz";
  public static final String TP_ZKZ = "strTpZkz";
  public static final String TP_CERTIFICATE = "strTpCertificate";
  public static final String TP_HMC = "strTpHmc";
  public static final String IS_NBT = "nIsNbt";
  public static final String NBT_EMAIL = "nNbtEmail";
  public static final String NBT_NOTICE = "nNbtNotice";
  public static final String NBT_TASK = "nNbtTask";
  public static final String NBT_MATERIAL = "nNbtMaterial";
  public static final String NBT_FILE = "nNbtFile";
  public static final String NBT_MESSAGE = "nNbtMessage";
  public static final String NBT_WORK = "nNbtWork";
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
  public static final String SCOREQUERYSITE = "strScoreQuerySite";
  public static final String CERQUERYSITE = "strCerQuerySite";
  public static final String ISMULTIJDJH = "nIsMultiJDJH";
  public static final String MERCHANT_NAME = "strMerchantName";
  public static final String MERCHANT_NO = "strMerchantNo";
  public static final String MERCHANT_KEY = "strMerchantKey";

  @ManyToMany(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY, mappedBy="lstProject")
  @OrderBy("lId")
  private Set<Customer> lstCustomer = new LinkedHashSet();

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
  @JoinColumn(name="ParID")
  private Project objParentProject;

  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objParentProject", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Project> lstChildrenProject = new HashSet();

  @ManyToMany(cascade={javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY, mappedBy="lstProject")
  @OrderBy("lId")
  private Set<Users> lstUsers = new LinkedHashSet();

  @ManyToMany(cascade={javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinTable(name="Pro_FiledInfo", joinColumns={@JoinColumn(name="PID")}, inverseJoinColumns={@JoinColumn(name="FIID")})
  @OrderBy("lId")
  private Set<FiledInfo> lstFiledInfo = new LinkedHashSet();

  @ManyToOne(cascade={javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST}, fetch=FetchType.LAZY)
  @JoinColumn(name="CTID")
  private CustomerType objCustomerType;

  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objProject", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<CustomerType> lstCustomerType = new HashSet();
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objProject", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<ProjectMenu> lstProjectMenu = new HashSet();

  @OneToMany(cascade={javax.persistence.CascadeType.MERGE}, mappedBy="objProject", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<Email> lstEmail = new HashSet();
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="objProject", fetch=FetchType.LAZY)
  @OrderBy("lId")
  private Set<News> lstNews = new HashSet();

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", nullable=false)
  private Long lId;

  @Column(name="CURRENT_LEVEL", nullable=false)
  private int nCurrentLevel;

  @Column(name="IS_LAST_LEVEL", nullable=false)
  private int nIsLastLevel;

  @Column(name="IS_ACTIVE", nullable=false)
  private int nIsActive;

  @Column(name="EmailListPID")
  private String strEmailListPID;

  @Column(name="EmailContent")
  private String strEmailContent;

  @Column(name="MERID")
  private String strMerId;

  @Column(name="KEYVALUE")
  private String strKeyValue;

  @Column(name="CALLBACKURL")
  private String strCallBackURL;

  @Column(name="EMAIL_LIST_STATUS")
  private int nEmailListStatus;
  
  @Column(name="projectKey")
  private String projectKey;

  @Transient
  private String strStatus;

  @Transient
  private Long lCustomerTypeID;

  @Transient
  private String strCustomerTypeName;

  @Column(name="PName", nullable=false)
  private String strPname;

  @Column(name="PENName")
  private String strPenname;

  @Column(name="PNum")
  private int nPnum;

  @Column(name="PType")
  private int nPtype;

  @Column(name="begin_Time")
  private String strBeginTime;

  @Column(name="end_Time")
  private String strEndTime;

  @Column(name="is_use_result_page")
  private int nIsUseResultPage;

  @Column(name="RESERVER1")
  private String strReserver1;

  @Column(name="RESERVER2")
  private String strReserver2;

  @Column(name="RESERVER3")
  private String strReserver3;

  @Column(name="RESERVER4")
  private String strReserver4;

  @Column(name="RESERVER5")
  private String strReserver5;

  @Column(name="RESERVER6")
  private String strReserver6;

  @Column(name="RESERVER7")
  private String strReserver7;

  @Column(name="RESERVER8")
  private String strReserver8;

  @Column(name="RESERVER9")
  private String strReserver9;

  @Column(name="RESERVER10")
  private String strReserver10;
  
  @Transient
  private String strErrorMsg;

  public Project(){}
  
  public Project(String strErrorMsg)
  {
	  this.strErrorMsg = strErrorMsg;
  }
  
  public int getnCurrentLevel() { return this.nCurrentLevel; }


  public void setNCurrentLevel(int nCurrentLevel)
  {
    this.nCurrentLevel = nCurrentLevel;
  }

  public int getNIsLastLevel()
  {
    return this.nIsLastLevel;
  }

  public void setNIsLastLevel(int nIsLastLevel)
  {
    this.nIsLastLevel = nIsLastLevel;
  }

  public int getNPnum()
  {
    return this.nPnum;
  }

  public void setNPnum(int nPnum)
  {
    this.nPnum = nPnum;
  }

  public int getNPtype()
  {
    return this.nPtype;
  }

  public void setNPtype(int nPtype)
  {
    this.nPtype = nPtype;
  }

  public String getStrPname()
  {
    return this.strPname;
  }

  public void setStrPname(String strPname)
  {
    this.strPname = strPname;
  }

  public String getStrPenname()
  {
    return this.strPenname;
  }

  public void setStrPenname(String strPenname)
  {
    this.strPenname = strPenname;
  }

  public String getStrBeginTime()
  {
    return this.strBeginTime;
  }

  public void setStrBeginTime(String strBeginTime)
  {
    this.strBeginTime = strBeginTime;
  }

  public String getStrEndTime()
  {
    return this.strEndTime;
  }

  public void setStrEndTime(String strEndTime)
  {
    this.strEndTime = strEndTime;
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

  public Set<Users> getLstUsers()
  {
    return this.lstUsers;
  }

  public void setLstUsers(Set<Users> lstUsers)
  {
    this.lstUsers = lstUsers;
  }

  public int getNIsActive()
  {
    return this.nIsActive;
  }

  public void setNIsActive(int nIsActive)
  {
    this.nIsActive = nIsActive;
  }

  public String getStrStatus()
  {
    if (this.nIsActive == 0)
    {
      return "未激活";
    }

    return "激活";
  }

  public void setStrStatus(String strStatus)
  {
    this.strStatus = strStatus;
  }

  public Long getLId()
  {
    return this.lId;
  }

  public void setLId(Long lId)
  {
    this.lId = lId;
  }

  public int getNCurrentLevel()
  {
    return this.nCurrentLevel;
  }

  public Long getlId()
  {
    return this.lId;
  }

  public void setlId(Long lId)
  {
    this.lId = lId;
  }

  public int getnIsLastLevel()
  {
    return this.nIsLastLevel;
  }

  public void setnIsLastLevel(int nIsLastLevel)
  {
    this.nIsLastLevel = nIsLastLevel;
  }

  public int getnIsActive()
  {
    return this.nIsActive;
  }

  public void setnIsActive(int nIsActive)
  {
    this.nIsActive = nIsActive;
  }

  public int getnPnum()
  {
    return this.nPnum;
  }

  public void setnPnum(int nPnum)
  {
    this.nPnum = nPnum;
  }

  public int getnPtype()
  {
    return this.nPtype;
  }

  public void setnPtype(int nPtype)
  {
    this.nPtype = nPtype;
  }

  public void setnCurrentLevel(int nCurrentLevel)
  {
    this.nCurrentLevel = nCurrentLevel;
  }

  public Project getObjParentProject()
  {
    return this.objParentProject;
  }

  public void setObjParentProject(Project objParentProject)
  {
    this.objParentProject = objParentProject;
  }

  public Set<Project> getLstChildrenProject()
  {
    return this.lstChildrenProject;
  }

  public void setLstChildrenProject(Set<Project> lstChildrenProject)
  {
    this.lstChildrenProject = lstChildrenProject;
  }

  public Set<CustomerType> getLstCustomerType()
  {
    return this.lstCustomerType;
  }

  public void setLstCustomerType(Set<CustomerType> lstCustomerType)
  {
    this.lstCustomerType = lstCustomerType;
  }

  public Set<Customer> getLstCustomer()
  {
    return this.lstCustomer;
  }

  public void setLstCustomer(Set<Customer> lstCustomer)
  {
    this.lstCustomer = lstCustomer;
  }

  public Set<Email> getLstEmail()
  {
    return this.lstEmail;
  }

  public void setLstEmail(Set<Email> lstEmail)
  {
    this.lstEmail = lstEmail;
  }

  public String getStrEmailListPID()
  {
    return this.strEmailListPID;
  }

  public void setStrEmailListPID(String strEmailListPID)
  {
    this.strEmailListPID = strEmailListPID;
  }

  public String getStrEmailContent()
  {
    return this.strEmailContent;
  }

  public void setStrEmailContent(String strEmailContent)
  {
    this.strEmailContent = strEmailContent;
  }

  public String getStrMerId()
  {
    return this.strMerId;
  }

  public void setStrMerId(String strMerId)
  {
    this.strMerId = strMerId;
  }

  public String getStrKeyValue()
  {
    return this.strKeyValue;
  }

  public void setStrKeyValue(String strKeyValue)
  {
    this.strKeyValue = strKeyValue;
  }

  public String getStrCallBackURL()
  {
    return this.strCallBackURL;
  }

  public void setStrCallBackURL(String strCallBackURL)
  {
    this.strCallBackURL = strCallBackURL;
  }

  public int getnEmailListStatus()
  {
    return this.nEmailListStatus;
  }

  public void setnEmailListStatus(int nEmailListStatus)
  {
    this.nEmailListStatus = nEmailListStatus;
  }

  public int getNEmailListStatus()
  {
    return this.nEmailListStatus;
  }

  public void setNEmailListStatus(int nEmailListStatus)
  {
    this.nEmailListStatus = nEmailListStatus;
  }

  public int getnIsUseResultPage()
  {
    return this.nIsUseResultPage;
  }

  public void setnIsUseResultPage(int nIsUseResultPage)
  {
    this.nIsUseResultPage = nIsUseResultPage;
  }

  public int getNIsUseResultPage()
  {
    return this.nIsUseResultPage;
  }

  public void setNIsUseResultPage(int nIsUseResultPage)
  {
    this.nIsUseResultPage = nIsUseResultPage;
  }

  public String getAdminName()
  {
    String strAdminName = "";

    if ((this.lstUsers != null) || (this.lstUsers.size() > 0))
    {
      List lstUsers = new ArrayList(this.lstUsers);

      if (this.lstUsers.size() == 1)
      {
        strAdminName = ((Users)lstUsers.get(0)).getStrName();
      }
      else if (this.lstUsers.size() == 2)
      {
        for (int i = 0; i < lstUsers.size(); i++)
        {
          Users objUsers;
          if ((objUsers = (Users)lstUsers.get(i)).getnCurrentLevel().intValue() == 2)
          {
            strAdminName = objUsers.getStrName();
          }
        }
      }
    }

    return strAdminName;
  }

  public CustomerType getObjCustomerType() {
    return this.objCustomerType;
  }

  public void setObjCustomerType(CustomerType objCustomerType) {
    this.objCustomerType = objCustomerType;
  }

  public Long getlCustomerTypeID()
  {
    if ((this.objCustomerType != null) && (this.objCustomerType.getlId().longValue() > 0L))
    {
      this.lCustomerTypeID = this.objCustomerType.getlId();
    }
    else
    {
      this.lCustomerTypeID = Long.valueOf(-1L);
    }

    return this.lCustomerTypeID;
  }

  public Long getLCustomerTypeID()
  {
    if ((this.objCustomerType != null) && (this.objCustomerType.getlId().longValue() > 0L))
    {
      this.lCustomerTypeID = this.objCustomerType.getlId();
    }
    else
    {
      this.lCustomerTypeID = Long.valueOf(-1L);
    }

    return this.lCustomerTypeID;
  }

  public String getStrCustomerTypeName() {
    return (this.objCustomerType != null) && (this.objCustomerType.getlId().longValue() > 0L) ? this.objCustomerType.getStrName() : "";
  }

	public Set<ProjectMenu> getLstProjectMenu() {
		return lstProjectMenu;
	}
	
	
	public void setLstProjectMenu(Set<ProjectMenu> lstProjectMenu) {
		this.lstProjectMenu = lstProjectMenu;
	}
	
	
	public Set<News> getLstNews() {
		return lstNews;
	}
	
	
	public void setLstNews(Set<News> lstNews) {
		this.lstNews = lstNews;
	}


	public String getProjectKey() {
		return projectKey;
	}


	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	
	

}