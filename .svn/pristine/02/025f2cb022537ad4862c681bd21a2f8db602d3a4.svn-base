package cn.edu.hdu.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="MEM_BAT")
@NamedQuery(name="MemBat.findAll", query="SELECT m FROM MemBat m")
public class MemBat implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="mem_bat_id",sequenceName="mem_bat_id",allocationSize = 1)
	@GeneratedValue(generator="mem_bat_id",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="MEMBER_ID")
	private String memberId;	//会员编号

	@Column(name="BATTERY_TYPE")
	private String batteryType;	//电池型号
	
	@Column(name="BATTERY_QTY")
	private long batteryQty;	//电池数量
	
	@Column(name="BATTERY_RE_QTY")
	private long batteryReQty;	//电池剩余数量
	
	@Column(name="STORE_NO")
	private String storeNo;		//用户所属店
	
	@Column(name="ORDER_ID")
	private String orderId;		//订单号
	
	@Column(name="CHARGE_ID")
	private String chargeId;	//付费号
	
	private String status;		//状态
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA")
	private Date date;			//建立日期
	
	public MemBat() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public long getBatteryQty() {
		return batteryQty;
	}

	public void setBatteryQty(long batteryQty) {
		this.batteryQty = batteryQty;
	}


	public long getBatteryReQty() {
		return batteryReQty;
	}


	public void setBatteryReQty(long batteryReQty) {
		this.batteryReQty = batteryReQty;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
	
}
