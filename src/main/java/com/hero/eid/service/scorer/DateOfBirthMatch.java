package com.hero.eid.service.scorer;

import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

import static com.hero.eid.service.scorer.ScorerUtil.doesMatchIfPresentOrMatchIsNull;

public class DateOfBirthMatch implements Scorer {
    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {
        if(query.getDateOfBirth() == null){
            return Optional.empty();
        }
        return doesMatchIfPresentOrMatchIsNull(query.getDateOfBirth(), match.getDateOfBirth()) ?
                Optional.of(new Score(10, false)) : Optional.empty();

    }
}
