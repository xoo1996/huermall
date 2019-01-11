package cn.edu.hdu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.pojo.Gift;

import com.zlzkj.core.sql.Row;

public interface GiftService {

	public Row getGiftList(String name,String symbol,Long changeNum,int rowNum,int page);
	
	public void saveGift(Gift gift,MultipartFile[] files) throws IOException;
	
	public Long saveGiftImg(MultipartFile file,String module,Long seriesId) throws IOException ;
	
	public boolean deleteGift(Long giftId);
	
	/**
	 * 修改礼品库存
	 * @param id   礼品id
	 * @param storeNum  新库存数量
	 * @return
	 */
	public boolean resetStoreNum(Long id ,Long storeNum);
	
	/**
	 * 礼品下拉框
	 * @return
	 */
	public List<Row> getGiftOptionList();
	/**
	 * 判断相应礼品兑换数量是否大于存库数量，返回礼品积分值
	 * @param number
	 * @param giftid
	 * @return
	 */
	public Long NumScore(Long number, String giftid);
	/**
	 * 积分兑换完更新礼品库存数量
	 * @param number
	 * @param giftid
	 * @param id
	 */
	public void updateStore(long number, String giftid,Long id, String account);

	/**
	 * 修改礼品对应积分
	 * @param id
	 * @param score
	 * @return
	 */
	boolean resetScore(Long id, Long score);


}
