/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import cn.jx.pxc.colcurevamansystem.bean.ClassInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
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
import cn.jx.pxc.colcurevamansystem.utils.CodeUtil;
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
	
	/**导出班级课程详细评价表，所有学生
     * @param request
     * @param response
     * @param beanQueryVo
	 * @throws Exception 
     */
    @RequestMapping(value = "/exportExcelToStudent.do", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportExcelToStudent( HttpServletRequest request, HttpServletResponse response,BeanQueryVo beanQueryVo) throws Exception {
    	String mether =request.getMethod();
    	//获取查询数据，在service层实现
    	List<StudentInfoCustom> list = studentInfoService.selectByClassList(beanQueryVo);
    	HSSFWorkbook wb = new HSSFWorkbook();//声明工
    	String tableName = list.get(0).getClassName()+"学生表";
    	Sheet sheet = wb.createSheet(tableName);//新建表
    	sheet.setDefaultColumnWidth(15);//设置表宽
    	HSSFCellStyle style = wb.createCellStyle();
    	org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
    	font.setFontHeightInPoints((short) 12);
    	HSSFCellStyle headerStyle = wb.createCellStyle();
    	org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
    	headerFont.setFontHeightInPoints((short) 14);
    	headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    	headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	headerStyle.setFont(headerFont);
    	CellRangeAddress cra0 = new CellRangeAddress(0, 1,0,9);
    	sheet.addMergedRegion(cra0);
    	sheet.setDefaultColumnWidth((short) 15);
    	Row row = sheet.createRow(0);
    	Cell cell1 = row.createCell(0);
    	
    	cell1.setCellValue(tableName);
    	cell1.setCellStyle(headerStyle);
    	//设置字体样式
    	org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
    	titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    	style.setFont(titleFont);
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	
    	Row row1 = sheet.createRow(2);
    	Cell cell = row1.createCell(0);
    	
    	cell.setCellValue("学号");
    	cell.setCellStyle(style);
    	cell = row1.createCell(1);
    	
    	cell.setCellValue("姓名");
    	cell.setCellStyle(style);
    	cell = row1.createCell(2);
    	
    	cell.setCellValue("电话号码");
    	cell.setCellStyle(style);
    	cell = row1.createCell(3);
    	
    	cell.setCellValue("邮箱");
    	cell.setCellStyle(style);
    	cell = row1.createCell(4);
    	
    	cell.setCellValue("班级");
    	cell.setCellStyle(style);
    	cell = row1.createCell(5);
    	
    	cell.setCellValue("学院");
    	cell.setCellStyle(style);
    	cell = row1.createCell(6);
  	
    	//时间转字符串的格式
    	for (int i = 0, imax = list.size(); i < imax; i++) {
    		row1 = sheet.createRow(i + 3);
    		//学号
    		if (list.get(i).getAccount() == null || "".equals(list.get(i).getAccount())) {//班级名称
    			row1.createCell(0).setCellValue("-");
    		} else {
    			row1.createCell(0).setCellValue(list.get(i).getAccount());
    		}
    		
    		//姓名
    		if (list.get(i).getUsername()== null || "".equals(list.get(i).getUsername())) {//课程名称
    			row1.createCell(1).setCellValue("-");
    		} else {
    			row1.createCell(1).setCellValue(list.get(i).getUsername());
    		}
    		
    		//电话号码
    		if (list.get(i).getTelphone() == null ||"".equals(list.get(i).getTelphone())) {//授课教师
    			row1.createCell(2).setCellValue("-");
    		} else {
    			row1.createCell(2).setCellValue(list.get(i).getTelphone());
    		}
    		//邮箱
    		if (list.get(i).getMail() == null||"".equals(list.get(i).getMail())) {//平均分
    			row1.createCell(3).setCellValue("-");
    		} else {
    			row1.createCell(3).setCellValue(list.get(i).getMail());
    		}
    		//班级
    		if (list.get(i).getClassName() == null||"".equals(list.get(i).getClassName())) {//平均分
    			row1.createCell(4).setCellValue("-");
    		} else {
    			row1.createCell(4).setCellValue(list.get(i).getClassName());
    		}
    		//学院
    		if (list.get(i).getProfessionName() == null||"".equals(list.get(i).getProfessionName())) {//平均分
    			row1.createCell(5).setCellValue("-");
    		} else {
    			row1.createCell(5).setCellValue(list.get(i).getProfessionName());
    		}
    	   	         
    	}
    	try {
    		response.reset();
    		response.setContentType("application/msexcel;charset=UTF-8");
    		SimpleDateFormat newsdf=new SimpleDateFormat("yyyyMMdd");
    		String date = newsdf.format(new Date());
    		String str = tableName + date + ".xls";
    		response.addHeader("Content-Disposition", "attachment;filename=\""
    				+ new String(str.getBytes("UTF-8"), "ISO8859-1") + "\"");
    		OutputStream out = response.getOutputStream();
    		wb.write(out);
    		out.flush();
    		out.close();
    	} catch (FileNotFoundException e) {
    		JOptionPane.showMessageDialog(null, "导出失败!");
    		e.printStackTrace();
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, "导出失败!");
    		e.printStackTrace();
    	}
    }	

	/**查看某班级的学生
	 * @param beanQueryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStudentsByClass.do")
	public String getStudentsByClass(BeanQueryVo  beanQueryVo, Model model) {
		try {
			if(beanQueryVo.getPageSize() == null) {//默认显示第几页
				beanQueryVo.setClassId(beanQueryVo.getId());
				beanQueryVo.setPageSize(5);
			}
			if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
				beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
			}
			List<StudentInfoCustom> stuList = studentInfoService.selectByClassList(beanQueryVo);
			
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
			model.addAttribute("classId", beanQueryVo.getClassId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_class_student";
	}

	/**进入登录页面
	 * @param model
	 * @param mes
	 * @return
	 */
	@RequestMapping("/goLogin.do")
	public String goLogin(Model model,@RequestParam(value="message",required=false)String mes) {
		model.addAttribute("message", mes);
		return "login";
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
			//密码加密
			//String enCodePassword = CodeUtil.getMD5Encoding(tea.getPassword());
			//tea.setPassword(enCodePassword);
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
			//密码加密
			String enCodePassword = CodeUtil.getMD5Encoding(tea.getPassword());
			tea.setPassword(enCodePassword);
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
			//密码加密
			String enCodePassword = CodeUtil.getMD5Encoding(stu.getPassword());
			stu.setPassword(enCodePassword);
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
				//密码加密
				String enCodePassword = CodeUtil.getMD5Encoding(stu.getPassword());
				stu.setPassword(enCodePassword);
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
			String enCodePassword = CodeUtil.getMD5Encoding(beanQueryVo.getPassword());
			beanQueryVo.setPassword(enCodePassword);
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
			model.addAttribute("classId", beanQueryVo.getClassId());
			model.addAttribute("status", beanQueryVo.getStatus());
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
			String enCodePasswd = CodeUtil.getMD5Encoding(studentInfo.getPassword());
			studentInfo.setPassword(enCodePasswd);
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
			 List<ClassInfoCustom>  claList = classInfoService.selectByProfessionList(beanQueryVo);
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
			String enCodePasswd = CodeUtil.getMD5Encoding(teacherInfo.getPassword());
			teacherInfo.setPassword(enCodePasswd);
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
			//密码加密
			//String enCodePassword = CodeUtil.getMD5Encoding(tea.getPassword());
			//tea.setPassword(enCodePassword);
			teacherInfoService.updateByPrimaryKeySelective(tea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roadParent", "roadParent");
		return "forward:teacherAdmin.do?param="+tea.getTeacherId();
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
	
	
	
	
	
	
	
	
	
	
	
	/**超级管理员后台首页
	 * @return
	 */
	@RequestMapping("/indexAdmin.do")
	public String indexAdmin() {
		return "ad_welcome";
	}
	
	/**教师后台首页
	 * @return
	 */
	@RequestMapping("/indexTeacher.do")
	public String indexTeacher() {
		return "te_welcome";
	}
	
	/**学生后台首页
	 * @return
	 */
	@RequestMapping("/indexStudent.do")
	public String indexStudent() {
		return "st_welcome";
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
