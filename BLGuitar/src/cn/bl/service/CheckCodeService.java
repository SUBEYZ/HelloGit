package cn.bl.service;

public class CheckCodeService {
	public boolean checkCode(String code,String sessionWord) {
		if(code==null || sessionWord==null || !sessionWord.equals(code)) {
			return false;
		}else {
			return true;
		}
	}
}
