package com.hero.eid.model;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Transactional
@Service
public class IdentityRepository {

    @PersistenceContext
    private  EntityManager entityManager;

    @Autowired
    public IdentityRepository() {

    }

    public IdentityRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Identity> findMatchingValues(Identity inputValues){
        return null;
    }

    public Identity persist(Identity identity){
        if(identity.getId() == null) {
            entityManager.persist(identity);
            return identity;
        } else {
            return entityManager.merge(identity);
        }
    }

    public Optional<Identity> findById(String id){
        return Optional.ofNullable(entityManager.find(Identity.class, id)).map(read->{
            read.toString();
            return read;
        });
    }
}
