package cn.edu.hdu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.edu.hdu.dao.impl.PictureDirDaoImpl;
import cn.edu.hdu.dao.impl.RGiftPictureDaoImpl;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.pojo.RGiftPicture;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.RGiftPictureService;

@Service(value="giftPictureService")
public class RGiftPictureServiceImpl extends GenericService<RGiftPicture> implements RGiftPictureService {

	@Resource
	private FileService fileService;
	@Resource
	private PictureDirDaoImpl pdService;
	
	public RGiftPictureDaoImpl getRGiftPictureDao() {
        return (RGiftPictureDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setRGiftPictureDao(RGiftPictureDaoImpl rGiftPictureDao) {
	    this.setGenericDao(rGiftPictureDao);
	}
	
	
	@Override
	public List<PictureDir> getPictureDirListBy(Long giftId) {
		try {
			List<RGiftPicture> gpList = this.findByProperty("giftId", giftId);
			List<PictureDir> pdList = new ArrayList<PictureDir>();
			if(gpList == null ) 
				return pdList;
			for(RGiftPicture gp:gpList){
				PictureDir pd = pdService.findById(gp.getPictureId());
				pdList.add(pd);
			}
			return pdList;
		} catch (Exception e) {
			throw e;
		}
	}

}
