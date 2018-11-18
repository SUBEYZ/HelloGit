package cn.bl.test;

import org.junit.Test;

import cn.bl.service.SendEmailService;
import cn.bl.util.RandomNumUtil;

public class TestEmail {
	@Test
	public void test() {
		String email = "11@11.com";
		String regex = "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$";
		if(!regex.matches(email)) {
			System.out.println(false);
		}else {
			System.out.println(true);
		}
		System.out.println("-------------");
		if(email.matches(regex)) {
			System.out.println(true);
		}
	}
	@Test
	public void test1() {
		System.out.println(RandomNumUtil.getRandomNum());
	}
	@Test
	public void test2() {
		boolean b = true;
		String res = ""+b;
		System.out.println(res);
	}
	@Test
	public void testSendEmailService() {
		SendEmailService service = new SendEmailService();
		System.out.println(service.sendEmail("一夜暴富", "1228127092@qq.com"));
	}
	
	
	
	
	
	
	
	
	
	
	
}
