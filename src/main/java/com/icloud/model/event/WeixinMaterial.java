package com.icloud.model.event;

import java.util.Date;

import com.icloud.model.BaseModel;

public class WeixinMaterial extends BaseModel{
	
    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String title;		// 标题
	private String picUrl;		// 图片地址
	private Long parentId;		// 父类id，跟为0
	private String vistUrl;		// 访问URL
	private String description; 	//图文描述
	private String wxPicUrl; 	//新增的图片素材的图片URL
	private String mediaId; 	//新增的永久素材的media_id
	private String defaults; 	//新增的永久素材的media_id

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getWxPicUrl() {
        return wxPicUrl;
    }

    public void setWxPicUrl(String wxPicUrl) {
        this.wxPicUrl = wxPicUrl == null ? null : wxPicUrl.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getVistUrl() {
		return vistUrl;
	}

	public void setVistUrl(String vistUrl) {
		this.vistUrl = vistUrl;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	
}