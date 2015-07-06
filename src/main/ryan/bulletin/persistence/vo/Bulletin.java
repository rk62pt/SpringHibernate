package main.ryan.bulletin.persistence.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.ryan.bulletin.business.vo.BulletinVO;

/**
 * 公佈欄.
 */
@Repository
@Entity
@Table(name = "bulletin")
public class Bulletin extends BulletinVO{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {                                                                                                                
        return id;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}

    @Column(name = "tb_title")
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

	@Column(name = "tb_description")
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		super.setDescription(description);
	}

	@Column(name = "tb_create_time")
	public Date getCreate_time() {
		// TODO Auto-generated method stub
		return super.getCreate_time();
	}

	@Override
	public void setCreate_time(Date create_time) {
		// TODO Auto-generated method stub
		super.setCreate_time(create_time);
	}

	@Column(name = "tb_creator")
	public String getCreator() {
		// TODO Auto-generated method stub
		return super.getCreator();
	}

	@Override
	public void setCreator(String creator) {
		// TODO Auto-generated method stub
		super.setCreator(creator);
	}

	
    
    
}
