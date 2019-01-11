package cn.edu.hdu.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the MEMBER database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="member_id",sequenceName="member_id",allocationSize = 1)
	@GeneratedValue(generator="member_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	private long batterynum;

	@Column(name="MEM_LEVEL")
	private Long memLevel;

	@Column(name="MEMBER_NO")
	private String memberNo;
	
	@Column(name="CARD")
	private String idCardNo;

	private String name;

	private String password;

	private String phone;

	private long score;
	
	private long coin;

	
	@Column(name="STORE_ID")
	private Long storeId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	public Member() {
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getBatterynum() {
		return batterynum;
	}

	public void setBatterynum(long batterynum) {
		this.batterynum = batterynum;
	}

	public Long getMemLevel() {
		return memLevel;
	}

	public void setMemLevel(Long memLevel) {
		this.memLevel = memLevel;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	
}