package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.bean.Guitar;
import cn.bl.bean.PageBean;
import cn.bl.service.PagingService;

public class QueryPagingProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//类别
		String genre = request.getParameter("genre");
		if(genre==null) {
			return;
		}
		
		//当前页
		String curPageStr = request.getParameter("curPage");
		//System.out.println(curPageStr);
		if(curPageStr==null) {
			curPageStr="1";
		}
		int curPage = Integer.parseInt(curPageStr);
		
		//假定每页是8条记录
		int curCount = 8;
		/*QueryGuitarByGenreService service = new QueryGuitarByGenreService();
		ArrayList<Guitar>list = service.queryGuitar("ballad");*/
		
		PageBean<Guitar>pageBean = null;
		PagingService service = new PagingService();
		pageBean = service.getPageBean(curPage, curCount, genre);
		
		//设置request域
		request.setAttribute("pageBean", pageBean);
		//四种类别--ballad yucli classic electric
		switch (genre) {
		case "ballad":
			request.setAttribute("genre", "ballad");
			request.setAttribute("genreGBK", "民谣吉他");
			break;
		case "yucli":
			request.setAttribute("genre", "yucli");
			request.setAttribute("genreGBK", "尤克里里");
			break;
		case "classic":
			request.setAttribute("genre", "classic");
			request.setAttribute("genreGBK", "古典吉他");
			break;
		case "electric":
			request.setAttribute("genre", "electric");
			request.setAttribute("genreGBK", "电吉他");
			break;
		}
		
		//下面两种方式区别看笔记
		//response.sendRedirect("/BLGuitar/jsps/ballad.jsp");
		request.getRequestDispatcher("/jsps/pagingProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}