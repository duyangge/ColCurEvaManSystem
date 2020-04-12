package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  StudentInfo.java</p>
 *<p> Description:  学生实体</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月3日下午8:33:09
 * @version 版本号
 */
@SuppressWarnings("all")
public class StudentInfo {
    private Integer studentId;

    private Integer roleId;
    
    private Integer classId;
    
    private Integer professionId;

    private String account;

    private String password;

    private String username;

    private String telphone;

    private String headImage;

    private String mail;

    private String status;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
    

	private ClassInfo classInfo;//学生和班级一对一
	
	private RoleInfo roleInfo;//学生和角色一对一
	
	private ProfessionInfo professionInfo;//学生和学院 一对一
	
	
    public ProfessionInfo getProfessionInfo() {
		return professionInfo;
	}

	public void setProfessionInfo(ProfessionInfo professionInfo) {
		this.professionInfo = professionInfo;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
		return "StudentInfo [studentId=" + studentId + ", roleId=" + roleId + ", classId=" + classId + ", professionId="
				+ professionId + ", account=" + account + ", password=" + password + ", username=" + username
				+ ", telphone=" + telphone + ", headImage=" + headImage + ", mail=" + mail + ", status=" + status
				+ ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + ", classInfo=" + classInfo + ", roleInfo=" + roleInfo
				+ ", professionInfo=" + professionInfo + "]";
	}
    
    
}