package main.ryan.bulletin.business;

import java.util.List;

import main.ryan.bulletin.business.vo.BulletinVO;

public interface BulletinManager {
	public abstract BulletinVO search(String keyword) throws Exception;
	
	public abstract BulletinVO load(Integer id) throws Exception;
	
	public abstract List<BulletinVO> list() throws Exception;
	
	public abstract BulletinVO create(BulletinVO vo) throws Exception;
	
	public abstract BulletinVO modify(BulletinVO vo) throws Exception;
	
	public abstract Integer delete(BulletinVO vo) throws Exception;
}
