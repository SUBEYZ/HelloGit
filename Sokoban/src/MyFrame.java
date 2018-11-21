import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//ItemListener用于监听带有item的组件产生的事件
//而ActionListener 是所有监听的父类
public class MyFrame extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;//序列化是为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	JLabel lb1;// 标签
	JLabel lb2;
	// 右栏按钮
	JButton btnReNew;// 重来
	JButton btnLast;// 上一关
	JButton btnNext;// 下一关
	JButton btnChoose;// 选关
	JButton btnFirst;// 第一关
	JButton btnOver;// 最终关
	JButton btnMusic;// 音乐开关
	JButton btnBack;// 悔一步

	MainPanel mainPanel;
	Sound sound;
	@SuppressWarnings("rawtypes")
	JComboBox jComboBox = new JComboBox();// 下拉菜单形式的复选框
	
	// 下面是菜单项定义
	MenuItem renew = new MenuItem("   Restar");
	MenuItem last = new MenuItem("   Last");
	MenuItem back = new MenuItem("   Back");
	MenuItem next = new MenuItem("   Next");
	MenuItem choose = new MenuItem("   Choose");
	MenuItem music = new MenuItem("   Music");
	MenuItem exit = new MenuItem("   Exit");
	MenuItem about = new MenuItem(" About");
	MenuItem defout = new MenuItem("Defout  ");
	MenuItem loveSong = new MenuItem("LoveSong");
	MenuItem kissBye =  new MenuItem("KissBye");

	@SuppressWarnings("unchecked")
	public MyFrame() {
		super("SOKOBAN");
		//////// 布局设置///////////
		setBounds(320, 10, 720, 720);// 设置窗口位置，大小
		setResizable(false);// 窗口不可更改
		setDefaultCloseOperation(EXIT_ON_CLOSE);// 关闭按钮直接退出
		java.awt.Container container = getContentPane();// 将面板放到容器container，方便使用
		container.setLayout(null);// 空布局
		container.setBackground(Color.lightGray);// 设置背景色为浅灰色

		///////// 菜单设置///////////
		MenuBar bar = new MenuBar();
		setMenuBar(bar);// 挂一个菜单栏

		Menu option = new Menu("    Option");
		// 菜单项添加到菜单--选项
		option.add(renew);// 重新开始
		option.add(choose);// 选关
		option.add(back);// 悔一步
		option.add(next);// 下一关
		option.add(last);// 最终关
		option.addSeparator();// 加一条分割线
		option.add(exit);// 退出
		// 各菜单项添加监听
		renew.addActionListener(this);
		choose.addActionListener(this);
		back.addActionListener(this);
		next.addActionListener(this);
		last.addActionListener(this);
		exit.addActionListener(this);

		Menu musicMenu = new Menu("     Music");
		// 菜单项添加到菜单栏--音乐
		musicMenu.add(defout);
		musicMenu.add(loveSong);
		musicMenu.add(kissBye);
		// 各菜单项添加监听
		defout.addActionListener(this);
		loveSong.addActionListener(this);
		kissBye.addActionListener(this);

		Menu help = new Menu("    Help");
		// 菜单项添加到菜单栏--音乐
		help.add(about);
		// 各菜单项添加监听
		about.addActionListener(this);
		// 菜单添加到菜单栏
		bar.add(option);
		bar.add(musicMenu);
		bar.add(help); 
		defout.setEnabled(false);// 设置控件是否可用 这里就是菜单栏里的默认按钮不可用

		// 下面是两个标签的设置(lb1,lb2)
		lb1 = new JLabel("~~Sokoban~~", JLabel.CENTER);
		lb2 = new JLabel("Music", JLabel.CENTER);
		lb1.setBounds(100, 20, 450, 20);// 设置位置，大小
		lb2.setBounds(625, 500, 55, 20);
		add(lb1);
		add(lb2);

		///////// 右侧按钮///////
		btnReNew = new JButton("Restar");
		btnBack = new JButton("Back");
		btnLast = new JButton("Last");
		btnNext = new JButton("Next");
		btnChoose = new JButton("Choose");
		btnFirst = new JButton("First");
		btnOver = new JButton("Final");
		btnMusic = new JButton("MusON");// 默认关闭音乐
		// 各个按钮添加监听
		btnBack.addActionListener(this);
		btnChoose.addActionListener(this);
		btnFirst.addActionListener(this);
		btnLast.addActionListener(this);
		btnMusic.addActionListener(this);
		btnNext.addActionListener(this);
		btnOver.addActionListener(this);
		btnReNew.addActionListener(this);
		// 各个按钮的位置
		btnReNew.setBounds(625, 100, 80, 30);
		btnBack.setBounds(625, 150, 80, 30);
		btnFirst.setBounds(625, 200, 80, 30);
		btnLast.setBounds(625, 250, 80, 30);
		btnNext.setBounds(625, 300, 80, 30);
		btnOver.setBounds(625, 350, 80, 30);
		btnChoose.setBounds(625, 400, 80, 30);
		btnMusic.setBounds(625, 450, 80, 30);
		// 添加
		add(btnReNew);
		add(btnFirst);
		add(btnChoose);
		add(btnLast);
		add(btnNext);
		add(btnOver);
		add(btnBack);
		add(btnMusic);
		jComboBox.setBounds(625, 530, 80, 20);// 下拉菜单位置
		// 添加三个下拉菜单选项
		jComboBox.addItem("Default");
		jComboBox.addItem("loveSong");
		jComboBox.addItem("kissBye");
		jComboBox.addActionListener(this);// 监听下拉菜单
		jComboBox.addItemListener(this);
		container.add(jComboBox);// 添加


		// 游戏进行的主面板设置
		mainPanel = new MainPanel();
		mainPanel.Sokoban(mainPanel.level);// 调用判断关数(level:MainPanel里定义，起始为1)
		mainPanel.requestFocus();// 把输入焦点放在调用这个方法的控件上
		add(mainPanel);// 添加
		
		// 加载声音文件
		sound = new Sound();

		setVisible(true);
	}

	// 接口ItemListener的实现(监听音乐)
	public void itemStateChanged(ItemEvent e) {
		int num = jComboBox.getSelectedIndex();//下拉菜单选择了第几个就返回一个整数
		switch (num) {
		case 0:
			sound.setMusic("musics\\def.mid");//设置音乐路径
			if (sound.isPlay()) {//如果正在播放，则关掉
				sound.stopMusic();
			}
			sound.loadSound();//加载声音
			btnMusic.setText("MusOFF");
			//上方菜单项按钮可否选择
			defout.setEnabled(false);
			loveSong.setEnabled(true);
			kissBye.setEnabled(true);
			mainPanel.requestFocus();
			break;
		case 1:
			sound.setMusic("musics\\loveSong.mid");
			if (sound.isPlay()) {
				sound.stopMusic();
			}
			sound.loadSound();
			btnMusic.setText("MusOFF");
			defout.setEnabled(true);
			loveSong.setEnabled(false);
			kissBye.setEnabled(true);
			mainPanel.requestFocus();
			break;
		case 2:
			sound.setMusic("musics\\kissBye.mid");
			if (sound.isPlay()) {
				sound.stopMusic();
			}
			sound.loadSound();
			btnMusic.setText("MusOFF");
			defout.setEnabled(true);
			loveSong.setEnabled(true);
			kissBye.setEnabled(false);
			mainPanel.requestFocus();
			break;
		}
	}

	// 接口ActionListener的实现(监听菜单栏和侧栏按钮)
	public void actionPerformed(ActionEvent e) {
		// 按下--重新开始按钮
		if (e.getSource() == btnReNew || e.getSource() == renew) {
			mainPanel.Sokoban(mainPanel.level);
			mainPanel.requestFocus();
			mainPanel.mysStack.removeAllElements();
		}
		// 按下--上一关按钮
		else if (e.getSource() == btnLast || e.getSource() == last) {
			mainPanel.level--;
			if(mainPanel.level < 1){//已经是第一关
				mainPanel.level++;
				JOptionPane.showMessageDialog(this, "This is the first stage");
				mainPanel.requestFocus();
			}else {
				mainPanel.Sokoban(mainPanel.level);
				mainPanel.requestFocus();
			}
			mainPanel.mysStack.removeAllElements();
			mainPanel.requestFocus();
		}
		// 按下--下一关按钮
		else if (e.getSource() == btnNext || e.getSource() == next) {
			mainPanel.level++;//关数加一
			if(mainPanel.level > mainPanel.max){//达到最后一关
				mainPanel.level--;
				JOptionPane.showMessageDialog(this, "This is the final stage");
				mainPanel.requestFocus();
			}else {
				mainPanel.Sokoban(mainPanel.level);
				mainPanel.requestFocus();
			}
			mainPanel.mysStack.removeAllElements();
		}
		// 按下--退出按钮
		else if (e.getSource() == exit) {
			System.exit(0);
		}
		// 按下--关于按钮
		else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "         SOKOBAN\nDEVELOPER：柒寸思\nQQ:   1228127092");// \nEmail:
		}
		// 按下--选择关数按钮
		else if (e.getSource() == btnChoose || e.getSource() == choose) {
			String le = JOptionPane.showInputDialog(this, "Please input the stage:<0~20>");
			try {
				int L = Integer.parseInt(le);
				mainPanel.mysStack.removeAllElements();
				if(L > mainPanel.max || L < 1){
					JOptionPane.showMessageDialog(this, "We have no this stage");
					mainPanel.requestFocus();
				}else {
					mainPanel.level = L;
					mainPanel.Sokoban(L);
					mainPanel.mysStack.removeAllElements();
					mainPanel.requestFocus();
				}
			} catch (Exception e2) {
			}
			
		}
		// 按下--第一关按钮
		else if (e.getSource() == btnFirst) {
			mainPanel.level = 1;//将关数重设为1
			mainPanel.Sokoban(1);//开始第一关
			mainPanel.requestFocus();//窗口固定
			mainPanel.mysStack.removeAllElements();
		}
		// 按下--最终关按钮
		else if (e.getSource() == btnOver) {
			mainPanel.level = 20;
			mainPanel.Sokoban(mainPanel.level);
			mainPanel.requestFocus();
			mainPanel.mysStack.removeAllElements();
		}
		// 音乐开关按钮
		else if (e.getSource() == btnMusic) {
			if (sound.isPlay()) {//如果正在播放
				sound.stopMusic();//关闭音乐
				btnMusic.setText("musON");//设置标签
			}else {//否则
				sound.loadSound();//加载音乐
				btnMusic.setText("musOFF");//设置标签
			}
		}
		// 悔一步按钮（上菜单或者右栏菜单）
		else if (e.getSource() == btnBack || e.getSource() == back) {
			if (mainPanel.isMystackEmty()) {//还没移动
				JOptionPane.showMessageDialog(this, "You have no move");
			}else {
				switch (mainPanel.back()) {
				case 10:
					mainPanel.backUp(10);
					break;
				case 11:
					mainPanel.backUp(11);
					break;
				case 20:
					mainPanel.backDown(20);
					break;
				case 21:
					mainPanel.backDown(21);
					break;
				case 30:
					mainPanel.backLeft(30);
					break;
				case 31:
					mainPanel.backLeft(31);
					break;
				case 40:
					mainPanel.backRight(40);
					break;
				case 41:
					mainPanel.backRight(41);
					break;
				}
			}
			mainPanel.requestFocus();//back之后可以继续
		}
		// 按下--右栏默认按钮
		else if (e.getSource() == defout) {
			jComboBox.setSelectedIndex(0);//设置下拉菜单默认选择为第（0+1）个（这里就是默认选择第一个）
		}
		//按下--下拉菜单lovaSong
		else if (e.getSource() == loveSong) {
			jComboBox.setSelectedIndex(1);
		}
		else if(e.getSource() == kissBye){
			jComboBox.setSelectedIndex(2);
		}
	}
}