package cn.edu.hdu.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the MENU database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_ID")
	@SequenceGenerator(name="menu_id",sequenceName="menu_id",allocationSize = 1)
	@GeneratedValue(generator="menu_id",strategy=GenerationType.SEQUENCE)
	private Long menuId;
	
	@Column(name="IS_ALLOCATABLE")
	private String isAllocatable;

	@Column(name="IS_MENU")
	private Integer isMenu;

	@Column(name="IS_SHOW")
	private Integer isShow;

	@Column(name="LEVEL_ID")
	private Integer levelId;


	@Column(name="MENU_NAME")
	private String menuName;

	@Column(name="SORT_ID")
	private Integer sortId;

	private String url;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="PAR_ID")
	private Menu menu;

	@OneToMany(mappedBy="menu")
	private List<Menu> menus;

	public Menu() {
	}

	public String getIsAllocatable() {
		return this.isAllocatable;
	}

	public void setIsAllocatable(String isAllocatable) {
		this.isAllocatable = isAllocatable;
	}

	public Integer getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getLevelId() {
		return this.levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}