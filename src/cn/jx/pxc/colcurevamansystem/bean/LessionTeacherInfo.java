package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;

/**
 *<p> Title:  LessionTeacherInfo.java</p>
 *<p> Description:  教师所教课程</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午11:14:50
 * @version 版本号
 */
@SuppressWarnings("all")
public class LessionTeacherInfo {
    private Integer lessionTeacherId;

    private Integer teacherId;

    private Integer classId;

    private Integer lessionId;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    
    private TeacherInfo teacherInfo;//
    
	private List<ClassInfo> classInfoList;//一个老师和班级 一对多
	
	private List<LessionInfo> lessionInfoList;//老师和课程 一对多
	



	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public List<ClassInfo> getClassInfoList() {
		return classInfoList;
	}

	public void setClassInfoList(List<ClassInfo> classInfoList) {
		this.classInfoList = classInfoList;
	}

	public List<LessionInfo> getLessionInfoList() {
		return lessionInfoList;
	}

	public void setLessionInfoList(List<LessionInfo> lessionInfoList) {
		this.lessionInfoList = lessionInfoList;
	}

	public Integer getLessionTeacherId() {
        return lessionTeacherId;
    }

    public void setLessionTeacherId(Integer lessionTeacherId) {
        this.lessionTeacherId = lessionTeacherId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
		return "LessionTeacherInfo [lessionTeacherId=" + lessionTeacherId + ", teacherId=" + teacherId + ", classId="
				+ classId + ", lessionId=" + lessionId + ", createdUser=" + createdUser + ", createdTime=" + createdTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + ", teacherInfo=" + teacherInfo
				+ ", classInfoList=" + classInfoList + ", lessionInfoList=" + lessionInfoList + "]";
	}
    
}