package main.ryan.util.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.ryan.util.dao.GenericDAO;

@Component("GenericDAO")
@Transactional
public class GenericDAOHibernateImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

    //private static org.apache.log4j.Logger log = Logger.getLogger(GenericDAOHibernateImpl.class);
    
	@Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    private T persistentClass;
    
    public GenericDAOHibernateImpl() {}
    
    /**
     * 指定實體名稱.
     */
    public GenericDAOHibernateImpl(T persistentClass) {
        this.persistentClass = persistentClass;
    }
    
    public Session getSession() {
        //Session session = sessionFactory.withOptions().openSession();
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        //SessionBuilder sb = sessionFactory.withOptions();
        //Session session = sb.connection(connection).openSession();
        return session;
    }
    
    public Class<? extends Object> getPersistentClass() {  
        return persistentClass.getClass();  
    } 

    @SuppressWarnings("unchecked")
    @Override
    public T loadByPK(PK id) {
        return (T) getSession().load(getPersistentClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByPK(PK id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    @Transactional
    public void save(T entity) throws Exception {
        getSession().save(entity);
    }
    
    @Override
    @Transactional
    public Integer saveWithId(T entity) throws Exception {
        return (Integer)getSession().save(entity);
    }
    
    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }  

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {

        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return findByCriteria();  
    }

    //http://ufaw0116.erufa.com/wordpress/?p=745
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly=false)
    public void update(T entity) {
        Session session = getSession();
        getSession().update(entity);
        System.out.println("test");
        flush(session);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Session session = getSession();
        session.delete(entity);
        flush(session);
    }
    
//    @Override
//    public void delete(PK id){
//        Session session = getSession();
//        session.delete(session.get(getPersistentClass(), id));
//        flush(session);
//    }

    @Override
    public void flush(Session session) {
        session.flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @Override
    public void evict(Object obj) {
        getSession().evict(obj);
    }

    @Override
    public void refresh(Object obj) {
        getSession().refresh(obj);
    }
    
    public void done(Session session) {
        session.flush();
        session.clear();
    }
    
    /**
     * Use this inside subclasses as a convenience method.
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(Criterion... criterion) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, int startResult) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        criteria.setFirstResult(startResult);
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, int startResult, final int limitResult) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        criteria.setFirstResult(startResult);
        criteria.setMaxResults(limitResult);
        return criteria.list();
    }
    
    /*--------------- HQL ---------------*/
    
    @SuppressWarnings("unchecked")
    @Override  
    public T queryForObject(String hql, Object[] params) {  
        Query query = getSession().createQuery(hql);  
        setQueryParams(query, params);  
        return (T) query.uniqueResult();  
    }  
  
    @SuppressWarnings("unchecked")
    @Override
    public T queryForTopObject(String hql, Object[] params) {  
        Query query = getSession().createQuery(hql);  
        setQueryParams(query, params);  
        return (T) query.setFirstResult(0).setMaxResults(1).uniqueResult();  
    }  
  
    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryForList(String hql, Object[] params) {  
        Query query = getSession().createQuery(hql);  
        setQueryParams(query, params);  
        return query.list();  
    }  
  
    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryForList(final String hql, final Object[] params, final int recordNum) {  
        Query query = getSession().createQuery(hql);  
        setQueryParams(query, params);  
        return query.setFirstResult(0).setMaxResults(recordNum).list();  
    }
    /*
    public List<Object[]> queryObjectBySQL(String sql) {
        List<Object[]> list = getSession().createSQLQuery(sql).list();
        return list;
    }
    
    public int updateObjectBySQL(String sql) {
        int result = 0;
        Session session = null;
        Transaction tx = null;
        try {
            session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            result = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException ee) {
                    ee.printStackTrace();
                }
            }
        }
        return result;
    }*/
    
    @Override
    public Long getRecordCount(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        long count = Long.parseLong((criteria.setProjection(Projections.rowCount()).uniqueResult()).toString());
        criteria.setProjection(null);
        return Long.valueOf("" + count);
    }
  
    private void setQueryParams(Query query, Object[] params) {  
        if (null == params) {  
            return;  
        }  
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }  
    }
}
