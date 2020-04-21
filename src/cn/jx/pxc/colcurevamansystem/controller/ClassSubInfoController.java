/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;

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
	
	@Resource
	public LessionInfoService lessionInfoService;
	
	@Resource
	public TeacherInfoService teacherInfoService;
	
	
	/**教师查看自己教授课程的学生评价列表
	 * @param model
	 * @param session
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findStudentClassSubByTeacher.do")
	public String findStudentClassSubByTeacher(Model model,HttpSession session,BeanQueryVo beanQueryVo) {
		TeacherInfo teacherInfo  = (TeacherInfo) session.getAttribute("admin");
		TeacherInfo tea = teacherInfoService.selectByPrimaryKey(teacherInfo.getTeacherId());
		List<LessionEvaTemp> lesEvaList = null;
		beanQueryVo.setTeacherId(tea.getTeacherId());
		try {
			lesEvaList = classSubInfoService.selectByteacher(beanQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    model.addAttribute("lesEvaList", lesEvaList);
		return "tea_lession_eva";
	}
	
	
	
	/**管理员查看详细评价内容
	 * @param model
	 * @param beanQueryVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/goAdminSeeLessionEva.do")
	public String goAdminSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
	try {
			LessionEvaTemp lesTemp =classSubInfoService.selectClassSubById(beanQueryVo);
			model.addAttribute("lesTemp", lesTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin_lession_eva_see";
	}
	
	/**教师查看详细评价内容
	 * @param model
	 * @param beanQueryVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/goTeacherSeeLessionEva.do")
	public String goTeacherSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		try {
			LessionEvaTemp lesTemp =classSubInfoService.selectClassSubById(beanQueryVo);
			model.addAttribute("lesTemp", lesTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tea_lession_eva_see";
	}
	
	
	
	
	/**管理员：分页+分类+模糊查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findClassSub.do")
	public String findClassSub(Model model,BeanQueryVo beanQueryVo,@RequestParam(value="evaId",required=false)Integer id) {
		List<LessionEvaTemp>  lesEvaList = null;
		if(id != null) {//修改评价是否可见(默认都可见)
			lesEvaList = new ArrayList<LessionEvaTemp>();
			BeanQueryVo bean  = new BeanQueryVo();
			bean.setId(id);
			LessionEvaTemp lesEva = classSubInfoService.selectLessionEvaById(bean);
			lesEvaList.add(lesEva);
		}else {
			  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		}
		
		model.addAttribute("lesEvaList", lesEvaList);
		return "admin_lession_eva";
	}
	
	
	/**学生查看分页+分类+模糊查询:自查+添加评价成功后自动查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findStudentClassSub.do")
	public String findStudentClassSub(Model model,BeanQueryVo beanQueryVo,HttpSession session, @RequestParam(value="claSubId",required=false)Integer id) {
		List<LessionEvaTemp> lesEvaList = null;
		if(id != null) {//添加评价后，自动查询
			ClassSubInfo claSub = classSubInfoService.selectByPrimaryKey(id);//得到该条记录
			beanQueryVo.setClassSubInfo(claSub);
			lesEvaList = new ArrayList<LessionEvaTemp>();
			LessionEvaTemp les = classSubInfoService.selectLessionEvaById(beanQueryVo);
			lesEvaList.add(les);
			model.addAttribute("lesEvaList", lesEvaList);
		}else {//默认查询所有，分页查询+模糊查询
			  StudentInfo stu = (StudentInfo) session.getAttribute("student");
			  beanQueryVo.setStudentId(stu.getStudentId());
			  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
			  model.addAttribute("lesEvaList", lesEvaList);
		}
		return "stu_lession_eva";
	}
	
	
	/**调整评价课程页面
	 * 两种情况：
	 * 第一种知道该课程id直接评价：（班级id，学生ID，课程id）
	 * 第二中:在所有课程中选择要评价的（已知班级id，学生ID，+选择的课程id+）
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goSubLessionEva.do")
	public String goSubLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		beanQueryVo.setClassId(stu.getClassId());
		beanQueryVo.setStudentId(stu.getStudentId());
			try {
				LessionInfoTemp lesTemp = lessionInfoService.selectByIdLessionTemp(beanQueryVo);
				model.addAttribute("lesTemp", lesTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "stu_lession_eva_sub";
	}
	
	/**学生查看自己详细评价内容
	 * 两种情况：
	 * 第一种知道该课程id直接评价：（班级id，学生ID，课程id）
	 * 第二中:在所有课程中选择要评价的（已知班级id，学生ID，+选择的课程id+）
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goSeeLessionEva.do")
	public String goSeeLessionEva(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		beanQueryVo.setClassId(stu.getClassId());
		beanQueryVo.setStudentId(stu.getStudentId());
			try {
				LessionEvaTemp lesTemp =classSubInfoService.selectLessionEvaById(beanQueryVo);
				model.addAttribute("lesEva", lesTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "stu_lession_eva_see";
	}	
	
	
	
	
	/**学生提交评价后+直接查询该条记录
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/subLessionEva.do")
	public String subLessionEva(Model model, ClassSubInfo claSub) {
		try {
			claSub.setSubStatus(0);
			classSubInfoService.insertSelective(claSub);
			Integer id = claSub.getSubEvaId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:../lession/findLessionByClass.do";
	}
	
	/**
	 * @param model
	 * @param claSub
	 * @return
	 */
	@RequestMapping("/updateLessionEva.do")
	public String updateLessionEva(Model model,ClassSubInfo claSub) {
		classSubInfoService.updateByPrimaryKeySelective(claSub);
		return "forward:findClassSub.do?evaId="+claSub.getSubEvaId();
	}
	
}
