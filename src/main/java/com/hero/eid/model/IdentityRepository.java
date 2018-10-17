package com.hero.eid.model;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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

    public List<Identity> findMatchingValues(Identity inputValues){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Identity> query = builder.createQuery(Identity.class);
        Root<Identity> root = query.from(Identity.class);
        Optional<Join<Identity, Address>> addresses = ofNullable(inputValues.getAddresses())
                .filter(c -> !c.isEmpty())
                .map(a -> root.join("addresses"));
        Optional<Join<Identity, StateIdentification>> stateIds = ofNullable(inputValues.getStateIds())
                .filter(c -> !c.isEmpty())
                .map(i -> root.join("stateIds"));
        //noinspection unchecked
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
                                    ),
                                    ofNullable(inputValues.getAddresses())
                                            .orElse(Collections.emptyList())
                                            .stream()
                                            .flatMap(address ->
                                                    Stream.of(
                                                            ofNullable(address.getNumber()).map(v -> builder.equal(addresses.get().get("number"), v)),
                                                            ofNullable(address.getStreet()).map(v -> builder.equal(addresses.get().get("street"), v)),
                                                            ofNullable(address.getUnit()).map(v -> builder.equal(addresses.get().get("unit"), v)),
                                                            ofNullable(address.getAdditional()).map(v -> builder.equal(addresses.get().get("additional"), v)),
                                                            ofNullable(address.getCityRegion()).map(v -> builder.equal(addresses.get().get("cityRegion"), v)),
                                                            ofNullable(address.getStateProvince()).map(v -> builder.equal(addresses.get().get("stateProvince"), v)),
                                                            ofNullable(address.getPostalCode()).map(v -> builder.equal(addresses.get().get("postalCode"), v)),
                                                            ofNullable(address.getCountryCode()).map(v -> builder.equal(addresses.get().get("countryCode"), v)),
                                                            ofNullable(address.getStartDate()).map(v -> builder.equal(addresses.get().get("startDate"), v)),
                                                            ofNullable(address.getAdditional()).map(v -> builder.equal(addresses.get().get("additional"), v))
                                                    )
                                            ).collect(Collectors.toList()),
                                    ofNullable(inputValues.getStateIds())
                                            .orElse(Collections.emptyList())
                                            .stream()
                                            .flatMap(id ->
                                                    Stream.of(
                                                            ofNullable(id.getIssuer()).map(v -> builder.equal(stateIds.get().get("issuer"), v)),
                                                            ofNullable(id.getNumber()).map(v -> builder.equal(stateIds.get().get("number"), v)),
                                                            ofNullable(id.getExpiration()).map(v -> builder.equal(stateIds.get().get("expiration"), v))
                                                    )
                                            ).collect(Collectors.toList())

                            )
                        )

                );
        Query q = entityManager.createQuery(query);
        q.setMaxResults(10);
        //noinspection unchecked,ResultOfMethodCallIgnored
        return ((List<Identity>)q.getResultList()).stream().peek(Identity::toString).collect(Collectors.toList());
    }

    private Predicate[] collapse(List<Optional<Predicate>>... predicates) {
        return Stream.of(predicates)
                .flatMap(Collection::stream)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(Predicate[]::new);

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
