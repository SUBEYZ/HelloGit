import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	public int level = 1;// 默认通关数是 1
	int max = 20;// 最大关数为20关
	int snailX, snailY;// snailX代表水平方向上的第几个图片
	int[][] map, mapTemp;// 两个二维数组（map是随游戏进行而改变的。mapTemp是不会改变的，用于判断）
	Image[] myImage;// 存放图片的数组
	int len = 30;// 像素

	ReadMap Levelmap;// 类的两个实例
	ReadMap Levelmaptmp;
	
	Stack<Integer> mysStack = new Stack<Integer>();//新建一个栈（int）

	MainPanel() {
		setBounds(15, 50, 600, 600);// 主面板的大小，位置
		addKeyListener(this);// 添加键盘监听

		myImage = new Image[10];// 一共十个图片
		for (int i = 0; i < 10; i++) {
			// 将十个图片放到myImage[i]
			myImage[i] = java.awt.Toolkit.getDefaultToolkit().getImage("pic\\" + i + ".gif");
		}
	}

	// 进入第几关
	void Sokoban(int i) {
		try {
			Levelmap = new ReadMap(i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		map = Levelmap.getMap();
		snailX = Levelmap.getX();
		snailY = Levelmap.getY();

		try {
			Levelmaptmp = new ReadMap(i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mapTemp = Levelmaptmp.getMap();
		repaint();
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				g.drawImage(myImage[map[j][i]], i * len, j * len, this);
			}
		}
		g.setColor(Color.darkGray);
		g.setFont(new Font("楷体",Font.BOLD,28));
		g.drawString("Now is "+level+" stage", 210, 40);
	}

	// ////// 实现接口KeyListener 的方法////////////////////
	public void keyPressed(KeyEvent e) {// 键被按下(使用这个)
		if (e.getKeyCode() == KeyEvent.VK_UP) { // 按下方向键--上
			moveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {// 按下方向键--下
			moveDown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 按下方向键--左
			moveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 按下方向键--右
			moveRight();
		}
		if (isWin()) {// 如果胜出 ，则询问是否进入下一关
			if (level == max) {
				JOptionPane.showMessageDialog(this, "Congratulations,you had passed all stage!");
			}else {
				String title = "PASS";
				String messageString = "Congratulations,you had passed"+level+"stage";
				int type = JOptionPane.YES_NO_OPTION;
				int choice = 0;
				choice = JOptionPane.showConfirmDialog(null, messageString, title, type);
				if (choice == 0) {
					level++;
					Sokoban(level);
					mysStack.removeAllElements();//到下一关，则清空栈
				}else if(choice == 1){
					System.exit(0);
				}
			}
		}
	}
	boolean isMystackEmty(){
		return mysStack.empty();//返回栈时候空的boolean值
	}
	public void keyTyped(KeyEvent e) {// 字符被输入
	}
	public void keyReleased(KeyEvent e) {// 键被弹起
	}
	int back(){
//		return (Integer)mysStack.pop();
		return mysStack.pop();//出栈
	}
	// /////// 游戏进行 上下左右四个方法/////////////

	// 向前一步，有可能是遇到障碍物（1），草地（2），箱子（3），目的地（4），到达目的地的箱子（9）
	// 进栈的10,20,30,40代表蜗牛移动到草地或目的地
	// 11,21,31,41代表有推动箱子
	void moveUp() {
		if(map[snailY-1][snailX] == 2 || map[snailY -1][snailX] == 4){//如果当前位置的上一个是 草地（2）和  目的地（4）可以直接进去
			//下面一个if：maptmp是用来存放蜗牛当前位置的背景是多少（当它离开一个目的地的时候需要将其还原为目的地而不是草地）
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
				map[snailY][snailX] = 4;
			}else{
				map[snailY][snailX] = 2;//将当前位置设置为草地
			}
			map[snailY-1][snailX] = 8;//将上面的位置设置为向上 的蜗牛
			snailY--;
			mysStack.push(10);//进栈
		}else if(map[snailY-1][snailX] == 3){//如果是箱子（3）（则需要判断该箱子的前一个是什么）
			if(map[snailY-2][snailX] == 4){//如果蜗牛的前两个是目的地--
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//草地
				}
				map[snailY-2][snailX] = 9;//到达目的地的箱子
				map[snailY-1][snailX] = 8;//向上的蜗牛
				snailY--;
				mysStack.push(11);
			}else if(map[snailY-2][snailX] == 2){//如果是草地，将箱子推到草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//草地
				}
				map[snailY-2][snailX] = 3;//箱子
				map[snailY-1][snailX] = 8;//向上的蜗牛
				snailY--;
				mysStack.push(11);
			}else{//剩下的障碍物，箱子，到达目的地的箱子的情况  都不能动(并且没必要记录到堆栈当中)
				map[snailY][snailX] = 8;
			}
		}else if(map[snailY-1][snailX] == 9){//如果是已经到达指定位置的箱子(9)（则需要判断该箱子的前一个是什么）
			if(map[snailY-2][snailX] == 4){//如果是目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//草地
				}
				map[snailY-2][snailX] = 9;
				map[snailY-1][snailX] = 8;
				snailY--;
				mysStack.push(11);
			}else if(map[snailY-2][snailX] == 2){//如果是草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//草地
				}
				map[snailY-2][snailX] = 3;
				map[snailY-1][snailX] = 8;
				snailY--;
				mysStack.push(11);
			}else{//其他情況都不能动
				map[snailY][snailX] = 8;
			}
		}else if(map[snailY-1][snailX] == 1){//如果是障碍物，则不能移动
			map[snailY][snailX] = 8;
		}
		repaint();
	}

	void moveDown() {
		if(map[snailY+1][snailX] == 2 || map[snailY+1][snailX] == 4){//如果是草地或者目的地
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
				map[snailY][snailX] = 4;
			}else{
				map[snailY][snailX] = 2;//将当前位置设置为草地
			}
			map[snailY+1][snailX] = 5;
			snailY++;
			mysStack.push(20);
		}else if(map[snailY+1][snailX] == 3){//如果是箱子
			if(map[snailY+2][snailX] == 4){//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY+2][snailX] = 9;//到达目的地的箱子
				map[snailY+1][snailX] = 5;
				snailY++;
				mysStack.push(21);
			}else if(map[snailY+2][snailX] == 2){//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY+2][snailX] = 3;//箱子
				map[snailY+1][snailX] = 5;
				snailY++;
				mysStack.push(21);
			}else{//障碍物
				map[snailY][snailX] = 5;
			}
		}else if(map[snailY+1][snailX] == 9){//如果是到达位置的箱子
			if(map[snailY+2][snailX] == 4){//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY+2][snailX] = 9;
				map[snailY+1][snailX] = 5;
				snailY++;
				mysStack.push(21);
			}else if(map[snailY+2][snailX] == 2){//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY+2][snailX] = 3;
				map[snailY+1][snailX] = 5;
				snailY++;
				mysStack.push(21);
			}else{//障碍物
				map[snailY][snailX] = 5;
			}
		}else if(map[snailY+1][snailX] == 1){//障碍物
			map[snailY][snailX] = 5;
		}
		repaint();
	}

	void moveLeft() {
		if (map[snailY][snailX-1] == 2 || map[snailY][snailX-1] == 4) {//草地或者是目的地
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
				map[snailY][snailX] = 4;
			}else{
				map[snailY][snailX] = 2;//将当前位置设置为草地
			}
			map[snailY][snailX-1] = 6;//向左的蜗牛
			snailX--;
			mysStack.push(30);
		}else if (map[snailY][snailX-1] == 3) {//箱子
			if (map[snailY][snailX-2] == 4) {//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX-2] = 9;
				map[snailY][snailX-1] = 6;
				snailX--;
				mysStack.push(31);
			}else if (map[snailY][snailX-2] == 2) {//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX-2] = 3;
				map[snailY][snailX-1] = 6;
				snailX--;
				mysStack.push(31);
			}else {
				map[snailY][snailX] = 6;
			}
		}else if (map[snailY][snailX-1] == 9){//到达目的地的箱子
			if (map[snailY][snailX-2] == 4) {//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX-2] = 9;
				map[snailY][snailX-1] = 6;
				snailX--;
				mysStack.push(31);
			}else if (map[snailY][snailX-2] == 2) {//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX-2] = 3;
				map[snailY][snailX-1] = 6;
				snailX--;
				mysStack.push(31);
			}else {
				map[snailY][snailX] = 6;
			}
		}else if (map[snailY][snailX-1] == 1) {//障碍物
			map[snailY][snailX] = 6;
		}
		repaint();
	}

	void moveRight() {
		if (map[snailY][snailX+1] == 2 || map[snailY][snailX+1] == 4) {//草地或者是目的地
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
				map[snailY][snailX] = 4;
			}else{
				map[snailY][snailX] = 2;//将当前位置设置为草地
			}
			map[snailY][snailX+1] = 7;//向右的蜗牛
			snailX++;
			mysStack.push(40);
		}else if (map[snailY][snailX+1] == 3) {//箱子
			if (map[snailY][snailX+2] == 4) {//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX+2] = 9;
				map[snailY][snailX+1] = 7;
				snailX++;
				mysStack.push(41);
			}else if (map[snailY][snailX+2] == 2) {//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX+2] = 3;
				map[snailY][snailX+1] = 7;
				snailX++;
				mysStack.push(41);
			}else {
				map[snailY][snailX] = 7;
			}
		}else if (map[snailY][snailX+1] == 9){//到达目的地的箱子
			if (map[snailY][snailX+2] == 4) {//目的地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX+2] = 9;
				map[snailY][snailX+1] = 7;
				snailX++;
				mysStack.push(41);
			}else if (map[snailY][snailX+2] == 2) {//草地
				if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){//目的地（4），到达目的地的箱子（9）
					map[snailY][snailX] = 4;
				}else{ 
					map[snailY][snailX] = 2;//将当前位置设置为草地
				}
				map[snailY][snailX+2] = 3;
				map[snailY][snailX+1] = 7;
				snailX++;
				mysStack.push(41);
			}else {
				map[snailY][snailX] = 7;
			}
		}else if (map[snailY][snailX+1] == 1) {//障碍物
			map[snailY][snailX] = 7;
		}
		repaint();
	}

	// //////// 游戏悔一步 上下左右四个方法/////////////////
	void backUp(int t) {
		int num = t;
		if (num == 10) {//没推动箱子
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9) {//目的地，到达位置的箱子
				map[snailY][snailX] = 4;
			}else {
				map[snailY][snailX] = 2;
			}
		}else if (num == 11) {//有推动箱子
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9) {//原地图当前位置是目的地或者是到达目的地的箱子
				map[snailY][snailX] = 9;
			} else{
				map[snailY][snailX] = 3;
			}
			if (mapTemp[snailY - 1][snailX] == 4 || mapTemp[snailY - 1][snailX] == 9) {
				map[snailY - 1][snailX] = 4;
			} else{
				map[snailY - 1][snailX] = 2;
			}
		}
		map[snailY+1][snailX] = 8;//向上的蜗牛
		snailY++;//相当于向下了一步
		repaint();
	}

	void backDown(int t) {
		int num = t;
		if (num == 20) {//如果没推动箱子
			if(mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){
				map[snailY][snailX] = 4;
			}else {
				map[snailY][snailX] = 2;
			}
		}else if (num == 21) {
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9) {
				map[snailY][snailX] = 9;
			} else{
				map[snailY][snailX] = 3;
			}
			if (mapTemp[snailY + 1][snailX] == 4 || mapTemp[snailY + 1][snailX] == 9) {
				map[snailY + 1][snailX] = 4;
			} else{
				map[snailY + 1][snailX] = 2;
			}
		}
		map[snailY-1][snailX] = 5;
		snailY--;//蜗牛向上一步
		repaint();
	}

	void backLeft(int t) {
		int num = t;
		if (num == 30) {
			if(mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){
				map[snailY][snailX] = 4;
			}else {
				map[snailY][snailX] = 2;
			}
		}else if (num == 31) {
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9) {
				map[snailY][snailX] = 9;
			} else{
				map[snailY][snailX] = 3;
			}
			if (mapTemp[snailY][snailX - 1] == 4 || mapTemp[snailY][snailX - 1] == 9) {
				map[snailY][snailX - 1] = 4;
			} else{
				map[snailY][snailX - 1] = 2;
			}
		}
		map[snailY][snailX + 1] = 6;
		snailX++;
		repaint();
	}

	void backRight(int t) {
		int num = t;
		if (num == 40) {
			if(mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9){
				map[snailY][snailX] = 4;
			}else {
				map[snailY][snailX] = 2;
			}
		}else if (num == 41) {
			if (mapTemp[snailY][snailX] == 4 || mapTemp[snailY][snailX] == 9) {
				map[snailY][snailX] = 9;
			} else
				map[snailY][snailX] = 3;
			if (mapTemp[snailY][snailX + 1] == 4 || mapTemp[snailY][snailX + 1] == 9) {
				map[snailY][snailX + 1] = 4;
			} else
				map[snailY][snailX + 1] = 2;
		}
		map[snailY][snailX - 1] = 7;
		snailX--;
		repaint();
	}

	// //////////// 是否通过一关////////////
	boolean isWin() {
		boolean judge = false;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if(mapTemp[i][j]==4 || mapTemp[i][j]==9){//如果玩家的地图每一个目的地都有了箱子，就为胜出
					if(map[i][j] == 9){
						judge = true;
					}
					else{
						return false;
					}
				}
			}
		}
		return judge;
	}
}
/*
 * 正移动：小人向上、下、左、右的移动是一个判断算法， 其判断都是通过判断小人前面是否是草地或者是箱子亦或者是障碍物（或者边界）。
 * 如果是障碍物或者是边界，就不能够进行移动；如果是没有箱子或者是障碍物，
 * 就可以自由移动；又如果是有箱子，就要判断是否可以移动箱子，最后再讨论箱子被推过的位置，小人移动的位置，
 * 以及它们的原位置和被遮挡住的新位置的图形变化等等，需要运用算法使其重新绘制地图，填补空白。算法判断完毕后，
 * 传出数据并且将其记录在一个堆栈中，以备“Regret step”时使用。
 * 负移动：通过记录在堆栈中的数据来判断，前一步小人的移动方向以及移动中使用过的算法， 逆向将代码重新运行，同时绘制并刷新地图以达到前一步的状态。
 */
