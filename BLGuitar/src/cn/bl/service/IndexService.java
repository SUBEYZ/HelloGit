package cn.bl.service;

import java.util.ArrayList;

import cn.bl.bean.Guitar;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.factory.GuitarFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class IndexService {
	private GuitarDao dao = GuitarFactory.getInstance();
	public String index() {
		//1.调用dao层获取数据--页面中的所有商品信息
		ArrayList<Guitar> list = dao.getByGenre("index");//首页的八个商品图片
		list.addAll(dao.getByGenre("rou"));//首页的三个轮播图
		
		//2.开始将数据转换为json格式的数据
		JSONObject object = new JSONObject();
		object.put("guitars", list);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(object);

		//5.将json格式的数据返回到servlet
		String res = jsonArray.toString();
		res = res.substring(1, res.length()-1);//将前后的[]去掉，方便index.js使用json解析
		//System.out.println(res);
		return res;
	}
}

/*
 * json数组打印出来的效果：
 * System.out.println(jsonArray.toString());
 * [{"guitars":[
 * {"bimgpath":"goodsimgs/bguitar01.jpg","guitarname":"泰勒量产高端系列","id":1,"imgpath":"goodsimgs/guitar01.jpg","price":34999},
 * {"bimgpath":"goodsimgs/bguitar02.jpg","guitarname":"LAVA全桃花芯木面单","id":2,"imgpath":"goodsimgs/guitar02.jpg","price":23000},
 * {"bimgpath":"goodsimgs/bguitar03.jpg","guitarname":"TYMA2018全新单板杰作","id":3,"imgpath":"goodsimgs/guitar03.jpg","price":12000},
 * {"bimgpath":"goodsimgs/bguitar04.jpg","guitarname":"泰勒700/800/900","id":4,"imgpath":"goodsimgs/guitar04.jpg","price":79000},
 * {"bimgpath":"goodsimgs/bguitar05.jpg","guitarname":"Gibson 吉普森 J-45","id":5,"imgpath":"goodsimgs/guitar05.jpg","price":24800},
 * {"bimgpath":"goodsimgs/bguitar06.jpg","guitarname":"德国手工Lakewood雷克梧德","id":6,"imgpath":"goodsimgs/guitar06.jpg","price":28999},
 * {"bimgpath":"goodsimgs/bguitar07.jpg","guitarname":"吉他平方越南Ayers爱乐诗","id":7,"imgpath":"goodsimgs/guitar07.jpg","price":24319},
 * {"bimgpath":"goodsimgs/bguitar08.jpg","guitarname":"John Petrucci JP6 变色龙","id":8,"imgpath":"goodsimgs/guitar08.jpg","price":34399}
 * ]}]
 */

