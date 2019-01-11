package cn.edu.hdu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.UrlCode;
import cn.edu.hdu.config.webSend;
import cn.edu.hdu.dao.impl.BatEventDaoImpl;
import cn.edu.hdu.dao.impl.SuperChangeHistoryDaoImpl;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.BatEvent;
import cn.edu.hdu.pojo.SuperChangeHistory;
import cn.edu.hdu.service.SuperChangeHistoryService;
import cn.edu.hdu.util.DataUtil;

@Service("superChangeHistoryService")
public class SuperChangeHistoryServiceImpl extends GenericService <SuperChangeHistory> implements SuperChangeHistoryService {
	
	public SuperChangeHistoryDaoImpl GetSuperChangeHistryDao() {
        return (SuperChangeHistoryDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setSuperChangeHistoryDao(SuperChangeHistoryDaoImpl superChangeHistoryDao) {
	    this.setGenericDao(superChangeHistoryDao);
	}

	@Override
	public void record(String storeId, String memberId, String memBatId, String batEventId, String operation, String oldBatType,
			String newBatType, String oldQty, String newQty, String oldReQty, String newReQty, String oldGetNum,
			String newGetNum) {
		
		SuperChangeHistory superChangeHistory = new SuperChangeHistory();
		superChangeHistory.setStoreId(storeId);
		superChangeHistory.setMemberId(memberId);
		superChangeHistory.setMemBatId(memBatId);
		superChangeHistory.setBatEventId(batEventId);
		superChangeHistory.setOperation(operation);
		superChangeHistory.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		superChangeHistory.setOldBatType(oldBatType);
		superChangeHistory.setNewBatType(newBatType);
		superChangeHistory.setOldQty(oldQty);
		superChangeHistory.setNewQty(newQty);
		superChangeHistory.setOldReQty(oldReQty);
		superChangeHistory.setNewReQty(newReQty);
		superChangeHistory.setOldGetNum(oldGetNum);
		superChangeHistory.setNewGetNum(newGetNum);

		this.save(superChangeHistory);
		
	}
	
	

	@Override
	public Row getSuperChangeHistoryList(String storeName, String memberName, String phoneNum,String startTime,String endTime, int page, int rowNumber) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("sch.id,acc.name,mem.name as memname,sch.membat_id,sch.batevent_id,sch.operation,sch.handle_date ")
				.from("super_change_history sch left join account acc on acc.account=sch.store_no "
						+ "left join member mem on mem.id = sch.member_id ")
				.like("acc.name", storeName)
				.like("mem.name", memberName)
				.buildHql();
		
		if(phoneNum!=null&&phoneNum!=""){
			
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and mem.phone ='"+phoneNum+"' ";
			else 
				sql_data += "where mem.phone ='"+phoneNum+"' ";
			
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!="")
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
		}
		else if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
			else
				sql_data += " where sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";

		}
		
		sql_data +=" order by sch.handle_date DESC";
		
		System.out.println(sql_data);
		
		Query query = this.getSqlQuery(sql_data);
		
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page,rowNumber).list();
		String sql_sum = hb.select("count(*) ")
				.from("super_change_history sch left join account acc on acc.account=sch.store_no "
						+ "left join member mem on mem.id = sch.member_id ")
				.like("acc.name", storeName)
				.like("mem.name", memberName)
				.buildHql();
		
		if(phoneNum!=null&&phoneNum!=""){
			
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and mem.phone ='"+phoneNum+"' ";
			else 
				sql_data += "where mem.phone ='"+phoneNum+"' ";
			
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!="")
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
		}
		else if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
			else
				sql_data += " where sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";

		}
		
		sql_data +=" order by sch.handle_date DESC";
		
		query = this.getSqlQuery(sql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("id", acc[0]);
			row.put("name", acc[1]);
			row.put("membername", acc[2]);
			row.put("membat_id", acc[3]);
			row.put("batevent_id", acc[4]);
			row.put("operation", acc[5]);
			row.put("handle_date", new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(acc[6]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public String conCatOperation(String oldBatName, String newBatName, String oldQty, String newQty, String oldReQty,
			String newReQty, String oldGetNum, String newGetNum) {
		
		if(oldBatName.equals("国产--A675电池"))
			oldBatName="国产A675";
		else if(oldBatName.equals("国产--A312电池"))
			oldBatName="国产A312";
		else if(oldBatName.equals("国产--A13电池"))
			oldBatName="国产A13";
		else if(oldBatName.equals("国产--A10电池"))
			oldBatName="国产A10";
		else if(oldBatName.equals("电池--至力长声A10电池"))
			oldBatName="至力长声A10";
		else if(oldBatName.equals("电池--至力长声A13电池"))
			oldBatName="至力长声A13";
		else if(oldBatName.equals("电池--至力长声A312电池"))
			oldBatName="至力长声A312";
		else if(oldBatName.equals("电池--至力长声A675电池"))
			oldBatName="至力长声A675";
		else if(oldBatName.equals("电池--至力耳蜗675电池"))
			oldBatName="至力耳蜗675";
		else if(oldBatName.equals("电池--KIND A10电池"))
			oldBatName="KINDA10";
		else if(oldBatName.equals("电池--KIND A13电池"))
			oldBatName="KINDA13";
		else if(oldBatName.equals("电池--KIND A312电池"))
			oldBatName="KINDA312";
		else if(oldBatName.equals("电池--KIND A675电池"))
			oldBatName="KINDA675";
		else if(oldBatName.equals("电池--雷特威A10电池"))
			oldBatName="雷特威A10";
		else if(oldBatName.equals("电池--雷特威A13电池"))
			oldBatName="雷特威A13";
		else if(oldBatName.equals("电池--雷特威A312电池"))
			oldBatName="雷特威A312";
		else if(oldBatName.equals("电池--雷特威A675电池"))
			oldBatName="雷特威A675";
		else if(oldBatName.equals("电池--雷特威耳蜗675电池"))
			oldBatName="雷特威耳蜗675";
		
		
		if(newBatName.equals("国产--A675电池"))
			newBatName="国产A675";
		else if(newBatName.equals("国产--A312电池"))
			newBatName="国产A312";
		else if(newBatName.equals("国产--A13电池"))
			newBatName="国产A13";
		else if(newBatName.equals("国产--A10电池"))
			newBatName="国产A10";
		else if(newBatName.equals("电池--至力长声A10电池"))
			newBatName="至力长声A10";
		else if(newBatName.equals("电池--至力长声A13电池"))
			newBatName="至力长声A13";
		else if(newBatName.equals("电池--至力长声A312电池"))
			newBatName="至力长声A312";
		else if(newBatName.equals("电池--至力长声A675电池"))
			newBatName="至力长声A675";
		else if(newBatName.equals("电池--至力耳蜗675电池"))
			newBatName="至力耳蜗675";
		else if(newBatName.equals("电池--KIND A10电池"))
			newBatName="KINDA10";
		else if(newBatName.equals("电池--KIND A13电池"))
			newBatName="KINDA13";
		else if(newBatName.equals("电池--KIND A312电池"))
			newBatName="KINDA312";
		else if(newBatName.equals("电池--KIND A675电池"))
			newBatName="KINDA675";
		else if(newBatName.equals("电池--雷特威A10电池"))
			newBatName="雷特威A10";
		else if(newBatName.equals("电池--雷特威A13电池"))
			newBatName="雷特威A13";
		else if(newBatName.equals("电池--雷特威A312电池"))
			newBatName="雷特威A312";
		else if(newBatName.equals("电池--雷特威A675电池"))
			newBatName="雷特威A675";
		else if(newBatName.equals("电池--雷特威耳蜗675电池"))
			newBatName="雷特威耳蜗675";
		
		String operation ="";
		if(!oldBatName.equals(newBatName)){
			operation+="电池型号:"+oldBatName+"-->"+newBatName;
		}
		if(!oldQty.equals(newQty)){
			operation+=" 电池赠送数量:"+oldQty+"-->"+newQty;
		}
		if(!oldReQty.equals(newReQty)){
			operation+=" 电池剩余数量:"+oldReQty+"-->"+newReQty;
		}
		if(!oldGetNum.equals(newGetNum)){
			operation+=" 电池领取数量:"+oldGetNum+"-->"+newGetNum;
		}
		return operation;
	}

	@Override
	public List<Object[]> getSuperChangeHistoryList(String storeName, String memberName, String phoneNum,
			String startTime, String endTime) {
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("sch.id,acc.name,mem.name as memname,sch.membat_id,sch.batevent_id,sch.operation,sch.handle_date ")
				.from("super_change_history sch left join account acc on acc.account=sch.store_no "
						+ "left join member mem on mem.id = sch.member_id ")
				.like("acc.name", storeName)
				.like("mem.name", memberName)
				.buildHql();
		
		if(phoneNum!=null&&phoneNum!=""){
			
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and mem.phone ='"+phoneNum+"' ";
			else 
				sql_data += "where mem.phone ='"+phoneNum+"' ";
			
			if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!="")
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
		}
		else if(startTime!=null&&endTime!=null&&startTime!=""&&startTime!=""){
			if((storeName!=null&&storeName!="")||(memberName!=null&&memberName!=""))
				sql_data += " and (sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1) ";
			else
				sql_data += " where sch.handle_date between to_date('"+startTime+"','yyyy-mm-dd') and to_date('"+endTime+"','yyyy-mm-dd')+1 ";

		}
		
		sql_data +=" order by sch.handle_date DESC";
		
		System.out.println(sql_data);
		
		Query query = this.getSqlQuery(sql_data);
		
		hb.setParam(query);
		List<Object[]> accList = query.list();

		return accList;
	}

	@Override
	public void recordDelMembat(SuperChangeHistory superChangeHistory) {
		this.save(superChangeHistory);
	}



}
