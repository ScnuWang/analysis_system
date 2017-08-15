package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamCategory {
    private Integer pkId;

    private Integer parentId;

    private String categoryName;

    private Integer categoryStatus;

    private String updateUser;

    private Date updateDate;

    private String categoryDesc;

    private String cover;

    private String cateDescOuter;

    private BigDecimal recentMoney;

    private Integer recentMoneyRank;

    private BigDecimal totalMoney;

    private Integer totalMoneyRank;

    private Integer totalProduct;

    private Integer totalProductRank;

    private Integer totalSupport;

    private Integer totalSupportRank;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getCateDescOuter() {
        return cateDescOuter;
    }

    public void setCateDescOuter(String cateDescOuter) {
        this.cateDescOuter = cateDescOuter == null ? null : cateDescOuter.trim();
    }

    public BigDecimal getRecentMoney() {
        return recentMoney;
    }

    public void setRecentMoney(BigDecimal recentMoney) {
        this.recentMoney = recentMoney;
    }

    public Integer getRecentMoneyRank() {
        return recentMoneyRank;
    }

    public void setRecentMoneyRank(Integer recentMoneyRank) {
        this.recentMoneyRank = recentMoneyRank;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalMoneyRank() {
        return totalMoneyRank;
    }

    public void setTotalMoneyRank(Integer totalMoneyRank) {
        this.totalMoneyRank = totalMoneyRank;
    }

    public Integer getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Integer totalProduct) {
        this.totalProduct = totalProduct;
    }

    public Integer getTotalProductRank() {
        return totalProductRank;
    }

    public void setTotalProductRank(Integer totalProductRank) {
        this.totalProductRank = totalProductRank;
    }

    public Integer getTotalSupport() {
        return totalSupport;
    }

    public void setTotalSupport(Integer totalSupport) {
        this.totalSupport = totalSupport;
    }

    public Integer getTotalSupportRank() {
        return totalSupportRank;
    }

    public void setTotalSupportRank(Integer totalSupportRank) {
        this.totalSupportRank = totalSupportRank;
    }
}