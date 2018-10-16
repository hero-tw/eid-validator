package com.hero.eid.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.Id;

import com.hero.eid.Config;
import com.hero.eid.EIDValidatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertEquals(stored.toString(), read.toString());
    }

    @Test
    public void shouldFindSimpleNameMatch(){
        Identity i = new Identity()
                .name(new Name()
                    .givenName("John")
                        .secondaryName("Thomas")
                        .surname("Doe")
                );
        i = repository.persist(i);

        Identity query = new Identity()
                .name(new Name()
                        .givenName("John")
                        .surname("Doe"));

        List<Identity> results = repository.findMatchingValues(query);
        assertEquals(1, results.size());
        assertEquals(i.toString(), results.get(0).toString());
    }

    @Test
    public void shouldFindDOBMatch(){
        Identity i = new Identity()
               .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 02));

        i = repository.persist(i);

        Identity query = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 02));

        List<Identity> results = repository.findMatchingValues(query);
        assertEquals(1, results.size());
        assertEquals(i.toString(), results.get(0).toString());
    }

}