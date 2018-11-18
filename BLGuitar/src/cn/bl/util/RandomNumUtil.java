package cn.bl.util;

import java.util.Random;

/**
 * 生成六位数邮箱验证码
 */
public class RandomNumUtil {
	public static String getRandomNum() {
		Random random = new Random(System.currentTimeMillis());
		String emailCode = ""+(random.nextInt(899999)+100000);//100000~999999
		return emailCode;
	}
}
