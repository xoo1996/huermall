package cn.edu.hdu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.hdu.dao.impl.WaitscoreDaoImpl;
import cn.edu.hdu.pojo.Member;
import cn.edu.hdu.pojo.ScoreEvent;
import cn.edu.hdu.pojo.Waitscore;
import cn.edu.hdu.service.WaitscoreService;

@Service("waitscoreService")
public class WaitscoreServiceImpl extends GenericService<Waitscore> implements
		WaitscoreService {

	@Resource
	private MemberServiceImpl memberservice;
	@Resource
	private ScoreServiceImpl scoreservice;
	
	public WaitscoreDaoImpl getWaitscoreDao() {
        return (WaitscoreDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setWaitscoreDao(WaitscoreDaoImpl waitscoreDao) {
	    this.setGenericDao(waitscoreDao);
	}

	@Override
	public boolean addWaitscore(String user, Long l, String folno, Long score, Long coin,String pdtid,String effect,String balance) {
		try{
			Waitscore ws=new Waitscore();
			ws.setScore(l);
			ws.setMemberNo(user);
			ws.setFolno(folno);
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, +30);
			Date dt=cal.getTime();
			ws.setCreatedt(dt);
			ws.setCoin(0L);
			ws.setEffect(effect);
			ws.setChangecoin(coin);
			ws.setChangescore(score);
			ws.setPdtid(pdtid);
			ws.setApplyid("");
			ws.setBalance(balance);
			this.save(ws);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean addWaitcoin(String user, Long l, String folno, Long score, Long coin,String pdtid,String applyid) {
		try{
			Waitscore ws=new Waitscore();
			ws.setScore(0L);
			ws.setMemberNo(user);
			ws.setFolno(folno);
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, +30);
			Date dt=cal.getTime();
			ws.setCreatedt(dt);
			ws.setCoin(l);
			ws.setEffect("0");
			ws.setChangecoin(coin);
			ws.setChangescore(score);
			ws.setPdtid(pdtid);
			ws.setApplyid(applyid);
			this.save(ws);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public void delWaitscore(String folno) {
		try{
			List<Waitscore> ws=this.findByProperty("folno", folno);
			List<ScoreEvent> ss=scoreservice.findByProperty("folno", folno);
			for(Waitscore i: ws){
				if(i.getPdtid()!=null&&!"BTE".equals(i.getPdtid())){
					if(i.getChangecoin()!=0||i.getChangescore()!=0){
						memberservice.addScore(i.getMemberNo(), i.getChangescore());
						memberservice.addCoin(i.getMemberNo(), i.getChangecoin());
						memberservice.insertScore("", ss.get(0).getAccount(),"退机返回会员购买抵扣的积分,惠耳币",i.getPdtid(),"-1",-i.getChangescore()+"","","","",-i.getChangecoin()+"");
						scoreservice.logRec(i.getMemberNo(), i.getChangescore(), i.getChangecoin(), ss.get(0).getAccount());
					}
				}else {
					memberservice.insertScore("", ss.get(0).getAccount(),"退机扣除特殊通道赠送积分","extra","-1","",-i.getScore()+"","","","");
					scoreservice.logRecSpe(i.getMemberNo(), i.getScore(), ss.get(0).getAccount());
				}
				delete(i);
			}
		}catch(Exception e){
			
		}
	}
	
	@Override
	public void delApplycoin(String applyid){
		try{
			List<Waitscore> ws=this.findByProperty("applyid", applyid);
			List<ScoreEvent> ss=scoreservice.findByProperty("memberNo", applyid);
			for(Waitscore i: ws){
				if("0".equals(i.getEffect())){
					memberservice.insertScore("", ss.get(0).getAccount(),"退机扣除邀请人的惠耳币","apply","-1","","","",-i.getCoin()+"","");
					delete(i);
					scoreservice.logRecApp(i.getMemberNo(), i.getCoin(), ss.get(0).getAccount());
				}
			}
		}catch(Exception e){
			
		}
	}

	@Override
	public void delnor(String folno, int rnt, String mon,String pdtid) {
		try{
			List<Waitscore> ws=this.findByProperty("folno", folno);
			List<ScoreEvent> ss=scoreservice.findByProperty("folno", folno);
			long score1,score2,coin1;
			for(Waitscore i: ws){
				if("BTE".equals(i.getPdtid())){
					score2=Math.round(Double.parseDouble(mon)*rnt/Double.parseDouble(i.getBalance())*i.getScore());
					if((i.getChangecoin()!=0||i.getChangescore()!=0)&&i.getBalance()!= null && i.getBalance().length() > 0){
						score1=Math.round(Double.parseDouble(mon)*rnt/Double.parseDouble(i.getBalance())*i.getChangescore());
						coin1=Math.round(Double.parseDouble(mon)*rnt/Double.parseDouble(i.getBalance())*i.getChangecoin());
						memberservice.addScore(i.getMemberNo(), score1);
						memberservice.addCoin(i.getMemberNo(), coin1);
						memberservice.insertScore("", ss.get(0).getAccount(),"退机返回会员购买抵扣的积分,惠耳币",pdtid,"-1",-score1+"","","","",-coin1+"");
						scoreservice.logRec(i.getMemberNo(), score1, coin1, ss.get(0).getAccount());
					}
					else if("0".equals(i.getEffect())){
						memberservice.insertScore("", ss.get(0).getAccount(),"退机扣除特殊通道赠送积分","extra","-1","",-score2+"","","","");
						i.setBalance(""+(Double.parseDouble(i.getBalance())-Double.parseDouble(mon)*rnt));
						i.setScore(i.getScore()-score2);
						if(i.getScore()==0) i.setEffect("1");
						update(i);
						scoreservice.logRecSpe(i.getMemberNo(), score2, ss.get(0).getAccount());
					}
				}
			}
		}catch(Exception e){
		}
	}

}
