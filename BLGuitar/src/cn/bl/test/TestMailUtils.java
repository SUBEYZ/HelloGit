package cn.bl.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import cn.bl.util.MailUtils;

public class TestMailUtils {
	@Test
	public void test() {
		try {
			MailUtils.sendMail("1228127092@qq.com", "hello barrylee");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
