package cn.edu.hdu.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="SUPER_CHANGE_HISTORY")
@NamedQuery(name="SuperChangeHistory.findAll", query="SELECT g FROM SuperChangeHistory g")
public class SuperChangeHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="super_change_history_id",sequenceName="super_change_history_id",allocationSize = 1)
	@GeneratedValue(generator="super_change_history_id",strategy=GenerationType.SEQUENCE)
	private Long id;//id
	
	@Column(name="STORE_NO")
	private String storeId;//店铺id
	
	@Column(name="MEMBER_ID")
	private String memberId;//客户id
	
	@Column(name="MEMBAT_ID")
	private String memBatId;//赠送记录id
	
	@Column(name="BATEVENT_ID")
	private String batEventId;//领取记录id
	
	@Column(name="OPERATION")
	private String operation;//操作
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_DATE")
	private Date handleDate;//日期
	
	@Column(name="OLD_BATTYPE")
	private String oldBatType;//修改前电池型号
	
	@Column(name="NEW_BATTYPE")
	private String newBatType;//修改后电池型号
	
	@Column(name="OLD_QTY")
	private String oldQty;//修改前赠送数量
	
	@Column(name="NEW_QTY")
	private String newQty;//修改后赠送数量
	
	@Column(name="OLD_REQTY")
	private String oldReQty;//剩余数量
	
	@Column(name="NEW_REQTY")
	private String newReQty;
	
	@Column(name="OLD_GETNUM")
	private String oldGetNum;//领取数量
	
	@Column(name="NEW_GETNUM")
	private String newGetNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemBatId() {
		return memBatId;
	}

	public void setMemBatId(String memBatId) {
		this.memBatId = memBatId;
	}

	public String getBatEventId() {
		return batEventId;
	}

	public void setBatEventId(String batEventId) {
		this.batEventId = batEventId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getOldBatType() {
		return oldBatType;
	}

	public void setOldBatType(String oldBatType) {
		this.oldBatType = oldBatType;
	}

	public String getNewBatType() {
		return newBatType;
	}

	public void setNewBatType(String newBatType) {
		this.newBatType = newBatType;
	}

	public String getOldQty() {
		return oldQty;
	}

	public void setOldQty(String oldQty) {
		this.oldQty = oldQty;
	}

	public String getNewQty() {
		return newQty;
	}

	public void setNewQty(String newQty) {
		this.newQty = newQty;
	}

	public String getOldReQty() {
		return oldReQty;
	}

	public void setOldReQty(String oldReQty) {
		this.oldReQty = oldReQty;
	}

	public String getNewReQty() {
		return newReQty;
	}

	public void setNewReQty(String newReQty) {
		this.newReQty = newReQty;
	}

	public String getOldGetNum() {
		return oldGetNum;
	}

	public void setOldGetNum(String oldGetNum) {
		this.oldGetNum = oldGetNum;
	}

	public String getNewGetNum() {
		return newGetNum;
	}

	public void setNewGetNum(String newGetNum) {
		this.newGetNum = newGetNum;
	}

}
