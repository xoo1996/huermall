package cn.edu.hdu.test;

import org.junit.Test;

public class SimpleTEst {

	@Test
	public void split(){
		String url = "http://localhost:8080/huiermall/memop";
		for(String u:url.split("/")){
			System.out.println(u);
		}
	}
}
