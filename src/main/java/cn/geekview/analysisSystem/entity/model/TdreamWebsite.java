package cn.geekview.analysisSystem.entity.model;

import java.util.Date;

public class TdreamWebsite {
    private Integer pkId;

    private Integer websiteCode;

    private String name;

    private String nickname;

    private String filePath;

    private Integer websiteStatus;

    private Integer websiteType;

    private String classGrap;

    private String classAnalysee;

    private String classTemp;

    private Integer grapStatus;

    private Integer rankStatus;

    private Integer analyseStatus;

    private Integer listenerStatus;

    private Date uptime;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getWebsiteCode() {
        return websiteCode;
    }

    public void setWebsiteCode(Integer websiteCode) {
        this.websiteCode = websiteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getWebsiteStatus() {
        return websiteStatus;
    }

    public void setWebsiteStatus(Integer websiteStatus) {
        this.websiteStatus = websiteStatus;
    }

    public Integer getWebsiteType() {
        return websiteType;
    }

    public void setWebsiteType(Integer websiteType) {
        this.websiteType = websiteType;
    }

    public String getClassGrap() {
        return classGrap;
    }

    public void setClassGrap(String classGrap) {
        this.classGrap = classGrap == null ? null : classGrap.trim();
    }

    public String getClassAnalysee() {
        return classAnalysee;
    }

    public void setClassAnalysee(String classAnalysee) {
        this.classAnalysee = classAnalysee == null ? null : classAnalysee.trim();
    }

    public String getClassTemp() {
        return classTemp;
    }

    public void setClassTemp(String classTemp) {
        this.classTemp = classTemp == null ? null : classTemp.trim();
    }

    public Integer getGrapStatus() {
        return grapStatus;
    }

    public void setGrapStatus(Integer grapStatus) {
        this.grapStatus = grapStatus;
    }

    public Integer getRankStatus() {
        return rankStatus;
    }

    public void setRankStatus(Integer rankStatus) {
        this.rankStatus = rankStatus;
    }

    public Integer getAnalyseStatus() {
        return analyseStatus;
    }

    public void setAnalyseStatus(Integer analyseStatus) {
        this.analyseStatus = analyseStatus;
    }

    public Integer getListenerStatus() {
        return listenerStatus;
    }

    public void setListenerStatus(Integer listenerStatus) {
        this.listenerStatus = listenerStatus;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}