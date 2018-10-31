package cn.bl.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bl.domain.User;
import cn.bl.service.IUserService;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService service;
	
	@RequestMapping("/login")
	public String login(User user,PrintWriter out,HttpSession session) {
		User res = service.login(user);
		System.out.println("con:"+res);
		if(res!=null) {
			session.setAttribute("curUser", res);//这个curUser不能写成user，否则出错：jsp中找到的是原来的user
		}else {
			session.invalidate();
		}
		return "index.jsp";
	}
}
