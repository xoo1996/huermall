package cn.edu.hdu.dao;

import java.util.List;

import cn.edu.hdu.pojo.MemBat;

public interface MemBatDao {
	
	public int updateMemBat(Long id, String batteryType,long batteryReQty);
	
	public int updateMemBatStatus(Long id,String status);
	
	public int updateMemBatStatus(String id,String op,String status);
	
	public List<Object[]> getMembatList(String op,String value);
}
