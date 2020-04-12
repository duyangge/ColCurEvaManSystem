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
import org.springframework.web.bind.annotation.RequestParam;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;

/**
 *<p> Title:  ClassInfoController.java</p>
 *<p> Description:  描述</p>
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
	
	/**分页+模糊查询
	 * @param model
	 * @param param
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/classAdmin.do")
	public String classAdmin(Model model,@RequestParam(value="claId",required=false)Integer param,BeanQueryVo beanQueryVo) {
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
		model.addAttribute("claList", claList);
		return "admin_class";
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
        return "admin_class_add";
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
		return "admin_class_edit";
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
