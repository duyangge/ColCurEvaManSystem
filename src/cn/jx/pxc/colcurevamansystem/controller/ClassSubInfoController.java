/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;

/**
 *<p> Title:  ClassSubInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月10日下午9:39:14
 * @version 版本号
 */
@Controller
@RequestMapping("/classSub")
@SuppressWarnings("all")
public class ClassSubInfoController {
	
	@Resource
	public ClassSubInfoService classSubInfoService;
	
	@Resource
	public StudentInfoService studentInfoService;
	
	/**分页+分类+模糊查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findClassSub.do")
	public String findClassSub(Model model,BeanQueryVo beanQueryVo) {
		//classSubInfoService
		List<LessionEvaTemp>  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		model.addAttribute("lesEvaList", lesEvaList);
		return "admin_lession_eva";
	}
	
	
	/**学生查看分页+分类+模糊查询:自查
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findStudentClassSub.do")
	public String findStudentClassSub(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		//classSubInfoService
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		beanQueryVo.setStudentId(stu.getStudentId());
		List<LessionEvaTemp>  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		model.addAttribute("lesEvaList", lesEvaList);
		return "stu_lession_eva";
	}
	
	
	
	
}
