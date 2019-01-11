package cn.edu.hdu.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.ScoreEventType;
import cn.edu.hdu.Code.VerifyCode;
import cn.edu.hdu.dao.impl.GenericDao;
import cn.edu.hdu.dao.impl.ScoreDaoImpl;
import cn.edu.hdu.pojo.Account;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.PriceConfig;
import cn.edu.hdu.pojo.ScoreEvent;
import cn.edu.hdu.service.AccountService;
import cn.edu.hdu.service.MemberService;
import cn.edu.hdu.service.ScoreService;
import cn.edu.hdu.util.DataUtil;

@Service("scoreService")
public class ScoreServiceImpl extends GenericService<ScoreEvent> implements
		ScoreService {

	public ScoreDaoImpl getScoreDao() {
        return (ScoreDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setScoreDao(ScoreDaoImpl scoreDao) {
	    this.setGenericDao(scoreDao);
	}
	
	@Autowired
	private AccountServiceImpl accountService;
	@Autowired
	private MemberServiceImpl memberService;

	@Override
	public Row getScoreList(String memberName, String memberId,
			String memberPhone,String eventType,String storeName,
			String page,String rowNumber,String account,List<String> list) {
		String hql_data = "select a.id,a.handle_type,a.handle_date,a.content,a.remain_score,a.member_no,m.name name1,m.phone,acc.name,a.have_give_score,acc.name name2,a.folno "
				+ "from score_event a left join member m on m.member_no=a.member_no "
				+ "left join account acc on acc.id = m.store_id";
		HqlBuilder builder = new HqlBuilder();
		if(account != null)
			builder.where("a.account", account);
		if(list != null)
			builder.in("a.handle_type", list);
		String w = builder.like("m.name", memberName)
			.like("m.member_no", memberId)
			.like("m.phone", memberPhone)
			.like("acc.name",storeName)
			.where("a.handle_type", eventType)
			.orderby("a.handle_date", "desc")
			.buildHql();
		hql_data += w;
		Query query = this.getSqlQuery(hql_data);
		builder.setParam(query);
		List<Object[]> accList = DataUtil.getPages(query, Integer.parseInt(page), Integer.parseInt(rowNumber)).list(); 
		String hql_sum = "select count(*) from score_event a left join member m on m.member_no=a.member_no "
						+ "left join account acc on acc.id = m.store_id";
		
		if(account != null)
			builder.where("a.account", account);
		if(list != null)
			builder.in("a.handle_type", list);
		w = builder.like("m.name", memberName)
			.like("m.member_no", memberId)
			.like("m.phone", memberPhone)
			.like("acc.name",storeName)
			.where("a.handle_type", eventType)
			.orderby("a.handle_date", "desc")
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
			row.put("handle_type", ScoreEventType.strMap.get(acc[1]));
			row.put("handle_date", acc[2] == null?"":sdf.format((Date)acc[2]));
			row.put("content", acc[3]);
			row.put("remain_score", acc[4]);
			row.put("member_no", acc[5]);
			row.put("name", acc[6]);
			row.put("phone", acc[7]);
			row.put("account", acc[8]);
			row.put("isusedCode", acc[9]);
			row.put("isUsed", VerifyCode.getStr(acc[9]==null?"":(String)acc[9]));
			row.put("storeName", acc[10]);
			row.put("folno", acc[11]);
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}
	
	@Override
	public Row getScoreApplyList(String memberName, String memberId,
			String memberPhone, String eventType, String row, String rowNumber,
			String acc) {
		return this.getScoreList(memberName, memberId, memberPhone, eventType,null, row, rowNumber, acc, null);
	}

	@Override
	public void record(Long id, String name, Long score, long number, String account) {
		Member mem = memberService.findById(id);
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(mem.getScore()-score*number);
		se.setMemberNo(mem.getMemberNo());
		se.setHandleType(ScoreEventType.SCORE_GIFT);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setContent("兑换"+name+"共"+number+"件，合计消费"+score*number+"积分,当前积分余额"+(mem.getScore()-score*number));
		se.setChangeScore(score*number);
		se.setAccount(account);
		this.save(se);
	}

	@Override
	public void logConsume(String memberNo, Long score,String account) {
		if(score == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(mem.getScore()-score);
		se.setMemberNo(mem.getMemberNo());
		se.setHandleType(ScoreEventType.SCORE_CASH);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setContent("购买使用"+score+"积分,当前积分余额"+(mem.getScore()-score));
		se.setAccount(account);
		this.save(se);
	}
	
	@Override
	public void logCharge(String memberNo, Long score, Long sco,String account,String folno) {
		if(score == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(mem.getScore()+sco+score);
		se.setMemberNo(mem.getMemberNo());
		se.setHandleType(ScoreEventType.CHARGE_SCORE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setContent("产生"+score+"积分，当前积分余额"+(mem.getScore()+sco+score));
		se.setAccount(account);
		se.setFolno(folno);
		se.setHaveGivenScore(VerifyCode.NOT_USED);
		this.save(se);
	}

	@Override
	public void logNewCharge(String user, Long l,String account,String folno) {
		if(l == 0) //如果产生的积分为0.则不进行记录
			return;
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(l);
		se.setMemberNo(user);
		se.setHandleType(ScoreEventType.CHARGE_SCORE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(l);
		se.setContent("产生"+l+"积分，当前积分余额"+l);
		se.setAccount(account);
		se.setFolno(folno);
		se.setHaveGivenScore(VerifyCode.NOT_USED);
		this.save(se);
	}
	
	@Override
	public void logStoreApply(String memberNo, Long score,String account) {
		if(score == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(mem.getScore());
		se.setMemberNo(mem.getMemberNo());
		se.setHandleType(ScoreEventType.STORE_SCORE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setHaveGivenScore(VerifyCode.NOT_USED);
		se.setContent("通过申请，多赠送"+score+"积分，30天后生效,当前积分余额"+mem.getScore());
		se.setAccount(account);
		this.save(se);
	}
	
	@Override
	public void logOld(String memberNo, long score,String account) {
		if(score == 0) //如果产生的积分为0.则不进行记录
			return;
		ScoreEvent se=new ScoreEvent();
		se.setRemainScore(score);
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.OLD_SCORE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setContent("老用户注册，赠送"+score+"积分,当前积分余额"+score);
		se.setAccount(account);
		this.save(se);
	}

	@Override
	public void logCoin(String memberNo, Long coin,String account) {
		if(coin == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.COIN_CHANGE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(coin);
		se.setContent("购买使用"+coin+"惠耳币,当前惠耳币余额"+(mem.getCoin()-coin));
		se.setAccount(account);
		this.save(se);
	}
	
	@Override
	public void logrepair(String memberNo, Long coin,String account) {
		if(coin == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.COIN_CHANGE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(coin);
		se.setContent("维修抵扣使用"+coin+"惠耳币,当前惠耳币余额"+(mem.getCoin()-coin));
		se.setAccount(account);
		this.save(se);
	}

	@Override
	public void logApplyCoin(String memberNo, Long coin,String account) {
		if(coin == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.COIN_APPLY);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(coin);
		se.setContent("邀请新用户赠送"+coin+"惠耳币,30天后生效，当前惠耳币余额"+(mem.getCoin()));
		se.setAccount(account);
		this.save(se);
	}

	@Override
	public int getStoreScoreCount(String startDate, String endDate,
			String account) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HqlBuilder hb = new HqlBuilder();
		String sql_data = hb.select("se.change_score")
				.from("score_event se ")
				.where("se.account", account)
				.where("se.handle_type",ScoreEventType.CHARGE_SCORE)
				.between("se.handle_date", startDate, endDate)
				.buildHql();
		Query query = this.getSqlQuery(sql_data);
		hb.setParam(query);
		try {
			if(startDate != null && !startDate.trim().equals("")){
				query.setDate("start", sdf.parse(startDate));
			}
			if(endDate != null && !endDate.trim().equals("")){
				query.setDate("end", sdf.parse(endDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<BigDecimal> seList = query.list(); 
		int total = 0;
		for(BigDecimal se:seList){
			total += se.intValue();
		}
		return total;
	}

	@Override
	public Row getStoreScoreAccountList(String startDate, String endDate,
			String storeName,int rowNum,int page) {
		Row row = accountService.getAccountList(storeName, null, rowNum, page);
		List<Row> rows = (List<Row>)row.get("rows");
		Row retRow = new Row();
		List<Row> retRows = new ArrayList<Row>();
		for(Row r:rows){
			String account = r.getString("accountNo");
			int sum = this.getStoreScoreCount(startDate, endDate, account);
			Row listRow = new Row();
			listRow.put("total", sum);
			listRow.put("name", r.getString("name"));
			listRow.put("account", account);
			retRows.add(listRow);
		}
		retRow.put("total", row.get("total"));
		retRow.put("rows", retRows);
		return retRow;
	}

	@Override
	public void logRec(String memberNo,Long score, Long coin,String account) {
		if(coin == 0&&score==0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.REC_EXCHANGE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setContent("退机返回会员购买抵扣的积分"+score+",购买抵扣惠耳币"+coin+"，当前积分余额"+(mem.getScore()-score)+",当前惠耳币余额"+(mem.getCoin()+coin));
		se.setAccount(account);
		this.save(se);
	}
	
	@Override
	public void logRecSpe(String memberNo,Long score, String account) {
		if(score == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.REC_EXCHANGE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(score);
		se.setContent("退机扣除特殊通道赠送的待生效积分"+score);
		se.setAccount(account);
		this.save(se);
	}

	@Override
	public void logRecApp(String memberNo,Long coin, String account) {
		if(coin == 0) //如果产生的积分为0.则不进行记录
			return;
		HqlBuilder hb = new HqlBuilder();
		String hql = hb.from("Member m")
						.where("m.memberNo", memberNo)
						.buildHql();
		Query query = this.getQuery(hql);
		hb.setParam(query);
		Member mem = (Member)query.uniqueResult();
		ScoreEvent se=new ScoreEvent();
		se.setMemberNo(memberNo);
		se.setHandleType(ScoreEventType.REC_EXCHANGE);
		se.setHandleDate(new java.sql.Date(System.currentTimeMillis()));
		se.setChangeScore(coin);
		se.setContent("退机扣除邀请人赠送的待生效惠耳币"+coin);
		se.setAccount(account);
		this.save(se);
	}
	
	@Override
	public Row getNewMemberCount(Date startDate, Date endDate,
			String storeAccount, int rows, int page) {
		HqlBuilder hb = new HqlBuilder();
		/*String hql_data = hb.from("ScoreEvent se")
				.where("se.account",storeAccount)
				.between("se.handle_date", startDate, endDate)
				.buildHql();
		Query query = this.getQuery(hql_data);
		hb.setParam(query);
		List<ScoreEvent> seList = DataUtil.getPages(query, page, rows).list();*/
		String hql_sum = hb.select("count(*)")
				.from("Score_Event se left join Account acc on acc.account = se.account")
				.where("se.account", storeAccount)
				.where("se.handle_type", ScoreEventType.OLD_SCORE)
				.between("se.handle_date", startDate, endDate)
				.buildHql();
		Query query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		if(startDate != null ){
			query.setDate("start", startDate);
		}
		if(endDate != null ){
			query.setDate("end", endDate);
		}
		Object accSum = query.uniqueResult();
		//组装
		List<Row> listRow = new ArrayList<Row>();
		Row row = new Row();
		row.put("total", accSum);
		
		{
			List<Account> accList = accountService.findByProperty("account", storeAccount);
			if(accList != null){
				Account acc = accList.get(0);
				row.put("name", acc.getName());
				row.put("account", acc.getAccount());
			}
		}
		listRow.add(row);
		Row retRow = new Row();
		retRow.put("total", 1);
		retRow.put("rows", listRow);
		return retRow;
	}

	@Override
	public Row getNewMemberCountAll(Date startDate, Date endDate, int rows,
			int page) {
		HqlBuilder hb = new HqlBuilder();
		String hql_sum = hb.select("count(*),acc.name,see.account")
				.from(" Score_Event see left join Account acc on acc.account = see.account ")
				.where("see.handle_Type", ScoreEventType.OLD_SCORE)
				.between("see.handle_Date", startDate, endDate)
				.groupBy(" see.account,acc.name")
				.buildHql();
		Query query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		if(startDate != null ){
			query.setDate("start", startDate);
		}
		if(endDate != null ){
			query.setDate("end", endDate);
		}
		List<Object[]> accSum = DataUtil.getPages(query, page, rows).list();
		//组装
		List<Row> listRow = new ArrayList<Row>();
		for(Object[] obj:accSum){
			Row row = new Row();
			row.put("total", obj[0]);
			row.put("name", obj[1]);
			row.put("account", obj[2]);
			listRow.add(row);
		}
		Row retRow = new Row();
		retRow.put("total", accSum.size());
		retRow.put("rows", listRow);
		return retRow;
	}

	@Override
	public Row getNewMemberCountDetail(Date startDate, Date endDate,
			String storeAccount, int rows, int page) {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.select("acc.name,see.handle_date,memq.name as memName,memq.member_no")
				.from(" Score_Event see "
						+ "left join Account acc on acc.account = see.account "
						+ "left join Member memq on memq.member_no = see.member_no ")
				.where("see.account",storeAccount)
				.where("see.handle_Type", ScoreEventType.OLD_SCORE)
				.between("see.handle_date", startDate, endDate)
				.orderby("see.handle_date", "desc")
				.buildHql();
		//data
		Query query = this.getSqlQuery(hql_data);
		hb.setParam(query);
		if(startDate != null ){
			query.setDate("start", startDate);
		}
		if(endDate != null ){
			query.setDate("end", endDate);
		}
		List<Object[]> dataList = DataUtil.getPages(query, page, rows).list();
		
		//sum
		String hql_sum = hb.select("count(*)")
				.from(" Score_Event see ")
				.where("see.handle_Type", ScoreEventType.OLD_SCORE)
				.where("see.account",storeAccount)
				.between("see.handle_date", startDate, endDate)
				.orderby("see.handle_date", "desc")
				.buildHql();
		query = this.getSqlQuery(hql_sum);
		hb.setParam(query);
		if(startDate != null ){
			query.setDate("start", startDate);
		}
		if(endDate != null ){
			query.setDate("end", endDate);
		}
		List<Object[]> sum = query.list();
		
		//组装
		List<Row> listRow = new ArrayList<Row>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Object[] obj:dataList){
			Row row = new Row();
			row.put("storeName", obj[0]);
			row.put("handleDate", obj[1]==null?"":sdf.format((Date)obj[1]));
			row.put("memberName", obj[2]);
			row.put("memberNo", obj[3]);
			listRow.add(row);
		}
		Row retRow = new Row();
		retRow.put("total", sum);
		retRow.put("rows", listRow);
		return retRow;
	}

	

}
