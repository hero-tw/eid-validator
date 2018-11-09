package com.hero.eid.service.scorer;

import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

import static com.hero.eid.service.scorer.ScorerUtil.doesMatchIfPresentOrMatchIsNull;

public class DateOfBirthMatch implements Scorer {
    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {
        return Optional.ofNullable(query.getDateOfBirth())
                .filter(queryDob -> doesMatchIfPresentOrMatchIsNull(queryDob, match.getDateOfBirth()))
                .map(dob ->new Score(10, false));
    }
}
