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

import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.dao.impl.VerifyDaoImpl;
import cn.edu.hdu.pojo.ScoreEvent;
import cn.edu.hdu.pojo.Verify;
import cn.edu.hdu.pojo.Waitscore;
import cn.edu.hdu.service.VerifyService;
import cn.edu.hdu.util.DataUtil;

@Service
public class VerifyServiceImpl extends GenericService<Verify> implements
		VerifyService {
	
	@Resource
	private MemberServiceImpl memberservice;
	@Resource 
	private GlobalCfgServiceImpl globalCfgService;
	@Resource 
	private WaitscoreServiceImpl waitscoreService;
	@Resource 
	private ScoreServiceImpl scoreService;

	public VerifyDaoImpl getVerifyDao(){
		return (VerifyDaoImpl)this.getGenericDao();
	}
	
	@Autowired
	public void setVerifyDao(VerifyDaoImpl verifyDao){
		this.setGenericDao(verifyDao);
	}

	@Override
	public Object getVerifyList(String id,String memNo,String status,String sponsor,
			String type,int page,int rowNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.select("ver.id,ver.type,ver.member_no,ver.content,ver.status,acc.name, "
				+ "ver.start_date,ver.verify_date "
				+ "from verify ver left join account acc on acc.account = ver.bsc011")
				.where("ver.type", type)
				.like("ver.id", id)
				.like("ver.member_no", memNo)
				.where("ver.status", status)
				.like("acc.name", sponsor)
				.orderby("ver.start_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(hql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page, rowNum).list();
		String hql_sum = hb.select("count(*)")
				.from("Verify ver left join account acc on acc.account = ver.bsc011 ")
				.where("ver.type", type)
				.like("ver.id", id)
				.like("ver.member_no", memNo)
				.where("ver.status", status)
				.like("acc.name", sponsor)
				.buildHql();
		query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("checkboxid", acc[0]);
			row.put("id", acc[0]);
			row.put("type", VerifyCode.getStr(acc[1].toString()));
			row.put("member_no", acc[2]);
			row.put("content", acc[3]);
			row.put("status", VerifyCode.getStr(acc[4].toString()));
			row.put("account", acc[5]);
			row.put("start_date", acc[6]==null?"":sdf.format((Date)acc[6]));
			row.put("verify_date", acc[7]==null?"":sdf.format((Date)acc[7]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public Object getStoreVerifyList(String verId,String storeName,String memNo,String memName,String status,String type,int page,int rowNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.select("ver.id,ver.APPLY_SCORE,ver.FINAL_SCORE,ver.bsc011,ver.content,"
				+ "ver.status,acc.name name1,ver.VERIFY_DATE,mem.name name2 , mem.member_no , ver.start_date from verify ver "
				+ "left join account acc on acc.account = ver.bsc011 "
				+ "left join member mem on mem.member_no = ver.member_no ")
				.where("ver.type", type)
				.like("ver.id", verId)
				.like("acc.name", storeName)
				.like("mem.member_no", memNo)
				.like("mem.name", memName)
				.where("ver.status", status)
				.orderby("ver.start_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(hql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page, rowNum).list();
		
		String hql_sum = hb.select("count(*)")
				.from(" Verify ver left join account acc on acc.account = ver.account "
						+ " left join member mem on mem.member_no = ver.member_no ")
				.where("ver.type", type)
				.like("ver.id", verId)
				.like("acc.name", storeName)
				.like("mem.member_no", memNo)
				.like("mem.name", memName)
				.where("ver.status", status)
				.buildHql();
		query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rows = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("checkboxid", acc[0]);
			row.put("id", acc[0]);
			row.put("score", acc[1]);
			row.put("final_score", acc[2]);
			row.put("storeAcc", acc[3]);
			row.put("content", acc[4]);
			row.put("status", VerifyCode.getStr(acc[5].toString()));
			row.put("account", acc[6]);  //门店名称
			row.put("verift_date",acc[7]==null?"":sdf.format((Date)acc[7]));
			row.put("memName", acc[8]);  //会员名称
			row.put("memNo", acc[9]);  //会员编号
			row.put("start_date", acc[10]==null?"":sdf.format((Date)acc[10]));
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public boolean addApplyuser(Verify verify) {
		try{
			verify.setStatus(VerifyCode.WAITING_VERIFY);
			this.save(verify);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean ApaApply(String remark,Long id, boolean yn,String account,Long coin, String applyid) throws Exception {
		Verify verify=this.findById(id);
		if(yn){
			verify.setRemark(remark);
			verify.setStatus(VerifyCode.PASS);
			verify.setVerifyDate(new Date());
			waitscoreService.addWaitcoin(verify.getMemberNo(),coin,"",0L,0L,"",applyid);
			try {
				memberservice.insertScore("", verify.getBsc011(),"邀请新用户赠送惠耳币","apply","1","","","",""+coin,"");
				scoreService.logApplyCoin(verify.getMemberNo(), coin,verify.getBsc011());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception();
			}
		}else{
			verify.setStatus(VerifyCode.REFUSE);
		}
		verify.setAccount(account);
		this.update(verify);
		return true;
	}

	@Override
	public Row getMyVerifyList(String verId,String verifyer,String status,
			String account,int page, int rows) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.select("ver.id,ver.content,ver.status,acc.name name1,acc1.name name2 ，ver.remark,ver.verify_date,ver.start_date from verify ver "
				+ "left join account acc on acc.account = ver.bsc011 "  //审批发起者
				+ "left join account acc1 on acc1.account = ver.account ") // 审批处理者
				.where("ver.bsc011", account)
				.like("ver.id", verId)
				.like("acc1.name", verifyer)
				.where("status", status)
				.orderby("ver.start_date", "DESC")
				.buildHql();
		Query query = this.getSqlQuery(hql_data);
		hb.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, page, rows).list();
		String hql_sum = hb.select("count(*)")
				.from("Verify ver "
						+ "left join account acc on acc.account = ver.bsc011 "
						+ "left join account acc1 on acc1.account = ver.account ")
				.where("ver.bsc011", account)
				.like("ver.id", verId)
				.like("acc1.name", verifyer)
				.where("status", status)
				.buildHql();
		query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		Object accSum = query.uniqueResult();
		List<Row> rowList = new ArrayList<Row>();
		for(Object[] acc:accList){
			Row row = new Row();
			row.put("checkboxid", acc[0]);
			row.put("id", acc[0]);
			row.put("content", acc[1]);
			row.put("status", VerifyCode.getStr(acc[2].toString()));
			row.put("account", acc[4]);
			row.put("remark", acc[5]);
			row.put("verify_date", acc[6]==null?"":sdf.format((Date)acc[6]));
			row.put("start_date", acc[7]==null?"":sdf.format((Date)acc[7]));
			rowList.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rowList);
		return row;
	}


	@Override
	@Transactional
	public void storeScoreVerify(String editor,Long id,
			String resultStr, Long finalScore) throws Exception {
		Verify verify = this.findById(id);
		if(verify == null || resultStr == null){
			throw new Exception();
		}
		verify.setAccount(editor);
		verify.setVerifyDate(new Date());
		ScoreEvent se = scoreService.findById(verify.getScoreEventId());
		if(resultStr.equals("pass")){
			if(finalScore == null ){
				throw new Exception();
			}
			verify.setFinalScore(finalScore);
			verify.setStatus(VerifyCode.PASS);
			se.setHaveGivenScore(VerifyCode.USED);
			String pdtid="",balance="";
			List<Waitscore> ws=waitscoreService.findByProperty("folno", verify.getFolno());
			if("BTE".equals(ws.get(0).getPdtid())){ pdtid="BTE";balance=ws.get(0).getBalance();}
			waitscoreService.addWaitscore(se.getMemberNo(),finalScore,verify.getFolno(),0L,0L,pdtid,"0",balance);
			scoreService.logStoreApply(se.getMemberNo(), finalScore,verify.getBsc011());
			memberservice.insertScore("",verify.getBsc011(),"特殊通道赠送积分","extra","1","",finalScore+"","","","");
		}else{
			verify.setStatus(VerifyCode.REFUSE);
			se.setHaveGivenScore(VerifyCode.REFUSE);
		}
	}

	/*@Override
	public void storeScoreVerify(Member member,String editor,Long id,String folno) throws Exception {
		try {
			Verify verify = this.findById(id);
			if(verify == null || member == null){
				throw new Exception();
			}
			verify.setAccount(editor);
			verify.setMemberNo(member.getMemberNo());
			verify.setStatus(VerifyCode.PASS);
//	member.setScore(member.getScore() + verify.getFinalScore());
			waitscoreService.addWaitscore(member.getMemberNo(),verify.getFinalScore(),folno);
			memberservice.insertScore("",verify.getBsc011(),"特殊通道赠送积分","extra","","",verify.getFinalScore()+"","","","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}*/
	
	public List<Verify> getUndoVerify(String type,List<String> params){
		String hql = " from Verify ver where ver.type = :type and ver.status not in (:params)";
		List<Verify> list = this.getQuery(hql).setParameter("type", type).setParameterList("params", params).list();
		return list;
	}
	
}
