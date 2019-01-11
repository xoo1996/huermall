package cn.edu.hdu.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.ScoreEventType;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.dao.impl.BatEventDaoImpl;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.service.BatEventService;
import cn.edu.hdu.util.DataUtil;

//新增
@Service("batEventService")
public class BatEventServiceImpl extends GenericService<BatEvent> implements BatEventService {
	
	@Resource
	private BatteryServiceImpl batteryService;
	
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	public BatEventDaoImpl GetBatDao() {
        return (BatEventDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setBatEventDao(BatEventDaoImpl batEventDao) {
	    this.setGenericDao(batEventDao);
	}

	@Override
	public Row getBatList(String memberName, String memberId, String memberPhone, String eventType, String storeName,
			String row, String rowNumber, String accId, List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBatEventCurrentId(String memberNO,String inStoreNo,String getStoreNo,String batteryType, long getBatNum,String memBatId){

		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("b.id ")
				.from("bat_event b")
				.where("b.member_id", memberNO)
				.where("b.in_store_no", inStoreNo)
				.where("b.get_store_no", getStoreNo)
				.where("b.battery_type", batteryType)
				.where("b.get_bat_num", getBatNum)
				.where("b.membat_id", memBatId)
				.orderby("b.handle_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(sql_data);
		hb.setParam(query);
		
		
		return String.valueOf(query.list().get(0));
		
	}
	@Override
	public void record(String memberId, String inStoreNo, String getStoreNo, String batteryType, long getBatNum,String memBatId) {
		BatEvent batEvent = new BatEvent();
		batEvent.setMemberId(memberId);
		batEvent.setInStoreNo(inStoreNo);
		batEvent.setGetStoreNo(getStoreNo);
		batEvent.setBatteryType(batteryType);
		batEvent.setGetBatNum(getBatNum);
		batEvent.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		batEvent.setMemBatId(memBatId);

		this.save(batEvent);
	}
	
	public void sendToOldSystem(String memberId, String inStoreNo, String getStoreNo, String batteryType, long getBatNum,String memBatId,String thisBatEventId){
		Object[] objects1 =  new Object[] {getBatNum,inStoreNo,batteryType};
		Object[] objects2 =  new Object[] {memberId,inStoreNo,getStoreNo,batteryType,getBatNum,memBatId,thisBatEventId};
		try {
			webSend.sendService(objects1, UrlCode.URL, "battChange");
			webSend.sendService(objects2, UrlCode.URL, "batEvent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Row getBatEventList(String memberId, String inStoreNo, String batteryType, String page,
			String rowNumber) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("b.id,b.member_id,b.in_store_no,b.get_store_no,b.battery_type,b.get_bat_num,b.handle_date ")
				.from("bat_event b")
				.where("b.member_id", memberId)
				.where("b.in_store_no", inStoreNo)
				.where("b.battery_type", batteryType)
				.orderby("b.handle_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(sql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list();
		
		String sql_sum = hb.select("count(*) ")
				.from("bat_event b")
				.where("b.member_id", memberId)
				.where("b.in_store_no", inStoreNo)
				.where("b.battery_type", batteryType)
				.orderby("b.handle_date", "DESC")
				.buildHql();
		query = this.getSqlQuery(sql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("id", acc[0]);
			row.put("member_id", acc[1]);
			row.put("in_store_no", acc[2]);
			row.put("get_store_no", acc[3]);
			row.put("battery_type",  batteryService.findByProperty("type", acc[4]).get(0).getName());
			row.put("get_bat_num", acc[5]);
			row.put("handle_date", new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(acc[6]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public Row getBatEventList(String memberId, String memberName, String memberNo, String memberPhone,
			String storeName, String page, String rowNumber, String account) {
		String hql_data = "select b.id,b.handle_date,m.member_no,m.name name1,m.phone,acc.name name2,b.get_bat_num,bat.name,ac.name name3 "
				+ "from bat_event b left join member m on m.id=b.member_id "
				+ "left join account acc on acc.id = m.store_id "
				+ "left join account ac on ac.account = b.in_store_no "
				+ "left join battery bat on bat.type = b.battery_type";
		HqlBuilder builder = new HqlBuilder();
		if(account != null)
			builder.where("b.IN_STORE_NO", account);
		String w = builder.like("m.name", memberName)
			.like("b.member_id", memberId)
			.like("m.member_no", memberNo)
			.like("m.phone", memberPhone)
			.like("name2",storeName)
			.where("b.in_store_no","")
			.orderby("b.handle_date", "desc")
			.buildHql();
		hql_data += w;
		Query query = this.getSqlQuery(hql_data);
		builder.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list(); 
		String hql_sum = "select count(*) from bat_event b left join member m on m.id=b.member_id "
						+ "left join account acc on acc.id = m.store_id "
						+ "left join battery bat on bat.type = b.battery_type ";
		
		if(account != null)
			builder.where("b.IN_STORE_NO", account);
		w = builder.like("m.name", memberName)
			.like("b.member_id", memberId)
			.like("m.member_no", memberNo)
			.like("m.phone", memberPhone)
			.like("acc.name",storeName)
			.orderby("b.handle_date", "desc")
			.buildHql();
		hql_sum += w;
		query = this.getSqlQuery(hql_sum);
		builder.setParam(query);
		Object accSum = query.uniqueResult();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("id", acc[0]);
			row.put("handle_date", acc[1] == null?"":sdf.format((Date)acc[1]));
			row.put("member_no", acc[2]);
			row.put("name", acc[3]);
			row.put("phone", acc[4]);
			row.put("storeName", acc[5]);
			row.put("get_bat_num", acc[6]);
			row.put("battery_type", acc[7]);
			row.put("in_store_name", acc[8]);
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public Row superGetBatEventList(String memberId, String memberName, String memberNo, String memberPhone,
			String storeName, String page, String rowNumber, String account) {
		String hql_data = "select b.id,b.handle_date,m.member_no,m.name name1,m.phone,acc.name name2,b.get_bat_num,bat.name,ac.name name3,b.membat_id "
				+ "from bat_event b left join member m on m.id=b.member_id "
				+ "left join account acc on acc.id = m.store_id "
				+ "left join account ac on ac.account = b.in_store_no "
				+ "left join battery bat on bat.type = b.battery_type";
		HqlBuilder builder = new HqlBuilder();
		if(account != null)
			builder.where("b.IN_STORE_NO", account);
		String w = builder.like("m.name", memberName)
			.like("b.member_id", memberId)
			.like("m.member_no", memberNo)
			.like("m.phone", memberPhone)
			.like("name2",storeName)
			.where("b.in_store_no","")
			.orderby("b.handle_date", "desc")
			.buildHql();
		hql_data += w;
		Query query = this.getSqlQuery(hql_data);
		builder.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list(); 
		String hql_sum = "select count(*) from bat_event b left join member m on m.id=b.member_id "
						+ "left join account acc on acc.id = m.store_id "
						+ "left join battery bat on bat.type = b.battery_type ";
		
		if(account != null)
			builder.where("b.IN_STORE_NO", account);
		w = builder.like("m.name", memberName)
			.like("b.member_id", memberId)
			.like("m.member_no", memberNo)
			.like("m.phone", memberPhone)
			.like("acc.name",storeName)
			.orderby("b.handle_date", "desc")
			.buildHql();
		hql_sum += w;
		query = this.getSqlQuery(hql_sum);
		builder.setParam(query);
		Object accSum = query.uniqueResult();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("id", acc[0]);
			row.put("handle_date", acc[1] == null?"":sdf.format((Date)acc[1]));
			row.put("member_no", acc[2]);
			row.put("name", acc[3]);
			row.put("phone", acc[4]);
			row.put("storeName", acc[5]);
			row.put("get_bat_num", acc[6]);
			row.put("battery_type", acc[7]);
			row.put("in_store_name", acc[8]);
			row.put("membat_id", acc[9]);
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public String findMemberIdById(long id) {
		try{
			List<BatEvent> batEvent=this.findByProperty("id", id);
			String memberId=batEvent.get(0).getMemberId();
			return memberId;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public void updataBatEventNUM(BatEvent batEvent) {
		BatEvent be =this.findById(batEvent.getId());
		be.setGetBatNum(batEvent.getGetBatNum());
		this.update(be);
	}
	
	@Override
	public void updataAllBatEventByMemBatId(String membatId,String batteryType){
		List<BatEvent> batEventList = this.findByProperty("memBatId",membatId);
		for(BatEvent be:batEventList){
			be.setBatteryType(batteryType);
			this.update(be);
		}
	}
	@Override
	public List<BatEvent> getBatEventList(String membatId){
		List<BatEvent> batEventList = this.findByProperty("memBatId",membatId);
		return batEventList;
	}
	
	@Override
	public Row getJoinStoreGetBatHistoryList(String storeName, String storeNo,String startTime,String endTime, int page, int rowNumber) {

		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("b.id,s.gctnm as name1,m.name as name3,ss.gctnm as name2,bat.name as name4,b.get_bat_num,b.handle_date ")
				.from("bat_event b left join store s on s.gctid=b.in_store_no "
						+ "left join store ss on ss.gctid = b.get_store_no "
						+ "left join battery bat on bat.type = b.battery_type "
						+ "left join account acc on acc.account = b.in_store_no "
						+ "left join role ro on ro.role_id = acc.role_id "
						+ "left join member m on m.id = b.member_id ")
				.like("s.gctnm", storeName)
				.like("s.gctid", storeNo)
				.buildHql();
		
		if((storeName!=null&&storeName!="")||(storeNo!=null&&storeNo!="")){
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_data+="  and b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_data+=" and ro.role_name = '加盟店' ";
			}
			else{
				sql_data+=" and ro.role_name = '加盟店' ";
			}
		}
		else{
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_data+="  where b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_data+=" and ro.role_name = '加盟店' ";
			}
			else {
				sql_data += " where ro.role_name = '加盟店' ";			
			}
		}

		sql_data+=" order by s.gctid ASC"; 
		
				
		Query query = this.getSqlQuery(sql_data);
		
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page,rowNumber).list();
		
		String sql_sum = hb.select("count(*) ")
				.from("bat_event b left join store s on s.gctid=b.in_store_no "
						+ "left join store ss on ss.gctid = b.get_store_no "
						+ "left join battery bat on bat.type = b.battery_type "
						+ "left join account acc on acc.account = b.in_store_no "
						+ "left join role ro on ro.role_id = acc.role_id "
						+ "left join member m on m.id = b.member_id ")
				.like("s.gctnm", storeName)
				.like("s.gctid", storeNo)
				.buildHql();
		
		if((storeName!=null&&storeName!="")||(storeNo!=null&&storeNo!="")){
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_sum+="  and b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_sum+=" and ro.role_name = '加盟店' ";
			}
			else{
				sql_sum+=" and ro.role_name = '加盟店' ";
			}
		}
		else{
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_sum+="  where b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_sum+=" and ro.role_name = '加盟店' ";
			}
			else {
				sql_sum += " where ro.role_name = '加盟店' ";			
			}
		}

		sql_data+=" order by s.gctid ASC"; 
		query = this.getSqlQuery(sql_sum);
		hb.setParam(query);
		BigDecimal accSum = (BigDecimal) query.uniqueResult();
		
		

		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("id", acc[0]);
			row.put("instorename", acc[1]);
			row.put("membername", acc[2]);
			row.put("getstorename", acc[3]);
			row.put("batname", acc[4]);
			row.put("getnum", acc[5]);
			row.put("handle_date", new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(acc[6]));
			rows.add(row);
		}
			
		Row row = new Row();
		row.put("total",accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public List<Object[]> getJoinStoreGetBatHistoryList(String storeName, String storeNo, String startTime,
			String endTime) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("b.id,s.gctnm as name1,m.name as name3,ss.gctnm as name2,bat.name as name4,b.get_bat_num,b.handle_date ")
				.from("bat_event b left join store s on s.gctid=b.in_store_no "
						+ "left join store ss on ss.gctid = b.get_store_no "
						+ "left join battery bat on bat.type = b.battery_type "
						+ "left join account acc on acc.account = b.in_store_no "
						+ "left join role ro on ro.role_id = acc.role_id "
						+ "left join member m on m.id = b.member_id ")
				.like("s.gctnm", storeName)
				.like("s.gctid", storeNo)
				.buildHql();
		
		if((storeName!=null&&storeName!="")||(storeNo!=null&&storeNo!="")){
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_data+="  and b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_data+=" and ro.role_name = '加盟店' ";
			}
			else{
				sql_data+=" and ro.role_name = '加盟店' ";
			}
		}
		else{
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
				sql_data+="  where b.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";
				sql_data+=" and ro.role_name = '加盟店' ";
			}
			else {
				sql_data += " where ro.role_name = '加盟店' ";			
			}
		}

		sql_data+=" order by s.gctid ASC"; 
		
				
		Query query = this.getSqlQuery(sql_data);
		
		hb.setParam(query);
		List<Object[]> accList = query.list();
		
		return accList;
	}

}
