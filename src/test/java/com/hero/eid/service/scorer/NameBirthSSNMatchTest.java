package com.hero.eid.service.scorer;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.model.Name;
import com.hero.eid.model.StateIdentification;
import com.hero.eid.service.Score;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class NameBirthSSNMatchTest {

    @Test
    public void shouldProvideFinalScore50ForMatchingNameDOBSSN() {

        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));


        Optional<Score> result = new NameBirthSSNMatch().computeScore(query, match);
        assertTrue(result.get().isFinal);
        assertEquals(50, result.get().value);

    }

    @Test
    public void shouldNotMatchIfNameMismatch() {

        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Philip")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));


        Optional<Score> result = new NameBirthSSNMatch().computeScore(query, match);
        assertFalse(result.isPresent());

    }

    @Test
    public void shouldNotMatchIfSSNMismatch() {

        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .surname("Cooper")
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));


        Optional<Score> result = new NameBirthSSNMatch().computeScore(query, match);
        assertFalse(result.isPresent());

    }

    @Test
    public void shouldNotMatchIfDOBMismatch() {

        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .surname("Cooper")
                )
                .dateOfBirth(LocalDate.of(1977, Month.OCTOBER, 2));


        Optional<Score> result = new NameBirthSSNMatch().computeScore(query, match);
        assertFalse(result.isPresent());

    }

    @Test
    public void shouldNotMatchIfDOBAbsent() {

        Identity match = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .secondaryName("Thomas")
                                .surname("Cooper")
                )
                .stateIds(
                        Collections.singletonList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234")
                        )
                )
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity query = new Identity()
                .name(
                        new Name()
                                .givenName("Robert")
                                .surname("Cooper")
                )
                .dateOfBirth(null);


        Optional<Score> result = new NameBirthSSNMatch().computeScore(query, match);
        assertFalse(result.isPresent());

    }


}