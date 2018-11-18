package cn.bl.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.factory.UserFactory;
import cn.bl.util.MailUtils;
import cn.bl.util.RandomNumUtil;
/**
 * 发送邮件的业务逻辑层
 * uname
 * email
 * umail
 * false
 * true:emailCode----发送成功
 */
public class SendEmailService {
	private UserDao userDao = UserFactory.getInstance();
	public String sendEmail(String username, String receiveEmail) {
		//1.校验数据格式
		//用户名
		if(username==null || username.trim().length()<3 || username.trim().length()>18) {
			return "uname";//用户名格式错误
		}
		username = username.trim();
		
		//邮箱
		if(receiveEmail==null || receiveEmail.trim().length()==0) {
			return "email";
		}
		receiveEmail = receiveEmail.trim();
		String regex = "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$";
		if (!receiveEmail.matches(regex)) {
			return "email";//邮箱地址有误
		}
		
		//2.校验用户名和邮箱是否存在
		if( !userDao.getOneByNameEmail(username,receiveEmail) ) {
			return "umail";//用户名邮箱地址不匹配--
		}
		//3.获取随机验证码
		String emailCode = RandomNumUtil.getRandomNum();//获取随机验证码
		String msg = "您的邮箱验证码是："+emailCode+",请尽快完成找回密码操作";
		//System.out.println(emailCode+","+username+","+receiveEmail);
		//4.发送邮件并返回结果
		boolean boo = false;
		try {
			MailUtils.sendMail(receiveEmail, msg);
			boo = true;
		} catch (AddressException e) {
			boo = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			boo = false;
			e.printStackTrace();
		}
		if(boo) {
			return ""+boo+":"+emailCode;
		}else {
			return ""+boo;
		}
		
	}
}
