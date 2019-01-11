package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the R_GIFT_PICTURE database table.
 * 
 */
@Entity
@Table(name="R_GIFT_PICTURE")
@NamedQuery(name="RGiftPicture.findAll", query="SELECT r FROM RGiftPicture r")
public class RGiftPicture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="r_gift_picture_id",sequenceName="r_gift_picture_id",allocationSize = 1)
	@GeneratedValue(generator="r_gift_picture_id",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="GIFT_ID")
	private Long giftId;
	

	@Column(name="PICTURE_ID")
	private Long pictureId;

	public RGiftPicture() {
	}

	public Long getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

}