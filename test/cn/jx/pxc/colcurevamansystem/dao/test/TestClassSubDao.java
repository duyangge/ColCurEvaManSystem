/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.ClassSubInfoCustom;
import cn.jx.pxc.colcurevamansystem.mapper.ClassSubInfoMapper;

/**
 *<p> Title:  TestClassSubDao.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.dao.test
 * @author    23801
 * @date      2020年4月30日下午2:42:41
 * @version 版本号
 */
@SuppressWarnings("all")
public class TestClassSubDao {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml");
	public static ClassSubInfoMapper classSubInfoMapper = con.getBean(ClassSubInfoMapper.class);
	
	
	/**
	 * 测试得到某个班级的某门课程的评价平均分
	 * 业务：
	 * 1.所有班级中所有课程的评价分
	 * 2.一个班级中所有课程评价分
	 * 3.一个班级中某一门课程平均分
	 */
	@Test
	public void getAvgScore() {
		BeanQueryVo bean = new BeanQueryVo();
		//bean.setClassId(1);
		//bean.setLessionId(2);
		bean.setStartTime("2020-04-01");
		bean.setEndTime("2020-04-30");
		List<ClassSubInfoCustom> claList  = classSubInfoMapper.findAvgScoreByClassIdAndLessionId(bean);
		for (ClassSubInfoCustom cla : claList) {
			System.out.println(cla.toString());
		}
		con.close();
	}
}
