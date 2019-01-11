package cn.edu.hdu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.dao.impl.PictureDirDaoImpl;
import cn.edu.hdu.dao.impl.RActiPicDaoImpl;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.pojo.RActiPicture;
import cn.edu.hdu.service.RActiPicService;

@Service(value="actiPicService")
public class RActiPicImpl extends GenericService<RActiPicture> implements RActiPicService {
	@Resource
	private FileServiceImpl fileService;
	@Resource
	private PictureDirDaoImpl pdService;
	
	public RActiPicDaoImpl getRActiPicDao() {
        return (RActiPicDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setRActiPicDao(RActiPicDaoImpl apDao) {
	    this.setGenericDao(apDao);
	}
	

	@Override
	public boolean saveRActiPict(Long activityId, Long seriesId) {
		try {
			List<PictureDir> pictureList = fileService.findByProperty("seriesId", seriesId);
			for(PictureDir pict:pictureList){
				RActiPicture ap = new RActiPicture();
				ap.setActivityId(activityId);
				ap.setPictureId(pict.getId());
				this.save(ap);
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	

	
	

	

}
