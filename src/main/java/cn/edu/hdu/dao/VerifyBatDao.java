package cn.edu.hdu.dao;

import java.util.List;

public interface VerifyBatDao {

	public int updateVerifyBatStatus(String op,String id,String status);

	public List<Object[]> getVerifyBatList(String op, String value);

	public int updateVerifyBatVerifyIdAndDate(String op, String value, String verifyId, String date);

}
