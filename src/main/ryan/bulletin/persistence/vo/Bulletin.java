package main.ryan.bulletin.persistence.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import main.ryan.bulletin.business.vo.BulletinVO;

/**
 * 公佈欄.
 */
@Repository
@Entity
@Table(name = "BULLETIN")
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

	@Override
	public String getTb_title() {
		// TODO Auto-generated method stub
		return super.getTb_title();
	}

	@Override
	public void setTb_title(String tb_title) {
		// TODO Auto-generated method stub
		super.setTb_title(tb_title);
	}

	@Override
	public String getTb_description() {
		// TODO Auto-generated method stub
		return super.getTb_description();
	}

	@Override
	public void setTb_description(String tb_description) {
		// TODO Auto-generated method stub
		super.setTb_description(tb_description);
	}

	@Override
	public String getTb_create_time() {
		// TODO Auto-generated method stub
		return super.getTb_create_time();
	}

	@Override
	public void setTb_create_time(String tb_create_time) {
		// TODO Auto-generated method stub
		super.setTb_create_time(tb_create_time);
	}

	@Override
	public String getTb_creator() {
		// TODO Auto-generated method stub
		return super.getTb_creator();
	}

	@Override
	public void setTb_creator(String tb_creator) {
		// TODO Auto-generated method stub
		super.setTb_creator(tb_creator);
	}
    
    
}
