/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

/**
 *<p> Title:  BeanInfoQueryVo</p>
 *<p> Description:  用户综合查询类</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月3日下午8:39:42
 * @version 版本号
 */
@SuppressWarnings("all")
public class BeanQueryVo {
	
	private int [] ids;//id数组
	
	private Integer lessionId;//课程id
	
	private Integer classId;//班级id
	
	private Integer teacherId;//教师id
	
	private Integer studentId;//学生ID
	
	private Integer professionId;//学院ID
	
	private Integer roleId;//角色id
	
	private String keyWords;//关键字
	
	private String account;//账号
	
	private String password;//密码
	
	private String status;//状态
	
	private StudentInfo studentInfo;
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public Integer getProfessionId() {
		return professionId;
	}
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLessionId() {
		return lessionId;
	}
	public void setLessionId(Integer lessionId) {
		this.lessionId = lessionId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	

}