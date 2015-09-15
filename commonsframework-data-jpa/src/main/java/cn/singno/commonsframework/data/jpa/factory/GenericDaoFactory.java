package cn.singno.commonsframework.data.jpa.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import cn.singno.commonsframework.generic.GenericDaoImpl;

public class GenericDaoFactory extends JpaRepositoryFactory {   
	  
    public GenericDaoFactory(EntityManager entityManager) {   
        super(entityManager);   
        // TODO Auto-generated constructor stub  
    }   
    
    @Override  
    @SuppressWarnings({ "unchecked", "rawtypes" })   
    protected <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager em) {   
    	JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
        return new GenericDaoImpl(entityInformation, em);   
    }   
  
    @Override  
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {   
        return GenericDaoImpl.class;   
    }   
}  
