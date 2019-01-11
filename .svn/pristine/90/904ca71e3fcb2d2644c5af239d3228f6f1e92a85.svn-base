package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRICE_CONFIG database table.
 * 
 */
@Entity
@Table(name="PRICE_CONFIG")
@NamedQuery(name="PriceConfig.findAll", query="SELECT p FROM PriceConfig p")
public class PriceConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="price_id",sequenceName="price_id",allocationSize = 1)
	@GeneratedValue(generator="price_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="CHANGE_SCORE")
	private Long changeScore;

	@Column(name="END_PRICE")
	private Long endPrice;

	@Column(name="START_PRICE")
	private Long startPrice;

	public PriceConfig() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChangeScore() {
		return this.changeScore;
	}

	public void setChangeScore(Long changeScore) {
		this.changeScore = changeScore;
	}

	public Long getEndPrice() {
		return this.endPrice;
	}

	public void setEndPrice(Long endPrice) {
		this.endPrice = endPrice;
	}

	public Long getStartPrice() {
		return this.startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice = startPrice;
	}

}