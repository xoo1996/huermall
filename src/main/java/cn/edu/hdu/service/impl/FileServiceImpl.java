package cn.edu.hdu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.Code.UploadCode;
import cn.edu.hdu.dao.impl.PictureDirDaoImpl;
import cn.edu.hdu.dto.PictureDirDto;
import cn.edu.hdu.pojo.Activity;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.service.FileService;
import cn.edu.hdu.service.impl.GenericService;
import cn.edu.hdu.util.FileUtil;
import cn.edu.hdu.util.StrUtil;

@Service(value="fileService")
public class FileServiceImpl extends GenericService<PictureDir> implements FileService {
	private static Logger logger = Logger.getLogger(FileServiceImpl.class);
	@Resource
	private ActivityServiceImpl activityService;
	
	@Resource
	private FileService fileService;
	
	public PictureDirDaoImpl getPictureDirDao() {
        return (PictureDirDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setPictureDirDao(PictureDirDaoImpl pictureDirDao) {
	    this.setGenericDao(pictureDirDao);
	}
	
	
	@Override
	public Long saveImgPath(String path,String moduleName,Long seriesId) {
		if(path == null || moduleName == null || seriesId == null){
			return -1L;
		}
		Long id = null;
		try {
			id = this.getPictureDirDao().saveImgPath(path,moduleName, seriesId);
		} catch (Exception e) {
			return -1L;
		}
		if(id != null){
			return id;
		}else{
			return -1L;
		}
	}
	

	@Override
	public List<String> getImgPath(String moduleName) {
		if(moduleName == null){
			return new ArrayList<String>();
		}
		List<String> imgs = new ArrayList<String>();
		try {
			imgs = this.getPictureDirDao().getImgPath(moduleName);
		} catch (Exception e) {
			logger.error(" getImgPath " + moduleName + "查询异常");
			e.printStackTrace();
		}
		return imgs;
	}

	@Override
	public List<String> getImgUrl(String moduleName) {
		if(moduleName == null){
			return new ArrayList<String>();
		}
		List<Long> idList = new ArrayList<Long>();
		List<String> urlList = new ArrayList<String>();
		try {
			idList = this.getPictureDirDao().findByHql("select pd.id from PictureDir pd where pd.moduleName = ?"
					+ "order by pd.picIndex asc", moduleName);
			for(Long id:idList){
				String s = FileUtil.getUrl(UploadCode.MODULE_LUNBO, id);
				urlList.add(s);
			}
		} catch (Exception e) {
			logger.error(" getImgUrl " + moduleName + "查询异常");
			e.printStackTrace();
		}
		return urlList;
	}

	@Override
	public Long saveImg(String moduleDir,String moduleName,
			MultipartFile file, Long seriesId) throws IOException {
		String fileName = file.getOriginalFilename();
		String filePath = FileUtil.getFilePath(moduleDir,fileName,seriesId);
		FileUtil.saveImg(file, filePath); //保存到本地
		Long id = fileService.saveImgPath(filePath,moduleName, seriesId);//保存本地路径
		return id;
	}

	@Override
	public List<PictureDirDto> getImgDir(String moduleName) {
		if(moduleName == null){
			return new ArrayList<PictureDirDto>();
		}
		List<PictureDir> pdList = new ArrayList<PictureDir>();
		List<PictureDirDto> pddList = new ArrayList<PictureDirDto>();
		try {
			pdList = this.getPictureDirDao().findByHql(" from PictureDir pd where pd.moduleName = ? "
					+ "order by pd.picIndex asc", moduleName);
			for(PictureDir pd:pdList){
				PictureDirDto pdd = PictureDirDto.getInstance(pd);
				String s = FileUtil.getUrl(moduleName, pd.getId());
				pdd.setUrl(s);
				if(pdd.getActivityId() != null){
					Activity act = activityService.findById(pdd.getActivityId());
					pdd.setAct(act);
				}
				pddList.add(pdd);
			}
		} catch (Exception e) {
			logger.error(" getImgUrl " + moduleName + "查询异常");
			e.printStackTrace();
		}
		return pddList;
	}


	@Override
	public boolean saveImgPath(String path, String moduleName, Long seriesId,
			Long index) {
		try {
			Long id = this.saveImgPath(path, moduleName, seriesId);
			this.findById(id).setPicIndex(index);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean correctLunboImgIndex() {
		try {
			List<PictureDir> pdList = null;
			String moduleName = UploadCode.MODULE_LUNBO;
			pdList = this.getPictureDirDao().findByHql(" from PictureDir pd where pd.moduleName = ? "
					+ "order by pd.picIndex asc,pd.uploadDate desc", moduleName);
			for(int i = 0;i < pdList.size();i++){
				pdList.get(i).setPicIndex(Integer.valueOf(i + 1).longValue());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean correctLunboImgIndex(String[] dataArray) {
		try {
			for(String data:dataArray){
				if(data.equals("")){
					continue;
				}
				String[] da = data.split("=");
				Long id = Long.parseLong(da[0]);
				Long index = Long.parseLong(da[1]);
				PictureDir pd = this.findById(id);
				if(pd != null){
					pd.setPicIndex(index);
				}
			}
			this.correctLunboImgIndex();
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public boolean deleteLocalImg(Long pictureId) {
		try {
			PictureDir pd = this.findById(pictureId);
			String path = pd.getPath(); //活动图片服务器路径
			FileUtil.deleteFile(path);
			this.delete(pd);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
