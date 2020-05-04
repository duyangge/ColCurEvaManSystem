/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;
import cn.jx.pxc.colcurevamansystem.utils.DateFormateUtil;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;

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
	
	@Resource
	public ClassInfoService classInfoService;
	
	@Resource
	public ProfessionInfoService professionInfoService;
	
	
	 
    /**导出班级课程评价表（平均分）
     * @param request
     * @param response
     * @param beanQueryVo
     */
    @RequestMapping(value = "/exportReportStaticsData.do", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportReportStaticsData( HttpServletRequest request, HttpServletResponse response,BeanQueryVo beanQueryVo) {
        String mether =request.getMethod();
        //获取查询数据，在service层实现
        List<ClassSubInfoCustom>  list = classSubInfoService.findAvgScoreByClassIdAndLessionId(beanQueryVo);
        String tableName = "班级课程评价表";
        HSSFWorkbook wb = new HSSFWorkbook();//声明工
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
        
        cell.setCellValue("班级名称");
        cell.setCellStyle(style);
        cell = row1.createCell(1);
        
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row1.createCell(2);
        
        cell.setCellValue("授课教师");
        cell.setCellStyle(style);
        cell = row1.createCell(3);
        
        cell.setCellValue("平均分");
        cell.setCellStyle(style);
        cell = row1.createCell(4);
        
        cell.setCellValue("开始时间");
        cell.setCellStyle(style);
        cell = row1.createCell(5);
        
        cell.setCellValue("到期时间");
        cell.setCellStyle(style);
        cell = row1.createCell(6);
        
        cell.setCellValue("评价人数");
        cell.setCellStyle(style);
        cell = row1.createCell(7);

 
        for (int i = 0, imax = list.size(); i < imax; i++) {
            row1 = sheet.createRow(i + 3);
            if (list.get(i).getClassName() == null || "".equals(list.get(i).getClassName())) {//班级名称
                row1.createCell(0).setCellValue("-");
            } else {
                row1.createCell(0).setCellValue(list.get(i).getClassName());
            }
            if (list.get(i).getLessionName() == null || "".equals(list.get(i).getLessionName())) {//课程名称
                row1.createCell(1).setCellValue("-");
            } else {
                row1.createCell(1).setCellValue(list.get(i).getLessionName());
            }
            if (list.get(i).getUsername() == null ||"".equals(list.get(i).getUsername())) {//授课教师
                row1.createCell(2).setCellValue("-");
            } else {
                row1.createCell(2).setCellValue(list.get(i).getUsername());
            }
            if (list.get(i).getAvgScore() == null||"".equals(list.get(i).getAvgScore())) {//平均分
                row1.createCell(3).setCellValue("-");
            } else {
                row1.createCell(3).setCellValue(list.get(i).getAvgScore());
            }
            if (list.get(i).getStartTime() == null||"".equals(list.get(i).getStartTime())) {//开始时间
                row1.createCell(4).setCellValue("-");
            } else {
                row1.createCell(4).setCellValue(list.get(i).getStartTime());
            }
            if (list.get(i).getEndTime() == null||"".equals(list.get(i).getEndTime())) {//截至时间
                row1.createCell(5).setCellValue("-");
            } else {
                row1.createCell(5).setCellValue(list.get(i).getEndTime());
            }
            if (list.get(i).getEvaNum() == null||"".equals(list.get(i).getEvaNum())) {//评价人数
                row1.createCell(6).setCellValue("-");
            } else {
                row1.createCell(6).setCellValue(list.get(i).getEvaNum());
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
    
    /**导出班级课程详细评价表，所有学生
     * @param request
     * @param response
     * @param beanQueryVo
     */
    @RequestMapping(value = "/exportExcelToLessionEva.do", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportExcelToLessionEva( HttpServletRequest request, HttpServletResponse response,BeanQueryVo beanQueryVo) {
    	String mether =request.getMethod();
    	//获取查询数据，在service层实现
    	List<LessionEvaTemp>  list = classSubInfoService.selectLessionEva(beanQueryVo);
    	
    	HSSFWorkbook wb = new HSSFWorkbook();//声明工
    	String tableName = "班级课程评价详细表";
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
    	
    	cell.setCellValue("班级名称");
    	cell.setCellStyle(style);
    	cell = row1.createCell(1);
    	
    	cell.setCellValue("课程名称");
    	cell.setCellStyle(style);
    	cell = row1.createCell(2);
    	
    	cell.setCellValue("授课教师");
    	cell.setCellStyle(style);
    	cell = row1.createCell(3);
    	
    	cell.setCellValue("评价分");
    	cell.setCellStyle(style);
    	cell = row1.createCell(4);
    	
    	cell.setCellValue("评价内容");
    	cell.setCellStyle(style);
    	cell = row1.createCell(5);
    	
    	cell.setCellValue("评价学生");
    	cell.setCellStyle(style);
    	cell = row1.createCell(6);
    	
    	cell.setCellValue("开始时间");
    	cell.setCellStyle(style);
    	cell = row1.createCell(7);
    	
    	cell.setCellValue("截至时间");
    	cell.setCellStyle(style);
    	cell = row1.createCell(8);
    	
    	//时间转字符串的格式
    	for (int i = 0, imax = list.size(); i < imax; i++) {
    		row1 = sheet.createRow(i + 3);
    		//班级
    		if (list.get(i).getClassName() == null || "".equals(list.get(i).getClassName())) {//班级名称
    			row1.createCell(0).setCellValue("-");
    		} else {
    			row1.createCell(0).setCellValue(list.get(i).getClassName());
    		}
    		
    		//课程
    		if (list.get(i).getLessionName() == null || "".equals(list.get(i).getLessionName())) {//课程名称
    			row1.createCell(1).setCellValue("-");
    		} else {
    			row1.createCell(1).setCellValue(list.get(i).getLessionName());
    		}
    		
    		//授课教师
    		if (list.get(i).getTeacherName() == null ||"".equals(list.get(i).getTeacherName())) {//授课教师
    			row1.createCell(2).setCellValue("-");
    		} else {
    			row1.createCell(2).setCellValue(list.get(i).getTeacherName());
    		}
    		//评分
    		if (list.get(i).getScore() == null||"".equals(list.get(i).getScore())) {//平均分
    			row1.createCell(3).setCellValue("-");
    		} else {
    			row1.createCell(3).setCellValue(list.get(i).getScore());
    		}
    		//评价内容
    		if (list.get(i).getSubInfo() == null||"".equals(list.get(i).getSubInfo())) {//平均分
    			row1.createCell(4).setCellValue("-");
    		} else {
    			row1.createCell(4).setCellValue(list.get(i).getSubInfo());
    		}
    		//评价学生
   
    		if (list.get(i).getSubUserName() == null||"".equals(list.get(i).getSubUserName())) {//平均分
    			row1.createCell(5).setCellValue("-");
    		} else {
    			row1.createCell(5).setCellValue(list.get(i).getSubUserName());
    		}
    		//开始时间
    		if (list.get(i).getStartTime() == null||"".equals(list.get(i).getStartTime())) {//开始时间
    			row1.createCell(6).setCellValue("-");
    		} else {
    			row1.createCell(6).setCellValue(list.get(i).getStartTime());
    		}
    		//截至时间
    		if (list.get(i).getEndTime() == null||"".equals(list.get(i).getEndTime())) {//截至时间
    			row1.createCell(7).setCellValue("-");
    		} else {
    			row1.createCell(7).setCellValue(list.get(i).getEndTime());
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
	
	/**教师查看自己教授课程的学生评价列表
	 * @param model
	 * @param session
	 * @param beanQueryVo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findStudentClassSubByTeacher.do")
	public String findStudentClassSubByTeacher(Model model,HttpSession session,BeanQueryVo beanQueryVo) throws Exception {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}

		TeacherInfo teacherInfo  = (TeacherInfo) session.getAttribute("admin");
		TeacherInfo tea = teacherInfoService.selectByPrimaryKey(teacherInfo.getTeacherId());
		List<LessionEvaTemp> lesEvaList = null;
		beanQueryVo.setTeacherId(tea.getTeacherId());
		beanQueryVo.setStatus("0");//教师只能查看可见评价
		try {
			lesEvaList = classSubInfoService.selectByteacher(beanQueryVo);
			if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
	               List<LessionEvaTemp> userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
					
					model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					
					model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		//保持该教师所教所有班级
		List<ClassInfo> claList = new ArrayList<ClassInfo>();
		List<LessionTeacherInfo>  lesTeaList = lessionInfoService.selectByTeacherList(beanQueryVo);
		for (LessionTeacherInfo lesTea : lesTeaList) {
			ClassInfo cla = classInfoService.selectByPrimaryKey(lesTea.getClassId());
			claList.add(cla);
		}
		//去重class对象
		Set<ClassInfo> userSet = new HashSet<>(claList);
	    List<ClassInfo> list = new ArrayList<>(userSet);
		
		model.addAttribute("classId", beanQueryVo.getClassId());
		model.addAttribute("claList", list);
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "te_lession_eva";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<LessionEvaTemp> getPageContentByClassSubAndTeacher(Model model, Integer currentPage, Integer pageSize ,List<LessionEvaTemp> userInfoCustomListOld) throws Exception{
		ListPageUtil<LessionEvaTemp> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<LessionEvaTemp>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
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
		return "ad_lession_eva_see";
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
		return "te_lession_eva_see";
	}
	
	
	
	
	/**管理员：分页+分类+模糊查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findClassSub.do")
	public String findClassSub(Model model,BeanQueryVo beanQueryVo,@RequestParam(value="evaId",required=false)Integer id) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
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
		
		if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
            List<LessionEvaTemp> userInfoCustomList;
			try {
				userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
				model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//数据回显
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());
		model.addAttribute("startTime", beanQueryVo.getStartTime());
		model.addAttribute("endTime", beanQueryVo.getEndTime());
		//可见
		model.addAttribute("status",beanQueryVo.getStatus() );
		//类别
		model.addAttribute("category", beanQueryVo.getCategory());
		return "ad_lession_eva";
	}
	
	
	/**学生查看分页+分类+模糊查询:自查+添加评价成功后自动查询
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/findStudentClassSub.do")
	public String findStudentClassSub(Model model,BeanQueryVo beanQueryVo,HttpSession session, @RequestParam(value="claSubId",required=false)Integer id) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}

		List<LessionEvaTemp> lesEvaList = null;
		if(id != null) {//添加评价后，自动查询
			ClassSubInfo claSub = classSubInfoService.selectByPrimaryKey(id);//得到该条记录
			beanQueryVo.setClassSubInfo(claSub);
			lesEvaList = new ArrayList<LessionEvaTemp>();
			LessionEvaTemp les = classSubInfoService.selectLessionEvaById(beanQueryVo);
			lesEvaList.add(les);
		}else {//默认查询所有，分页查询+模糊查询
			  StudentInfo stu = (StudentInfo) session.getAttribute("student");
			  beanQueryVo.setStudentId(stu.getStudentId());
			  lesEvaList = classSubInfoService.selectLessionEva(beanQueryVo);
		}
		if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
            List<LessionEvaTemp> userInfoCustomList = null;
			try {
				userInfoCustomList = this.getPageContentByClassSubAndTeacher(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "st_lession_eva";
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
		return "st_lession_eva_sub";
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
		return "st_lession_eva_see";
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
	
	/**查看课程评价分
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/lookEvaAnaResult.do")
	public String lookEvaAnaResult(BeanQueryVo beanQueryVo, Model model) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		List<ClassSubInfoCustom>  lesEvaList = classSubInfoService.findAvgScoreByClassIdAndLessionId(beanQueryVo);
		String way = beanQueryVo.getShowWays();
		if(way == null || !way.equals("1")) {//判断显示方式
			if(lesEvaList.size() > 0) {//防止查询数据为空，报异常
	            List<ClassSubInfoCustom> userInfoCustomList = null;
				try {
					userInfoCustomList = this.getPageContent(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), lesEvaList);
					model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					model.addAttribute("lesEvaList", userInfoCustomList);//得到分页内容
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else {
			model.addAttribute("lesEvaList",JSON.toJSONString(lesEvaList));
		}
		    //数据回显
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());
			List<ProfessionInfo>  proList = professionInfoService.selectByName(null);
			model.addAttribute("proList", proList);//保存每个学院
			model.addAttribute("professionId", beanQueryVo.getProfessionId());
			model.addAttribute("classId", beanQueryVo.getClassId());
			model.addAttribute("startTime", beanQueryVo.getStartTime());
			model.addAttribute("endTime", beanQueryVo.getEndTime());
			model.addAttribute("showWays", beanQueryVo.getShowWays());
		    return "ad_lession_eva_ana";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<ClassSubInfoCustom> getPageContent(Model model, Integer currentPage, Integer pageSize ,List<ClassSubInfoCustom> userInfoCustomListOld) throws Exception{
		ListPageUtil<ClassSubInfoCustom> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<ClassSubInfoCustom>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**利用echart插件查看班级课程平均分
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/goEchartsPage.do", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public List<ClassSubInfoCustom>  goEchartsPage(Model model, BeanQueryVo beanQueryVo){
		List<ClassSubInfoCustom>  lesEvaList = classSubInfoService.findAvgScoreByClassIdAndLessionId(beanQueryVo);
		List<ProfessionInfo>  proList = professionInfoService.selectByName(null);
		//数据回显
		model.addAttribute("proList", proList);//保存每个学院
		model.addAttribute("professionId", beanQueryVo.getProfessionId());
		model.addAttribute("classId", beanQueryVo.getClassId());
		model.addAttribute("startTime", beanQueryVo.getStartTime());
		model.addAttribute("endTime", beanQueryVo.getEndTime());
		model.addAttribute("showWays", beanQueryVo.getShowWays());
		return lesEvaList;
	}
	
}
