/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.mapper.TeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.TeacherInfoService;

/**
 *<p> Title:  TeacherInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月8日上午10:01:04
 * @version 版本号
 */
@Service
@Transactional
@SuppressWarnings("all")
public class TeacherInfoServiceImpl implements TeacherInfoService {
	
	@Resource
	public TeacherInfoMapper teacherInfoMapper;
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#selectByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<TeacherInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception {
			 List<TeacherInfo> teaList = teacherInfoMapper.selectByNameList(beanQueryVo);//既有模糊，又有状态
		    return teaList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.TeacherInfo)
	 */
	@Override
	public int insertSelective(TeacherInfo tea) throws Exception {
		Date date = new Date();
		tea.setCreatedTime(date);
		tea.setModifiedTime(date);
		tea.setCreatedUser("admin");
		tea.setModifiedUser("admin");
		tea.setRoleId(2);
		return teacherInfoMapper.insertSelective(tea);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public TeacherInfo selectByPrimaryKey(Integer teacherId) {
		return teacherInfoMapper.selectByPrimaryKey(teacherId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.TeacherInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(TeacherInfo tea) {
		tea.setModifiedTime(new Date());
		tea.setModifiedUser("admin");
		return teacherInfoMapper.updateByPrimaryKeySelective(tea);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#selectByAccountList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public TeacherInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception {
		return teacherInfoMapper.selectByAccountList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#deleteList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public int deleteList(BeanQueryVo beanQueryVo) throws Exception {
		return teacherInfoMapper.deleteList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.TeacherInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer teacherId) throws Exception {
		// TODO Auto-generated method stub
		return teacherInfoMapper.deleteByPrimaryKey(teacherId);
	}

}
