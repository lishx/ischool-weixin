package com.ischool.weixin.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import com.ischool.weixin.dao.impl.BaseDAOImpl;



public class BaseRepositoryFactory extends JpaRepositoryFactory {
	@SuppressWarnings("unused")
	private final EntityManager entityManager;

	public BaseRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		Assert.notNull(entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,
			EntityManager entityManager) {
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new BaseDAOImpl(entityInformation, entityManager);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

		return BaseDAOImpl.class;
		
	}

}
