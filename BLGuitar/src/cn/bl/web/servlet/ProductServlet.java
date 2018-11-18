package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.bean.Guitar;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.factory.GuitarFactory;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GuitarDao guitarDao = GuitarFactory.getInstance();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Guitar guitar = guitarDao.getOneById(id);
		String guitarname = guitar.getGuitarname();
		double price = guitar.getPrice();
		String imgpath = guitar.getImgpath();
		String bimgpath = guitar.getBimgpath();
		String json = "{\"guitarname\":\""+guitarname+"\",\"price\":\""+price+"\",\"imgpath\":\""+imgpath+"\",\"bimgpath\":\""+bimgpath+"\"}";
		//System.out.println(json);
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}