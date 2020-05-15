/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *<p> Title:  DateFormate.java</p>
 *<p> Description:  描述</p>
 * @package   cn.jx.pxc.colcurevamansystem.utils
 * @author    23801
 * @date      2020年4月30日下午1:29:37
 * @version 版本号
 */
public class DateFormateUtil {
  
	/**将string类型转换为date类型
	 * @param s
	 * @return
	 */
	public static Date getStringToDateFormate(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
		//String date = formatter.format(new Date());//格式化数据，取当前时间结果为 2014-10-30
		Date date;
		try {
			date = formatter.parse(s);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
