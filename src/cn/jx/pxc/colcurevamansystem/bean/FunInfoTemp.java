/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

import java.util.Date;

/**
 *<p> Title:  FunTempInfo.java</p>
 *<p> Description:  菜单信息临时类</p>
 * @package   cn.jx.pxc.otsystem.bean
 * @author    黄信胜
 * @date      2019年6月3日上午11:53:27
 * @version 版本号
 */
@SuppressWarnings("all")
public class FunInfoTemp{
	private Integer funId;//唯一主键id
	
	private String funParentName;

    private Integer funParentId;

    private String funName;
    
    private String funImg;

    private String funUrl;

    private String funStatus; 
    
    private String roleName;
    
    private Integer roleId;

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;
	
	private String tableName;//标记是那一张表；
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getFunImg() {
		return funImg;
	}

	public void setFunImg(String funImg) {
		this.funImg = funImg;
	}

	public String getFunParentName() {
		return funParentName;
	}

	public void setFunParentName(String funParentName) {
		this.funParentName = funParentName;
	}


	public Integer getFunId() {
		return funId;
	}

	public void setFunId(Integer funId) {
		this.funId = funId;
	}

	public Integer getFunParentId() {
		return funParentId;
	}

	public void setFunParentId(Integer funParentId) {
		this.funParentId = funParentId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public String getFunStatus() {
		return funStatus;
	}

	public void setFunStatus(String funStatus) {
		this.funStatus = funStatus;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
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
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


}
