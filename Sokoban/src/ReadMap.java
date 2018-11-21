import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
	private int leval;// 关数
	private int mx, my;
	private int myMap[][] = new int[20][20];// 背景20*20个图片
	String aString = "";// 空串
	int[] x;

	ReadMap(int k) throws FileNotFoundException {
		leval = k;
		String string;
		// 读文件字符到aString
		try {
			File file = new File("maps\\" + leval + ".map");// 文件路径（将文件中的数字转换成对应图片）
			FileReader fReader = new FileReader(file);// 读文件file到fRead
			@SuppressWarnings("resource")
			BufferedReader bReader = new BufferedReader(fReader);// 文件fr里的字节读到缓存里
			while ((string = bReader.readLine()) != null) {
				aString = aString + string;// 连接起来
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		byte[] b = aString.getBytes();
		int len = aString.length();// 得到长度放到len
		int[] x = new int[len];
		for (int i = 0; i < len; i++) {
			x[i] = b[i] - 48;// 将ASCII，码装换为数字
		}
		int c = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				myMap[i][j] = x[c];
				if (myMap[i][j] == 5) {//编号5：代表蜗牛向下移动方向(默认方向)
					mx = j;
					my = i;
				}
				c++;
			}
		}
	}

	int[][] getMap() {
		return myMap;
	}

	int getX() {
		return mx;
	}

	int getY() {
		return my;
	}
}
