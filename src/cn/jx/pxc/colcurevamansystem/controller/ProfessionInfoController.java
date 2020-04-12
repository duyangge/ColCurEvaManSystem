/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;

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
	
	/**分页+模糊查询
	 * @param beanQueryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/professionAdmin.do")
	public String professionAdmin(BeanQueryVo beanQueryVo,Model model,@RequestParam(value="proId",required=false)Integer param) {
		List<ProfessionInfo>  proList = null;
		if(param != null) {//添加或修改后自动查询记录
			proList = new ArrayList<ProfessionInfo>();
			ProfessionInfo pro = professionInfoService.selectByPrimaryKey(param);
			proList.add(pro);
		}else {
			 proList = professionInfoService.selectByName(beanQueryVo);
		}
		model.addAttribute("proList", proList);
		return "admin_profession";
	}
	
	/**跳转添加页面
	 * @return
	 */
	@RequestMapping("/goAddProfession.do")
	public String goAddProfession() {
		return "admin_profession_add";
		
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
		return "admin_profession_edit";
		
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
