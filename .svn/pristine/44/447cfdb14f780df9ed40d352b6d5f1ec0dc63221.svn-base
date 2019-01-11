package cn.edu.hdu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.GlobalStatic;
import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.dao.impl.GiftDaoImpl;
import cn.edu.hdu.pojo.Gift;
import cn.edu.hdu.pojo.RGiftPicture;
import cn.edu.hdu.service.GiftService;
import cn.edu.hdu.util.DataUtil;
import cn.edu.hdu.util.FileUtil;

@Service(value="giftService")
public class GiftServiceImpl extends GenericService<Gift> implements GiftService {

	@Resource
	private FileServiceImpl fileService;
	@Resource
	private RGiftPictureServiceImpl gpService;
	@Resource
	private ScoreServiceImpl scoreService;
	
	public GiftDaoImpl getGiftDao() {
        return (GiftDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setGiftDao(GiftDaoImpl giftDao) {
	    this.setGenericDao(giftDao);
	}

	@Override
	public Row getGiftList(String name, String symbol,Long changeNum, int rowNum, int page) {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Gift gift")
				.like("gift.name", name)
				.where("gift.changeNum", GlobalStatic.getMap().get(symbol), changeNum)
				.buildHql();
		Query query = this.getQuery(hql_data);
		hb.setParam(query);
		List<Gift> giftList = DataUtil.getPages(query, page, rowNum).list();
		String hql_sum = hb.select("count(*)")
				.from("Gift gift")
				.like("gift.name", name)
				.where("gift.changeNum", GlobalStatic.getMap().get(symbol), changeNum)
				.buildHql();
		query = this.getQuery(hql_sum);
		hb.setParam(query);
		Object giftSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Gift gift:giftList){
			Row row = new Row();
			row.put("checkboxid", gift.getId());
			row.put("id", gift.getId());
			row.put("name", gift.getName());
			row.put("changeNum", gift.getChangeNum());
			row.put("storeNum", gift.getStoreNum());
			row.put("score", gift.getScore());
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", giftSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public Long saveGiftImg(MultipartFile file, String moduleName,
			Long seriesId) throws IOException {
		String fileName = file.getOriginalFilename();  
		String filePath = FileUtil.getFilePath(moduleName, fileName,seriesId);
        File tempFile = new File(filePath, String.valueOf(fileName));  
        if(tempFile.exists()){
        	tempFile.delete();
        }
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdirs();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return fileService.saveImgPath(filePath, moduleName, seriesId);
	}

	@Transactional
	@Override
	public void saveGift(Gift gift, MultipartFile[] files) throws IOException {
		try {
			gift.setChangeNum(0L);
			this.save(gift);
		//	Long imgDirId = this.saveGiftImg(file, module);
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				if(file.getSize() == 0){
					continue;
				}
				Long imgDirId  = fileService.saveImg(UploadCode.DIR_GIFT,
						UploadCode.MODULE_GIFT,file, new Date().getTime());
				RGiftPicture gp = new RGiftPicture();
				gp.setGiftId(gift.getId());
				gp.setPictureId(imgDirId);
				gpService.save(gp);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean deleteGift(Long giftId) {
		try {
			List<RGiftPicture> gpList = gpService.findByProperty("giftId", giftId);
			List<Long> ids = new ArrayList<Long>();
			for(RGiftPicture gp:gpList){
				ids.add(gp.getPictureId());
			}
			fileService.deleteByIds(ids);  //删除服务器上图片路径
			for(RGiftPicture gp:gpList){
				gpService.deleteById(gp.getId()); //删除礼物与图片的关联
				fileService.deleteLocalImg(gp.getPictureId());//删除本地图片
			}
			return this.getGiftDao().deleteById(giftId); //删除gift记录
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean resetStoreNum(Long id, Long storeNum) {
		try {
			Gift gift = this.findById(id);
			gift.setStoreNum(storeNum);
			this.save(gift);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
/**
 * 礼品下拉框
 * @return
 */
	@Override
	public List<Row> getGiftOptionList() {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Gift gift where gift.id > '0'")
				.buildHql();
		List<Gift> roleList = this.getGiftDao().findByHql(hql_data, null);
		List<Row> rows = new ArrayList<Row>();
		for(Gift role:roleList){
			Row row = new Row();
			row.put("id", role.getId());
			row.put("text", role.getName());
			rows.add(row);
		}
		return rows;
	}
/**
 * 判断相应礼品兑换数量是否大于存库数量，返回礼品积分值
 * @param number
 * @param giftid
 * @return
 */
	@Override
	public Long NumScore(Long number, String giftid) {
		List<Gift> gf=this.findByProperty("giftNo", giftid);
		if(gf.get(0).getStoreNum()<number) return null;
		return gf.get(0).getScore();
	}

	@Override
	public void updateStore(long number, String giftid, Long id, String account) {
		List<Gift> gf=this.findByProperty("giftNo", giftid);
		Gift gift=gf.get(0);
		Long remainStore=gift.getStoreNum()-number;
		Long changenum=gift.getChangeNum()+number;
		gift.setStoreNum(remainStore);
		gift.setChangeNum(changenum);
		this.update(gift);
		scoreService.record(id,gift.getName(),gift.getScore(),number,account);
	}

	@Override
	public boolean resetScore(Long id, Long score) {
		try {
			Gift gift = this.findById(id);
			gift.setScore(score);
			this.update(gift);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
