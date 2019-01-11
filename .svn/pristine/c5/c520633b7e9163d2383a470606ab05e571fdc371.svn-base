package cn.edu.hdu.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.dto.PictureDirDto;

public interface FileService {

	/**
	 * 存储图片的路径信息
	 * @param path  服务器存储路径
	 * @param moduleName 所属模块名称
	 * @param seriesId 串号
	 * @return
	 */
	public Long saveImgPath(String path,String moduleName,Long seriesId); 
	/**
	 * 存储图片的路径信息
	 * @param path  服务器存储路径
	 * @param moduleName 所属模块名称
	 * @param seriesId 串号
	 * @return
	 */
	public boolean saveImgPath(String path,String moduleName,Long seriesId,Long index); 
	/**
	 * 获得指定模块下的图片的服务器路径
	 * @param moduleName
	 * @return list实体，不会返回null
	 */
	public List<String> getImgPath(String moduleName);
	
	/**
	 * 获得指定模块下的图片的请求路径
	 * @param moduleName
	 * @return list实体，不会返回null
	 */
	public List<String> getImgUrl(String moduleName);
	
	/**
	 * 保存图片，全过程，包括图片存储、路径存储
	 * @param file
	 * @param seriesId
	 * @return
	 * @throws IOException
	 */
	public Long saveImg(String moduleDir,String moduleName,
			MultipartFile file,Long seriesId) throws IOException;
	
	/**
	 * 获得指定模块下的图片的请求路径
	 * @param moduleName
	 * @return list实体，不会返回null
	 */
	public List<PictureDirDto> getImgDir(String moduleName);
	
	/**
	 * 更新轮播图片索引
	 * @return
	 */
	public boolean correctLunboImgIndex();
	
	/**
	 * 更新轮播图片索引
	 * @return
	 */
	public boolean correctLunboImgIndex(String[] dataArray);
	
	/**
	 * 删除服务器上的图片
	 * @param pictureId pictureDir实体id
	 * @return
	 */
	public boolean deleteLocalImg(Long pictureId);
}
