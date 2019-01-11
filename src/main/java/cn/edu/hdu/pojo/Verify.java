package cn.edu.hdu.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * The persistent class for the VERIFY database table.
 * 
 */
@Entity
@Table(name="VERIFY")
@NamedQuery(name="Verify.findAll", query="SELECT v FROM Verify v")
public class Verify implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="verify_id",sequenceName="verify_id",allocationSize = 1)
	@GeneratedValue(generator="verify_id",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private String account;

	private String content;

	@Column(name="MEMBER_NO")
	private String memberNo;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	private String bsc011;
	
	@Column(name="APPLY_SCORE")
	private Long applyScore;
	
	@Column(name="FINAL_SCORE")
	private Long finalScore;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VERIFY_DATE")
	private Date verifyDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="SCORE_EVENT_ID")
	private Long scoreEventId;
	
	private String folno;
	
	private String remark;
	
	public String getFolno() {
		return folno;
	}

	public void setFolno(String folno) {
		this.folno = folno;
	}

	public Long getScoreEventId() {
		return scoreEventId;
	}

	public void setScoreEventId(Long scoreEventId) {
		this.scoreEventId = scoreEventId;
	}

	public String getBsc011() {
		return bsc011;
	}

	public void setBsc011(String bsc011) {
		this.bsc011 = bsc011;
	}

	public Verify() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getApplyScore() {
		return applyScore;
	}

	public void setApplyScore(Long applyScore) {
		this.applyScore = applyScore;
	}

	public Long getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Long finalScore) {
		this.finalScore = finalScore;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}