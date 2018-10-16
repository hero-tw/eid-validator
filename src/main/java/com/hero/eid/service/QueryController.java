package com.hero.eid.service;

import java.util.Comparator;
import java.util.List;

import com.hero.eid.model.Identity;
import com.hero.eid.model.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eid")
public class QueryController {

    private final IdentityRepository repo;

    @Autowired
    public QueryController(IdentityRepository repo) {
        this.repo = repo;
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
        //TODO Compute a score based on the things that match.
        return 0;
    }

}
