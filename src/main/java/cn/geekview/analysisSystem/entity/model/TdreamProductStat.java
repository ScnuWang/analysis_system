package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamProductStat {
    private Integer pkId;

    private Integer categoryId;

    private Date updateDate;

    private BigDecimal moneyMax;

    private BigDecimal moneyMin;

    private BigDecimal moneyAvg;

    private BigDecimal moneyMod;

    private Integer supportMax;

    private Integer supportMin;

    private Integer supportAvg;

    private Integer supportMod;

    private Integer finishMax;

    private Integer finishMin;

    private Integer finishAvg;

    private Integer finishMod;

    private BigDecimal capitaMax;

    private BigDecimal capitaMin;

    private BigDecimal capitaAvg;

    private BigDecimal capitaMod;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getMoneyMax() {
        return moneyMax;
    }

    public void setMoneyMax(BigDecimal moneyMax) {
        this.moneyMax = moneyMax;
    }

    public BigDecimal getMoneyMin() {
        return moneyMin;
    }

    public void setMoneyMin(BigDecimal moneyMin) {
        this.moneyMin = moneyMin;
    }

    public BigDecimal getMoneyAvg() {
        return moneyAvg;
    }

    public void setMoneyAvg(BigDecimal moneyAvg) {
        this.moneyAvg = moneyAvg;
    }

    public BigDecimal getMoneyMod() {
        return moneyMod;
    }

    public void setMoneyMod(BigDecimal moneyMod) {
        this.moneyMod = moneyMod;
    }

    public Integer getSupportMax() {
        return supportMax;
    }

    public void setSupportMax(Integer supportMax) {
        this.supportMax = supportMax;
    }

    public Integer getSupportMin() {
        return supportMin;
    }

    public void setSupportMin(Integer supportMin) {
        this.supportMin = supportMin;
    }

    public Integer getSupportAvg() {
        return supportAvg;
    }

    public void setSupportAvg(Integer supportAvg) {
        this.supportAvg = supportAvg;
    }

    public Integer getSupportMod() {
        return supportMod;
    }

    public void setSupportMod(Integer supportMod) {
        this.supportMod = supportMod;
    }

    public Integer getFinishMax() {
        return finishMax;
    }

    public void setFinishMax(Integer finishMax) {
        this.finishMax = finishMax;
    }

    public Integer getFinishMin() {
        return finishMin;
    }

    public void setFinishMin(Integer finishMin) {
        this.finishMin = finishMin;
    }

    public Integer getFinishAvg() {
        return finishAvg;
    }

    public void setFinishAvg(Integer finishAvg) {
        this.finishAvg = finishAvg;
    }

    public Integer getFinishMod() {
        return finishMod;
    }

    public void setFinishMod(Integer finishMod) {
        this.finishMod = finishMod;
    }

    public BigDecimal getCapitaMax() {
        return capitaMax;
    }

    public void setCapitaMax(BigDecimal capitaMax) {
        this.capitaMax = capitaMax;
    }

    public BigDecimal getCapitaMin() {
        return capitaMin;
    }

    public void setCapitaMin(BigDecimal capitaMin) {
        this.capitaMin = capitaMin;
    }

    public BigDecimal getCapitaAvg() {
        return capitaAvg;
    }

    public void setCapitaAvg(BigDecimal capitaAvg) {
        this.capitaAvg = capitaAvg;
    }

    public BigDecimal getCapitaMod() {
        return capitaMod;
    }

    public void setCapitaMod(BigDecimal capitaMod) {
        this.capitaMod = capitaMod;
    }
    
    //勿删
    private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
}