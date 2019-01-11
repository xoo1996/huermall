package cn.edu.hdu.service;

import java.util.List;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.pojo.Activity;

public interface ActivityService {

	/**
	 * 创建活动
	 * @param activity Activity实例，只记录了活动的内容content
	 * @return
	 */
	public boolean createActivity(Activity activity);
	/**
	 * 更新活动
	 * @param activity Activity实例
	 * @return
	 */
	public boolean updateActivity(Activity activity);
	
	/**
	 * 获得活动描述内容
	 * @param activityId  活动编号
	 * @return 返回字符串实体 ：存在描述时为String实体；不存在是返回空字符串
	 */
	public String getActivityContent(Long activityId);
	/**
	 * 获得符合条件的活动
	 * @param naem
	 * @return
	 */
	public Row getActivityList(String name,String status,int rows,int page);
	
	/**
	 * 发布活动
	 * @param activity
	 * @return
	 */
	public boolean releaseActivity(Long activityId);
	/**
	 * 获得已经发布的活动列表
	 * @return
	 */
	public List<Activity> getReleasedActivity();
	
	/**
	 * 修改活动内容
	 * @param activity
	 * @return
	 */
	public boolean editActivity(Activity activity);
	
	/**
	 * 删除活动：删除activity表，图片，以及图片活动关联表，删除服务器上图片
	 * 
	 * @param activity
	 * @return
	 */
	public boolean deleteActivity(Long activityId);

	/**
	 * 修改活动对应积分
	 * @param id
	 * @param score
	 * @return
	 */
	boolean resetScore(Long id, Long score);
	
	/**
	 * 获得下拉框数据
	 * @return
	 */
	public List<Row> getOptionList();
	
	/**
	 * 匹配轮播和对应活动
	 * @param pictId
	 * @param actiId
	 * @return
	 */
	public boolean matchLunboActivity(Long pictId,Long actiId);
}
