package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  SubFunInfo.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月22日下午5:34:10
 * @version 版本号
 */
@SuppressWarnings("all")
public class SubFunInfo {
    private Integer funId;

    private Integer funParentId;

    private String funName;

    private String funUrl;

    private String funStatus;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    

	private ParentFunInfo parentFunInfo;
	

    public ParentFunInfo getParentFunInfo() {
		return parentFunInfo;
	}

	public void setParentFunInfo(ParentFunInfo parentFunInfo) {
		this.parentFunInfo = parentFunInfo;
	}

	public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public Integer getFunParentId() {
        return funParentId;
    }

    public void setFunParentId(Integer funParentId) {
        this.funParentId = funParentId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    public String getFunUrl() {
        return funUrl;
    }

    public void setFunUrl(String funUrl) {
        this.funUrl = funUrl == null ? null : funUrl.trim();
    }

    public String getFunStatus() {
        return funStatus;
    }

    public void setFunStatus(String funStatus) {
        this.funStatus = funStatus == null ? null : funStatus.trim();
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser == null ? null : createdUser.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser == null ? null : modifiedUser.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}