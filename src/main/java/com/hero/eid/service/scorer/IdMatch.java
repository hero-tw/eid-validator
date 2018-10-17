package com.hero.eid.service.scorer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import com.hero.eid.model.Identity;
import com.hero.eid.model.StateIdentification;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

public class IdMatch implements Scorer {


    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {

        Map<String, StateIdentification> queryByIssuer = mapWithoutExpired(query.getStateIds());
        Map<String, StateIdentification> matchByIssuer = mapWithoutExpired(match.getStateIds());

        Set<String> allTypes = Sets.union(queryByIssuer.keySet(), matchByIssuer.keySet());
        int score = 0;
        for(String issuer : allTypes) {
            StateIdentification qid = queryByIssuer.get(issuer);
            StateIdentification mid = matchByIssuer.get(issuer);
            if(mid == null || qid == null){
                continue;
            }
            if(Objects.equals(qid.getNumber(), mid.getNumber()) &&
                    Objects.equals(qid.getExpiration(), mid.getExpiration())){
                score += 10;
            }
        }
        return score > 0 ? Optional.of(new Score(score, false)) : Optional.empty();
    }

    Map<String, StateIdentification> mapWithoutExpired(List<StateIdentification> list){
        return list.stream()
                .filter(id-> id.getExpiration() == null || !LocalDate.now().isAfter(id.getExpiration()))
                .collect(Collectors.toMap(StateIdentification::getIssuer, a->a));
    }


}
