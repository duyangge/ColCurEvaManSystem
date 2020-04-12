/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

/**
 *<p> Title:  StudentInfoCustom.java</p>
 *<p> Description:  学生扩展类</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月8日下午5:19:42
 * @version 版本号
 */
@SuppressWarnings("all")
public class StudentInfoCustom extends StudentInfo{
	
	private String className;
	
	private String professionName;
	
	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
