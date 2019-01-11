package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the BATTERY database table.
 * 
 */
@Entity
@NamedQuery(name="Battery.findAll", query="SELECT b FROM Battery b")
public class Battery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="battery_id",sequenceName="battery_id",allocationSize = 1)
	@GeneratedValue(generator="battery_id",strategy=GenerationType.SEQUENCE)
	private long id;

	@Column(name="CHANGE_NUM")
	private long changeNum;
	
	@Column(name="name")
	private String name;
	
	@Column(name="score")
	private long score;

	@Column(name="STORE_NUM")
	private long storeNum;

	@Column(name="\"TYPE\"")
	private String type;

	public Battery() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChangeNum() {
		return this.changeNum;
	}

	public void setChangeNum(long changeNum) {
		this.changeNum = changeNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getScore() {
		return this.score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getStoreNum() {
		return this.storeNum;
	}

	public void setStoreNum(long storeNum) {
		this.storeNum = storeNum;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}