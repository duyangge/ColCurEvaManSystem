/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo;
import cn.jx.pxc.colcurevamansystem.bean.RoleInfo;
import cn.jx.pxc.colcurevamansystem.mapper.RoleFunInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.RoleInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.RoleInfoService;

/**
 *<p> Title:  RoleInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月22日下午3:25:03
 * @version 版本号
 */
@Service
@Transactional
@SuppressWarnings("all")
public class RoleInfoServiceImpl implements RoleInfoService {
	
	@Resource
	public RoleInfoMapper roleInfoMapper;
	
	@Resource
	public RoleFunInfoMapper roleFunInfoMapper;
	
	

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer roleId) {
		try {
			roleFunInfoMapper.deleteByFunId(roleId);//先删除维护的第三张表
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleInfoMapper.deleteByPrimaryKey(roleId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#insert(cn.jx.pxc.colcurevamansystem.bean.RoleInfo)
	 */
	@Override
	public int insert(RoleInfo role) {
		Date date = new Date();
		role.setModifiedTime(date);
		role.setModifiedUser("admin");
		role.setCreatedTime(date);
		role.setCreatedUser("admin");
		roleInfoMapper.insertSelective(role);
		return role.getRoleId();
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.RoleInfo)
	 */
	@Override
	public int insertSelective(RoleInfo role) {
		Date date = new Date();
		role.setModifiedTime(date);
		role.setModifiedUser("admin");
		role.setCreatedTime(date);
		role.setCreatedUser("admin");
		roleInfoMapper.insertSelective(role);
		return role.getRoleId();
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public RoleInfo selectByPrimaryKey(Integer roleId) {
		// TODO Auto-generated method stub
		return roleInfoMapper.selectByPrimaryKey(roleId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.RoleInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(RoleInfo role) {
		Date date = new Date();
		role.setModifiedTime(date);
		role.setModifiedUser("admin");
		roleInfoMapper.updateByPrimaryKeySelective(role);
		return role.getRoleId();
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#updateByPrimaryKey(cn.jx.pxc.colcurevamansystem.bean.RoleInfo)
	 */
	@Override
	public int updateByPrimaryKey(RoleInfo role) {
		Date date = new Date();
		role.setModifiedTime(date);
		role.setModifiedUser("admin");
		roleInfoMapper.updateByPrimaryKey(role);
		return role.getRoleId();
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#selectByName(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<RoleInfo> selectByName(BeanQueryVo beanQueryVo) {
		// TODO Auto-generated method stub
		return roleInfoMapper.selectByName(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.RoleInfoService#addRoleFun(cn.jx.pxc.colcurevamansystem.bean.RoleFunInfo)
	 */
	@Override
	public int addRoleFun(RoleFunInfo roleFunInfo) {
		Date date = new Date();
		roleFunInfo.setCreatedTime(date);
		roleFunInfo.setModifiedTime(date);
		roleFunInfo.setCreatedUser("admin");
		roleFunInfo.setModifiedUser("admin");
		return roleFunInfoMapper.insertSelective(roleFunInfo);
	}

}
