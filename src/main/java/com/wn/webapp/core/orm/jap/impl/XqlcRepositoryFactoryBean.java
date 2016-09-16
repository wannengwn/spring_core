package com.wn.webapp.core.orm.jap.impl;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.wn.webapp.core.orm.jap.XqlcJpaRepository;

public class XqlcRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, I> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new DynamicQueryRepositoryFactory(entityManager);
	}

	private static class DynamicQueryRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
		private final EntityManager entityManager;

		public DynamicQueryRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;

		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		protected Object getTargetRepository(RepositoryInformation information) {
			// JpaEntityInformation<?, Serializable> entityInformation =
			// getEntityInformation(information.getDomainType());
			// return new
			// XqlcJpaRepositoryService(entityInformation,entityManager);
			return new XqlcJpaRepositoryService(information.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return XqlcJpaRepository.class;
		}
	}
}