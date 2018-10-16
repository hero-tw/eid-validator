package com.hero.eid.model;

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

}