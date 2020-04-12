/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;

/**
 *<p> Title:  LessionInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月10日下午4:40:17
 * @version 版本号
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/lession")
public class LessionInfoController {
	
	
	@Resource
	public LessionInfoService lessionInfoService;
	
	@Resource
	public ProfessionInfoService professionInfoService;
	
	/**分页+模糊查询
	 * @param model
	 * @param beanQueryVo
	 * @param param
	 * @return
	 */
	@RequestMapping("/lessionAdmin.do")
	public String lessionAdmin(Model model ,BeanQueryVo beanQueryVo ,@RequestParam(value="lesId",required=false)Integer param) {
		List<LessionInfo> lesList = null;
		if(param != null) {
			lesList = new ArrayList<LessionInfo>();
			LessionInfo les = lessionInfoService.selectByPrimaryKey(param);
			lesList.add(les);
		}else {
			try {
				lesList = lessionInfoService.selectByNameList(beanQueryVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("lesList", lesList);
		return "admin_lession";
	}
	
	
	/**跳转添加课程页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/goAddLession.do")
	public String goAddLession(Model model) {
		//保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);
		return "admin_lession_add";
	}
	
	
	/**添加课程
	 * @param model
	 * @param les
	 * @return
	 */
	@RequestMapping("/addLession.do")
	public String addLession(Model model ,LessionInfo les ) {
		try {
			lessionInfoService.insertSelective(les);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:lessionAdmin.do?lesId="+les.getLessionId();
	}
	
	/**跳转更新页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goUpdateLession.do")
	public String goUpdateLession(Model model ,Integer id) {
		//保存每个学院
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<ProfessionInfo>  proList = professionInfoService.selectByName(beanQueryVo);
		model.addAttribute("proList", proList);	
		
		//保存课程信息
		LessionInfo les = lessionInfoService.selectByPrimaryKey(id);
		model.addAttribute("les", les);
		return "admin_lession_edit";
	}
	
	/**更新课程信息(提交课程id)
	 * @param model
	 * @param les
	 * @return
	 */
	@RequestMapping("/updateLession.do")
	public String updateLession(Model model ,LessionInfo les ) {
		try {
			lessionInfoService.updateByPrimaryKeySelective(les);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:lessionAdmin.do?lesId="+les.getLessionId();
	}
	
	
	/**删除课程
	 * @param model
	 * @param id
	 * @param idStr
	 * @return
	 */
	@RequestMapping("/deleteLession.do")
	public String deleteLession(Model model ,Integer id,String idStr ) {
		try {
			if(id == null) {
				String[]  strList = idStr.split(",");
				//将string转换为int数组
				int[] array = Arrays.asList(strList).stream().mapToInt(Integer::parseInt).toArray();
				BeanQueryVo beanQueryVo = new BeanQueryVo();
				beanQueryVo.setIds(array);
				lessionInfoService.deleteByIdList(beanQueryVo);//批量删除
			}else {
				lessionInfoService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return "redirect:lessionAdmin.do";
	}

}
