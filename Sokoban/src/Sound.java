import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Sound {
	boolean sign;//用于判断是否在播放
	String path = new String("musics\\def.mid");// 路径
	Sequence sequence;//类 的实例
	Sequencer midi;//接口

	void loadSound() {
		try {
			//MidiSystem 类提供了对已安装的 MIDI 系统资源的访问
			sequence = MidiSystem.getSequence(new File(path));//将开始路径给sequence(如果没找到，需要抛出异常)
			midi = MidiSystem.getSequencer();//获得连接到默认设备上的默认的 Sequencer(音序器)
			midi.setSequence(sequence);
			midi.open();
			midi.start();
			
			//设置循环次数为无限
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);//LOOP_CONTINUOUSLY指示循环应无限继续而不是在执行完特定次数的循环后停止
		} catch (Exception e) {
			e.printStackTrace();
		}
		sign = true;//正在播放
	}

	void stopMusic() {//关闭音乐
		midi.close();//关闭
		sign = false;
	}

	boolean isPlay() {//用于判断是否在播放音乐
		return sign;
	}

	void setMusic(String string ) {
		path = string;
	}
}
