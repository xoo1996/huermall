package cn.edu.hdu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.And;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.dao.impl.MemBatDaoImpl;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.SuperChangeHistory;
import cn.edu.hdu.service.MemBatService;
import cn.edu.hdu.util.DataUtil;


//新增
@Service("memBatService")
public class MemBatServiceImpl extends GenericService<MemBat> implements MemBatService {
	private Logger logger=Logger.getLogger(MemBatServiceImpl.class);
	
	@Resource
	private GlobalCfgServiceImpl globalCfgService;
	
	@Resource
	private BatteryServiceImpl batteryService;
	
	public MemBatDaoImpl getMemBatDao() {
        return (MemBatDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setMemBatDaoImpl(MemBatDaoImpl memBatDao) {
	    this.setGenericDao(memBatDao);
	}
	
	
	@Override
	public long getBatteryReQty(String memberNO,String batteryType) {
		List<MemBat> memBat=this.findByProperty("memberNO", memberNO);
		if(memBat!=null){
			for (MemBat mb : memBat) {
				if(mb.getBatteryType()!=null&&mb.getBatteryType().equals(batteryType)){
					return mb.getBatteryReQty();
				}
			}
		}
		logger.info("getBatteryReQty()：在剩余电池查询方法中找不到该会员");
		return 0;
	}

	@Override
	public long getBatteryQty(String memberNO,String batteryType) {
		List<MemBat> memBat=this.findByProperty("memberNO", memberNO);
		if(memBat!=null){
			for (MemBat mb : memBat) {
				if(mb.getBatteryType()!=null&&mb.getBatteryType().equals(batteryType)){
					return mb.getBatteryQty();
				}
			}
		}
		logger.info("getBatteryQty()：在电池数量查询方法中找不到该会员");
		return 0;
	}

	@Override
	public void addMenBat(MemBat memBat) {
		this.save(memBat);
		//this.getmemBatDao().save(memBat);
		
	}

	@Override
	public Row getMemBatList(String memberId, int rownum, int page,String account) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("m.id,m.member_id,m.battery_type,m.battery_re_qty,m.battery_qty ")
				.from("mem_bat m")
				.where("m.member_id", memberId)
				.where("m.status", "full")
				.orderby("m.id", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(sql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page, rownum).list();
		
		String sql_sum = hb.select("count(*) ")
				.from("mem_bat m")
				.where("m.member_id", memberId)
				.where("m.status", "full")
				.orderby("m.id", "DESC")
				.buildHql();
		query = this.getSqlQuery(sql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
				Row row = new Row();
				row.put("id", acc[0]);
				row.put("ready_get_bat", ((Integer.parseInt(acc[4].toString())-Integer.parseInt(acc[3].toString()))+"")); //以领取的电池数
				row.put("member_id", acc[1]);
				row.put("battery_type", batteryService.findByProperty("type", acc[2]).get(0).getName());
				try {
					String b = (String)webSend.sendService(new Object[] {account,acc[2]}, UrlCode.URL, "battNum");
					if(b==null||b.equals(""))
						b="0";
					row.put("battery_num",b);//很慢 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				row.put("battery_re_qty", acc[3]);
				rows.add(row);
			
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public Row superGetBatEventListNotGetBatStock(String memberName, String memberNo, String memberPhone, String storeName,
			String page, String rowNumber){
		String hql_data = "select mb.id,m.name name1,m.phone,s.gctnm,bat.name name2,mb.battery_qty,mb.battery_re_qty,mb.data "
				+ "from mem_bat mb left join member m on m.id=mb.member_id "
				+ "left join store s on s.gctid = mb.store_no "
				+ "left join battery bat on bat.type = mb.battery_type";
		HqlBuilder builder = new HqlBuilder();
		if(memberPhone != null)
			builder.where("m.phone", memberPhone);
		String w = builder.like("m.name", memberName)
			.like("m.member_no", memberNo)
			.orderby("mb.data", "desc")
			.buildHql();
		hql_data += w;
		Query query = this.getSqlQuery(hql_data);
		builder.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list(); 
		String hql_sum = "select count(*) from mem_bat mb left join member m on m.id=mb.member_id "
				+ "left join store s on s.gctid = mb.store_no "
				+ "left join battery bat on bat.type = mb.battery_type";
		
		if(memberPhone != null)
			builder.where("m.phone", memberPhone);
		w = builder.like("m.name", memberName)
				.like("m.member_no", memberNo)
				.orderby("mb.data", "desc")
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
			row.put("name", acc[1]);
			row.put("phone", acc[2]);
			row.put("storeName", acc[3]);
			row.put("battery_type", acc[4]);
			row.put("battery_qty", acc[5]);
			row.put("battery_re_qty", acc[6]);
			row.put("handle_date", acc[7] == null?"":sdf.format((Date)acc[7]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public void getBat(Long id,long getBatNum) {
		List<MemBat> memBat=this.findByProperty("id", id);
		String batteryType = memBat.get(0).getBatteryType();
		long batteryReQty = memBat.get(0).getBatteryReQty()-getBatNum;
		if(batteryReQty==0){
			getMemBatDao().updateMemBat(id, batteryType, batteryReQty);
			getMemBatDao().updateMemBatStatus(id, "empty");
		}else{
			getMemBatDao().updateMemBat(id, batteryType, batteryReQty);
		}
		
	}
	
	public void delMemBat(String id,String op){
		getMemBatDao().updateMemBatStatus(id, op, "del");
	}
	
	@Override
	public MemBat findMemBatById(Long id) {
		List<MemBat> memBat=this.findByProperty("id", id);
		return memBat.get(0);
	}

	public MemBat getMemBatByOrderId(String orderId){
		List<MemBat> memBat=this.findByProperty("orderId", orderId);
		if(memBat!=null&&memBat.size()>0)
			return memBat.get(0);
		return null;
	}
		
	public MemBat getMemBatByChargeId(String chargeId){
		List<MemBat> memBat=this.findByProperty("chargeId", chargeId);
		if(memBat!=null&&memBat.size()>0)
			return memBat.get(0);
		return null;
	}
	
	@Override
	public String getMemberIdById(String id) {
		Long longid = Long.parseLong(id);
		List<MemBat> memBat=this.findByProperty("id", longid);
		String realId;
		if(memBat!=null&&memBat.size()>0){
			realId = memBat.get(0).getMemberId();
			return realId;
		}
		return null;
	}

	@Override
	public void updateMemBatNUM(MemBat membat) {
		MemBat mb=this.findById(membat.getId());
		mb.setBatteryQty(membat.getBatteryQty());
		mb.setBatteryReQty(membat.getBatteryReQty());
		this.update(mb);
	}
	
	@Override
	public void updateMemBatType(MemBat membat) {
		MemBat mb=this.findById(membat.getId());
		mb.setBatteryType(membat.getBatteryType());
		this.update(mb);
	}

	@Override
	public void delMenBat(Long id) {
		this.delete(this.findMemBatById(id));
	}

	@Override
	public void updateMembatStatus(String op, String value, String status) {
		getMemBatDao().updateMemBatStatus(value, op, status);
	}

	@Override
	public void updateMembatStatus(List<Object[]> list, String op, String status) {
		//for(MemBat memBat:list){
		for(int i =0;i<list.size();i++){
			getMemBatDao().updateMemBatStatus(String.valueOf(list.get(i)[0]),op,status);
		}	
	}

	
		
}
