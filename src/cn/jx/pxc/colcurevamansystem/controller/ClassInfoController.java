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
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;

/**
 *<p> Title:  ClassInfoController.java</p>
 *<p> Description:  班级管理控制台</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月10日下午4:40:45
 * @version 版本号
 */
@Controller
@RequestMapping("/class")
@SuppressWarnings("all")
public class ClassInfoController {
	
	@Resource
	public ClassInfoService classInfoService;
	
	@Resource
	public ProfessionInfoService professionInfoService;
	
	/**导出某个学院所有班级表
     * @param request
     * @param response
     * @param beanQueryVo
	 * @throws Exception 
     */
    @RequestMapping(value = "/exportExcelToClass.do", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportExcelToClass( HttpServletRequest request, HttpServletResponse response,BeanQueryVo beanQueryVo) throws Exception {
    	String mether =request.getMethod();
    	//获取查询数据，在service层实现
    	List<ClassInfoCustom> list = classInfoService.selectByProfessionList(beanQueryVo);
    	HSSFWorkbook wb = new HSSFWorkbook();//声明工
    	String tableName = list.get(0).getProfessionName()+"班级表";
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
    	
    	cell.setCellValue("人数");
    	cell.setCellStyle(style);
    	cell = row1.createCell(2);
    	
    	cell.setCellValue("备注");
    	cell.setCellStyle(style);
    	cell = row1.createCell(3);
    	
    	cell.setCellValue("学院");
    	cell.setCellStyle(style);
    	cell = row1.createCell(4);

    	//时间转字符串的格式
    	for (int i = 0, imax = list.size(); i < imax; i++) {
    		row1 = sheet.createRow(i + 3);
    		//班级
    		if (list.get(i).getClassName() == null || "".equals(list.get(i).getClassName())) {//班级名称
    			row1.createCell(0).setCellValue("-");
    		} else {
    			row1.createCell(0).setCellValue(list.get(i).getClassName());
    		}    		
    		//人数
    		if (list.get(i).getStudentNum() == null || "".equals(list.get(i).getStudentNum())) {//课程名称
    			row1.createCell(1).setCellValue("-");
    		} else {
    			row1.createCell(1).setCellValue(list.get(i).getStudentNum());
    		}    		
    		//备注
    		if (list.get(i).getClassInfo() == null ||"".equals(list.get(i).getClassInfo())) {//授课教师
    			row1.createCell(2).setCellValue("-");
    		} else {
    			row1.createCell(2).setCellValue(list.get(i).getClassInfo());
    		}
    		//学院
    		if (list.get(i).getProfessionName() == null||"".equals(list.get(i).getProfessionName())) {//平均分
    			row1.createCell(3).setCellValue("-");
    		} else {
    			row1.createCell(3).setCellValue(list.get(i).getProfessionName());
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
	
	
	
	
	
	
	
	
	/**查看某学院下的所有班级信息
	 * @param beanQueryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/getClassByProfession.do")
	public String getClassInfoByProfession(BeanQueryVo beanQueryVo , Model model) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setProfessionId(beanQueryVo.getId());
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		try {
			List<ClassInfoCustom> claList = classInfoService.selectClaCusByNameList(beanQueryVo);
			if(claList.size() > 0) {//防止查询数据为空，报异常
			    List<ClassInfoCustom>	userInfoCustomList = this.getPageContentByClassAdmin(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), claList);
	            model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				model.addAttribute("claList", userInfoCustomList);//得到分页内容
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		model.addAttribute("professionId", beanQueryVo.getProfessionId());
		return "ad_profession_class";
	}
	
	
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<ClassInfoCustom> getPageContentByClassAdmin(Model model, Integer currentPage, Integer pageSize ,List<ClassInfoCustom> userInfoCustomListOld) throws Exception{
		ListPageUtil<ClassInfoCustom> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<ClassInfoCustom>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<ClassInfoCustom>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<ClassInfoCustom>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<ClassInfoCustom>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<ClassInfoCustom>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**分页+模糊查询
	 * @param model
	 * @param param
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/classAdmin.do")
	public String classAdmin(Model model,@RequestParam(value="claId",required=false)Integer param,BeanQueryVo beanQueryVo) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		
		List<ClassInfoCustom> claList = null;
		
		if(param != null) {//添加或修改，自动查询该条记录
			claList = new ArrayList<ClassInfoCustom>();
			ClassInfoCustom cla = classInfoService.selectById(param);
			claList.add(cla);
		}else {
			try {
				claList = classInfoService.selectClaCusByNameList(beanQueryVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(claList.size() > 0) {//防止查询数据为空，报异常
            List<ClassInfoCustom> userInfoCustomList;
			try {
					userInfoCustomList = this.getPageContentByClassAdmin(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), claList);
		            model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
					model.addAttribute("claList", userInfoCustomList);//得到分页内容
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
			List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
			model.addAttribute("proList", proList);//保存每个学院
			model.addAttribute("professionId", beanQueryVo.getProfessionId());
			return "ad_class";
	}
	
	
	/**跳转添加班级页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/goAddClass.do")
	public String goAddClass(Model model) {
       //保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
        return "ad_class_add";
	}
	
	/**添加班级
	 * @param model
	 * @param classInfo
	 * @return
	 */
	@RequestMapping("/addClass.do")
	public String addClass(Model model,ClassInfo cla) {
		try {
			classInfoService.insertSelective(cla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:classAdmin.do?claId="+cla.getClassId();
	}
	
	
	/**跳转更新页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/goUpdateClass.do")
	public String goUpdateClass(Model model,Integer id) {
		//保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
		ClassInfo cla = classInfoService.selectByPrimaryKey(id);
		model.addAttribute("cla", cla);
		return "ad_class_edit";
	}
	
	
	/**更新班级信息(记得提交班级id)
	 * @param model
	 * @param cla
	 * @return
	 */
	@RequestMapping("/updateClass.do")
	public String updateClass(Model model,ClassInfo cla) {
		try {
			classInfoService.updateByPrimaryKeySelective(cla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:classAdmin.do?claId="+cla.getClassId();
	}
	
	
	
	/**删除班级（）
	 * @param model
	 * @param id
	 * @param idStr
	 * @return
	 */
	public String deleteClass(Model model,Integer id ,String idStr) {
		
		return "";
	}
	
}
