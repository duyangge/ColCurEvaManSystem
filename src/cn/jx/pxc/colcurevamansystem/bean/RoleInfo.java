package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  RoleInfo.java</p>
 *<p> Description:  角色实体</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月4日上午10:57:30
 * @version 版本号
 */

public class RoleInfo {
	
    private Integer roleId;

    private String roleName;

    private String roleInfo;

    private String roleStatus;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo == null ? null : roleInfo.trim();
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus == null ? null : roleStatus.trim();
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
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName + ", roleInfo=" + roleInfo + ", roleStatus="
				+ roleStatus + ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser="
				+ modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
    
}