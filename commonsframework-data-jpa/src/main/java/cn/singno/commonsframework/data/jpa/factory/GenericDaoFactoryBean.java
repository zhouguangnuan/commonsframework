package cn.singno.commonsframework.data.jpa.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class GenericDaoFactoryBean<T extends JpaRepository<Object, Serializable>> extends JpaRepositoryFactoryBean<T, Object, Serializable>  {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new GenericDaoFactory(entityManager);
	}
}
