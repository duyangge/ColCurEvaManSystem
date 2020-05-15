/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.exception;

/**
 *<p> Title:  CustomException.java</p>
 *<p> Description:  自定义异常类错误信息</p>
 * @package   cn.jx.pxc.exception
 * @author    黄信胜
 * @date      2018年12月21日下午6:13:56
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomException extends Exception{
	
	private String message;/*显示在页面的异常信息*/
	
	public CustomException() {
		super();
	}
	public CustomException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
