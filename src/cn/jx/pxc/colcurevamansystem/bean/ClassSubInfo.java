package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
import java.util.List;

/**
 *<p> Title:  ClassSubInfo.java</p>
 *<p> Description:  提交课程评价</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:51:40
 * @version 版本号
 */
@SuppressWarnings("all")
public class ClassSubInfo {
    private Integer subEvaId;

    private Integer subStudentId;//学生ID
    
    private Integer subLessionId;//评价课程id

    private Integer subScore;

    private String subInfo;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;

    private Integer subStatus;//是否匿名
    
    private List<StudentInfo> studentInfoList;//课程评价和学生 多对多
    
    private List<LessionInfo> lessionInfoList;//课程评价和课程 多对多
    

    public List<StudentInfo> getStudentInfoList() {
		return studentInfoList;
	}

	public void setStudentInfoList(List<StudentInfo> studentInfoList) {
		this.studentInfoList = studentInfoList;
	}

	public List<LessionInfo> getLessionInfoList() {
		return lessionInfoList;
	}

	public void setLessionInfoList(List<LessionInfo> lessionInfoList) {
		this.lessionInfoList = lessionInfoList;
	}

	public Integer getSubEvaId() {
        return subEvaId;
    }

    public void setSubEvaId(Integer subEvaId) {
        this.subEvaId = subEvaId;
    }

    public Integer getSubStudentId() {
        return subStudentId;
    }

    public void setSubStudentId(Integer subStudentId) {
        this.subStudentId = subStudentId;
    }

    public Integer getSubScore() {
        return subScore;
    }

    public void setSubScore(Integer subScore) {
        this.subScore = subScore;
    }

    public String getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(String subInfo) {
        this.subInfo = subInfo == null ? null : subInfo.trim();
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

    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    public Integer getSubLessionId() {
        return subLessionId;
    }

    public void setSubLessionId(Integer subLessionId) {
        this.subLessionId = subLessionId;
    }

	@Override
	public String toString() {
		return "ClassSubInfo [subEvaId=" + subEvaId + ", subStudentId=" + subStudentId + ", subLessionId="
				+ subLessionId + ", subScore=" + subScore + ", subInfo=" + subInfo + ", createdUser=" + createdUser
				+ ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
				+ ", subStatus=" + subStatus + ", studentInfoList=" + studentInfoList + ", lessionInfoList="
				+ lessionInfoList + "]";
	}
    
}