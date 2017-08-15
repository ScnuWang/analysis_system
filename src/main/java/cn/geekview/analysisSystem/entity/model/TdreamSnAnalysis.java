package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamSnAnalysis {
    private Integer pkId;

    private Integer gvIndex;

    private Integer productId;

    private Date updateDate;

    private Integer supportPerson;

    private Integer focusCount;

    private Integer currencyId;

    private BigDecimal targetMoney;

    private BigDecimal currMoney;

    private BigDecimal averageMoney;

    private BigDecimal itemCorePrice;

    private Integer itemCoreTotal;

    private Integer itemCoreSupport;

    private Integer flowValue;

    private Integer moneyValue;

    private Integer itemCoreValue;

    private Integer finishPer;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getGvIndex() {
        return gvIndex;
    }

    public void setGvIndex(Integer gvIndex) {
        this.gvIndex = gvIndex;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getSupportPerson() {
        return supportPerson;
    }

    public void setSupportPerson(Integer supportPerson) {
        this.supportPerson = supportPerson;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
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

    public BigDecimal getAverageMoney() {
        return averageMoney;
    }

    public void setAverageMoney(BigDecimal averageMoney) {
        this.averageMoney = averageMoney;
    }

    public BigDecimal getItemCorePrice() {
        return itemCorePrice;
    }

    public void setItemCorePrice(BigDecimal itemCorePrice) {
        this.itemCorePrice = itemCorePrice;
    }

    public Integer getItemCoreTotal() {
        return itemCoreTotal;
    }

    public void setItemCoreTotal(Integer itemCoreTotal) {
        this.itemCoreTotal = itemCoreTotal;
    }

    public Integer getItemCoreSupport() {
        return itemCoreSupport;
    }

    public void setItemCoreSupport(Integer itemCoreSupport) {
        this.itemCoreSupport = itemCoreSupport;
    }

    public Integer getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Integer flowValue) {
        this.flowValue = flowValue;
    }

    public Integer getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(Integer moneyValue) {
        this.moneyValue = moneyValue;
    }

    public Integer getItemCoreValue() {
        return itemCoreValue;
    }

    public void setItemCoreValue(Integer itemCoreValue) {
        this.itemCoreValue = itemCoreValue;
    }

    public Integer getFinishPer() {
        return finishPer;
    }

    public void setFinishPer(Integer finishPer) {
        this.finishPer = finishPer;
    }
}