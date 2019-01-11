package cn.edu.hdu.util;

public class StrUtil {

	
	public static String getlogString(String...strings){
		String str = "";
		for(String o:strings ){
			str += o;
			str += ",";
		}
		return str;
	}
	
	public static String getLastSubString(String str,String regex){
		String[] sss = str.split(regex);
		int length = sss.length;
		return sss[length-1];
	}
}
