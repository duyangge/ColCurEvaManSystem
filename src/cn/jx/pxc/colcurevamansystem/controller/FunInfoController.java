/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.FunInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;
import cn.jx.pxc.colcurevamansystem.service.FunInfoService;
import cn.jx.pxc.colcurevamansystem.service.RoleInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *<p> Title:  FunInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月22日下午3:21:56
 * @version 版本号
 */
@Controller
@RequestMapping("/fun")
@SuppressWarnings("all")
public class FunInfoController {
	
	@Resource
	public FunInfoService funInfoService;
	
	@Resource
	public RoleInfoService roleInfoService;
	
	/**显示所有菜单
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/getFunAd.do")
	public String getFunAd(Model model , BeanQueryVo beanQueryVo,@RequestParam(value="param",required=false)String param) {
		if(param != null && !param.equals("")) {//在前台判断是否要刷新父页面
			model.addAttribute("roadParent", "roadParent");
		}
		if(beanQueryVo.getPageSize() == null) {//默认查询每页5
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去掉前后空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		try {
			List<FunInfoTemp> funList = funInfoService.selectByNameTemp(beanQueryVo);
			if(funList.size() > 0 ) {
				List<FunInfoTemp> listFunTempInfo = this.getPageContentByFun(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), funList);
				model.addAttribute("pageSize", beanQueryVo.getPageSize());
				model.addAttribute("funList", listFunTempInfo);//有一个字段是判断是一级菜单还是，二级菜单
			}
			model.addAttribute("keyWords", beanQueryVo.getKeyWords());//查询名称
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"ad_fun";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param model
	 * @param currentPage
	 * @param pageSize
	 * @param funTempInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<FunInfoTemp> getPageContentByFun(Model model, Integer currentPage, Integer pageSize ,List<FunInfoTemp> funTempInfoCustomListOld) throws Exception{
		ListPageUtil<FunInfoTemp> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<FunInfoTemp>(funTempInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<FunInfoTemp>(funTempInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<FunInfoTemp>(funTempInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<FunInfoTemp>(funTempInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<FunInfoTemp>(funTempInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	
	/**添加一级菜单页面：名称，地址，状态，图标代码，四个
	 * @return
	 */
	@RequestMapping("/goAddParentFunPage.do")
	public  String goAddParentFunPage(Model model,BeanQueryVo beanQueryVo) {
		//保存所有角色
		List<RoleInfo> roleList = roleInfoService.selectByName(beanQueryVo);
		model.addAttribute("roleList", roleList);
		return "ad_fun_parent_add";
	}
	
	/**添加二级菜单页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goAddSubFunPage.do")
	public  String goAddSubFunPage(Model model, BeanQueryVo beanQueryVo) {
		//保存所有角色
		List<RoleInfo> roleList = roleInfoService.selectByName(beanQueryVo);
		model.addAttribute("roleList", roleList);
		//通过选择的角色信息再来选择一级菜单

		return "ad_fun_sub_add";
	}
	
	/**自动刷新班级二级联动://每个班级(二级联动)
	 * 通过学院id查询所有班级id
	 * produces = {"application/json;charset=UTF-8"}//防止得到的页面json数据中文乱码
	 * @param model
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value="/changeRole.do",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String changeRole(Model model,Integer id,BeanQueryVo beanQueryVo) {
		 JSONArray array=new JSONArray();
		  try {
			  List<ParentFunInfo> funList = null;
				if(id == 1) {//管理员一级菜单
					funList = funInfoService.selelctByRoleId(id);
				}else if(id == 2) {//教师一级菜单
					funList = funInfoService.selelctByRoleId(id);
				}else if(id == 3){//学生一级菜单
					funList = funInfoService.selelctByRoleId(id);
				}else {
				}
			for (ParentFunInfo fun : funList) {
				JSONObject obj=new JSONObject();
				obj.put("funParentId",fun.getFunParentId());
				obj.put("funParentName",fun.getFunParentName());
				array.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  return array.toString();
	}
	
	/**添加一级菜单
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/addParentFun.do")
	public  String addParentFun(Model model, ParentFunInfo paFun,RoleFunInfo roleFunInfo,String tableName) {
		try {
			funInfoService.insertSelectiveByParent(paFun);//插入父id
			//插入第三张维护表
			roleFunInfo.setFunId(paFun.getFunParentId());//功能id
			roleFunInfo.setRoleFunStatus("0");//状态
			//提交添加角色id
			roleInfoService.addRoleFun(roleFunInfo);			
			ParentFunInfo fun = funInfoService.selectByPrimaryKeyByParent(paFun.getFunParentId());
			model.addAttribute("roadParent", "roadParent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getFunAd.do?param="+1;
		
	}
	
	/**删除菜单
	 * @param model
	 * @param idStr
	 * @param id
	 * @return
	 */
	@RequestMapping("/delParentFun.do")
	public String delParentFun(Model model,String idStr,Integer id) {
		try {
			if(id == null) {//批量删除
				String[]  strList = idStr.split(",");
				//将string转换为int数组
				int[] array = Arrays.asList(strList).stream().mapToInt(Integer::parseInt).toArray();
				BeanQueryVo beanQueryVo = new BeanQueryVo();
				beanQueryVo.setIds(array);
				//funInfoService.deleteStudentList(beanQueryVo);
			}else {//单个删除
					funInfoService.deleteByPrimaryKeyByParent(id);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getFunAd.do?param="+1;
	}
	
	/**删除菜单
	 * @param model
	 * @param idStr
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSubFun.do")
	public String delSubFun(Model model,String idStr,Integer id) {
		try {
			if(id == null) {//批量删除
				String[]  strList = idStr.split(",");
				//将string转换为int数组
				int[] array = Arrays.asList(strList).stream().mapToInt(Integer::parseInt).toArray();
				BeanQueryVo beanQueryVo = new BeanQueryVo();
				beanQueryVo.setIds(array);
				//funInfoService.deleteStudentList(beanQueryVo);
			}else {//单个删除
					funInfoService.deleteByPrimaryKeyBySub(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getFunAd.do?param="+1;
	}
	
	/**添加二级菜单
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/addSubFun.do")
	public  String addSubFun(Model model, SubFunInfo subFun) {
		//二级菜单所有
		try {
			funInfoService.insertSelectiveBySub(subFun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("roadParent", "roadParent");
		return "forward:getFunAd.do?param="+1;
	}
	
	
	/**编辑一级菜单页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goEditParentFunPage.do")
	public  String goEditParentFunPage(Model model, BeanQueryVo beanQueryVo) {
		//保存所有角色
		List<RoleInfo> roleList = roleInfoService.selectByName(beanQueryVo);
		model.addAttribute("roleList", roleList);
		try {
			ParentFunInfo  fun = funInfoService.selectByPrimaryKeyByParent(beanQueryVo.getId());
			//通过功能id查询角色id
			RoleFunInfo role = funInfoService.selectByFunId(fun.getFunParentId());
			model.addAttribute("parentFunRole", role.getRoleId());
			model.addAttribute("fun", fun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_fun_parent_edit";
		
	}
	
	/**编辑二级菜单页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goEditSubFunPage.do")
	public  String goEditSubFunPage(Model model, BeanQueryVo beanQueryVo) {
		try {
			//保存所有角色
			List<RoleInfo> roleList = roleInfoService.selectByName(beanQueryVo);
			model.addAttribute("roleList", roleList);
			SubFunInfo fun = funInfoService.selectByPrimaryKeyBySub(beanQueryVo.getId());
			//通过功能id查询角色id
			RoleFunInfo role = funInfoService.selectByFunId(fun.getFunParentId());
			model.addAttribute("parentFunRole", role.getRoleId());
			//model.addAttribute("funParentId", fun.getFunParentId());
			model.addAttribute("fun", fun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_fun_sub_edit";
		
	}
	
	
	/**编辑一级菜单
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping(value="/editParentFun.do")
	public  String editParentFun(Model model, ParentFunInfo paFun) {
		try {
			funInfoService.updateByPrimaryKeySelectiveByParent(paFun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getFunAd.do?param="+1;
		
	}
	
	/**编辑二级菜单
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/editSubFun.do")
	public  String editSubFun(Model model, SubFunInfo subFun) {
		try {
			funInfoService.updateByPrimaryKeySelectiveBySub(subFun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getFunAd.do?param="+1;
		
	}
	
	/**维护网页中
	 * @return
	 */
	@RequestMapping("/maintainPage.do")
	public String maintainPage() {
		return "maintain_page";
	}
	

}
