/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.FunInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;
import cn.jx.pxc.colcurevamansystem.bean.SubFunInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ParentFunInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.RoleFunInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.RoleInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.SubFunInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.FunInfoService;

/**
 *<p> Title:  FunInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月22日下午3:25:32
 * @version 版本号
 */
@Service
@Transactional
@SuppressWarnings("all")
public class FunInfoServiceImpl implements FunInfoService {
	
	@Resource
	public ParentFunInfoMapper parentFunInfoMapper;
	
	@Resource
	public SubFunInfoMapper subFunInfoMapper;
	
	@Resource
	public RoleFunInfoMapper roleFunInfoMapper;
	
	@Resource
	public RoleInfoMapper roleInfoMapper;
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectByName(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ParentFunInfo> selectByName(BeanQueryVo beanQueryVo) throws Exception {
		return parentFunInfoMapper.selectByName(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#deleteByPrimaryKeyByParent(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKeyByParent(Integer funParentId) throws Exception {
		roleFunInfoMapper.deleteByFunId(funParentId);
		return parentFunInfoMapper.deleteByPrimaryKey(funParentId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#insertByParent(cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo)
	 */
	@Override
	public int insertByParent(ParentFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setCreatedTime(date);
		fun.setModifiedTime(date);
		fun.setCreatedUser("admin");
		fun.setModifiedUser("admin");
		return parentFunInfoMapper.insert(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#insertSelectiveByParent(cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo)
	 */
	@Override
	public int insertSelectiveByParent(ParentFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setCreatedTime(date);
		fun.setModifiedTime(date);
		fun.setCreatedUser("admin");
		fun.setModifiedUser("admin");
		return parentFunInfoMapper.insertSelective(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectByPrimaryKeyByParent(java.lang.Integer)
	 */
	@Override
	public ParentFunInfo selectByPrimaryKeyByParent(Integer funParentId) throws Exception {
		
		return parentFunInfoMapper.selectByPrimaryKey(funParentId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#updateByPrimaryKeySelectiveByParent(cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo)
	 */
	@Override
	public int updateByPrimaryKeySelectiveByParent(ParentFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setModifiedTime(date);
		fun.setModifiedUser("admin");
		return parentFunInfoMapper.updateByPrimaryKeySelective(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#updateByPrimaryKeyByParent(cn.jx.pxc.colcurevamansystem.bean.ParentFunInfo)
	 */
	@Override
	public int updateByPrimaryKeyByParent(ParentFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setModifiedTime(date);
		fun.setModifiedUser("admin");
		return parentFunInfoMapper.updateByPrimaryKey(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#deleteByPrimaryKeyBySub(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKeyBySub(Integer funId) throws Exception {
		return subFunInfoMapper.deleteByPrimaryKey(funId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#insertBySub(cn.jx.pxc.colcurevamansystem.bean.SubFunInfo)
	 */
	@Override
	public int insertBySub(SubFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setCreatedTime(date);
		fun.setModifiedTime(date);
		fun.setCreatedUser("admin");
		fun.setModifiedUser("admin");
		return subFunInfoMapper.insert(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#insertSelectiveBySub(cn.jx.pxc.colcurevamansystem.bean.SubFunInfo)
	 */
	@Override
	public int insertSelectiveBySub(SubFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setCreatedTime(date);
		fun.setModifiedTime(date);
		fun.setCreatedUser("admin");
		fun.setModifiedUser("admin");
		return subFunInfoMapper.insertSelective(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectByPrimaryKeyBySub(java.lang.Integer)
	 */
	@Override
	public SubFunInfo selectByPrimaryKeyBySub(Integer funId) throws Exception {
		return subFunInfoMapper.selectByPrimaryKey(funId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#updateByPrimaryKeySelectiveBySub(cn.jx.pxc.colcurevamansystem.bean.SubFunInfo)
	 */
	@Override
	public int updateByPrimaryKeySelectiveBySub(SubFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setModifiedTime(date);
		fun.setModifiedUser("admin");
		return subFunInfoMapper.updateByPrimaryKeySelective(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#updateByPrimaryKeyBySub(cn.jx.pxc.colcurevamansystem.bean.SubFunInfo)
	 */
	@Override
	public int updateByPrimaryKeyBySub(SubFunInfo fun) throws Exception {
		Date date = new Date();
		fun.setModifiedTime(date);
		fun.setModifiedUser("admin");
		return subFunInfoMapper.updateByPrimaryKey(fun);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectByNameTemp(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<FunInfoTemp> selectByNameTemp(BeanQueryVo beanQueryVo) throws Exception {
		List<FunInfoTemp>  funList = new ArrayList<FunInfoTemp>();
		//一级菜单
		List<ParentFunInfo>  paList = parentFunInfoMapper.selectByName(beanQueryVo);
		for (ParentFunInfo paFun : paList) {
			RoleFunInfo roleFun = roleFunInfoMapper.selectByFunId(paFun.getFunParentId());
			RoleInfo role = roleInfoMapper.selectByPrimaryKey(roleFun.getRoleId());
			FunInfoTemp fun = new FunInfoTemp();
			fun.setFunId(paFun.getFunParentId());
			fun.setModifiedTime(paFun.getModifiedTime());
			fun.setFunImg(paFun.getFunParentImg());
			fun.setFunName(paFun.getFunParentName());
			fun.setFunUrl(paFun.getFunParentUrl());
			fun.setFunStatus(paFun.getFunParentStatus());
			fun.setTableName("parentFun");
			fun.setRoleId(role.getRoleId());
			fun.setRoleName(role.getRoleName());
			funList.add(fun);
		}
		
		//二级菜单
		List<SubFunInfo> subList = subFunInfoMapper.selectSubFunByName(beanQueryVo);
		for (SubFunInfo subFun : subList) {
			RoleFunInfo roleFun = roleFunInfoMapper.selectByFunId(subFun.getFunParentId());
			RoleInfo role = roleInfoMapper.selectByPrimaryKey(roleFun.getRoleId());
			FunInfoTemp fun = new FunInfoTemp();
			fun.setFunId(subFun.getFunId());
			fun.setFunName(subFun.getFunName());
			fun.setFunImg("");
			fun.setFunUrl(subFun.getFunUrl());
			fun.setFunStatus(subFun.getFunStatus());
			fun.setTableName("subFun");
			if(subFun.getParentFunInfo() != null) {
				fun.setFunParentName(subFun.getParentFunInfo().getFunParentName());
			}else {
				fun.setFunParentName("无");
			}
			fun.setModifiedTime(subFun.getModifiedTime());
			fun.setRoleId(role.getRoleId());
			fun.setRoleName(role.getRoleName());
			funList.add(fun);
		}
		return funList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selelctByRoleId(java.lang.Integer)
	 */
	@Override
	public List<ParentFunInfo> selelctByRoleId(Integer roleId) {
		return parentFunInfoMapper.selelctByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectByFunId(java.lang.Integer)
	 */
	@Override
	public RoleFunInfo selectByFunId(Integer id) {
		return roleFunInfoMapper.selectByFunId(id);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#selectSubFunByParentFunId(java.lang.Integer)
	 */
	@Override
	public List<SubFunInfo> selectSubFunByParentFunId(Integer id) {
		return subFunInfoMapper.selectSubFunByParentFunId(id);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(RoleFunInfo roleFunInfo) {
		Date date = new Date();
		roleFunInfo.setModifiedTime(date);
		roleFunInfo.setModifiedUser("admin");
		return roleFunInfoMapper.updateByPrimaryKeySelective(roleFunInfo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.FunInfoService#loadMenuByRoleAndStatus(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ParentFunInfo> loadMenuByRoleAndStatus(BeanQueryVo beanQueryVo) {
		return parentFunInfoMapper.loadMenuByRoleAndStatus(beanQueryVo);
	}

}
