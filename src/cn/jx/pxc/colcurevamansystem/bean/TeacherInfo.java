package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;
/**
 *<p> Title:  TeacherInfo.java</p>
 *<p> Description:  教师实体</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:37:23
 * @version 版本号
 */
@SuppressWarnings("all")
public class TeacherInfo {
    private Integer teacherId;

    private Integer professionId;

    private Integer roleId;

    private String account;

    private String username;

    private String password;

    private String telphone;

    private String headImage;

    private String mail;

    private String status;

    private Date createdTime;

    private String createdUser;

    private Date modifiedTime;

    private String modifiedUser;

	private ProfessionInfo professionInfo;//老师和学院一对一
     
	private RoleInfo roleInfo;//教师和角色 一对一
	
    
    public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public ProfessionInfo getProfessionInfo() {
		return professionInfo;
	}

	public void setProfessionInfo(ProfessionInfo professionInfo) {
		this.professionInfo = professionInfo;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser == null ? null : createdUser.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser == null ? null : modifiedUser.trim();
    }

	@Override
	public String toString() {
		return "TeacherInfo [teacherId=" + teacherId + ", professionId=" + professionId + ", roleId=" + roleId
				+ ", account=" + account + ", username=" + username + ", password=" + password + ", telphone="
				+ telphone + ", headImage=" + headImage + ", mail=" + mail + ", status=" + status + ", createdTime="
				+ createdTime + ", createdUser=" + createdUser + ", modifiedTime=" + modifiedTime + ", modifiedUser="
				+ modifiedUser + ", professionInfo=" + professionInfo + ", roleInfo=" + roleInfo + "]";
	}
    
}