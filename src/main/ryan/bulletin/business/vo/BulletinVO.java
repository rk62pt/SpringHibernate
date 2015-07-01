package main.ryan.bulletin.business.vo;

public class BulletinVO {
	protected Integer id;
	protected String tb_title;
	protected String tb_description;
	protected String tb_create_time;
	protected String tb_creator;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTb_title() {
		return tb_title;
	}
	public void setTb_title(String tb_title) {
		this.tb_title = tb_title;
	}
	public String getTb_description() {
		return tb_description;
	}
	public void setTb_description(String tb_description) {
		this.tb_description = tb_description;
	}
	public String getTb_create_time() {
		return tb_create_time;
	}
	public void setTb_create_time(String tb_create_time) {
		this.tb_create_time = tb_create_time;
	}
	public String getTb_creator() {
		return tb_creator;
	}
	public void setTb_creator(String tb_creator) {
		this.tb_creator = tb_creator;
	}
}
