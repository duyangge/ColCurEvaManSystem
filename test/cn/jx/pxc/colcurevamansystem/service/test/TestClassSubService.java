/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import cn.jx.pxc.colcurevamansystem.bean.BeanQueryVo;
import cn.jx.pxc.colcurevamansystem.bean.LessionEvaTemp;
import cn.jx.pxc.colcurevamansystem.service.ClassSubInfoService;

/**
 *<p> Title:  TestClassSubService.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.service.test
 * @author    23801
 * @date      2020年4月11日上午10:32:20
 * @version 版本号
 */
@SuppressWarnings("all")
public class TestClassSubService {
	public static ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("application-dao.xml","application-service.xml");
	
	/**
	 * 查询所有
	 */
	@Test
	public void selectByAll() {
		ClassSubInfoService claSubService = con.getBean(ClassSubInfoService.class);
		BeanQueryVo beanQueryVo = new BeanQueryVo();
		List<LessionEvaTemp>  lesEvaList = claSubService.selectLessionEva(beanQueryVo);
		for (LessionEvaTemp lesEva : lesEvaList) {
			System.out.println(lesEva.toString());
		}		
	}
	
	@Test
	public void testFind() {
		boolean data = StringUtils.isEmpty(null);
		System.err.println(data);
	}
	
	
}
