package cn.geekview.analysisSystem.entity.model;

import java.util.Date;
/**
 * 日志类
 * @author Jason
 *
 */
public class TdreamLog {
    private Integer pkId;

    private String title;

    private String content;
    
    private Date updateDate;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}