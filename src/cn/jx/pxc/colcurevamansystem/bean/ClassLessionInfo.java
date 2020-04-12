package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;

/**
 *<p> Title:  ClassLessionInfo.java</p>
 *<p> Description:  班级对应的课程表</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:49:31
 * @version 版本号
 */
@SuppressWarnings("all")
public class ClassLessionInfo {
    private Integer classLessionId;

    private Integer classId;

    private Integer lessionId;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    
	private List<LessionInfo> lessionInfoList;//班级和课程一对多
	
    public List<LessionInfo> getLessionInfoList() {
		return lessionInfoList;
	}

	public void setLessionInfoList(List<LessionInfo> lessionInfoList) {
		this.lessionInfoList = lessionInfoList;
	}

    public Integer getClassLessionId() {
        return classLessionId;
    }

    public void setClassLessionId(Integer classLessionId) {
        this.classLessionId = classLessionId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getLessionId() {
        return lessionId;
    }

    public void setLessionId(Integer lessionId) {
        this.lessionId = lessionId;
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
		return "ClassLessionInfo [classLessionId=" + classLessionId + ", classId=" + classId + ", lessionId="
				+ lessionId + ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser="
				+ modifiedUser + ", modifiedTime=" + modifiedTime + ", lessionInfoList=" + lessionInfoList + "]";
	}
    
}