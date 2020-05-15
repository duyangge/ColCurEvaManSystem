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
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;
import cn.jx.pxc.colcurevamansystem.service.FunInfoService;
import cn.jx.pxc.colcurevamansystem.service.RoleInfoService;
import cn.jx.pxc.colcurevamansystem.utils.ListPageUtil;

/**
 *<p> Title:  RoleInfoController.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.controller
 * @author    23801
 * @date      2020年4月22日下午3:20:51
 * @version 版本号
 */
@Controller
@RequestMapping("/role")
@SuppressWarnings("all")
public class RoleInfoController {
	
	@Resource
	public RoleInfoService roleInfoService;
	
	@Resource
	public FunInfoService funInfoService;
	
	
	/**跳转添加角色页面
	 * @return
	 */
	@RequestMapping("/goAddRolePage.do")
	public String goAddRolePage() {
		return "ad_role_add";
	}
	
	/**添加角色
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping("/addRole.do")
	public String addRole(Model model,RoleInfo role){
		try {
			//默认插入一级二级所有，状态全为1
			
			roleInfoService.insertSelective(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:getRoleAd.do?id="+role.getRoleId();
	}
	
	/**跳转编辑普通管理员权限
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	public String goEditPublicAdminFunPage(Model model,BeanQueryVo beanQueryVo) {
		List<ParentFunInfo> parentFunInfoList = null;
		try {
			beanQueryVo.setId(1);
			parentFunInfoList = funInfoService.selectByName(beanQueryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("parentFunInfoList", parentFunInfoList);//该角色所有一级菜单及其二级菜单
		return "ad_fun_role_edit";
	}
	
	public String editPublicAdminFun() {
		
		return "";
	}
	
	/**模糊+分页+状态查询
	 * @param beanQueryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/getRoleAd.do")
	public String getRoleAdBySelect(BeanQueryVo beanQueryVo , Model model, @RequestParam(value="id",required=false)Integer id) {
		if(beanQueryVo.getPageSize() == null) {//默认显示第几页
			beanQueryVo.setPageSize(5);
		}
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().equals("") ) {//去点空格
			beanQueryVo.setKeyWords(beanQueryVo.getKeyWords().trim());
		}
		List<RoleInfo> roleList = null;
		if(id != null) {//修改后
			try {
				RoleInfo role = roleInfoService.selectByPrimaryKey(id);
				roleList = new ArrayList<RoleInfo>();
				roleList.add(role);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			 roleList = roleInfoService.selectByName(beanQueryVo);
		}
		if(roleList.size() > 0) {//防止查询数据为空，报异常
			try {
				List<RoleInfo> userInfoCustomList  = this.getPageContentByRole(model, beanQueryVo.getCurrentPage(), beanQueryVo.getPageSize(), roleList);
				model.addAttribute("pageSize", beanQueryVo.getPageSize());//每页显示数
				model.addAttribute("roleList", userInfoCustomList);//得到分页内容
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("keyWords", beanQueryVo.getKeyWords());//数据回显
		return "ad_role";
	}
	
	/**分页显示：默认显示第一页内容
	 * @param currentPage
	 * @param pageSize
	 * @param userInfoCustomListOld
	 * @return
	 * @throws Exception
	 */
	public List<RoleInfo> getPageContentByRole(Model model, Integer currentPage, Integer pageSize ,List<RoleInfo> userInfoCustomListOld) throws Exception{
		ListPageUtil<RoleInfo> list = null;
		if(currentPage != null) {//当前页不为空
			list = new ListPageUtil<RoleInfo>(userInfoCustomListOld, currentPage, pageSize);
			if(currentPage >=list.getTotalPage()) {
				list = new ListPageUtil<RoleInfo>(userInfoCustomListOld, list.getTotalPage(), pageSize);
			}
			if(currentPage <=0) {
				list = new ListPageUtil<RoleInfo>(userInfoCustomListOld, 1, pageSize);
			}else {
				list = new ListPageUtil<RoleInfo>(userInfoCustomListOld, list.getCurrentPage(), pageSize);
			}
			model.addAttribute("currentPage",list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}else{//当前页为空,默认是第一页
			list = new ListPageUtil<RoleInfo>(userInfoCustomListOld, 1, pageSize);
			model.addAttribute("currentPage", list.getCurrentPage());
			model.addAttribute("totalPage", list.getTotalPage());
			return list.getData();
		}
		
	}
	
	/**跳转页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goEditRolePage.do")
	public String goEditRolePage(Model model, Integer  id) {
		try {
			RoleInfo role = roleInfoService.selectByPrimaryKey(id);
			model.addAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_role_edit";
	}
	
	
	/**编辑角色信息
	 * @param model
	 * @param roleInfo
	 * @return
	 */
	@RequestMapping("/editRole.do")
	public String editRole(Model model, RoleInfo roleInfo) {
		try {
			roleInfoService.updateByPrimaryKeySelective(roleInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:getRoleAd.do?id="+roleInfo.getRoleId();
	}
	
	/**跳转编辑角色权限页面
	 * @param model
	 * @param beanQueryVo
	 * @return
	 */
	@RequestMapping("/goEditRoleFunPage.do")
	public String goEditRoleFunPage(Model model, BeanQueryVo beanQueryVo) {
		//遍历所有父功能
	try {
		    List<ParentFunInfo> parentFunInfoList = null;
			if(beanQueryVo.getId() != 1 && beanQueryVo.getId() != 2 && beanQueryVo.getId() != 3 ) {//新增角色id
				 beanQueryVo.setId(1);
			}
		     //默认用户角色
			 parentFunInfoList = funInfoService.selectByName(beanQueryVo);
			model.addAttribute("roleId",beanQueryVo.getId());
			model.addAttribute("parentFunInfoList", parentFunInfoList);//该角色所有一级菜单及其二级菜单
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad_fun_role_edit";
	}
	
	/**编辑角色权限：仅仅修改一级，二级菜单中的状态
	 * @param model
	 * @param subIds
	 * @param parentIds
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/editRoleFun.do")
	public String editRoleFun(Model model, String subIds, String parentIds, Integer roleId) throws Exception {
		List<Integer> subIdsList = new ArrayList<Integer>();//二级菜单选中id数组；没选中，修改二级菜单状态为1
		List<Integer> parentIdsList = new ArrayList<Integer>();//一级菜单选中id数组；没选中，修改一级菜单状态为1
		String[] subIdsToString = subIds.split(",");//二级菜单id
		for (String subIdString : subIdsToString) {
			if(!subIdString.trim().equals("")) {
				Integer subId = Integer.valueOf(subIdString.trim());
				subIdsList.add(subId);
			}
		}
		String[] parentIdsToString = parentIds.split(",");//一级菜单id
		for (String parentIdString : parentIdsToString) {
			if(!parentIdString.trim().equals("")) {
				Integer parentId = Integer.valueOf(parentIdString.trim());
				parentIdsList.add(parentId);
			}
		}		
		
		
		//通过角色id来修改状态
		List<ParentFunInfo> paList = funInfoService.selelctByRoleId(roleId);
		
		for (ParentFunInfo pa : paList) {//该角色所有一级菜单
			boolean flagParent = true;
			for(Integer paId : parentIdsList) {//所有的父id
				if(paId == pa.getFunParentId()) {
					List<SubFunInfo>  subList = funInfoService.selectSubFunByParentFunId(pa.getFunParentId());
					for (SubFunInfo sub : subList) {//父id下所有子id
						if(sub.getFunId() != null) {
							boolean flagSub = true;
							for(Integer subId : subIdsList) {
								if(sub.getFunId() == subId) flagSub = false;//判断是否存在该id
							}
							RoleFunInfo roleFunInfo  = funInfoService.selectByFunId(pa.getFunParentId());
							if(flagSub) {
								 sub.setFunStatus("1");
								//编辑关联表
								roleFunInfo.setRoleFunStatus("1");
								
						     }else {
						    	 sub.setFunStatus("0");
								 roleFunInfo.setRoleFunStatus("0");
						     }
							funInfoService.updateByPrimaryKeySelectiveBySub(sub);
							funInfoService.updateByPrimaryKeySelective(roleFunInfo);
						}//sub.getFunId() != null
					}//for subList
					flagParent = false;
				} //paId == pa.getFunParentId()
			}
			if(flagParent) {//找不到：就去修改一级菜单状态
				pa.setFunParentStatus("1");
			}else {
				pa.setFunParentStatus("0");
			}
			funInfoService.updateByPrimaryKeySelectiveByParent(pa);
		}
		return "redirect:getRoleAd.do";
	}
}
