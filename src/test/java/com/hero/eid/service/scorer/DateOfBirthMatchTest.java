package com.hero.eid.service.scorer;

import java.time.LocalDate;
import java.time.Month;

import com.hero.eid.model.Identity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DateOfBirthMatchTest {


    @Test
    public void shouldReturnTenIfMatches() {
        DateOfBirthMatch scorer = new DateOfBirthMatch();
        Identity q = new Identity()
                .dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        Identity m = new Identity()
                .dateOfBirth(LocalDate.of(1977, Month.APRIL, 29));

        assertFalse(scorer.computeScore(q, m).isPresent());

        m.dateOfBirth(LocalDate.of(1974, Month.OCTOBER, 2));

        assertEquals(10, scorer.computeScore(q, m).get().value);
    }

}