package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.IndexService;

/**
 * 为index.js服务，加载所有的商品信息返回
 * json数组自己封装太麻烦了，因此这里引入json-lib-2.4以及它所需要的依赖jar包来帮我转换
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用逻辑层
		IndexService service = new IndexService();
		String res = service.index();
		//返回结果
		response.getWriter().print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
/*
Connection connection = JDBCUtils.getConnection();
		//System.out.println(connection);
		try {
			//1.链接数据库
			Statement statement = connection.createStatement();
			String sql = "select * from guitar";
			
			//2.执行SQL语句获得结果集
			ResultSet set = statement.executeQuery(sql);
			
			//3.将结果集ResultSet转换为ArrayList，用于封装Json
			//(JSONObject放进去的必须是一个list才能进行正确的转换)
			ArrayList<Guitar> list = new ArrayList<>();
			while (set.next()) {
				Guitar guitar = new Guitar();
				guitar.setId(set.getInt(1));
				guitar.setGuitarname(set.getString(2));
				guitar.setPrice(set.getDouble(3));
				guitar.setPhotopath(set.getString(4));
				list.add(guitar);
			}
			
			//4.开始将数据转换为json格式的数据
			JSONObject object = new JSONObject();
			object.put("guitars", list);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(object);
			
			 * json数组打印出来的效果：
			 * System.out.println(jsonArray.toString());
			 * [{"guitars":[
			 * {"guitarname":"泰勒量产高端系列","id":1,"imgpath":"goodsimgs/guitar01.jpg","price":34999},
			 * {"guitarname":"LAVA全桃花芯木面单","id":2,"imgpath":"goodsimgs/guitar02.jpg","price":23000},
			 * {"guitarname":"TYMA2018全新单板杰作","id":3,"imgpath":"goodsimgs/guitar03.jpg","price":12000},
			 * {"guitarname":"泰勒700/800/900","id":4,"imgpath":"goodsimgs/guitar04.jpg","price":79000}
			 * ]}]
			 
			//5.将json格式的数据返回到index.js
			String res = jsonArray.toString();
			res = res.substring(1, res.length()-1);//将前后的[]去掉，方便index.js使用json解析
			//System.out.println(res);
			response.getWriter().print(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
 */
