package cn.edu.hdu.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.GlobalParam;
import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.dao.impl.MemBatDaoImpl;
import cn.edu.hdu.dao.impl.SuperChangeHistoryDaoImpl;
import cn.edu.hdu.dao.impl.VerifyBatDaoImpl;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.VerifyBat;
import cn.edu.hdu.service.VerifyBatService;
import cn.edu.hdu.util.DataUtil;

@Service("verifyBatService")
public class VerifyBatServiceImpl  extends GenericService <VerifyBat> implements VerifyBatService{
			
	public VerifyBatDaoImpl getVerifyBatDao() {
        return (VerifyBatDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setVerifyBatDao(VerifyBatDaoImpl verifyBatDao) {
	    this.setGenericDao(verifyBatDao);
	}

	
	@Override
	public void addVerifyBat(VerifyBat verifyBat) {
		this.save(verifyBat);	
	}
	
	@Override
	public void updateStatus(String op, String value, String status) {
		getVerifyBatDao().updateVerifyBatStatus(op, value, status);
		
	}
	
	@Override
	public Row getVerifyingList(String memberName, String memberNo, String memberPhone, String storeName,
			String page, String rowNumber) {
		String hql_data = "select vb.id,s.gctnm,m.name name1,m.phone,bat.name name2,vb.bat_num,vb.apply_date "
				+ "from verify_bat vb left join member m on m.id=vb.mem_id "
				+ "left join store s on s.gctid = vb.store_id "
				+ "left join battery bat on bat.type = vb.bat_type "
				+ "where vb.status='verifying' ";
		HqlBuilder builder = new HqlBuilder();
		if(memberPhone != null)
			hql_data += "and m.phone = '"+memberPhone+"' ";
		String w = builder.like("m.name", memberName)
			.like("m.member_no", memberNo)
			.orderby("vb.apply_date", "desc")
			.buildHql();
		hql_data += w;
		Query query = this.getSqlQuery(hql_data);
		builder.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list(); 
		String hql_sum = "select count(*) from verify_bat vb left join member m on m.id=vb.mem_id "
				+ "left join store s on s.gctid = vb.store_id "
				+ "left join battery bat on bat.type = vb.bat_type "
				+ "where vb.status='verifying' ";
		
		if(memberPhone != null)
			hql_data += "and m.phone = '"+memberPhone+"' ";
		 w = builder.like("m.name", memberName)
					.like("m.member_no", memberNo)
					.orderby("vb.apply_date", "desc")
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
			row.put("storeId", acc[1]);
			row.put("memName", acc[2]);
			row.put("phone", acc[3]);
			row.put("batType", acc[4]);
			row.put("batNum", acc[5]);
			row.put("applyDate", acc[6] == null?"":sdf.format((Date)acc[6]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public List<Object[]> getVerifyBatList(String op,String value) {
		List<Object[]> list = getVerifyBatDao().getVerifyBatList(op, value);
		return list;
	}
	
	@Override
	public void updateVerifyBatStatus(List<Object[]> verifyBats,String value) {
		for(int i =0;i<verifyBats.size();i++){
			getVerifyBatDao().updateVerifyBatStatus("id",String.valueOf(verifyBats.get(i)[0]),value);
		}	
		
	}
	@Override
	public void updateVerifyIdAndDate(List<Object[]> verifyBats, String id, String date) {
		for(int i =0;i<verifyBats.size();i++){
			getVerifyBatDao().updateVerifyBatVerifyIdAndDate("id",String.valueOf(verifyBats.get(i)[0]),id,date);
		}	
	}
	
	@Override
	public Row getVerifyBatList(String memberName, String memberNo, String memberPhone, String storeNo,
			String statusCheck,String page, String rowNumber) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("vb.id,s.gctnm,m.name name1,m.phone,bat.name name2,vb.bat_num,vb.apply_date,vb.status ")
				.from("verify_bat vb left join member m on m.id=vb.mem_id left join store s on s.gctid = vb.store_id left join battery bat on bat.type = vb.bat_type ")
				.where("m.name", memberName)
				.where("m.id", memberNo)
				.where("m.phone", memberPhone)
				.where("s.gctid", storeNo)
				.where("vb.status", statusCheck)
				.orderby("vb.apply_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(sql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list();
		
		String sql_sum = hb.select("count(*) ")
				.from("verify_bat vb left join member m on m.id=vb.mem_id left join store s on s.gctid = vb.store_id left join battery bat on bat.type = vb.bat_type ")
				.where("m.name", memberName)
				.where("m.id", memberNo)
				.where("m.phone", memberPhone)
				.where("s.gctid", storeNo)
				.where("vb.status", statusCheck)
				.orderby("vb.apply_date", "DESC")
				.buildHql();
		query = this.getSqlQuery(sql_sum);
		hb.setParam(query);
		
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		for(Object[] acc:accList){
				Row row = new Row();
				row.put("id", acc[0]);
				row.put("id", acc[0]);
				row.put("storeId", acc[1]);
				row.put("memName", acc[2]);
				row.put("phone", acc[3]);
				row.put("batType", acc[4]);
				row.put("batNum", acc[5]);
				row.put("applyDate", acc[6] == null?"":sdf.format((Date)acc[6]));
				if(String.valueOf(acc[7]).equals("pass")){
					row.put("status", "通过");
				}else if(String.valueOf(acc[7]).equals("reject")){
					row.put("status", "未通过");
				}else if(String.valueOf(acc[7]).equals("verifying")){
					row.put("status", "审核中");
				}
				rows.add(row);
			
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

}
