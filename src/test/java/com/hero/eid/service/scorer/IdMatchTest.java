package com.hero.eid.service.scorer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.hero.eid.model.Identity;
import com.hero.eid.model.StateIdentification;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class IdMatchTest {

    private Identity match = new Identity()
            .stateIds(
                    Arrays.asList(
                            new StateIdentification()
                                    .issuer("State of Georgia")
                                    .expiration(LocalDate.of(2019, Month.OCTOBER, 2))
                                    .number("999"),
                            new StateIdentification()
                                    .issuer("SSA")
                                    .number("555-12-1234"),
                            new StateIdentification()
                                    .issuer("US Department of State")
                                    .number("C44444444444")
                                    .expiration(LocalDate.of(2011, Month.JANUARY, 1))

                    )
            );

    @Test
    public void shouldIncrementBy10OnEachIdMatch() {
        Identity query = new Identity()
                .stateIds(
                        new ArrayList<>(Collections.singletonList(
                                new StateIdentification()
                                        .issuer("State of Georgia")
                                        .expiration(LocalDate.of(2019, Month.OCTOBER, 2))
                                        .number("999")
                        ))
                );
        IdMatch scorer = new IdMatch();
        assertEquals(10, scorer.computeScore(query, match).get().value);

        query.getStateIds().add(new StateIdentification()
                .issuer("SSA")
                .number("555-12-1234")
        );

        assertEquals(20, scorer.computeScore(query, match).get().value);
    }

    @Test
    public void shouldIgnoreExpiredIds() {
        Identity query = new Identity()
                .stateIds(
                        Arrays.asList(
                                new StateIdentification()
                                        .issuer("SSA")
                                        .number("555-12-1234"),
                                new StateIdentification()
                                        .issuer("US Department of State")
                                        .number("C44444444444")
                                        .expiration(LocalDate.of(2011, Month.JANUARY, 1))
                        )
                );
        IdMatch scorer = new IdMatch();
        assertEquals(10, scorer.computeScore(query, match).get().value);
    }

}