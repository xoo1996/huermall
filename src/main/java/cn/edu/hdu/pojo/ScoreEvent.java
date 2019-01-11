package cn.edu.hdu.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SCORE_EVENT database table.
 * 
 */
@Entity
@Table(name="SCORE_EVENT")
@NamedQuery(name="ScoreEvent.findAll", query="SELECT s FROM ScoreEvent s")
public class ScoreEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="score_id",sequenceName="score_id",allocationSize = 1)
	@GeneratedValue(generator="score_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="CHANGE_SCORE")
	private Long changeScore;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HANDLE_DATE")
	private Date handleDate;

	@Column(name="HANDLE_TYPE")
	private String handleType;

	@Column(name="MEMBER_NO")
	private String memberNo;

	@Column(name="REMAIN_SCORE")
	private Long remainScore;

	private String account;
	
	@Column(name="HAVE_GIVE_SCORE")
	private String haveGivenScore;

	private String folno;
	
	public String getFolno() {
		return folno;
	}

	public void setFolno(String folno) {
		this.folno = folno;
	}

	public ScoreEvent() {
	}

	public String getHaveGivenScore() {
		return haveGivenScore;
	}

	public void setHaveGivenScore(String haveGivenScore) {
		this.haveGivenScore = haveGivenScore;
	}


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getHandleDate() {
		return this.handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getHandleType() {
		return this.handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Long getRemainScore() {
		return this.remainScore;
	}

	public void setRemainScore(Long remainScore) {
		this.remainScore = remainScore;
	}

}