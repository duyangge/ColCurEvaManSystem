/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.dao.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfo;
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

/**
 *<p> Title:  TestLessionSubDao.java</p>
 *<p> Description:  课程评价</p>
 * @package   cn.jx.pxc.colcurevamansystem.dao
 * @author    23801
 * @date      2020年4月5日下午3:52:56
 * @version 版本号
 */
@SuppressWarnings("all")
public class TestLessionSubDao {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml");
	public static ClassSubInfoMapper classSubInfoMapper = con.getBean(ClassSubInfoMapper.class);
	public static StudentInfoMapper studentInfoMapper = con.getBean(StudentInfoMapper.class);
	public static TeacherInfoMapper teacherInfoMapper = con.getBean(TeacherInfoMapper.class);
	public static ClassInfoMapper classInfoMapper = con.getBean(ClassInfoMapper.class);
	public static LessionInfoMapper lessionInfoMapper = con.getBean(LessionInfoMapper.class);
	public static LessionTeacherInfoMapper lessionTeacherInfoMapper = con.getBean(LessionTeacherInfoMapper.class);
	
	

	
	
	/**
	 * 课程评价查询:
	 * 1.一个学生可以评价课程多次：一周评价一次
	 * 2.教师：通过所教课程id:查询学生评价（班级+课程）
	 * 3.学生：通过所在班级id查询所有课程和自己的 评价：
	 * 4.管理员：分类查询，通过班级，课程，教师，来查询课程评价
	 */
/*	Optional.ofNullable(personList).orElseGet(() -> {
	    System.out.println("personList为null！");
	    return new ArrayList<>();
	  }).stream().filter(Objects::nonNull).forEach(person -> {
	    System.out.println(person.getName());
	    System.out.println(person.getAge());
	  });*/
	@Test
	public void select() {
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		/*查询所有管理员
		 *分数
		 *得到班级id，的都班级名称，学生名称
		 *课程id+班级id确定教师id
		 * */
	/*	try {
			List<ClassSubInfo> cslist =	ClassSubInfoMapper.selectByLessionList(beanQueryVo);
			System.err.println("班级名称    课程名称    授课教师    得分    建议     评价人");
			for (ClassSubInfo classSubInfo : cslist) {
				
				LessionInfo les = lessionInfoMapper.selectByPrimaryKey(classSubInfo.getSubLessionId());//由课程id得课程名称
				StudentInfo stu = studentInfoMapper.selectByPrimaryKey(classSubInfo.getSubStudentId());//由学生id得班级id
				beanQueryVo.setLessionId(classSubInfo.getSubLessionId());
				beanQueryVo.setClassId(stu.getClassId());
				LessionTeacherInfo lestea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);//得到教师id
				//得到教师名称
				TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lestea.getTeacherId());
				//班级名称
				ClassInfo cla = classInfoMapper.selectByPrimaryKey(stu.getClassId());
				System.out.println(cla.getClassName()+"  "+les.getLessionName()+"  "+tea.getUsername()+" "
						+classSubInfo.getSubScore()+" "+classSubInfo.getSubInfo()+stu.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*通过教师id查询课程评价（安所教班分类）:
		 * 1.先通过教师id得到课程id和班级id
		 * 2.在通过班级id查询所有学生id，在
		 * 3.由学生id和课程id共同确定一条课程评价数据
		 * 4.最后循环遍历出来
		 * 
		 * */
		/*	//-------------------对list集合进行判空
		Optional.ofNullable(stuList).ifPresent(list -> {
			for (StudentInfo stu : list) {
				//对studentInfo进行操做
			}
		});*/
		//Boolean data = StringUtils.isEmpty(clasub);//工具包判空
		/*beanQueryVo.setTeacherId(1);//模拟教师1
		try {
			List<LessionTeacherInfo> cslist = lessionTeacherInfoMapper.selectByTeacherList(beanQueryVo);
			System.err.println("班级名称    课程名称    授课教师    得分    建议     评价人");
			for (LessionTeacherInfo lessionTeacherInfo : cslist) {//遍历某个老师所有课程评价
				beanQueryVo.setClassId(lessionTeacherInfo.getClassId());
				List<StudentInfo> stuList = studentInfoMapper.selectByClassList(beanQueryVo);//由学生id得班级id
				for (StudentInfo studentInfo : stuList) {//遍历同一班级所有学生
					beanQueryVo.setStudentId(studentInfo.getStudentId());//学生id
					beanQueryVo.setLessionId(lessionTeacherInfo.getLessionId());//课程id
					ClassSubInfo  clasub = ClassSubInfoMapper.selectByLessionAndStudentList(beanQueryVo);
					//班级名称
					ClassInfo cla = classInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getClassId());
					cla.getClassName();
					//课程名称
					LessionInfo les = lessionInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getLessionId());
					les.getLessionName();
					//授课教师
					TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(beanQueryVo.getTeacherId());
					if(clasub != null)
					System.out.println(cla.getClassName()+"  "+les.getLessionName()+"  "+tea.getUsername()+" "
							+clasub.getSubScore()+" "+clasub.getSubInfo()+" "+clasub.getCreatedUser());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		/*通过学生id查询课程评价
		 * 1.通过学生id查询其相关课程评价
		 * 2.
		 * **/
		beanQueryVo.setStudentId(1);//模拟学生1
		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(beanQueryVo.getStudentId());//由学生id得班级id
		ClassInfo cla = classInfoMapper.selectByPrimaryKey(stu.getClassId());
		beanQueryVo.setClassId(stu.getClassId());
		System.err.println("班级名称    课程名称    授课教师    得分    建议     评价人     时间");
		try {
			List<ClassSubInfo> cslist =	classSubInfoMapper.selectByStudentList(beanQueryVo);
			//判断list集合是否空
			Optional.ofNullable(cslist).orElseGet(() -> {
			    System.out.println("cslist为null！");
			    return new ArrayList<>();
			  }).stream().filter(Objects::nonNull).forEach(clasub -> {
					beanQueryVo.setLessionId(clasub.getSubLessionId());
					try {
						LessionTeacherInfo lestea = lessionTeacherInfoMapper.selectByLessionAndClass(beanQueryVo);
						TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lestea.getTeacherId());//为NULL
						LessionInfo les = lessionInfoMapper.selectByPrimaryKey(clasub.getSubLessionId());
						System.out.println(cla.getClassName()+"  "+les.getLessionName()+"  "+
								tea.getUsername()+"  "+clasub.getSubScore() +"  "+clasub.getSubInfo()+"  "+
								clasub.getCreatedUser()+" "+clasub.getCreatedTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
			  });
		} catch (Exception e) {
			e.printStackTrace();
		}//由学生id得班级id
		
	}
	/**
	 * 添加课程评价
	 * 1.默认选项：学生id，
	 */
	@Test
	public void add() {
		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(4);//模拟学生 
		ClassSubInfo clasub = new ClassSubInfo();
		clasub.setCreatedUser(stu.getUsername());
		clasub.setCreatedTime(new Date());
		clasub.setSubStudentId(4);
		clasub.setSubLessionId(2);
		clasub.setSubScore(7);
		clasub.setSubInfo("课堂气氛不活跃");
		clasub.setSubStatus(1);
		classSubInfoMapper.insertSelective(clasub);
		System.out.println("新添加-------------");
		clasub.getSubEvaId();
		System.out.println(classSubInfoMapper.selectByPrimaryKey(clasub.getSubEvaId()).toString());
	}
	

}
