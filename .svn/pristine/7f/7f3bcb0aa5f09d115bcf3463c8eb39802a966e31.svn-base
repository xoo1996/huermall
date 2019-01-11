package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the PICTURE_DIR database table.
 * 
 */
@Entity
@Table(name="PICTURE_DIR")
@NamedQuery(name="PictureDir.findAll", query="SELECT p FROM PictureDir p")
public class PictureDir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="picture_dir_id",sequenceName="picture_dir_id",allocationSize = 1)
	@GeneratedValue(generator="picture_dir_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="PATH")
	private String path;

	@Column(name="MODULE_NAME")
	private String moduleName;
	
	@Column(name="SERIES_ID")
	private Long seriesId;
	
	@Column(name="PIC_INDEX")
	private Long picIndex;
	
	@Column(name="ACTIVITY_ID")
	private Long activityId;

	@Temporal(TemporalType.DATE)
	@Column(name="UPLOAD_DATE")
	private Date uploadDate;

	public PictureDir() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	public Long getPicIndex() {
		return picIndex;
	}

	public void setPicIndex(Long picIndex) {
		this.picIndex = picIndex;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}


}