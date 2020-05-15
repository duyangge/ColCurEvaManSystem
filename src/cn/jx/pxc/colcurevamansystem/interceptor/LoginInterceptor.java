/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *<p> Title:  LoginInterceptor.java</p>
 *<p> Description:  登录拦截器</p>
 * @package   cn.jx.pxc.colcurevamansystem.interceptor
 * @author    23801
 * @date      2020年5月2日下午3:36:13
 * @version 版本号
 */
public class LoginInterceptor implements HandlerInterceptor {


	/**
	 * 前进controller方法之前，执行preHandle
	 * @return false :表示拦截 ，不向下执行；or ture: 不拦截，直接往下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		String url = request.getRequestURI();//获取访问的url路径
		//访问路径中包含login.do,login.jsp不拦截
		if(url.indexOf("login") >= 0)  return true;
		//sesion中包含用户信息不拦截
		if(request.getSession().getAttribute("student") != null ||request.getSession().getAttribute("admin") != null) {
			return true;
		}else {
			request.getRequestDispatcher("../user/goLogin.do").forward(request, response);
			return false;
		}  
	}
	
	/**
	 * 进入controller方法之后，返回modelAndView之前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	/**
	 * 执行controller方法之后，执行该方法，可以进行统一日志信息处理（异常）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {		
	}
}
