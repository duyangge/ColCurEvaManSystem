/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfoCustom;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ProfessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.StudentInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.StudentInfoService;

/**
 *<p> Title:  StudentInfoServiceImpl.java</p>
 *<p> Description:  学生操作服务层</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月8日上午10:00:28
 * @version 版本号
 */
@Service
@Transactional
@SuppressWarnings("all")
public class StudentInfoServiceImpl implements StudentInfoService {
	
	@Resource
	public StudentInfoMapper studentInfoMapper;
	
	@Resource
	public ClassInfoMapper classInfoMapper;
	
	@Resource
	public ProfessionInfoMapper professionInfoMapper;
	

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<StudentInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception {
		return studentInfoMapper.selectByNameList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectByClassList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<StudentInfoCustom> selectByClassList(BeanQueryVo beanQueryVo) throws Exception {
		List<StudentInfo> stuList = studentInfoMapper.selectByClassList(beanQueryVo);
		List<StudentInfoCustom> stuCusList = new ArrayList<StudentInfoCustom>();
		for (int i = 0; i < stuList.size(); i++) {  //容易出现空指针异常
	        StudentInfoCustom stuCu = new StudentInfoCustom(); 
	        BeanUtils.copyProperties(stuList.get(i), stuCu);
            ClassInfo cla = classInfoMapper.selectByPrimaryKey(stuList.get(i).getClassId());
    		ProfessionInfo pro = professionInfoMapper.selectByPrimaryKey(stuList.get(i).getProfessionId());
    		stuCu.setClassName(cla.getClassName());
    		stuCu.setProfessionName(pro.getProfessionName());;
    		stuCusList.add(stuCu);
}
		return stuCusList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public StudentInfo selectByPrimaryKey(Integer studentId) {
		return studentInfoMapper.selectByPrimaryKey(studentId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.StudentInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(StudentInfo record) throws Exception {
		record.setModifiedUser("admin");
		record.setModifiedTime(new Date());
		return studentInfoMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.StudentInfo)
	 */
	@Override
	public int insertSelective(StudentInfo studentInfo) throws Exception {
		Date date = new Date();
		studentInfo.setCreatedTime(date);//创建时间
		studentInfo.setCreatedUser("admin");//创建人
		studentInfo.setModifiedTime(date);
		studentInfo.setModifiedUser("admin");
		studentInfo.setRoleId(3);//角色
		return studentInfoMapper.insertSelective(studentInfo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectByAccountList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public StudentInfo selectByAccountList(BeanQueryVo beanQueryVo) throws Exception {
		return studentInfoMapper.selectByAccountList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#findStudentByName(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<StudentInfoCustom> findStudentByName(BeanQueryVo beanQueryVo) throws Exception {
		List<StudentInfo>  stuList = studentInfoMapper.selectByNameList(beanQueryVo);//模糊查询得到所有
		List<StudentInfoCustom> stuCuList = new ArrayList<StudentInfoCustom>();
		//复制类
		for (int i = 0; i < stuList.size(); i++) {  //容易出现空指针异常
			        StudentInfoCustom stuCu = new StudentInfoCustom(); 
			        BeanUtils.copyProperties(stuList.get(i), stuCu);
		            ClassInfo cla = classInfoMapper.selectByPrimaryKey(stuList.get(i).getClassId());
		    		ProfessionInfo pro = professionInfoMapper.selectByPrimaryKey(stuList.get(i).getProfessionId());
		    		stuCu.setClassName(cla.getClassName());
		    		stuCu.setProfessionName(pro.getProfessionName());;
		    		stuCuList.add(stuCu);
		}
		return stuCuList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectCustomByKey(java.lang.Integer)
	 */
	@Override
	public StudentInfoCustom selectCustomByKey(Integer studentId) {
		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(studentId);
		StudentInfoCustom stuCu = new StudentInfoCustom();
		BeanUtils.copyProperties(stu, stuCu);
		ClassInfo cla = classInfoMapper.selectByPrimaryKey(stu.getClassId());
   		ProfessionInfo pro = professionInfoMapper.selectByPrimaryKey(stu.getProfessionId());
   		stuCu.setClassName(cla.getClassName());
		stuCu.setProfessionName(pro.getProfessionName());; 
		return stuCu;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#deleteStudent(java.lang.Integer)
	 */
	@Override
	public int deleteStudent(Integer id) throws Exception {
		return studentInfoMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#deleteStudentList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public int deleteStudentList(BeanQueryVo beanQueryVo) throws Exception {
		return studentInfoMapper.deleteStudentList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.StudentInfoService#selectNumByClass(java.lang.Integer)
	 */
	@Override
	public int selectNumByClass(Integer classId) throws Exception {
		return studentInfoMapper.selectNumByClass(classId);
	}
	
	
	
	

}
