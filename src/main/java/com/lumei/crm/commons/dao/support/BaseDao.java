package com.lumei.crm.commons.dao.support;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lumei.crm.commons.mybatis.support.Example;

/**
 * 
 * @author dave
 *
 * @param <T>
 */
public interface BaseDao<T> {

  /**
   * @param example
   * @return
   */
  int countByExample(Example<T> example);

  /**
   * @param id
   * @return
   */
  int deleteByPrimaryKeyHard(T record);

  /**
   * @param id
   * @return
   */
  int deleteByPrimaryKey(T record);
  
  /**
   * @param record
   * @return
   */
  int insert(T record);

  /**
   * @param record
   * @return
   */
  int insertSelective(T record);

  /**
   * @param example
   * @return
   */
  List<T> selectByExample(Example<T> example);

  /**
   * @param rowBounds
   * @return
   */
  List<T> selectByExample(Example<T> example, RowBounds rowBounds);

  /**
   * @param id
   * @return
   */
  T selectByPrimaryKey(T record);

  /**
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(T record);

  /**
   * @param record
   * @return
   */
  int updateByPrimaryKey(T record);

}
