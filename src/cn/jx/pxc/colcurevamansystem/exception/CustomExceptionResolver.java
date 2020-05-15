/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *<p> Title:  CustomExceptionResolver.java</p>
 *<p> Description:  全局异常处理器</p>
 * @package   cn.jx.pxc.exception
 * @author    黄信胜
 * @date      2018年12月21日下午6:17:46
 * @version 1.0
 */
@SuppressWarnings("all")
public class CustomExceptionResolver implements HandlerExceptionResolver {

	
	/*
	 * handler即使处理器适配器要执行Handler对象（只有method方法）
	 * 解析出异常类型
	 * 如果异常类型是系统自定义的异常，直接去除异常信息，在错误页面显示
	 * 如果该异常类型不是系统自定义的异常，构造一个自定义的异常类型（信息为“未知错误")
	 * */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
	
	/*	String message = null;
		if (ex instanceof CustomException) {
			message = (String)((CustomException)ex).getMessage();
		}else {
			message="未知错误";
		} */
		
		//替换上边代码
		CustomException customException = null;
		
		if(ex instanceof CustomException) 
			customException = (CustomException)ex;
		else
			customException = new CustomException("未知错误");
		
		//错误信息
		String message = customException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);//在页面上使用jstl标签获取
		modelAndView.setViewName("error");//返回的错误页面
		return modelAndView;
	}

	

}
