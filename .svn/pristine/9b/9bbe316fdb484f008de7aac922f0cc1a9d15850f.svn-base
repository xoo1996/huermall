package cn.edu.hdu.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the R_ACTI_PICTURE database table.
 * 
 */
@Entity
@Table(name="R_ACTI_PICTURE")
@NamedQuery(name="RActiPicture.findAll", query="SELECT r FROM RActiPicture r")
public class RActiPicture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="R_ACTI_PICTURE_ID_GENERATOR", sequenceName="R_ACTI_PICTURE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="R_ACTI_PICTURE_ID_GENERATOR")
	private Long id;

	@Column(name="ACTIVITY_ID")
	private Long activityId;

	@Column(name="PICTURE_ID")
	private Long pictureId;

	public RActiPicture() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

}