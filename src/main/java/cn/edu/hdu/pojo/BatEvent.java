package cn.edu.hdu.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//新增
@Entity
@Table(name="BAT_EVENT")
@NamedQuery(name="BatEvent.findAll", query="SELECT g FROM BatEvent g")
public class BatEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="bat_event_id",sequenceName="bat_event_id",allocationSize = 1)
	@GeneratedValue(generator="bat_event_id",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="IN_STORE_NO")
	private String inStoreNo;
	
	@Column(name="GET_STORE_NO")
	private String getStoreNo;
	
	@Column(name="BATTERY_TYPE")
	private String batteryType;
	
	@Column(name="GET_BAT_NUM")
	private Long getBatNum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_DATE")
	private Date handleDate;
	
	@Column(name="MEMBAT_ID")
	private String memBatId;

	public String getMemBatId() {
		return memBatId;
	}

	public void setMemBatId(String memBatId) {
		this.memBatId = memBatId;
	}

	public BatEvent() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInStoreNo() {
		return inStoreNo;
	}

	public void setInStoreNo(String inStoreNo) {
		this.inStoreNo = inStoreNo;
	}

	public String getGetStoreNo() {
		return getStoreNo;
	}

	public void setGetStoreNo(String getStoreNo) {
		this.getStoreNo = getStoreNo;
	}

	public Long getGetBatNum() {
		return getBatNum;
	}

	public void setGetBatNum(Long getBatNum) {
		this.getBatNum = getBatNum;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
	
	
	
}
