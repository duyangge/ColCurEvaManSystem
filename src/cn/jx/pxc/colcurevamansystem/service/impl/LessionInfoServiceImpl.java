/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassLessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfoTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.ProfessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ClassLessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionTeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ProfessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.StudentInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.TeacherInfoMapper;
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
@SuppressWarnings("all")
public class LessionInfoServiceImpl implements LessionInfoService {
	
	@Resource
	public LessionInfoMapper lessionInfoMapper;
	
	@Resource
	public ClassLessionInfoMapper classLessionInfoMapper;
		
	@Resource
	public ClassInfoMapper classInfoMapper;	
	
	@Resource
	public ProfessionInfoMapper professionInfoMapper;

	@Resource
	public TeacherInfoMapper teacherInfoMapper;  	
	
	@Resource
	public LessionTeacherInfoMapper lessionTeacherInfoMapper;
	
	@Resource
	public StudentInfoMapper studentInfoMapper;
	
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByNameList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionInfo> selectByNameList(BeanQueryVo beanQueryVo) throws Exception {
		return lessionInfoMapper.selectByNameList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer lessionId) throws Exception {
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
		return lessionInfoMapper.deleteByIdList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByClassList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionInfo> selectByClassList(BeanQueryVo beanQueryVo) throws Exception {
		List<LessionInfo> lesList = new ArrayList<LessionInfo>();
		//该班级查询所有课程
		List<ClassLessionInfo> claLesList =	classLessionInfoMapper.selectByClassIdList(beanQueryVo);
		for (ClassLessionInfo claLes : claLesList) {
			LessionInfo les = lessionInfoMapper.selectByPrimaryKey(claLes.getLessionId());
			lesList.add(les);
		}
		
		//从所有课程中选择具有关键字段的课程:利用迭代器删除对象(模糊查询)
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().trim().equals("")) {
			Iterator<LessionInfo> it = lesList.iterator();
			while(it.hasNext()) {
				LessionInfo lesInfo = it.next();
				if(lesInfo.getLessionName().trim().indexOf(beanQueryVo.getKeyWords()) == -1) {
					it.remove();
				}
			}
		}		
		return lesList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectLessionInfoTempByClassList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionInfoTemp> selectLessionInfoTempByClassList(BeanQueryVo beanQueryVo) throws Exception {
		//beanQueryVo 需要输入classId,professionId
		List<LessionInfoTemp> lesList = new ArrayList<LessionInfoTemp>();
		//该班级查询所有课程
		List<ClassLessionInfo> claLesList =	classLessionInfoMapper.selectByClassIdList(beanQueryVo);
		
		//班级id
		ClassInfo  cla = classInfoMapper.selectByPrimaryKey(beanQueryVo.getClassId());
		cla.getClassName();
		
		ProfessionInfo pro = professionInfoMapper.selectByPrimaryKey(beanQueryVo.getProfessionId());
		
		//课程id
		for (ClassLessionInfo claLes : claLesList) {
			LessionInfo les = lessionInfoMapper.selectByPrimaryKey(claLes.getLessionId());
			beanQueryVo.setLessionId(les.getLessionId());
			LessionTeacherInfo lesTea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);
			TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lesTea.getTeacherId());
			
			LessionInfoTemp lesTemp = new LessionInfoTemp();
			
			lesTemp.setTeacherId(tea.getTeacherId());
			lesTemp.setTeacherName(tea.getUsername());
			
			lesTemp.setLessionId(les.getLessionId());
			lesTemp.setLessionName(les.getLessionName());
			
			lesTemp.setProfessionId(pro.getProfessionId());
			lesTemp.setProfessionName(pro.getProfessionName());
			
			lesTemp.setClassId(cla.getClassId());
			lesTemp.setClassName(cla.getClassName());
			lesList.add(lesTemp);
			
		}
		
		//从所有课程中选择具有关键字段的课程:利用迭代器删除对象(模糊查询)
		if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().trim().equals("")) {
			Iterator<LessionInfoTemp> it = lesList.iterator();
			while(it.hasNext()) {
				LessionInfoTemp lesInfo = it.next();
				if(lesInfo.getLessionName().trim().indexOf(beanQueryVo.getKeyWords()) == -1) {
					it.remove();
				}
			}
		}		
		return lesList;

	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByIdLessionTemp(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public LessionInfoTemp selectByIdLessionTemp(BeanQueryVo beanQueryVo) throws Exception {
		//已知班级id，课程id，学生ID，求得教师id
		LessionInfoTemp lesTemp = new LessionInfoTemp();
		
		LessionTeacherInfo lesTea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);
		
		TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lesTea.getTeacherId());
		lesTemp.setTeacherId(tea.getTeacherId());
		lesTemp.setTeacherName(tea.getUsername());
		
		ClassInfo cla = classInfoMapper.selectByPrimaryKey(beanQueryVo.getClassId());
		lesTemp.setClassId(cla.getClassId());
		lesTemp.setClassName(cla.getClassName());
		
		LessionInfo les = lessionInfoMapper.selectByPrimaryKey(beanQueryVo.getLessionId());
		lesTemp.setLessionId(les.getLessionId());
		lesTemp.setLessionName(les.getLessionName());
		
		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(beanQueryVo.getStudentId());
		lesTemp.setStudentId(stu.getStudentId());
		lesTemp.setStudentName(stu.getUsername());
		
		return lesTemp;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByTeacher(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionInfoTemp> selectByTeacher(BeanQueryVo beanQueryVo) throws Exception {
		List<LessionInfoTemp> lesTempList  = new ArrayList<LessionInfoTemp>();
		List<LessionTeacherInfo>  lesTeaList = lessionTeacherInfoMapper.selectByTeacherList(beanQueryVo);
		for (LessionTeacherInfo lesTea : lesTeaList) {//查询所有教授课程信息
			LessionInfoTemp lesTemp = new LessionInfoTemp();
			ClassInfo cla = classInfoMapper.selectByPrimaryKey(lesTea.getClassId());//班级
			ProfessionInfo pro = professionInfoMapper.selectByPrimaryKey(cla.getProfessionId());//学院
			LessionInfo les = lessionInfoMapper.selectByPrimaryKey(lesTea.getLessionId());//课程
			TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(beanQueryVo.getTeacherId());//教师
			lesTemp.setProfessionId(pro.getProfessionId());
			lesTemp.setProfessionName(pro.getProfessionName());
			lesTemp.setClassId(cla.getClassId());
			lesTemp.setClassName(cla.getClassName());
			lesTemp.setLessionId(les.getLessionId());
			lesTemp.setLessionName(les.getLessionName());
			lesTempList.add(lesTemp);
		}
		//进行模糊查询操作
		String word = beanQueryVo.getKeyWords();
		if( word != null && !word.equals("")) {
			Iterator<LessionInfoTemp> it = lesTempList.iterator();
			while(it.hasNext()) {
				LessionInfoTemp lesTemp = it.next();
				if(lesTemp.getLessionName().indexOf(word) == -1) {
					it.remove();
				}
			}
		}
		
		return lesTempList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByLession(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public LessionInfoTemp selectByLession(BeanQueryVo beanQueryVo) throws Exception {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.LessionInfoService#selectByTeacherList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionTeacherInfo> selectByTeacherList(BeanQueryVo beanQueryVo) throws Exception {
		return lessionTeacherInfoMapper.selectByTeacherList(beanQueryVo);
	}
	
	
	
	

}
