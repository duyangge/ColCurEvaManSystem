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
