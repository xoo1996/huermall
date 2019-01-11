package cn.edu.hdu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.ActivityCode;
import cn.edu.hdu.dao.impl.ActivityDaoImpl;
import cn.edu.hdu.pojo.Activity;
import cn.edu.hdu.pojo.Gift;
import cn.edu.hdu.pojo.PictureDir;
import cn.edu.hdu.pojo.RActiPicture;
import cn.edu.hdu.service.ActivityService;
import cn.edu.hdu.util.DataUtil;
import cn.edu.hdu.util.FileUtil;
@Service
public class ActivityServiceImpl extends GenericService<Activity> implements
		ActivityService {

	@Resource
	private RActiPicImpl apService;
	
	@Resource
	private FileServiceImpl fileService;
	
	
	public ActivityDaoImpl getActivityDao(){
		return (ActivityDaoImpl)this.getGenericDao();
	}
	@Autowired
	public void setActivityDao(ActivityDaoImpl activityDao) {
	    this.setGenericDao(activityDao);
	}
	
	@Override
	public boolean createActivity(Activity activity) {
		activity.setUpdateDate(new Date());
		activity.setStatus(ActivityCode.NOT_RELEASED);
		try {
			this.getActivityDao().save(activity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateActivity(Activity activity) {
		Activity act = this.findById(activity.getId());
		act.setContent(activity.getContent());
		act.setCoverUrl(activity.getCoverUrl());
		act.setName(activity.getName());
		act.setUpdateDate(new Date());
		try {
			this.getActivityDao().update(act);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public String getActivityContent(Long activityId) {
		if(activityId == null){
			return "";
		}
		try {
			Activity activity = this.getActivityDao().findById(activityId);
			if(activity == null){
				return "";
			}else{
				return activity.getContent();
			}
		} catch (Exception e) {
			return "";
		}
	}
	@Override
	public Row getActivityList(String name, String status, int rowNum,
			int page) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Activity act")
				.like("act.name", name)
				.like("act.status", status)
				.buildHql();
		Query query = this.getQuery(hql_data);
		hb.setParam(query);
		List<Activity> actList = DataUtil.getPages(query, page, rowNum).list();
		String hql_sum = hb.select("count(*)")
				.from("Activity act")
				.like("act.name", name)
				.like("act.status", status)
				.buildHql();
		query = this.getQuery(hql_sum);
		hb.setParam(query);
		Object actSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Activity act:actList){
			Row row = new Row();
			row.put("checkboxid", act.getId());
			row.put("name", act.getName());
			row.put("releaseDate", act.getReleaseDate() == null?"":sdf.format(act.getReleaseDate()));
			row.put("updateDate", act.getUpdateDate() == null?"":sdf.format(act.getUpdateDate()));
			row.put("status",ActivityCode.strMap.get(act.getStatus()));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", actSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public boolean releaseActivity(Long activityId) {
		try {
			Activity act = this.getActivityDao().findById(activityId);
			act.setStatus(ActivityCode.RELEASED);
			act.setReleaseDate(new Date());
			this.save(act);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<Activity> getReleasedActivity() {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Activity act")
				.where("act.status", ActivityCode.RELEASED)
				.orderby("act.releaseDate", "desc")
				.buildHql();
		Query query = this.getQuery(hql_data);
		hb.setParam(query);
		List<Activity> actList = query.list();
		return actList;
	}
	
	@Override
	public boolean editActivity(Activity activity) {
		Activity act = this.findById(activity.getId());
		act.setContent(activity.getContent());
		if(activity.getCoverUrl()!=null){
			act.setCoverUrl(activity.getCoverUrl());
		}
		act.setName(activity.getName());
		act.setUpdateDate(new Date());
		try {
			this.getActivityDao().save(act);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	@Transactional
	public boolean deleteActivity(Long activityId) {
		// 删除activity表，图片，以及图片活动关联表，删除服务器上图片
		Activity activity = this.findById(activityId);
		if(activity == null){
			return false;
		}
		//删除封面
		String coverUrl = activity.getCoverUrl();
		if(coverUrl != null){
			String[] sss = coverUrl.split("/");
			Long pictureId = Long.parseLong(sss[sss.length-1]);//获得图片实体
			fileService.deleteLocalImg(pictureId);//删除图片路径记录和本地图片
		}
		//删除活动关联图片
		List<RActiPicture> cpList = apService.findByProperty("activityId", activityId);
		for(RActiPicture cp:cpList){
			Long pdId = cp.getPictureId();
			apService.deleteById(cp.getId());//删除活动关联表记录
			fileService.deleteLocalImg(pdId);//删除图片路径记录和本地图片
		}
		//删除活动主体
		this.deleteById(activityId);
		return true;
	}
	
	@Override
	public boolean resetScore(Long id, Long score) {
		try {
			Activity gift = this.findById(id);
			gift.setScore(score);
			this.update(gift);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<Row> getOptionList() {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("Activity act where act.status = '" + ActivityCode.RELEASED + "'")
				.buildHql();
		List<Activity> actList = this.getActivityDao().findByHql(hql_data, null);
		List<Row> rows = new ArrayList<Row>();
		for(Activity act:actList){
			Row row = new Row();
			row.put("id", act.getId());
			row.put("text", act.getName());
			rows.add(row);
		}
		return rows;
	}
	
	@Override
	public boolean matchLunboActivity(Long pictId, Long actiId) {
		try {
			PictureDir lunbo = fileService.findById(pictId);
			lunbo.setActivityId(actiId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
