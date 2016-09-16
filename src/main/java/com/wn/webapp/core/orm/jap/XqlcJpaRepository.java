package com.wn.webapp.core.orm.jap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface XqlcJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public abstract List<T> queryByMap(Map<String, Object> paramMap);

	public abstract List<T> queryByMap(Map<String, Object> paramMap, int paramInt);

	public abstract List<T> queryByMap(Map<String, Object> paramMap, int paramInt, Sort paramSort);

	public abstract List<T> queryByMap(Map<String, Object> paramMap, int paramInt1, int paramInt2);

	public abstract List<T> queryByMap(Map<String, Object> paramMap, int paramInt1, int paramInt2, Sort paramSort);

	public abstract List<T> queryByMap(Map<String, Object> paramMap, Sort paramSort);

	public abstract List<T> queryByCriteria(Criteria paramCriteria);

	public abstract List<T> queryByCriteria(Criteria paramCriteria, int paramInt);

	public abstract List<T> queryByCriteria(Criteria paramCriteria, int paramInt, Sort paramSort);

	public abstract List<T> queryByCriteria(Criteria paramCriteria, int paramInt1, int paramInt2);

	public abstract List<T> queryByCriteria(Criteria paramCriteria, int paramInt1, int paramInt2, Sort paramSort);

	public abstract List<T> queryByCriteria(Criteria paramCriteria, Sort paramSort);

	public abstract long countByMap(Map<String, Object> paramMap);

	public abstract long countByCriteria(Criteria paramCriteria);

	public abstract Page<T> queryPageByMap(Map<String, Object> paramMap, Pageable paramPageable);

	public abstract Page<T> queryPageByCriteria(Criteria paramCriteria, Pageable paramPageable);

	public abstract Criteria createCriteria(Map<String, Object> paramMap);

	public abstract Disjunction createdDisjunction(Map<String, Object> paramMap);

	public abstract int nativeSqlUpdate(String paramString, Object[] paramArrayOfObject);

	public abstract int nativeSqlUpdate(String paramString, Map<String, ?> paramMap);

	public abstract T saveEntity(T paramT);

	public abstract void insertInBatch(List<T> paramList);
}
