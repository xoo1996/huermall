package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the WAITSCORE database table.
 * 
 */
@Entity
@NamedQuery(name="Waitscore.findAll", query="SELECT w FROM Waitscore w")
public class Waitscore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="wait_id",sequenceName="wait_id",allocationSize = 1)
	@GeneratedValue(generator="wait_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	private Long coin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdt;

	private String effect;

	private String folno;

	@Column(name="MEMBER_NO")
	private String memberNo;

	private Long score;
	
	private Long changescore;
	
	private Long changecoin;
	
	private String pdtid;

	private String applyid;
	
	private String balance;
	
	public Waitscore() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoin() {
		return coin;
	}

	public void setCoin(Long coin) {
		this.coin = coin;
	}

	public Date getCreatedt() {
		return createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getFolno() {
		return folno;
	}

	public void setFolno(String folno) {
		this.folno = folno;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getChangescore() {
		return changescore;
	}

	public void setChangescore(Long changescore) {
		this.changescore = changescore;
	}

	public Long getChangecoin() {
		return changecoin;
	}

	public void setChangecoin(Long changecoin) {
		this.changecoin = changecoin;
	}

	public String getPdtid() {
		return pdtid;
	}

	public void setPdtid(String pdtid) {
		this.pdtid = pdtid;
	}

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}


}