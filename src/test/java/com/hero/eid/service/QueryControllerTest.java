package com.hero.eid.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.model.IdentityRepository;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueryControllerTest {

    private IdentityRepository repository;
    private  Identity query = new Identity();
    private Identity match = new Identity();
    private Identity secondaryMatch = new Identity();


    @Before
    public void before(){
        this.repository = mock(IdentityRepository.class);
    }


    @Test
    public void shouldReturnOnlyFinalValueIfMatched(){
       when(repository.findMatchingValues(eq(query))).thenReturn(singletonList(match));
       Scorer finalScorer = (query, match) -> Optional.of(new Score(17, true));
       Scorer addtlScorer = (query, match) -> Optional.of(new Score(10, false));
       QueryController controller = new QueryController(repository, Arrays.asList(addtlScorer, finalScorer));

        QueryResult result = controller.findByValues(query);
        assertEquals(17, result.getScore());
        assertEquals(match, result.getIdentity());
    }

    @Test
    public void shouldReturnSumOfScores(){
        when(repository.findMatchingValues(eq(query))).thenReturn(singletonList(match));
        List<Scorer> scorers = Arrays.asList(
                ((query, match) -> Optional.of(new Score(10, false))),
                ((query, match) -> Optional.of(new Score(5, false))),
                ((query, match) -> Optional.empty())
        );
        QueryController controller = new QueryController(repository, scorers);

        QueryResult result = controller.findByValues(query);
        assertEquals(15, result.getScore());
        assertEquals(match, result.getIdentity());
    }

    @Test
    public void shouldReturnMatchWithHighestScore(){
        when(repository.findMatchingValues(eq(query))).thenReturn(Arrays.asList(match, secondaryMatch));
        List<Scorer> scorers = Arrays.asList(
                ((query, match) -> Optional.of(new Score(10, false))),
                ((query, match) -> match == secondaryMatch ? Optional.of(new Score(5, false)) : Optional.empty()),
                ((query, match) -> Optional.empty())
        );
        QueryController controller = new QueryController(repository, scorers);

        QueryResult result = controller.findByValues(query);
        assertEquals(15, result.getScore());
        assertEquals(secondaryMatch, result.getIdentity());
    }

}