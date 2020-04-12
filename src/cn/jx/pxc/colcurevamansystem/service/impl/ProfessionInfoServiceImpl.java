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
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ProfessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService;

/**
 *<p> Title:  ProfessionInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月9日下午3:43:15
 * @version 版本号
 */

@Service
@Transactional
@SuppressWarnings("all")
public class ProfessionInfoServiceImpl implements ProfessionInfoService {
	
	@Resource
	public ProfessionInfoMapper professionInfoMapper;
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#selectByName(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ProfessionInfo> selectByName(BeanQueryVo beanQueryVo) {
		
		return professionInfoMapper.selectByName(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer professionId) {
		// TODO Auto-generated method stub
		return professionInfoMapper.deleteByPrimaryKey(professionId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#insert(cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo)
	 */
	@Override
	public int insert(ProfessionInfo record) {
		// TODO Auto-generated method stub
		return professionInfoMapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo)
	 */
	@Override
	public int insertSelective(ProfessionInfo pro) {
		Date date = new Date();
		pro.setCreatedTime(date);
		pro.setCreatedUser("admin");
		pro.setModifiedTime(date);
		pro.setModifiedUser("admin");
		return professionInfoMapper.insertSelective(pro);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public ProfessionInfo selectByPrimaryKey(Integer professionId) {
		// TODO Auto-generated method stub
		return professionInfoMapper.selectByPrimaryKey(professionId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(ProfessionInfo pro) {
		pro.setModifiedTime(new Date());
		pro.setModifiedUser("admin");
		return professionInfoMapper.updateByPrimaryKeySelective(pro);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ProfessionInfoService#updateByPrimaryKey(cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo)
	 */
	@Override
	public int updateByPrimaryKey(ProfessionInfo record) {
		// TODO Auto-generated method stub
		return professionInfoMapper.updateByPrimaryKey(record);
	}

}
