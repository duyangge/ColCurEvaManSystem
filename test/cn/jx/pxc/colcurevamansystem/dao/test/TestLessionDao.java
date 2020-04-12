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
import cn.jx.pxc.colcurevamansystem.bean.LessionInfo;
import cn.jx.pxc.colcurevamansystem.bean.LessionTeacherInfo;
import cn.jx.pxc.colcurevamansystem.bean.TeacherInfo;
import cn.jx.pxc.colcurevamansystem.mapper.ClassInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.LessionTeacherInfoMapper;
import cn.jx.pxc.colcurevamansystem.mapper.TeacherInfoMapper;

/**
 *<p> Title:  TestLessionDao.java</p>
 *<p> Description:  课程代理</p>
 * @package   cn.jx.pxc.colcurevamansystem.dao
 * @author    23801
 * @date      2020年4月5日下午1:38:47
 * @version 版本号
 */
@SuppressWarnings("all")
public class TestLessionDao {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml");
	public static LessionInfoMapper lessionInfoMapper = con.getBean(LessionInfoMapper.class);
	public static TeacherInfoMapper teacherInfoMapper = con.getBean(TeacherInfoMapper.class);
	public static LessionTeacherInfoMapper lessionTeacherInfoMapper = con.getBean(LessionTeacherInfoMapper.class);
	public static ClassInfoMapper classInfoMapper = con.getBean(ClassInfoMapper.class);
	/**
	 * 查询课程
	 */
	@Test
	public void select() {
		try {			
			//查询课程
			BeanQueryVo bqv = new BeanQueryVo();
			
			/*bqv.setKeyWords("导论");
			List<LessionInfo> leslist = lessionInfoMapper.selectByNameList(bqv);
			
			for (LessionInfo lessionInfo : leslist) {
				System.out.println(lessionInfo.toString());
			}
		LessionInfo les = lessionInfoMapper.selectByPrimaryKey(1);
		System.out.println(les.toString());*/
	    /*查询这么课程被那些老师教（）
			 * 教师：班级+课程
			 * 课程：班级+教师
			 * */
			bqv.setLessionId(1);
			List<LessionTeacherInfo>  ltlist = lessionTeacherInfoMapper.selectByLessionList(bqv);
			for (LessionTeacherInfo lessionTeacherInfo : ltlist) {
				ClassInfo cla = classInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getClassId());
				TeacherInfo tea = teacherInfoMapper.selectByPrimaryKey(lessionTeacherInfo.getTeacherId());
				System.out.println("班级名称\t教师名称");
				System.out.println(cla.getClassName()+"\t"+tea.getUsername()+"老师");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 添加课程
	 */
	@Test
	public void add() {
		LessionInfo les = new LessionInfo();
		les.setLessionImg("images");;
		les.setLessionInfo("计算机");
		les.setLessionName("概率统计");
		les.setCreatedTime(new Date());
		les.setCreatedUser("admin");
	  try {
		lessionInfoMapper.insertSelective(les);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  /**
	   * Integer key =  lessionInfoMapper.insertSelective(les);
	   * 得到是影响的行数
	   * 需要从实体类les中获得les.getLessionId();
	  */
	   System.out.println(lessionInfoMapper.selectByPrimaryKey(les.getLessionId()).toString());
		
	}
	
	/**
	 * 更新课程信息
	 */
	@Test
	public void update() {
		LessionInfo les = new LessionInfo();
		les.setLessionId(17);
		les.setLessionImg("images");;
		les.setLessionInfo("计算机");
		les.setLessionName("jsp设计");
		les.setModifiedTime(new Date());
		les.setModifiedUser("admin");
		try {
			lessionInfoMapper.updateByPrimaryKeySelective(les);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(lessionInfoMapper.selectByPrimaryKey(les.getLessionId()));
	}
	
}
