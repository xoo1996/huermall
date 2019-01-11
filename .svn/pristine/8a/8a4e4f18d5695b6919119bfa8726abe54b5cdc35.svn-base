package cn.edu.hdu.pojo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the R_ROLE_MENU database table.
 * 
 */
@Entity
@Table(name="R_ROLE_MENU")
@NamedQuery(name="RRoleMenu.findAll", query="SELECT r FROM RRoleMenu r")
public class RRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="r_role_menu_id",sequenceName="r_role_menu_id",allocationSize = 1)
	@GeneratedValue(generator="r_role_menu_id",strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="MENU_ID")
	private Long menuId;

	@Column(name="ROLE_ID")
	private Long roleId;

	public RRoleMenu() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}