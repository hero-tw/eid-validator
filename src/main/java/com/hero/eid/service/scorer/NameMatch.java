package com.hero.eid.service.scorer;

import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

import static com.hero.eid.service.scorer.ScorerUtil.compareName;

public class NameMatch implements Scorer {

    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {
        return compareName(query.getName(), match.getName()) ?
                Optional.of(new Score(10, false)) : Optional.empty();
    }
}
