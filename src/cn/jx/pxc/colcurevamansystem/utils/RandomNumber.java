package cn.jx.pxc.colcurevamansystem.utils;

import java.util.Random;

public class RandomNumber {
	public static String getRandomNumber() {
		String account=null;
		StringBuilder str=new StringBuilder();  //定义变长字符串
		Random random=new Random();            //随机生成数字，并添加到字符串
		int cc=(int)(Math.random()*8)+1;
		String a=cc+"";
		/*str.append(Math.random()*8+1);*/
		for(int i=1;i<8;i++){
		    str.append(random.nextInt(10));
		}
		//将字符串转换为数字并输出
		account=str.toString();
		String account1=a+account;
		return account1;
	}
}
