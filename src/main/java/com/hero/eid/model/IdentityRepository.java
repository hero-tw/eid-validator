package com.hero.eid.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

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

    public List<Identity> findMatchingValues(Identity inputValues){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Identity> query = builder.createQuery(Identity.class);
        Root<Identity> root = query.from(Identity.class);
        query.select(root)
                .where(
                        builder.and(
                            collapse(
                                    asList(
                                            ofNullable(inputValues.getDateOfBirth()).map(v->builder.equal(root.get("dateOfBirth"), v)),
                                            ofNullable(inputValues.getName()).map(Name::getPrefix).map(v->builder.equal(root.get("name").get("prefix"), v)),
                                            ofNullable(inputValues.getName()).map(Name::getGivenName).map(v->builder.equal(root.get("name").get("givenName"), v)),
                                            ofNullable(inputValues.getName()).map(Name::getSecondaryName).map(v->builder.equal(root.get("name").get("secondaryName"), v)),
                                            ofNullable(inputValues.getName()).map(Name::getSurname).map(v->builder.equal(root.get("name").get("surname"), v)),
                                            ofNullable(inputValues.getName()).map(Name::getSuffix).map(v->builder.equal(root.get("name").get("suffix"), v))
                                    )
                            )
                        )

                );
        Query q = entityManager.createQuery(query);
        q.setMaxResults(10);
        //noinspection unchecked
        return (List<Identity>) q.getResultList();
    }

    private Predicate[] collapse(List<Optional<Predicate>> predicates){
        return predicates
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList())
                .toArray(new Predicate[0]);

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
        return ofNullable(entityManager.find(Identity.class, id)).map(read->{
            //noinspection ResultOfMethodCallIgnored
            read.toString();
            return read;
        });
    }
}
