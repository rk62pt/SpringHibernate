package main.ryan.bulletin.business.vo;

import java.util.Date;

public class BulletinVO {
	protected Integer id;
	protected String title;
	protected String description;
	protected Date create_time;
	protected String creator;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "BulletinVO [id=" + id + ", title=" + title + ", description="
				+ description + ", create_time=" + create_time + ", creator="
				+ creator + "]";
	}
	
}
