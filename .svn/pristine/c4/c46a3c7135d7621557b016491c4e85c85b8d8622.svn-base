package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the GIFT database table.
 * 
 */
@Entity
@NamedQuery(name="Gift.findAll", query="SELECT g FROM Gift g")
public class Gift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="gift_id",sequenceName="gift_id",allocationSize = 1)
	@GeneratedValue(generator="gift_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="CHANGE_NUM")
	private Long changeNum;

	@Column(name="GIFT_NO")
	private String giftNo;

	private String name;

	private Long score;

	@Column(name="STORE_NUM")
	private Long storeNum;

	public Gift() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChangeNum() {
		return this.changeNum;
	}

	public void setChangeNum(Long changeNum) {
		this.changeNum = changeNum;
	}

	public String getGiftNo() {
		return this.giftNo;
	}

	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getStoreNum() {
		return this.storeNum;
	}

	public void setStoreNum(Long storeNum) {
		this.storeNum = storeNum;
	}

}