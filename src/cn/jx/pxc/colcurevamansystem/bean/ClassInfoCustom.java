/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.bean;

/**
 *<p> Title:  ClassInfoCustom.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.bean
 * @author    23801
 * @date      2020年4月10日下午8:18:38
 * @version 版本号
 */
@SuppressWarnings("all")
public class ClassInfoCustom extends ClassInfo {
	
	private String professionName;
	
	private Integer studentNum;
	

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	
	
}
