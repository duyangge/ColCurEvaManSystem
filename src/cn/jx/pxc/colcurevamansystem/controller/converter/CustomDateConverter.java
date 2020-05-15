/**
 * 
 */
package cn.jx.pxc.colcurevamansystem.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 *<p> Title:  CustomDateConverter.java</p>
 *<p> Description:  日期格式转换器</p>
 * @package   cn.jx.pxc.ssm.controller.converter
 * @author    黄信胜
 * @date      2018年12月19日下午8:46:59
 * @version 1.0
 */
@SuppressWarnings("all")
public class CustomDateConverter implements Converter<String,Date>{
	//类型转换其实现接口Converter<String,Date>
	@Override
	public Date convert(String source) {
		//实现，将日期串转换成日期类型（格式 ：yyyy-MM-dd HH:mm:ss）
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		try {	
			 return simpleDateFormat.parse(source);//转换成功输出
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;//转换失败null
	}

}
