package cn.edu.hdu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="VERIFY_BAT")
@NamedQuery(name="VerifyBat.findAll", query="SELECT g FROM VerifyBat g")
public class VerifyBat {
	@Id
	@SequenceGenerator(name="verify_bat_id",sequenceName="verify_bat_id",allocationSize = 1)
	@GeneratedValue(generator="verify_bat_id",strategy=GenerationType.SEQUENCE)
	private Long id;//id
	
	@Column(name="STATUS")
	private String status;//状态
	
	@Column(name="STORE_ID")
	private String storeId;//店铺id

	@Column(name="MEM_ID")
	private String memId;//会员id
	
	@Column(name="BAT_TYPE")
	private String batType;//电池类型id
	
	@Column(name="BAT_NUM")
	private String batNum;//电池数量id
	
	@Column(name="APPLY_ID")
	private String applyId;//申请id
	
	@Column(name="VERIFY_ID")
	private String verifyId;//审核id
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPLY_DATE")
	private Date applyDate;//申请日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VERIFY_DATE")
	private Date verifyDate;//审核日期
	
	@Column(name="REMARK")
	private String remark;//备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getBatType() {
		return batType;
	}

	public void setBatType(String batType) {
		this.batType = batType;
	}

	public String getBatNum() {
		return batNum;
	}

	public void setBatNum(String batNum) {
		this.batNum = batNum;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
