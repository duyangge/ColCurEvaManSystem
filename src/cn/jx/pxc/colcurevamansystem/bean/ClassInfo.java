package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;

/**
 *<p> Title:  ClassInfo.java</p>
 *<p> Description:  班级实体类</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:39:42
 * @version 版本号
 */
@SuppressWarnings("all")
public class ClassInfo {
    private Integer classId;

    private Integer professionId;

    private String className;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;

    private String classInfo;
   
    
	private ProfessionInfo professionInfo;//班级和学院一对一
	

	public ProfessionInfo getProfessionInfo() {
		return professionInfo;
	}

	public void setProfessionInfo(ProfessionInfo professionInfo) {
		this.professionInfo = professionInfo;
	}

	public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
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

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo == null ? null : classInfo.trim();
    }

	@Override
	public String toString() {
		return "ClassInfo [classId=" + classId + ", professionId=" + professionId + ", className=" + className
				+ ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + ", classInfo=" + classInfo + ", professionInfo=" + professionInfo
				+ "]";
	}
}