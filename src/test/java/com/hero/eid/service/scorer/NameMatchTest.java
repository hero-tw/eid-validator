package com.hero.eid.service.scorer;

import com.hero.eid.model.Identity;
import com.hero.eid.model.Name;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class NameMatchTest {

    @Test
    public void shouldReturn20OnAMatch(){
        Identity query = new Identity()
                .name(
                        new Name()
                        .givenName("Robert")
                        .surname("Cooper")
                );
        Identity match = new Identity()
                .name(
                        new Name()
                        .givenName("Robert")
                        .secondaryName("Thomas")
                        .surname("Cooper")
                );

        assertEquals(10, new NameMatch().computeScore(query, match).get().value);
    }

    @Test
    public void shouldReturnEmptyOnMismatch(){
        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Billy")
                                .surname("Cooper")
                );
        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                );

        assertFalse(new NameMatch().computeScore(query, match).isPresent());
    }

}