
package cn.jx.pxc.colcurevamansystem.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *<p> Title:  CodeUtil.java</p>
 *<p> Description:  MD5加密工具类</p>
 * @package   cn.jx.pxc.otsystem.utils
 * @author    黄信胜
 * @date      2019年5月27日下午15:10:42
 * @version 1.0
 */
public class CodeUtil {
	
	/**
	 * 直接调用getMD5Encoding，返回加密后的密码
	 * @param password
	 * @return
	 */
	public static String getMD5Encoding( String password) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes = password.getBytes();
		md5.update(bytes);
		String newPwds = new BigInteger(1, md5.digest()).toString(16).substring(15);
		return newPwds;
	}
	

}
