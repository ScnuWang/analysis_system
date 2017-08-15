package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamRankPlatform {
    private Integer pkId;

    private Integer website;

    private BigDecimal targetMoney;

    private BigDecimal currMoney;

    private Integer finishPer;

    private Integer supportPerson;

    private Integer projectCount;

    private BigDecimal recentMoney;

    private Integer recentSupport;

    private Date updateDate;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getWebsite() {
        return website;
    }

    public void setWebsite(Integer website) {
        this.website = website;
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

    public Integer getSupportPerson() {
        return supportPerson;
    }

    public void setSupportPerson(Integer supportPerson) {
        this.supportPerson = supportPerson;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public BigDecimal getRecentMoney() {
        return recentMoney;
    }

    public void setRecentMoney(BigDecimal recentMoney) {
        this.recentMoney = recentMoney;
    }

    public Integer getRecentSupport() {
        return recentSupport;
    }

    public void setRecentSupport(Integer recentSupport) {
        this.recentSupport = recentSupport;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}