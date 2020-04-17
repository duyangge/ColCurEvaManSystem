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
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ClassSubInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionTeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.StudentInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.TeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;

/**
 *<p> Title:  ClassSubInfoServiceImpl.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.impl
 * @author    23801
 * @date      2020年4月10日下午9:40:27
 * @version 版本号
 */
@Service
@Transactional
@SuppressWarnings("all")
public class ClassSubInfoServiceImpl implements ClassSubInfoService {
	
	@Resource
	public ClassSubInfoMapper classSubInfoMapper;
	
	@Resource
	public LessionInfoMapper lessionInfoMapper;
	
	@Resource
	public TeacherInfoMapper teacherInfoMapper;
	
	@Resource
	public ClassInfoMapper classInfoMapper;
	
	@Resource
	public LessionTeacherInfoMapper lessionTeacherInfoMapper;
	
	@Resource
	public StudentInfoMapper studentInfoMapper;
	
	
	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectByLessionList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ClassSubInfo> selectByLessionList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return classSubInfoMapper.selectByAllList();
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectByLessionAndStudentList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public ClassSubInfo selectByLessionAndStudentList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return classSubInfoMapper.selectByLessionAndStudentList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectByStudentList(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<ClassSubInfo> selectByStudentList(BeanQueryVo beanQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return classSubInfoMapper.selectByStudentList(beanQueryVo);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer subEvaId) {
		// TODO Auto-generated method stub
		return classSubInfoMapper.deleteByPrimaryKey(subEvaId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#insert(cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo)
	 */
	@Override
	public int insert(ClassSubInfo claSub) {
		// TODO Auto-generated method stub
		return classSubInfoMapper.insert(claSub);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#insertSelective(cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo)
	 */
	@Override
	public int insertSelective(ClassSubInfo claSub) {
		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(claSub.getSubStudentId());
		Date date = new Date();
		claSub.setCreatedTime(date);
		claSub.setCreatedUser(stu.getUsername());
		claSub.setModifiedTime(date);
		claSub.setModifiedUser(stu.getUsername());
		return classSubInfoMapper.insertSelective(claSub);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public ClassSubInfo selectByPrimaryKey(Integer subEvaId) {
		return classSubInfoMapper.selectByPrimaryKey(subEvaId);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#updateByPrimaryKeySelective(cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(ClassSubInfo claSub) {
		// TODO Auto-generated method stub
		return classSubInfoMapper.updateByPrimaryKeySelective(claSub);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#updateByPrimaryKey(cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo)
	 */
	@Override
	public int updateByPrimaryKey(ClassSubInfo claSub) {
		// TODO Auto-generated method stub
		return classSubInfoMapper.updateByPrimaryKey(claSub);
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectLessionEva(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionEvaTemp> selectLessionEva(BeanQueryVo beanQueryVo) {
		List<LessionEvaTemp> lesEvaList = new ArrayList<LessionEvaTemp>();
		List<ClassSubInfo> claSubList = null;
		try {
			if(beanQueryVo.getStudentId() != null) {//通过学生ID查询
				
				
					 String keyWords =beanQueryVo.getKeyWords();
					 claSubList = classSubInfoMapper.selectByStudentList(beanQueryVo);
					 
					 if( keyWords != null && !keyWords.equals("")) {//学生模糊查询
						 Iterator<ClassSubInfo> it = claSubList.iterator();
						 while(it.hasNext()) {
							 ClassSubInfo claSub = it.next();
							 LessionInfo les = lessionInfoMapper.selectByPrimaryKey(claSub.getSubLessionId());//由课程id得课程名称
							 TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(claSub.getSubTeacherId());
							 int ll = les.getLessionName().indexOf(keyWords);
							 int tt = tea.getUsername().indexOf(keyWords);
							 if(ll == -1 && tt == -1) {
								it.remove();
							}//
						 }
					}else {//学生默认查询所有
						claSubList = classSubInfoMapper.selectByStudentList(beanQueryVo);
				    }
				
				
			}else {//管理员查询所有(班级，教师，课程+状态)
				Integer cate = beanQueryVo.getCategory();
				String status = beanQueryVo.getStatus();
				if(status == null && cate == null) {//默认查询所有
					claSubList = classSubInfoMapper.selectByAllList();
					
				}else {
					if(status.equals("1")) {//正常
						if(cate == 1) {//班级
							
						}else if(cate == 2){//教师
							
						}else if(cate == 3){//课程
							
						}else{
							
						}
						
					}else if(status.equals("2")){//异常
						
						
					}else {//正常异常
						
						
					}
					
					
					
				}
				
				
				
				
				
			}
			
			//得到需要的字段存入临时类中显示
			for (ClassSubInfo claSub : claSubList) {
				List<StudentInfo>  stuList = claSub.getStudentInfoList();
				LessionInfo les = lessionInfoMapper.selectByPrimaryKey(claSub.getSubLessionId());//由课程id得课程名称
				StudentInfo stu = studentInfoMapper.selectByPrimaryKey(claSub.getSubStudentId());//由学生id得班级id
				beanQueryVo.setLessionId(claSub.getSubLessionId());
				beanQueryVo.setClassId(stu.getClassId());
				LessionTeacherInfo lestea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);//得到教师id
				//得到教师名称
				TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lestea.getTeacherId());
				//班级名称
				ClassInfo cla = classInfoMapper.selectByPrimaryKey(stu.getClassId());
				LessionEvaTemp lesEva = new LessionEvaTemp();
				lesEva.setId(claSub.getSubEvaId());//id
				lesEva.setCreatedTime(claSub.getCreatedTime());//评价时间
				lesEva.setSubUserName(claSub.getCreatedUser());//评价人
				lesEva.setTeacherName(tea.getUsername());//课程教师
				lesEva.setClassName(cla.getClassName());//班级名称
				lesEva.setLessionName(les.getLessionName());//课程名称
				lesEva.setScore(claSub.getSubScore());//课程评价分
				lesEva.setSubInfo(claSub.getSubInfo());//评价
				lesEva.setStatus(claSub.getSubStatus());
				lesEvaList.add(lesEva);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//需要获得：课程名称，班级名称，教师名称，
		return lesEvaList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectLessionEvaById(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public LessionEvaTemp selectLessionEvaById(BeanQueryVo beanQueryVo) {	
		LessionEvaTemp lesEva = new LessionEvaTemp();
		
		ClassSubInfo claSub = classSubInfoMapper.selectByPrimaryKey(beanQueryVo.getId());
		//LessionTeacherInfo lestea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);//得到教师id
		//得到教师名称
		TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(claSub.getSubTeacherId());
		//班级名称
		ClassInfo cla = classInfoMapper.selectByPrimaryKey(claSub.getSubClassId());
		//课程名称
		LessionInfo les  = lessionInfoMapper.selectByPrimaryKey(claSub.getSubLessionId());
		
		lesEva.setId(claSub.getSubEvaId());//id
		lesEva.setCreatedTime(claSub.getCreatedTime());//评价时间
		lesEva.setSubUserName(claSub.getCreatedUser());//评价人
		lesEva.setTeacherName(tea.getUsername());//课程教师
		lesEva.setClassName(cla.getClassName());//班级名称
		lesEva.setLessionName(les.getLessionName());//课程名称
		lesEva.setScore(claSub.getSubScore());//课程评价分
		lesEva.setSubInfo(claSub.getSubInfo());//评价
		
		return lesEva;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectByteacher(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public List<LessionEvaTemp> selectByteacher(BeanQueryVo beanQueryVo) throws Exception {
		List<LessionEvaTemp> lesEvaList = new ArrayList<LessionEvaTemp>();
		List<ClassSubInfo> claSubList = classSubInfoMapper.selectByteacher(beanQueryVo);//输入teacherid
		
		for (ClassSubInfo claSub: claSubList) {
			LessionEvaTemp lesEva = new LessionEvaTemp();
			
			//得到教师名称
			TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(claSub.getSubTeacherId());
			
			//班级名称
			ClassInfo cla = classInfoMapper.selectByPrimaryKey(claSub.getSubClassId());
			
			//课程名称
			LessionInfo les  = lessionInfoMapper.selectByPrimaryKey(claSub.getSubLessionId());
			
			lesEva.setId(claSub.getSubEvaId());//id
			lesEva.setCreatedTime(claSub.getCreatedTime());//评价时间
			lesEva.setSubUserName(claSub.getCreatedUser());//评价人
			lesEva.setTeacherName(tea.getUsername());//课程教师
			lesEva.setClassName(cla.getClassName());//班级名称
			lesEva.setLessionName(les.getLessionName());//课程名称
			lesEva.setScore(claSub.getSubScore());//课程评价分
			lesEva.setSubInfo(claSub.getSubInfo());//评价
			
			lesEvaList.add(lesEva);
			
		}
		//从所有课程中选择具有关键字段的课程:利用迭代器删除对象(模糊查询)
				if(beanQueryVo.getKeyWords() != null && !beanQueryVo.getKeyWords().trim().equals("")) {
					Iterator<LessionEvaTemp> it = lesEvaList.iterator();
					while(it.hasNext()) {
						LessionEvaTemp lesInfo = it.next();
						if(lesInfo.getLessionName().trim().indexOf(beanQueryVo.getKeyWords()) == -1) {
							it.remove();
						}
					}
				}		
		
		return lesEvaList;
	}

	/* (non-Javadoc)
	 * @see cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService#selectClassSubById(cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo)
	 */
	@Override
	public LessionEvaTemp selectClassSubById(BeanQueryVo beanQueryVo) throws Exception {
		       LessionEvaTemp lesEva = new LessionEvaTemp();
		       ClassSubInfo claSub = classSubInfoMapper.selectByPrimaryKey(beanQueryVo.getId());
		       //得到教师名称
				TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(claSub.getSubTeacherId());
				//班级名称
				ClassInfo cla = classInfoMapper.selectByPrimaryKey(claSub.getSubClassId());
				//课程名称
				LessionInfo les  = lessionInfoMapper.selectByPrimaryKey(claSub.getSubLessionId());
				
				lesEva.setId(claSub.getSubEvaId());//id
				lesEva.setCreatedTime(claSub.getCreatedTime());//评价时间
				lesEva.setSubUserName(claSub.getCreatedUser());//评价人
				lesEva.setTeacherName(tea.getUsername());//课程教师
				lesEva.setClassName(cla.getClassName());//班级名称
				lesEva.setLessionName(les.getLessionName());//课程名称
				lesEva.setScore(claSub.getSubScore());//课程评价分
				lesEva.setSubInfo(claSub.getSubInfo());//评价
				
				return lesEva;
	}

}
