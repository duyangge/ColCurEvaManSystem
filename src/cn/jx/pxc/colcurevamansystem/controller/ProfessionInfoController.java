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
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;

/**
 *<p> Title:  ProfessionInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月10日下午4:41:00
 * @version 版本号
 */
@Controller
@RequestMapping("/profession")
@SuppressWarnings("all")
public class ProfessionInfoController {
   
	@Resource
	public ProfessionInfoService professionInfoService;
	
	/**导出学院信息
     * @param request
     * @param response
     * @param beanQueryVo
     */
    @RequestMapping(value = "/exportReportStaticsData.do", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportReportStaticsData( HttpServletRequest request, HttpServletResponse response,BeanQueryVo beanQueryVo) {
        String mether =request.getMethod();
        //获取查询数据，在service层实现
        List<ProfessionInfo>  list = professionInfoService.selectByName(beanQueryVo);
        
        String tableName = "学院信息表";
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
        
        cell.setCellValue("学院名称");
        cell.setCellStyle(style);
        cell = row1.createCell(1);
        
        cell.setCellValue("备注信息");
        cell.setCellStyle(style);
        cell = row1.createCell(2);
        
 

 
        for (int i = 0, imax = list.size(); i < imax; i++) {
            row1 = sheet.createRow(i + 3);
            if (list.get(i).getProfessionName() == null || "".equals(list.get(i).getProfessionName())) {//班级名称
                row1.createCell(0).setCellValue("-");
            } else {
                row1.createCell(0).setCellValue(list.get(i).getProfessionName());
            }
            if (list.get(i).getProfessionInfo() == null || "".equals(list.get(i).getProfessionInfo())) {//课程名称
                row1.createCell(1).setCellValue("-");
            } else {
                row1.createCell(1).setCellValue(list.get(i).getProfessionInfo());
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
	

	/**分页+模糊查询
	 * @param beanQueryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/professionAdmin.do")
	public String professionAdmin(BeanQueryVo beanQueryVo,Model model,@RequestParam(value="proId",required=false)Integer param) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		List<ProfessionInfo>  proList = null;
		if(param != null) {//添加或修改后自动查询记录
			proList = new ArrayList<ProfessionInfo>();
			ProfessionInfo pro = professionInfoService.selectByPrimaryKey(param);
			proList.add(pro);
		}else {
			 proList = professionInfoService.selectByName(beanQueryVo);
		}
		if(proList.size() > 0) {//防止查询数据为空，报异常
            List<ProfessionInfo> userInfoCustomList;
			try {
				userInfoCustomList = this.getPageContentByProfession(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), proList);
				
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				
				model.addAttribute("proList", userInfoCustomList);//得到分页内容
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "ad_profession";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<ProfessionInfo> getPageContentByProfession(Model model, Integer currentPage, Integer pageSize ,List<ProfessionInfo> userInfoCustomListOld) throws Exception{
		ListPageUtil<ProfessionInfo> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<ProfessionInfo>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<ProfessionInfo>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<ProfessionInfo>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<ProfessionInfo>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<ProfessionInfo>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	
	
	/**跳转添加页面
	 * @return
	 */
	@RequestMapping("/goAddProfession.do")
	public String goAddProfession() {
		return "ad_profession_add";
		
	}
	
	/**提交学院信息
	 * @param pro
	 * @return
	 */
	@RequestMapping(value="/addProfession.do",method=RequestMethod.POST)
	public String addProfession(ProfessionInfo pro) {
		try {
			professionInfoService.insertSelective(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:professionAdmin.do?proId="+pro.getProfessionId();
	}
	
	/**跳转更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/goUpdateProfession.do")
	public String goUpdateProfession(Integer id, Model model) {
		ProfessionInfo pro = professionInfoService.selectByPrimaryKey(id);
		model.addAttribute("pro", pro);
		return "ad_profession_edit";
		
	}
	
	/**提交更新信息
	 * @param pro
	 * @return
	 */
	@RequestMapping(value="/updateProfession.do",method=RequestMethod.POST)
	public String updateProfession(ProfessionInfo pro) {
		try {
			professionInfoService.updateByPrimaryKeySelective(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:professionAdmin.do?proId="+pro.getProfessionId();
	}
	

	
	
}
