/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfoCustom;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ProfessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.ClassInfoService;

/**
 *<p> Title:  ClassInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月9日下午3:43:55
 * @version 版本号
 */
@Service
@Transactional
public class ClassInfoServiceImpl implements ClassInfoService {
	
	@Resource
	public ClassInfoMapper classInfoMapper;
	
	@Resource
	public ProfessionInfoMapper professionMapper;
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#selectByProfessionList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ClassInfo> selectByProfessionList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return classInfoMapper.selectByProfessionList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#selectByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ClassInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return classInfoMapper.selectByNameList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer classId) {
		return classInfoMapper.deleteByPrimaryKey(classId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#insert(cn.jx.pxc.colcurevamansystem.bean.ClassInfo)
	 */
	@Override
	public int insert(ClassInfo record) {
		return classInfoMapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.ClassInfo)
	 */
	@Override
	public int insertSelective(ClassInfo cla) {
		Date date = new Date();
		cla.setCreatedTime(date);
		cla.setCreatedUser("admin");
		cla.setModifiedTime(date);
		cla.setModifiedUser("admin");
		return classInfoMapper.insertSelective(cla);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public ClassInfo selectByPrimaryKey(Integer classId) {
		return classInfoMapper.selectByPrimaryKey(classId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.ClassInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(ClassInfo record) {
		return classInfoMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#updateByPrimaryKey(cn.jx.pxc.colcurevamansystem.bean.ClassInfo)
	 */
	@Override
	public int updateByPrimaryKey(ClassInfo record) {
		return classInfoMapper.updateByPrimaryKey(record);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#selectClaCusByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ClassInfoCustom> selectClaCusByNameList(BeanQueryVo beanQueryVo) throws Exception {
		List<ClassInfo>  claList = classInfoMapper.selectByNameList(beanQueryVo);
		List<ClassInfoCustom> claCuList = new ArrayList<ClassInfoCustom>();
		for (ClassInfo classInfo : claList) {
			//if(classInfo.getClassName() != null) {
				ClassInfoCustom claCus = new ClassInfoCustom();
				BeanUtils.copyProperties(claCus, classInfo);
				ProfessionInfo pro = professionMapper.selectByPrimaryKey(classInfo.getProfessionId());
				claCus.setProfessionName(pro.getProfessionName());
				claCuList.add(claCus);
			//}
		}
		return claCuList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassInfoService#selectById(java.lang.Integer)
	 */
	@Override
	public ClassInfoCustom selectById(Integer classId) {
		ClassInfo cla =  classInfoMapper.selectByPrimaryKey(classId);
		ClassInfoCustom claCu = new ClassInfoCustom();
		try {
			BeanUtils.copyProperties(claCu,cla );
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ProfessionInfo pro = professionMapper.selectByPrimaryKey(cla.getProfessionId());
		claCu.setProfessionName(pro.getProfessionName());
		return claCu;
	}

}
