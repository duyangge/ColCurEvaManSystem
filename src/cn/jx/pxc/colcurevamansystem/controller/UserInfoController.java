/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *<p> Title:  UserInfoController</p>
 *<p> Description:  用户控制层</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    黄信胜
 * @date      2020年4月6日下午12:47:19
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserInfoController {
	
	@Resource
	public StudentInfoService studentInfoService;
	
	@Resource
	public TeacherInfoService teacherInfoService;
	
	@Resource
	public ProfessionInfoService professionInfoService;
	
	@Resource
	public ClassInfoService classInfoService;
	
	/**进入登录页面
	 * @return
	 */
	@RequestMapping("/goLogin.do" )
	public String goLogin() {
		return "login";
	}
	
	/**跳转个人基本信息
	 * @return
	 */
	@RequestMapping(value="/goUpdateAdminInfo.do")
	public String  goUpdateAdminInfo(HttpSession session,Model model) {
		TeacherInfo tea = (TeacherInfo) session.getAttribute("admin");
		model.addAttribute("tea", teacherInfoService.selectByPrimaryKey(tea.getTeacherId()));
		return "admin_info";
	}
	
	
	
	/**修改个人基本信息+修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateAdminInfo.do" )
	public String  updateAdminInfo(Model model,TeacherInfo tea) {
		try {
			if(tea.getPassword() != null) {//修改密码成功后，返回登录页面
				teacherInfoService.updateByPrimaryKeySelective(tea);
				model.addAttribute("message", "退出登录");
			}else {//修改基本信息后，返回基本信息页面
				TeacherInfo teacherInfo = teacherInfoService.selectByPrimaryKey(tea.getTeacherId());
				model.addAttribute("tea", teacherInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "admin_info";
	}
	
	
	
	
	
	/**用户登录：
	 * 把所有操作交给Service层
	 * 学生页面：
	 * 教师页面：
	 * 管理员页面：
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	
	@RequestMapping("/login.do")
	public String login(Model model,BeanQueryVo beanQueryVo,HttpSession session) {
		try {
			StudentInfo stu = studentInfoService.selectByAccountList(beanQueryVo);
			TeacherInfo  tea = teacherInfoService.selectByAccountList(beanQueryVo);
			if ( stu != null ) {
				session.setAttribute("student",stu);
				return "stu_index";//学生页面
				
			}else if( tea != null && tea.getRoleId() != 1 ) {
				session.setAttribute("teacher",tea);
				return "tea_index";//教师页面
			}else { 
				session.setAttribute("admin",tea);
				return "admin_index";//管理员界面
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "login";
	}
		
	/**分页+
	 * 选中状态
	 * 模糊查询所有学生
	 * @param model
	 * @return
	 */
	@RequestMapping("/studentAdmin.do")
	public String studentAdmin(Model model,BeanQueryVo beanQueryVo,@RequestParam(value="addStudentId",required=false)Integer param) {
	try {
		   List<StudentInfoCustom> stuList = null ;
			if( param != null) {//添加修改信息自动查询
				StudentInfoCustom stuCu = studentInfoService.selectCustomByKey(param);
				stuList = new ArrayList<StudentInfoCustom>();
				stuList.add(stuCu);
			}else {//模糊查询+条件查询
				stuList = studentInfoService.findStudentByName(beanQueryVo);
			}
			model.addAttribute("stuList", stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "admin_user_student";
	}
	
	/**添加学生：
	 * 添加学生后，自动通过主键显示查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping(value="/addStudent.do",method=RequestMethod.POST)
	public String addStudent(Model model,BeanQueryVo beanQueryVo) {
		//接收前台传递的json串
		StudentInfo studentInfo = new StudentInfo();
		BeanUtils.copyProperties(beanQueryVo.getStudentInfo(), studentInfo);//基本信息
		try {
			studentInfoService.insertSelective(studentInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:studentAdmin.do?addStudentId="+studentInfo.getStudentId();
	}
	
	
	/**去添加学生页面：
	 * @param model
	 * @return
	 */
	@RequestMapping("/goAddStudent.do")
	public String goAddStudent(Model model) {
		//保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
		return "admin_user_student_add";
	}
	

	
	/**自动刷新班级二级联动://每个班级(二级联动)
	 * 通过学院id查询所有班级id
	 * produces = {"application/json;charset=UTF-8"}//防止得到的页面json数据中文乱码
	 * @param model
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value="/changeClass.do",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String changeClass(Model model,Integer id,BeanQueryVo beanQueryVo) {
		beanQueryVo.setProfessionId(id);
		 JSONArray array=new JSONArray();
		  try {
			 List<ClassInfo>  claList = classInfoService.selectByProfessionList(beanQueryVo);
			for (ClassInfo cla : claList) {
				JSONObject obj=new JSONObject();
				obj.put("classId",cla.getClassId() );
				obj.put("className",cla.getClassName());
				array.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  return array.toString();
	}
	
	
	/**去修改学生信息页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goUpdateStudent.do")
	public String goUpdateStudent(Model model,BeanQueryVo beanQueryVo) {
		StudentInfo stu = studentInfoService.selectByPrimaryKey(beanQueryVo.getStudentId());
		model.addAttribute("stu", stu);//保存编辑学生
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);//保存所有学院
		return "admin_user_student_edit";
	}
	
	
	/**修改学生信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateStudent.do")
	public String updateStudent(Model model,StudentInfo stu) {
		try {
			studentInfoService.updateByPrimaryKeySelective(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:studentAdmin.do?addStudentId="+stu.getStudentId();
	}
	
	
	
	/**删除学生
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping("/deleteStudent.do")
	public String deleteStudent(Model model,String idStr,Integer id) {
		try {
			if(id == null) {
				String[]  strList = idStr.split(",");
				//将string转换为int数组
				int[] array = Arrays.asList(strList).stream().mapToInt(Integer::parseInt).toArray();
				BeanQueryVo beanQueryVo = new BeanQueryVo();
				beanQueryVo.setIds(array);
				studentInfoService.deleteStudentList(beanQueryVo);
			}else {
				studentInfoService.deleteStudent(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:studentAdmin.do";
	}
	
	
	
	/**分页+模糊查询所有教师：
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/teacherAdmin.do")
	public String teacherAdmin(Model model,BeanQueryVo beanQueryVo,@RequestParam(value="teacherId",required=false)Integer param) throws Exception { 
		
		    List<TeacherInfo>  teaList = null;
			if( param != null) {//添加修改信息自动查询
				teaList = new ArrayList<TeacherInfo>() ;
				TeacherInfo tea = teacherInfoService.selectByPrimaryKey(param);
				teaList.add(tea);
			}else {//模糊查询+条件查询
				  teaList = teacherInfoService.selectByNameList(beanQueryVo);
			}	
			model.addAttribute("teaList", teaList);
		    return "admin_user_teacher";
	}
	
	
	/**跳转添加教师页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goAddTeacher.do")
	public String goAddTeacher(Model model,BeanQueryVo beanQueryVo) {
		//保存每个学院
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
		return "admin_user_teacher_add";
	}
	
	/**提交添加教师信息
	 * @param teacherInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/addTeacher.do")
	public String addTeacher(TeacherInfo teacherInfo,Model model) {
		try {
			teacherInfoService.insertSelective(teacherInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:teacherAdmin.do?teacherId="+teacherInfo.getTeacherId();
		
	}
	
	
	/**跳转教师信息更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/goUpdateTeacher.do")
	public String goUpdateTeacher(Integer id,Model model) {
		//保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
		TeacherInfo tea = teacherInfoService.selectByPrimaryKey(id);
		model.addAttribute("tea", tea);
		return "admin_user_teacher_edit";
	}
	
	/**提交修改信息
	 * @param model
	 * @param tea
	 * @return
	 */
	@RequestMapping("/updateTeacher.do")
	public String updateTeacher(Model model,TeacherInfo tea) {
		try {
			teacherInfoService.updateByPrimaryKeySelective(tea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:teacherAdmin.do?param"+tea.getTeacherId();
	}
	
	/**删除教师
	 * @param id
	 * @param idStr
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteTeacher.do")
	public String deleteTeacher(Integer id ,String idStr,Model model) {
		try {
			if(id == null) {
				String[]  strList = idStr.split(",");
				//将string转换为int数组
				int[] array = Arrays.asList(strList).stream().mapToInt(Integer::parseInt).toArray();
				BeanQueryVo beanQueryVo = new BeanQueryVo();
				beanQueryVo.setIds(array);
				teacherInfoService.deleteList(beanQueryVo);
			}else {
				teacherInfoService.deleteByPrimaryKey(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:teacherAdmin.do";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**后台首页
	 * @return
	 */
	@RequestMapping("/indexAdmin.do")
	public String indexAdmin() {
		return "admin_welcome";
	}
	
	/**退出登录
	 * @return
	 */
	@RequestMapping("/loginOut.do")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}
	/**自动刷新学院
	 * @param model
	 * @param beanQueryVo
	 *//*
	@ResponseBody
	@RequestMapping(value="/changeProfession.do",method=RequestMethod.POST)
	public String changeProfession(Model model,BeanQueryVo beanQueryVo) {
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		JSONArray array=new JSONArray();
		for (ProfessionInfo professionInfo : proList) {
			JSONObject obj=new JSONObject();
			obj.put("professionId",professionInfo.getProfessionId() );
			obj.put("professionName", professionInfo.getProfessionName());
			array.add(obj);
		}
		return array.toString();
	}*/
}
