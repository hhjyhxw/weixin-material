package com.icloud.dao;

import java.util.List;

import com.icloud.common.QueryBuilder;

public interface BaseDao<T> {
	/**
	 * 保存
	 * @param t
	 */
	public Long insert(T t) throws Exception;
	/**
	 * 保存非空字段
	 * @param t
	 */
	 int insertSelective(T record)throws Exception;
	/**
	 * 更新所有
	 * @param t
	 * @throws Exception
	 */
	public int updateByPrimaryKey(T t) throws Exception;
	
	
	/**
	 * 更新非空字段
	 * @param t
	 * @throws Exception
	 */
	public int updateByPrimaryKeySelective(T t) throws Exception;
	
	/**
	 * 查找列表
	 * @param t
	 * @return
	 * @throws Exception
	 */
	 List<T> selectByExample(QueryBuilder example);
	/**
	 * 通过Id删除
	 * @param id
	 */
	public void deleteByPrimaryKey(Long id) throws Exception;
	
	/**
	 * 通过主键查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T selectByPrimaryKey(Long id)throws Exception;
	
	/**
	 * 通过条件统计
	 * @author   : zdh
	 * @param example
	 * @return
	 * @throws Exception   :
	 */
    long countByExample(QueryBuilder example)throws Exception;

    /**
	 * 通过条件删除
	 * @author   : zdh
	 * @param example
	 * @return
	 * @throws Exception   :
	 */
    int deleteByExample(QueryBuilder example)throws Exception;
	
    public List<T> findByPage(T t);
    
    public List<T> findForList(T t);
} 
