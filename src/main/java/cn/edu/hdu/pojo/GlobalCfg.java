package cn.edu.hdu.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the GLOBAL_CFG database table.
 * 
 */
@Entity
@Table(name="GLOBAL_CFG")
@NamedQuery(name="GlobalCfg.findAll", query="SELECT g FROM GlobalCfg g")
public class GlobalCfg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String applyscore;

	@Column(name="CHANGE_MONEY")
	private long changeMoney;

	@Column(name="CHANGE_SCORE")
	private long changeScore;

	@Column(name="INIT_PWD")
	private String initPwd;

	@Column(name="MOST_RATE")
	private long mostRate;
	
	@Column(name="OLD_LOGIN")
	private long oldLogin;

	@Column(name="ROLL_AD")
	private String rollAd;

	private String scoremoney;

	private Date actisdt;
	
	private Date actiedt;
	
	@Type(type="byte")
	@Column(name="AUTO_VERIFY")
	private Byte autoVerify;
	
	public GlobalCfg() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplyscore() {
		return this.applyscore;
	}

	public void setApplyscore(String applyscore) {
		this.applyscore = applyscore;
	}

	public long getChangeMoney() {
		return this.changeMoney;
	}

	public void setChangeMoney(long changeMoney) {
		this.changeMoney = changeMoney;
	}

	public long getChangeScore() {
		return this.changeScore;
	}

	public void setChangeScore(long changeScore) {
		this.changeScore = changeScore;
	}

	public String getInitPwd() {
		return this.initPwd;
	}

	public void setInitPwd(String initPwd) {
		this.initPwd = initPwd;
	}

	public long getMostRate() {
		return this.mostRate;
	}

	public void setMostRate(long mostRate) {
		this.mostRate = mostRate;
	}

	public String getRollAd() {
		return this.rollAd;
	}

	public void setRollAd(String rollAd) {
		this.rollAd = rollAd;
	}

	public String getScoremoney() {
		return this.scoremoney;
	}

	public void setScoremoney(String scoremoney) {
		this.scoremoney = scoremoney;
	}

	public long getOldLogin() {
		return oldLogin;
	}

	public void setOldLogin(long oldLogin) {
		this.oldLogin = oldLogin;
	}

	public Date getActisdt() {
		return actisdt;
	}

	public void setActisdt(Date actisdt) {
		this.actisdt = actisdt;
	}

	public Date getActiedt() {
		return actiedt;
	}

	public void setActiedt(Date actiedt) {
		this.actiedt = actiedt;
	}

	public Byte getAutoVerify() {
		return autoVerify;
	}

	public void setAutoVerify(Byte autoVerify) {
		this.autoVerify = autoVerify;
	}

}