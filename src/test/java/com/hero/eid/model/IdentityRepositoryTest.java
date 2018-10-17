package com.hero.eid.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import com.hero.eid.EIDValidatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EIDValidatorApplication.class)
@ActiveProfiles("test")
public class IdentityRepositoryTest {


    @Autowired
    IdentityRepository repository;

    @Test
    public void shouldPersistSimpleIdentity(){

        Identity i = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                                .nickname("Snake")
                );
        Identity stored = repository.persist(i);

        Identity read = repository.findById(stored.getId()).get();
        assertEquals(stored, read);
    }

    @Test
    public void shouldFindSimpleNameMatch(){
        Identity i = new Identity()
                .name(new Name()
                        .givenName("John")
                        .secondaryName("David")
                        .surname("Doe")
                );
        i = repository.persist(i);

        Identity query = new Identity()
                .name(new Name()
                        .givenName("John")
                        .surname("Doe"));

        List<Identity> results = repository.findMatchingValues(query);
        assertEquals(1, results.size());
        assertEquals(i, results.get(0));
    }

    @Test
    public void shouldFindDOBMatch(){
        Identity i = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        i = repository.persist(i);

        Identity query = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        List<Identity> results = repository.findMatchingValues(query);
        assertEquals(1, results.size());
        assertEquals(i.toString(), results.get(0).toString());
    }

    @Test
    public void shouldFindAddressMatch() {
        Identity i = new Identity()
                .name(new Name()
                        .givenName("Robert")
                        .secondaryName("Thomas")
                        .surname("Cooper")
                )
                .addresses(
                        asList(
                                new Address()
                                        .number("984")
                                        .street("Michigan Ave NW")
                                        .cityRegion("Atlanta")
                                        .stateProvince("Georgia")
                                        .postalCode("30314")
                                        .countryCode("US")
                                        .startDate(LocalDate.of(2018, Month.JUNE, 20)),
                                new Address()
                                        .number("840")
                                        .street("Penn Ave NE")
                                        .unit("5")
                                        .cityRegion("Atlanta")
                                        .stateProvince("Georgia")
                                        .postalCode("30308")
                                        .countryCode("US")
                                        .startDate(LocalDate.of(2006, Month.OCTOBER, 1))
                                        .endDate(LocalDate.of(2018, Month.JUNE, 20))
                        )
                );
        i = repository.persist(i);

        Identity query = new Identity()
                .name(new Name()
                        .givenName("Robert")
                        .surname("Cooper")
                )
                .addresses(Collections.singletonList(
                        new Address()
                                .number("984")
                                .street("Michigan Ave NW")
                                .cityRegion("Atlanta")
                                .stateProvince("Georgia")
                                .postalCode("30314")
                ));

        Identity result = repository.findMatchingValues(query).get(0);

        assertEquals(i, result);


    }

    @Test
    public void shouldMergeChangesOnPersist() {
        Identity i = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 3));

        i = repository.persist(i);

        i.name(new Name().givenName("Robert").surname("Cooper"));

        i = repository.persist(i);


        Identity query = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 3));

        List<Identity> results = repository.findMatchingValues(query);
        assertEquals(1, results.size());
        assertEquals(i, results.get(0));
    }

}