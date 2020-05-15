package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;
/**
 *<p> Title:  ParentFunInfo.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月22日下午5:33:52
 * @version 版本号
 */
@SuppressWarnings("all")
public class ParentFunInfo {
    private Integer funParentId;

    private String funParentName;

    private String funParentUrl;

    private String funParentStatus;

    private String funParentImg;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    

	private List<SubFunInfo> subFunInfoList;//一对多

    public List<SubFunInfo> getSubFunInfoList() {
		return subFunInfoList;
	}

	public void setSubFunInfoList(List<SubFunInfo> subFunInfoList) {
		this.subFunInfoList = subFunInfoList;
	}

	public Integer getFunParentId() {
        return funParentId;
    }

    public void setFunParentId(Integer funParentId) {
        this.funParentId = funParentId;
    }

    public String getFunParentName() {
        return funParentName;
    }

    public void setFunParentName(String funParentName) {
        this.funParentName = funParentName == null ? null : funParentName.trim();
    }

    public String getFunParentUrl() {
        return funParentUrl;
    }

    public void setFunParentUrl(String funParentUrl) {
        this.funParentUrl = funParentUrl == null ? null : funParentUrl.trim();
    }

    public String getFunParentStatus() {
        return funParentStatus;
    }

    public void setFunParentStatus(String funParentStatus) {
        this.funParentStatus = funParentStatus == null ? null : funParentStatus.trim();
    }

    public String getFunParentImg() {
        return funParentImg;
    }

    public void setFunParentImg(String funParentImg) {
        this.funParentImg = funParentImg == null ? null : funParentImg.trim();
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