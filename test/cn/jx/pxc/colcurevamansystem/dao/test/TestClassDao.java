/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassInfo;
import cn.jx.pxc.colcurevamansystem.bean.ClassLessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.StudentInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.ClassLessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.StudentInfoMapper;

/**
 *<p> Title:  TestClassDao.java</p>
 *<p> Description:  班级测试</p>
 * @package   cn.jx.pxc.colcurevamansystem.dao
 * @author    23801
 * @date      2020年4月5日下午12:31:46
 * @version 版本号
 * 
 */
@SuppressWarnings("all")
public class TestClassDao {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml");
	public static ClassInfoMapper classInfoMapper = con.getBean(ClassInfoMapper.class);
	public static LessionInfoMapper lessionInfoMapper = con.getBean(LessionInfoMapper.class);
	public static ClassLessionInfoMapper classLessionInfoMapper = con.getBean(ClassLessionInfoMapper.class);
	public static StudentInfoMapper studentInfoMapper = con.getBean(StudentInfoMapper.class);
	/**
	 * 班级查询
	 */
	@Test
	public void select() {
		//模糊查询
		BeanQueryVo userInfo = new BeanQueryVo();
		try {
			List<ClassInfo>  claList = classInfoMapper.selectByNameList(userInfo);
			for (ClassInfo classInfo : claList) {
				System.out.println(classInfo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*userInfo.setKeyWords("软工");
		try {
			List<ClassInfo> clist = classInfoMapper.selectByNameList(userInfo);
			for (ClassInfo classInfo : clist) {
				System.err.println(classInfo.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//主键查询
		/*ClassInfo cls = classInfoMapper.selectByPrimaryKey(1);
		System.err.println(cls.toString());
		
		userInfo.setClassId(1);
		try {
			//一个班级多少课程
			List<ClassLessionInfo> cllist = classLessionInfoMapper.selectByClassIdList(userInfo);//得到某个班级所有课程
			for (ClassLessionInfo classLessionInfo : cllist) {
				LessionInfo les = lessionInfoMapper.selectByPrimaryKey(classLessionInfo.getLessionId());
				System.err.println(les.toString());
			}
			//一个班级多少学生
			List<StudentInfo> slist = studentInfoMapper.selectByClassList(userInfo);
			for (StudentInfo studentInfo : slist) {
				System.err.println(studentInfo.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		
	}
	
	/**
	 * 添加班级
	 */
	@Test
	public void add() {
		ClassInfo cla = new ClassInfo();
		cla.setClassName("16信息技术");
		cla.setProfessionId(1);
		cla.setClassInfo("专科班");
		classInfoMapper.insertSelective(cla);
		System.err.println(classInfoMapper.selectByPrimaryKey(cla.getClassId()).toString());
	}
	/**
	 * 修改班级
	 */
	@Test
	public void update(){
		ClassInfo cla = classInfoMapper.selectByPrimaryKey(2);
		System.err.println(cla.toString());
		cla.setClassInfo("本科班1");
		cla.setModifiedTime(new Date());
		classInfoMapper.updateByPrimaryKey(cla);
		System.err.println(classInfoMapper.selectByPrimaryKey(2).toString());
	}
	
	
	
	
	

}
