package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;
/**
 *<p> Title:  LessionInfo.java</p>
 *<p> Description:  课程实体</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:41:58
 * @version 版本号
 */
@SuppressWarnings("all")
public class LessionInfo {
    private Integer lessionId;

    private String lessionName;
    
    private String lessionStatus;

    private String lessionImg;

    private String lessionInfo;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    
    private List<ClassInfo> classInfoList;//课程和班级多对多
    

	public String getLessionStatus() {
		return lessionStatus;
	}

	public void setLessionStatus(String lessionStatus) {
		this.lessionStatus = lessionStatus;
	}

	public List<ClassInfo> getClassInfoList() {
		return classInfoList;
	}

	public void setClassInfoList(List<ClassInfo> classInfoList) {
		this.classInfoList = classInfoList;
	}

	public Integer getLessionId() {
        return lessionId;
    }

    public void setLessionId(Integer lessionId) {
        this.lessionId = lessionId;
    }

    public String getLessionName() {
        return lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName == null ? null : lessionName.trim();
    }

    public String getLessionImg() {
        return lessionImg;
    }

    public void setLessionImg(String lessionImg) {
        this.lessionImg = lessionImg == null ? null : lessionImg.trim();
    }

    public String getLessionInfo() {
        return lessionInfo;
    }

    public void setLessionInfo(String lessionInfo) {
        this.lessionInfo = lessionInfo == null ? null : lessionInfo.trim();
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

	@Override
	public String toString() {
		return "LessionInfo [lessionId=" + lessionId + ", lessionName=" + lessionName + ", lessionImg=" + lessionImg
				+ ", lessionInfo=" + lessionInfo + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + ", classInfoList="
				+ classInfoList + "]";
	}
    
}