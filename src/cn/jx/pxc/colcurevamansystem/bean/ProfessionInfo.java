package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  ProfessionInfo.java</p>
 *<p> Description:  学院实体</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:57:04
 * @version 版本号
 */
public class ProfessionInfo {
    private Integer professionId;

    private String professionName;
    
    private String professionInfo;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;

   

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName == null ? null : professionName.trim();
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

    public String getProfessionInfo() {
        return professionInfo;
    }

    public void setProfessionInfo(String professionInfo) {
        this.professionInfo = professionInfo == null ? null : professionInfo.trim();
    }

	@Override
	public String toString() {
		return "ProfessionInfo [professionId=" + professionId + ", professionName=" + professionName
				+ ", professionInfo=" + professionInfo + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
    
}