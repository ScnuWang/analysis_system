package cn.geekview.analysisSystem.entity.model;

import java.util.Date;

public class TdreamRankProject {
    private Integer pkId;

    private Integer gvIndex;

    private Integer productId;

    private Date updateDate;

    private Integer rankingNational;

    private Integer rankingWorld;

    private Integer rankingNationalChange;

    private Integer rankingWorldChange;

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

    public Integer getRankingNational() {
        return rankingNational;
    }

    public void setRankingNational(Integer rankingNational) {
        this.rankingNational = rankingNational;
    }

    public Integer getRankingWorld() {
        return rankingWorld;
    }

    public void setRankingWorld(Integer rankingWorld) {
        this.rankingWorld = rankingWorld;
    }

    public Integer getRankingNationalChange() {
        return rankingNationalChange;
    }

    public void setRankingNationalChange(Integer rankingNationalChange) {
        this.rankingNationalChange = rankingNationalChange;
    }

    public Integer getRankingWorldChange() {
        return rankingWorldChange;
    }

    public void setRankingWorldChange(Integer rankingWorldChange) {
        this.rankingWorldChange = rankingWorldChange;
    }
}