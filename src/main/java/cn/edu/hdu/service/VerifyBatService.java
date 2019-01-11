package cn.edu.hdu.service;

import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.VerifyBat;

public interface VerifyBatService {

	public void addVerifyBat(VerifyBat verifyBat);

	public void updateStatus(String op, String id, String status);

	public Row getVerifyingList(String memberName, String memberNo, String memberPhone, String storeName, String page,
			String rowNumber);
	/**
	 * 根据相应条件找到verifyBat的list
	 * @param op
	 * @param status
	 * @return
	 */
	public List<Object[]> getVerifyBatList(String op,String value);

	public void updateVerifyBatStatus(List<Object[]> verifyBats,String value);

	public void updateVerifyIdAndDate(List<Object[]> verifyBats, String id, String date);

	public Row getVerifyBatList(String memberName, String memberNo, String memberPhone, String storeNo, String statusCheck,
			String page, String rowNumber);

}
