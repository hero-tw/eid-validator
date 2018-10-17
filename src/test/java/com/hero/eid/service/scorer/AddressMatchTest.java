package com.hero.eid.service.scorer;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import com.hero.eid.model.Address;
import com.hero.eid.model.Identity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AddressMatchTest {


    Identity match = new Identity()
            .addresses(
                    Arrays.asList(
                            new Address()
                                    .number("742")
                                    .street("Evergreen Terrace")
                                    .cityRegion("Springfield")
                                    .stateProvince("Kentucky")
                                    .postalCode("42082")
                                    .countryCode("US")
                                    .startDate(LocalDate.of(2010, Month.MAY, 1)),
                            new Address()
                                    .number("10")
                                    .street("K Street")
                                    .cityRegion("Washington")
                                    .stateProvince("District of Columbia"),
                            new Address()
                                    .number("1600")
                                    .street("Pennsylvania Avenue")
                                    .cityRegion("Washington")
                                    .stateProvince("District of Columbia"),
                            new Address()
                                    .number("221")
                                    .street("Baker Street")
                                    .unit("B")
                                    .cityRegion("London")
                                    .stateProvince("London")
                                    .postalCode("NW1 6XE")
                                    .countryCode("UK")
                                    .startDate(LocalDate.of(2000, Month.JANUARY, 10))
                                    .endDate(LocalDate.of(2010, Month.APRIL, 29)),
                            new Address()
                                    .number("221")
                                    .street("Baker Street")
                                    .unit("C")
                                    .cityRegion("London")
                                    .stateProvince("London")
                                    .postalCode("NW1 6XE")
                                    .countryCode("UK")
                                    .startDate(LocalDate.of(2000, Month.JANUARY, 10))
                                    .endDate(LocalDate.of(2010, Month.APRIL, 29))
                    )
            );

    @Test
    public void shouldIncrementBy5OnEachMatch() {
        Identity query = new Identity();


        AddressMatch scorer = new AddressMatch();
        assertFalse(scorer.computeScore(query, match).isPresent());

        query.getAddresses().add(new Address()
                .number("221")
                .street("Baker Street")
                .unit("B")
                .cityRegion("London")
                .stateProvince("London")
                .postalCode("NW1 6XE")
                .countryCode("UK")
                .startDate(LocalDate.of(2000, Month.JANUARY, 10))
                .endDate(LocalDate.of(2010, Month.APRIL, 29)));

        assertEquals(5, scorer.computeScore(query, match).get().value);

        query.getAddresses().add(
                new Address()
                        .number("1600")
                        .street("Pennsylvania Avenue")
                        .cityRegion("Washington")
                        .stateProvince("District of Columbia")
        );

        assertEquals(10, scorer.computeScore(query, match).get().value);

        query.getAddresses().add(
                new Address()
                        .number("10")
                        .street("Downing Street")
                        .cityRegion("London")
                        .stateProvince("London")
        );

        assertEquals(10, scorer.computeScore(query, match).get().value);




    }

    @Test
    public void shouldMatchFractional(){
        Identity query = new Identity();


        AddressMatch scorer = new AddressMatch();

        query.addresses(Collections.singletonList(
                new Address()
                        .number("221")
                        .street("Baker Street")
                        .cityRegion("London")
                        .stateProvince("London")
                        .postalCode("NW1 6XE")
                )
        );

        assertEquals(10, scorer.computeScore(query, match).get().value);
    }

}