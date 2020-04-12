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
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.LessionInfoService;

/**
 *<p> Title:  LessionInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月9日下午3:49:07
 * @version 版本号
 */
@Transactional
@Service
public class LessionInfoServiceImpl implements LessionInfoService {
	
	@Resource
	public LessionInfoMapper lessionInfoMapper;

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return lessionInfoMapper.selectByNameList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer lessionId) throws Exception {
		// TODO Auto-generated method stub
		return lessionInfoMapper.deleteByPrimaryKey(lessionId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#insert(cn.jx.pxc.colcurevamansystem.bean.LessionInfo)
	 */
	@Override
	public int insert(LessionInfo les) throws Exception {
		Date date = new Date();
		les.setCreatedTime(date);//创建时间
		les.setCreatedUser("admin");//创建人
		les.setModifiedTime(date);
		les.setModifiedUser("admin");
		return lessionInfoMapper.insert(les);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.LessionInfo)
	 */
	@Override
	public int insertSelective(LessionInfo les) throws Exception {
		Date date = new Date();
		les.setCreatedTime(date);//创建时间
		les.setCreatedUser("admin");//创建人
		les.setModifiedTime(date);
		les.setModifiedUser("admin");
		return lessionInfoMapper.insertSelective(les);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public LessionInfo selectByPrimaryKey(Integer lessionId) {
		// TODO Auto-generated method stub
		return lessionInfoMapper.selectByPrimaryKey(lessionId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.LessionInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(LessionInfo les) throws Exception {
		Date date = new Date();
		les.setModifiedTime(date);
		les.setModifiedUser("admin");
		return lessionInfoMapper.updateByPrimaryKeySelective(les);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#updateByPrimaryKey(cn.jx.pxc.colcurevamansystem.bean.LessionInfo)
	 */
	@Override
	public int updateByPrimaryKey(LessionInfo les) throws Exception {
		Date date = new Date();
		les.setModifiedTime(date);
		les.setModifiedUser("admin");
		return lessionInfoMapper.updateByPrimaryKey(les);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#deleteByIdList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public int deleteByIdList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return lessionInfoMapper.deleteByIdList(beanQueryVo);
	}

}
