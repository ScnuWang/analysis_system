package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamTbItem {
    private Integer pkId;

	private Integer projectId;

	private String itemId;

	private String itemDesc;

	private String images;

	private BigDecimal itemPrice;

	private Integer makeDays;

	private Integer itemSupport;

	private Integer isExpress;

	private Integer itemTotal;

	private Integer canBuy;

	private String buyUrl;

	private String itemTitle;

	private Date updateDate;

	public Integer getPkId()
	{
		return pkId;
	}

	public void setPkId(Integer pkId)
	{
		this.pkId = pkId;
	}

	public Integer getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Integer projectId)
	{
		this.projectId = projectId;
	}

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getItemDesc()
	{
		return itemDesc;
	}

	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc == null ? null : itemDesc.trim();
	}

	public String getImages()
	{
		return images;
	}

	public void setImages(String images)
	{
		this.images = images == null ? null : images.trim();
	}

	public BigDecimal getItemPrice()
	{
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice)
	{
		this.itemPrice = itemPrice;
	}

	public Integer getMakeDays()
	{
		return makeDays;
	}

	public void setMakeDays(Integer makeDays)
	{
		this.makeDays = makeDays;
	}

	public Integer getItemSupport()
	{
		return itemSupport;
	}

	public void setItemSupport(Integer itemSupport)
	{
		this.itemSupport = itemSupport;
	}

	public Integer getIsExpress()
	{
		return isExpress;
	}

	public void setIsExpress(Integer isExpress)
	{
		this.isExpress = isExpress;
	}

	public Integer getItemTotal()
	{
		return itemTotal;
	}

	public void setItemTotal(Integer itemTotal)
	{
		this.itemTotal = itemTotal;
	}

	public Integer getCanBuy()
	{
		return canBuy;
	}

	public void setCanBuy(Integer canBuy)
	{
		this.canBuy = canBuy;
	}

	public String getBuyUrl()
	{
		return buyUrl;
	}

	public void setBuyUrl(String buyUrl)
	{
		this.buyUrl = buyUrl == null ? null : buyUrl.trim();
	}

	public String getItemTitle()
	{
		return itemTitle;
	}

	public void setItemTitle(String itemTitle)
	{
		this.itemTitle = itemTitle == null ? null : itemTitle.trim();
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

}