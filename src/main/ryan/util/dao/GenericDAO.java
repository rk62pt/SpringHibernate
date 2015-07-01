package main.ryan.util.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T, PK extends Serializable> {

    /**
     * 新增.
     * 
     * @param entity
     */
    void save(T entity) throws Exception;
    
    /**
     * 新增.
     * 
     * @param entity
     */
    Integer saveWithId(T entity) throws Exception;

    /**
     * 新增or刪除.
     * 
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * 刪除.
     * 
     * @param entity
     */
    void delete(T entity);

    /**
     * 刪除.
     * 
     * @param entit
     */
    //void delete(PK id);

    /**
     * 查詢.
     * 
     * @param id
     * @return
     */
    T loadByPK(PK id);

    /**
     * 查詢.
     * 
     * @param id
     * @return
     */
    T getByPK(PK id);

    List<T> findByExample(T exampleInstance, String... excludeProperty);

    List<T> findAll();

    /**
     * 修改.
     * 
     * @param entity
     */
    void update(T entity);

    void flush(Session session);

    void clear();

    void evict(Object obj);

    void refresh(Object obj);

    /**
     * Criteria 查詢.
     * 
     * @param criterion
     * @return
     */
    List<T> findByCriteria(Criterion... criterion);

    /**
     * 查詢單筆(HQL).
     * 
     * @param hql
     * @param params
     * @return
     */
    T queryForObject(String hql, Object[] params);

    /**
     * 查詢第一筆(HQL).
     * 
     * @param hql
     * @param params
     * @return
     */
    T queryForTopObject(String hql, Object[] params);

    /**
     * 查詢列表(HQL).
     * 
     * @param hql
     * @param params
     * @return
     */
    List<T> queryForList(String hql, Object[] params);

    /**
     * 查詢N筆資料(HQL).
     * 
     * @param hql
     * @param params
     * @return
     */
    List<T> queryForList(String hql, Object[] params, int recordNum);
    
    List<T> findByCriteria(DetachedCriteria detachedCriteria, int startResult);
    
    List<T> findByCriteria(DetachedCriteria detachedCriteria, int startResult, int limitResult);
    
    Long getRecordCount(DetachedCriteria detachedCriteria);
    

}