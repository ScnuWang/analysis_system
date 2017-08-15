package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamJdItem {
    private Integer pkId;

    private Integer projectId;

    private String itemDesc;

    private BigDecimal itemPrice;

    private Integer itemSupport;

    private Integer itemTotal;

    private Date updateDate;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemSupport() {
        return itemSupport;
    }

    public void setItemSupport(Integer itemSupport) {
        this.itemSupport = itemSupport;
    }

    public Integer getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}