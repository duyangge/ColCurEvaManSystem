/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

/**
 * <p>
 * Title: LessionInfoTemp.java
 * </p>
 * <p>
 * Description: 描述
 * </p>
 * 
 * @package cn.jx.pxc.colcurevamansystem.bean
 * @author 23801
 * @date 2020年4月12日下午3:52:47
 * @version 版本号
 */
@SuppressWarnings("all")
public class LessionInfoTemp {
	
	private Integer classId;
	
	private Integer lessionId;
	
	private Integer teacherId;
	
	private Integer studentId;
	
	private Integer professionId;
	
	private String lessionName;
	
	private String teacherName;
	
	private String className;
	
	private String professionName;
	
	private String studentName;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
		this.className = className;
	}
	public String getLessionName() {
		return lessionName;
	}
	public void setLessionName(String lessionName) {
		this.lessionName = lessionName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	

}
