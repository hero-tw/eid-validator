package com.hero.eid.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.model.IdentityRepository;
import com.hero.eid.service.scorer.AddressMatch;
import com.hero.eid.service.scorer.DateOfBirthMatch;
import com.hero.eid.service.scorer.IdMatch;
import com.hero.eid.service.scorer.NameBirthSSNMatch;
import com.hero.eid.service.scorer.NameMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eid")
public class QueryController {

    private final IdentityRepository repo;
    private final List<Scorer> scorers;

    @Autowired
    public QueryController(IdentityRepository repo) {
        this(repo,
                Arrays.asList(
                        new NameBirthSSNMatch(),
                        new NameMatch(),
                        new DateOfBirthMatch(),
                        new AddressMatch(),
                        new IdMatch()
                )
        );
    }

    QueryController(IdentityRepository repo, List<Scorer> scorers) {
        this.repo = repo;
        this.scorers = scorers;
    }

    @PostMapping
    public QueryResult findByValues(@RequestBody Identity query){
        Identity match =repo.findMatchingValues(query)
               .stream()
                .max(compareIdentities(query))
                .orElseThrow(NotFoundException::new);

        return new QueryResult(score(query, match), match);

    }


    private Comparator<Identity> compareIdentities(final Identity query){
        return Comparator.comparingInt(o -> score(query, o));
    }

    private int score(final Identity query, Identity match){
        int score = 0;
        for (Scorer s : scorers) {
            Optional<Score> result = s.computeScore(query, match);
            if (!result.isPresent()) {
                continue;
            }
            if (result.get().isFinal) {
                return result.get().value;
            } else {
                score += result.get().value;
            }
        }
        return Integer.min(50, score);
    }


}
