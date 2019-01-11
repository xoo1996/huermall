package cn.edu.hdu.dto;

import cn.edu.hdu.pojo.Activity;
import cn.edu.hdu.pojo.PictureDir;

public class PictureDirDto extends PictureDir{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private Activity act;

	public Activity getAct() {
		return act;
	}

	public void setAct(Activity act) {
		this.act = act;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static PictureDirDto getInstance(PictureDir pd){
		PictureDirDto pdd = new PictureDirDto();
		pdd.setId(pd.getId());
		pdd.setModuleName(pd.getModuleName());
		pdd.setPath(pd.getPath());
		pdd.setSeriesId(pd.getSeriesId());
		pdd.setUploadDate(pd.getUploadDate());
		pdd.setPicIndex(pd.getPicIndex());
		pdd.setActivityId(pd.getActivityId());
		return pdd;
	}
	
}
