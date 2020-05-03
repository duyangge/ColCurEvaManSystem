/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.FunInfoService;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.RoleInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;
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
	
	@Resource
	public LessionInfoService lessionInfoService;
	
	@Resource
	public FunInfoService funInfoService;
	
	@Resource
	public RoleInfoService roleInfoService;

	/**进入登录页面
	 * @param model
	 * @param mes
	 * @return
	 */
	@RequestMapping("/goLogin.do")
	public String goLogin(Model model,@RequestParam(value="message",required=false)String mes) {
		model.addAttribute("message", mes);
		return "logins";
	}
	
	/**跳转管理员个人基本信息
	 * @return
	 */
	@RequestMapping("/goUpdateAdminInfo.do")
	public String  goUpdateAdminInfo(HttpSession session,Model model) {
		TeacherInfo tea = (TeacherInfo) session.getAttribute("admin");
		TeacherInfo teacherInfo = teacherInfoService.selectByPrimaryKey(tea.getTeacherId());
		model.addAttribute("tea",teacherInfo );
		return "ad_info";
	}
	
	
	
	/**修改个人基本信息+修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateAdminInfo.do" )
	public String  updateAdminInfo(Model model,TeacherInfo tea,Integer id,BindingResult bindingResult, MultipartFile head_image) {
		try {
			tea.setTeacherId(id);
			//存储图片的物理地址
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
			//得到图片的原始name
			String originalFileName = head_image.getOriginalFilename();//
			//上传图片，判断上传的图片不能为空
			if (head_image != null && originalFileName != null && originalFileName != "") {
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				tea.setHeadImage(newFileName);;//将图片名称写入数据库中
			}
			teacherInfoService.updateByPrimaryKeySelective(tea);//由于更新后，tea.id变成影响行数		
		    //修改基本信息后，返回基本信息页面
			TeacherInfo teacherInfo = teacherInfoService.selectByPrimaryKey(id);
			model.addAttribute("tea", teacherInfo);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roadParent", "roadParent");
		return "ad_info";
	}
	
	/**修改管理员密码(教师和超级管理员)
	 * @param model
	 * @param tea
	 * @return
	 */
	@RequestMapping("/updateAdminPassWord.do")
	public String updateAdminPassWord(Model model,TeacherInfo tea) {
		try {
			teacherInfoService.updateByPrimaryKeySelective(tea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "退出登录");
		return "ad_info";
	}
	
	/**修改学生密码
	 * @param model
	 * @param tea
	 * @return
	 */
	@RequestMapping("/updateStudentPassWord.do")
	public String updateStudentPassWord(Model model,StudentInfo stu) {
		try {
			studentInfoService.updateByPrimaryKeySelective(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "退出登录");
		return "ad_info";
	}
	
	
	
	
	/**跳转学生基本信息
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/goUpdateStudentInfo.do")
	public String  goUpdateStudentInfo(HttpSession session,Model model) {
		StudentInfo stu = (StudentInfo) session.getAttribute("student");
		StudentInfoCustom stuCus = studentInfoService.selectCustomByKey(stu.getStudentId());
		model.addAttribute("stu",stuCus );
		return "st_info";
	}
	
	
	
	/**修改学生个人基本信息+修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateStudentInfo.do" )
	public String  updateStudentInfo(Model model,StudentInfo stu, BindingResult bindingResult, MultipartFile head_image) {
		try {
			//存储图片的物理地址
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
			//得到图片的原始name
			String originalFileName = head_image.getOriginalFilename();//
			//上传图片，判断上传的图片不能为空
			if (head_image != null &&originalFileName != null && !"".equals(originalFileName) ) {				
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));				
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				stu.setHeadImage(newFileName);;//将图片名称写入数据库中
			}			
			    studentInfoService.updateByPrimaryKeySelective(stu);//由于更新后，tea.id变成影响行数		
				StudentInfoCustom stuCus = studentInfoService.selectCustomByKey(stu.getStudentId());
				model.addAttribute("stu", stuCus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roadParent", "roadParent");
		return "st_info";
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
			String message="账号异常，请通知管理员处理！";
			StudentInfo stu = studentInfoService.selectByAccountList(beanQueryVo);
			TeacherInfo  tea = teacherInfoService.selectByAccountList(beanQueryVo);
			if ( stu != null ) {//学生登录
				RoleInfo role = roleInfoService.selectByPrimaryKey(stu.getRoleId());
				if(stu.getStatus().equals("1")||role.getRoleStatus().equals("1")) { 
					return "forward:goLogin.do?message="+message;
				}
				beanQueryVo.setId(3);
				List<ParentFunInfo>	funList = funInfoService.loadMenuByRoleAndStatus(beanQueryVo);
				for (ParentFunInfo pa : funList) {
					List<SubFunInfo> subList = pa.getSubFunInfoList();
					if(subList.get(0).getFunId() != null) {
						Iterator<SubFunInfo> it =  subList.iterator();
						while(it.hasNext()) {
							SubFunInfo sub = it.next();
							if(sub.getFunStatus().equals("1")) it.remove();
						}
					}
				}
				model.addAttribute("funList", funList);
				session.setAttribute("student",stu);
				model.addAttribute("stu", stu);
				return "st_index";//学生页面
			}else if( tea != null && tea.getRoleId() != 1 ) {//教师登录
				RoleInfo role = roleInfoService.selectByPrimaryKey(tea.getRoleId());
				if(tea.getStatus().equals("1")||role.getRoleStatus().equals("1")) { 
					return "forward:goLogin.do?message="+message;
				}
				beanQueryVo.setId(2);
				List<ParentFunInfo>	funList = funInfoService.loadMenuByRoleAndStatus(beanQueryVo);
				for (ParentFunInfo pa : funList) {
					List<SubFunInfo> subList = pa.getSubFunInfoList();
					if(subList.get(0).getFunId() != null) {
						Iterator<SubFunInfo> it =  subList.iterator();
						while(it.hasNext()) {
							SubFunInfo sub = it.next();
							if(sub.getFunStatus().equals("1")) it.remove();
						}
					}
				}
				model.addAttribute("funList", funList);
				session.setAttribute("admin",tea);
				model.addAttribute("tea", tea);
				return "te_index";//教师页面
			}else if(tea != null && tea.getRoleId() == 1){ //管理员登录
				beanQueryVo.setId(1);
				List<ParentFunInfo>	funList = funInfoService.loadMenuByRoleAndStatus(beanQueryVo);
				for (ParentFunInfo pa : funList) {
					List<SubFunInfo> subList = pa.getSubFunInfoList();
					if(subList.get(0).getFunId() != null) {
						Iterator<SubFunInfo> it =  subList.iterator();
						while(it.hasNext()) {
							SubFunInfo sub = it.next();
							if(sub.getFunStatus().equals("1")) it.remove();
						}
					}
				}
				model.addAttribute("funList", funList);
				session.setAttribute("admin",tea);
				model.addAttribute("admin", tea);
				return "ad_index";//管理员界面
			}else {//登录错误
				 message="用户名或密码错误，请重新登录！";
				 return "forward:goLogin.do?message="+message;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:goLogin.do";
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
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		    List<StudentInfoCustom> stuList = null ;
			if( param != null) {//添加修改信息自动查询
				StudentInfoCustom stuCu = studentInfoService.selectCustomByKey(param);
				stuList = new ArrayList<StudentInfoCustom>();
				stuList.add(stuCu);
			}else {//模糊查询+条件查询
				stuList = studentInfoService.findStudentByName(beanQueryVo);
			}
			
			if(stuList.size() > 0) {//防止查询数据为空，报异常
               List<StudentInfoCustom> userInfoCustomList = this.getPageContentByStudent(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), stuList);
				
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				
				model.addAttribute("stuList", userInfoCustomList);//得到分页内容
			}
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
			//保存每个学院
			List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
			model.addAttribute("proList", proList);
			model.addAttribute("professionId", beanQueryVo.getProfessionId());
			//需要重新获取用户信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ad_user_student";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<StudentInfoCustom> getPageContentByStudent(Model model, Integer currentPage, Integer pageSize ,List<StudentInfoCustom> userInfoCustomListOld) throws Exception{
		ListPageUtil<StudentInfoCustom> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<StudentInfoCustom>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<StudentInfoCustom>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<StudentInfoCustom>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<StudentInfoCustom>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<StudentInfoCustom>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<TeacherInfo> getPageContentByTeacher(Model model, Integer currentPage, Integer pageSize ,List<TeacherInfo> userInfoCustomListOld) throws Exception{
		ListPageUtil<TeacherInfo> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<TeacherInfo>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<TeacherInfo>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<TeacherInfo>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<TeacherInfo>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<TeacherInfo>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	
	
	
	
	
	
	
	
	/**添加学生：
	 * 添加学生后，自动通过主键显示查询
	 * @param model
	 * @param beanQueryVo
	 * @return 
	 */
	@RequestMapping(value="/addStudent.do",method=RequestMethod.POST)
	public String addStudent(Model model,StudentInfo studentInfo, BindingResult bindingResult, MultipartFile head_image ) {
		try {
			
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";//存储图片的物理地址
			//真实项目中创建图片服务器；在tomcat服务器下创建图片缓存地址：图片虚拟目录（两个方式：直接图形添加和在service.xml中添加）
			//物理地址和虚拟地址			
			String originalFileName = head_image.getOriginalFilename();//得到图片的原始name
			//上传图片，判断上传的图片不能为空
			if (head_image != null && originalFileName != null && originalFileName != "") {
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				studentInfo.setHeadImage(newFileName);;//将图片名称写入数据库中
			}
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
		//系统自动生成随机账号（8位）
		model.addAttribute("account",this.getNum(8));
		return "ad_user_student_add";
	}
	
	/**
	 * @param digit 位数zd
	 * @return 随机生成digit位数的数字
	 */
	public static String getNum(int digit) {
	    StringBuilder str = new StringBuilder();
	    for (int i = 0; i < digit; i++) {
	        if (i == 0 && digit > 1)
	            str.append(new Random().nextInt(9) + 1);
	        else
	            str.append(new Random().nextInt(10));
	    }
	    return str.toString();
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
		return "ad_user_student_edit";
	}
	
	
	/**管理员修改学生信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateStudent.do")
	public String updateStudent(Model model,StudentInfo stu,BindingResult bindingResult, MultipartFile head_image) {
		try {
			//存储图片的物理地址
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
			//得到图片的原始name
			String originalFileName = head_image.getOriginalFilename();//
			//上传图片，判断上传的图片不能为空
			if (head_image != null && originalFileName != null && !"".equals(originalFileName) ) {				
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));				
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				stu.setHeadImage(newFileName);;//将图片名称写入数据库中
			}			
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
		    if(beanQueryVo.getPageSize() == null) {//默认显示第几页
				beanQueryVo.setPageSize(5);
			}
			if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
				beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
			}
			if( param != null) {//添加修改信息自动查询
				teaList = new ArrayList<TeacherInfo>() ;
				TeacherInfo tea = teacherInfoService.selectByPrimaryKey(param);
				teaList.add(tea);
			}else {//模糊查询+条件查询
				  teaList = teacherInfoService.selectByNameList(beanQueryVo);
			}	
			if(teaList.size() > 0) {//防止查询数据为空，报异常
	               List<TeacherInfo> userInfoCustomList = this.getPageContentByTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), teaList);
					
					model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					
					model.addAttribute("teaList", userInfoCustomList);//得到分页内容
			}
				model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
			
		    return "ad_user_teacher";
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
		//系统自动生成随机账号（8位）
		model.addAttribute("account",this.getNum(8));
		return "ad_user_teacher_add";
	}
	
	/**提交添加教师信息
	 * @param teacherInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/addTeacher.do")
	public String addTeacher(TeacherInfo teacherInfo,Model model,BindingResult bindingResult, MultipartFile head_image) {
		try {
			//存储图片的物理地址
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
			//得到图片的原始name
			String originalFileName = head_image.getOriginalFilename();//
			//上传图片，判断上传的图片不能为空
			if (head_image != null && originalFileName != null && originalFileName != "") {
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				teacherInfo.setHeadImage(newFileName);;//将图片名称写入数据库中
			}
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
		return "ad_user_teacher_edit";
	}
	
	/**提交修改信息
	 * @param model
	 * @param tea
	 * @return
	 */
	@RequestMapping("/updateTeacher.do")
	public String updateTeacher(Model model,TeacherInfo tea,BindingResult bindingResult, MultipartFile head_image) {
		try {
			//存储图片的物理地址
			String pic_path = "E:\\learnsoftware\\fileUpload\\temp\\";
			//得到图片的原始name
			String originalFileName = head_image.getOriginalFilename();//
			//上传图片，判断上传的图片不能为空
			if (head_image != null && originalFileName != null && originalFileName != "") {
				//新的图片名称
				String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
				//新图片
				File newFile = new File(pic_path+newFileName);
				//将内存中的数据写入磁盘
				head_image.transferTo(newFile);
				tea.setHeadImage(newFileName);;//将图片名称写入数据库中
			}
			teacherInfoService.updateByPrimaryKeySelective(tea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roadParent", "roadParent");
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
		return "ad_welcome";
	}
	
	/**退出登录
	 * @return
	 */
	@RequestMapping("/loginOut.do")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "redirect:goLogin.do";
	}
	
	/**查看疫情分布
	 * @return
	 */
	@RequestMapping("/goLookEpidemicPage.do")
	public String goLookEpidemicPage() {
	return "look_epidemic";	
	}
}
