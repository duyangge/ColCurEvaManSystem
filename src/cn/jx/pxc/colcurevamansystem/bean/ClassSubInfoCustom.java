/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  ClassSubInfoCustom.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月30日下午2:22:37
 * @version 版本号
 */
@SuppressWarnings("all")
public class ClassSubInfoCustom extends ClassSubInfo {
	public Integer id;//唯一标识
	public String className;
	public String lessionName;
	public String username;//授课教师
	public Double avgScore;//课程平均分
	public Integer evaNum;//评价人数
	
	public String startTime;
	
	public String endTime;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLessionName() {
		return lessionName;
	}
	public void setLessionName(String lessionName) {
		this.lessionName = lessionName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}
	public Integer getEvaNum() {
		return evaNum;
	}
	public void setEvaNum(Integer evaNum) {
		this.evaNum = evaNum;
	}
	@Override
	public String toString() {
		return "ClassSubInfoCustom [id=" + id + ", className=" + className + ", lessionName=" + lessionName
				+ ", username=" + username + ", avgScore=" + avgScore + ", evaNum=" + evaNum + "]";
	}
}
