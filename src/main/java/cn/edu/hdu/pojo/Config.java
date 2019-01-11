package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CONFIG")
@NamedQuery(name="Config.findAll", query="SELECT g FROM Config g")
public class Config implements Serializable{
	
	@Id
	@SequenceGenerator(name="config_id",sequenceName="config_id",allocationSize = 1)
	@GeneratedValue(generator="config_id",strategy=GenerationType.SEQUENCE)
	private Long id;//id
	
	@Column(name="MEMBAT_VERIFY_SWITCH")
	private String membatVerifySwitch;//自动审核membat的开关

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMembatVerifySwitch() {
		return membatVerifySwitch;
	}

	public void setMembatVerifySwitch(String membatVerifySwitch) {
		this.membatVerifySwitch = membatVerifySwitch;
	}
		
}
