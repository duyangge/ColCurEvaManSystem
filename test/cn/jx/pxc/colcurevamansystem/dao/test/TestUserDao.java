/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionTeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.StudentInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.TeacherInfoMapper;

/**
 *<p> Title:  TestUserDao.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.dao
 * @author    23801
 * @date      2020年4月5日上午11:51:43
 * @version 版本号
 */
@SuppressWarnings("all")
public class TestUserDao {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml");
	public static StudentInfoMapper studentInfoMapper = con.getBean(StudentInfoMapper.class);
	public static TeacherInfoMapper teacherInfoMapper = con.getBean(TeacherInfoMapper.class);
	public static LessionTeacherInfoMapper lessionTeacherInfoMapper = con.getBean(LessionTeacherInfoMapper.class);
	public static ClassInfoMapper classInfoMapper = con.getBean(ClassInfoMapper.class);
	public static LessionInfoMapper lessionInfoMapper = con.getBean(LessionInfoMapper.class);
	/**
	 * 点击用户管理：
	 * 默认查询所有用户：（学生，管理员）
	 */
	@Test
	public void selectUser() {
	    BeanQueryVo userInfo = new BeanQueryVo();
	   
		try {
			//查询用户
			/*userInfo.setKeyWords("马");
			List<StudentInfo> slist = studentInfoMapper.selectByNameList(userInfo);
			List<TeacherInfo> tlist = teacherInfoMapper.selectByNameList(userInfo);
			System.err.println("\n---------------学生列表----------------");
			for (StudentInfo studentInfo : slist) {
				System.out.println("\n"+studentInfo.toString());
			}
			System.err.println("\n---------------教师列表----------------");
			for (TeacherInfo teacherInfo : tlist) {
				System.out.println("\n"+teacherInfo.toString());
			}*/
			/*查询这么课程被那些老师教（）
			 * 教师：班级+课程
			 * 课程：班级+教师
			 * */
			userInfo.setTeacherId(1);
			TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(1);
			List<LessionTeacherInfo>  ltlist = lessionTeacherInfoMapper.selectByTeacherList(userInfo);
			System.err.println(tea.getUsername()+"教师所教课程：");
			for (LessionTeacherInfo lessionTeacherInfo : ltlist) {
				ClassInfo  cla = classInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getClassId());
				LessionInfo  les = lessionInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getLessionId());
				System.out.println(cla.getClassName()+"\t"+les.getLessionName());
			}
			
			
			
		} catch (Exception e) {
			System.out.println("无此用户");
		}
		con.close();
	}
	
	/**
	 * 添加新用户
	 */
	@Test
	public void  addUser() {
		//添加学生11
		/*StudentInfo stu = new StudentInfo();
		stu.setAccount("16478001");
		stu.setClassId(1);
		stu.setHeadImage("image");
		stu.setMail("2380110794@qq.com");
		stu.setPassword("00000000");
		stu.setProfessionId(1);
		stu.setRoleId(3);
		stu.setTelphone("13177513521");
		stu.setUsername("山本未来");
		stu.setStatus("0");*/
		
		     //添加教师10
				TeacherInfo tea = new TeacherInfo();
				tea.setAccount("16478001");
				tea.setHeadImage("image");
				tea.setMail("2380110794@qq.com");
				tea.setPassword("00000000");
				tea.setProfessionId(1);
				tea.setRoleId(3);
				tea.setTelphone("13177513521");
				tea.setUsername("山本未来");
				tea.setStatus("0");
		try {
			//studentInfoMapper.insert(stu);
			teacherInfoMapper.insert(tea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	/**
	 * 修改用户信息
	 */
	@Test
	public void updateUser() {
		//基本信息（先查询，后修改u，在修改）状态
		 
/*		StudentInfo stu = studentInfoMapper.selectByPrimaryKey(1);
		System.err.println("------------------");
		System.err.println(stu.toString());
		stu.setTelphone("18726508490");
		stu.setUsername("马丹娜");
		stu.setStatus("0");
		try {
			studentInfoMapper.updateByPrimaryKey(stu);
			System.out.println("------------修改后------------");
			System.err.println(studentInfoMapper.selectByPrimaryKey(1).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		 
		 //教师
		 
		TeacherInfo tea =  teacherInfoMapper.selectByPrimaryKey(2) ;
		System.err.println(tea.toString());
		tea.setUsername("金正中");
		tea.setStatus("1");
		teacherInfoMapper.updateByPrimaryKey(tea);
		System.err.println(teacherInfoMapper.selectByPrimaryKey(2).toString());
		
	
		
	}
	
	
}
