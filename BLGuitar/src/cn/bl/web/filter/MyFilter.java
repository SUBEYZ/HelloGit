package cn.bl.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 我的过滤器
 */
public class MyFilter implements Filter {
	private String encoding = null;

	/**
	 * 从配置文件中获取字符编码对encoding进行初始化
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	/**
	 * 过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(encoding!=null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(request, response);
	}

	/**
	 * 销毁
	 */
	public void destroy() {
		encoding = null;
	}
}
