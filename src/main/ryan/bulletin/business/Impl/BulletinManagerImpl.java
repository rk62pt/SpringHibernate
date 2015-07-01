package main.ryan.bulletin.business.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import main.ryan.bulletin.business.BulletinManager;
import main.ryan.bulletin.business.vo.BulletinVO;
import main.ryan.bulletin.persistence.vo.Bulletin;
import main.ryan.util.dao.GenericDAO;

@Service
public class BulletinManagerImpl implements BulletinManager {

	@Resource(name = "bulletinDAO")
	private GenericDAO<Bulletin,Integer> bulletinDAO;
	
	@Override
	public BulletinVO search(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BulletinVO load(Integer id) throws Exception {
		BulletinVO vo = new BulletinVO();
		Bulletin entity = bulletinDAO.getByPK(id);
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<BulletinVO> list() throws Exception {
		List<BulletinVO> voList = new ArrayList<BulletinVO>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bulletin.class);
		List<Bulletin> entityList = bulletinDAO.findByCriteria(detachedCriteria,0);
		
		for(Bulletin entity : entityList){
			BulletinVO vo = new BulletinVO();
			BeanUtils.copyProperties(entity, vo);
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public BulletinVO create(BulletinVO vo) throws Exception {
		Bulletin entity = new Bulletin();
		BeanUtils.copyProperties(vo, entity);
		Integer id = bulletinDAO.saveWithId(entity);
		vo.setId(id);
		return vo;
	}

	@Override
	public BulletinVO modify(BulletinVO vo) throws Exception {
		Bulletin entity = new Bulletin();
		Bulletin bulletin =	bulletinDAO.getByPK(vo.getId());
		BeanUtils.copyProperties(bulletin, entity);
		entity.setTitle(vo.getTitle());
		entity.setDescription(vo.getDescription());
		bulletinDAO.update(entity);
		
		return vo;
	}

	@Override
	public Integer delete(BulletinVO vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
