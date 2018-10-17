package com.hero.eid.service.scorer;

import com.hero.eid.model.Name;
import org.junit.Test;

import static com.hero.eid.service.scorer.ScorerUtil.compareName;
import static org.junit.Assert.*;

public class ScorerUtilTest {


    @Test
    public void shouldCompareNamesWithPartialMatchingAndMinimumGivenAndSurname() {

        Name query = new Name()
                .givenName("Robert")
                .surname("Cooper");

        Name match = new Name()
                .givenName("Robert")
                .secondaryName("Thomas")
                .surname("Cooper")
                .nickname("Snake")
                .suffix("Esq");

        assertTrue(compareName(query, match));

        query.givenName(null);

        assertFalse(compareName(query, match));

        query.givenName("Robert");
        query.nickname("Spike");

        assertFalse(compareName(query, match));

        query.nickname("Snake");

        assertTrue(compareName(query, match));

        assertFalse(compareName(null, match));
        assertFalse(compareName(query, null));

    }

}