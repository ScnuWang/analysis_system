package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TdreamXmProject {
    private Integer pkId;

    private Date updateDate;

    private String originalId;

    private String projectName;

    private String projectDesc;

    private String projectUrl;

    private String projectImage;

    private String projectVideo;

    private String projectQrcode;

    private Date beginDate;

    private Date endDate;

    private String projectStatus;

    private Integer statusValue;

    private Integer focusCount;

    private Integer supportPerson;

    private String moneyCurrency;

    private BigDecimal targetMoney;

    private BigDecimal currMoney;

    private Integer finishPer;

    private Integer remainDay;

    private String personName;

    private String personImage;

    private String personDesc;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId == null ? null : originalId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl == null ? null : projectUrl.trim();
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage == null ? null : projectImage.trim();
    }

    public String getProjectVideo() {
        return projectVideo;
    }

    public void setProjectVideo(String projectVideo) {
        this.projectVideo = projectVideo == null ? null : projectVideo.trim();
    }

    public String getProjectQrcode() {
        return projectQrcode;
    }

    public void setProjectQrcode(String projectQrcode) {
        this.projectQrcode = projectQrcode == null ? null : projectQrcode.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getSupportPerson() {
        return supportPerson;
    }

    public void setSupportPerson(Integer supportPerson) {
        this.supportPerson = supportPerson;
    }

    public String getMoneyCurrency() {
        return moneyCurrency;
    }

    public void setMoneyCurrency(String moneyCurrency) {
        this.moneyCurrency = moneyCurrency == null ? null : moneyCurrency.trim();
    }

    public BigDecimal getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(BigDecimal targetMoney) {
        this.targetMoney = targetMoney;
    }

    public BigDecimal getCurrMoney() {
        return currMoney;
    }

    public void setCurrMoney(BigDecimal currMoney) {
        this.currMoney = currMoney;
    }

    public Integer getFinishPer() {
        return finishPer;
    }

    public void setFinishPer(Integer finishPer) {
        this.finishPer = finishPer;
    }

    public Integer getRemainDay() {
        return remainDay;
    }

    public void setRemainDay(Integer remainDay) {
        this.remainDay = remainDay;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonImage() {
        return personImage;
    }

    public void setPersonImage(String personImage) {
        this.personImage = personImage == null ? null : personImage.trim();
    }

    public String getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc == null ? null : personDesc.trim();
    }
    
    //勿删
  	private Integer productEnabled;
  	private String categoryName;
  	
    public Integer getProductEnabled() {
  		return productEnabled;
  	}

  	public void setProductEnabled(Integer productEnabled) {
  		this.productEnabled = productEnabled;
  	}
  	
    public String getCategoryName() {
  		return categoryName;
  	}

  	public void setCategoryName(String categoryName) {
  		this.categoryName = categoryName;
  	}
  	
	private List<TdreamXmItem> itemList;

	public List<TdreamXmItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TdreamXmItem> itemList) {
		this.itemList = itemList;
	}
}