package com.hero.eid.service.scorer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.hero.eid.model.Identity;
import com.hero.eid.model.StateIdentification;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

import static com.hero.eid.service.scorer.ScorerUtil.compareName;

public class NameBirthSSNMatch implements Scorer {
    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {
        return compareName(query.getName(), match.getName()) &&
                compareSSN(query.getStateIds(), match.getStateIds()) &&
                Objects.nonNull(query.getDateOfBirth()) && Objects.nonNull(match.getDateOfBirth()) &&
                Objects.equals(query.getDateOfBirth(), match.getDateOfBirth())
                ?
         Optional.of(new Score(50, true)) : Optional.empty();
    }



    public boolean compareSSN(List<StateIdentification> query, List<StateIdentification> match){
        Optional<StateIdentification> qSSN = selectSSN(query);
        Optional<StateIdentification> mSSN = selectSSN(match);
        if(!(qSSN.isPresent() && mSSN.isPresent())){
            return false;
        }
        return qSSN.get().getNumber().equals(mSSN.get().getNumber());
    }

    private Optional<StateIdentification> selectSSN(List<StateIdentification> list){
        return list.stream()
                .filter(id-> id.getIssuer().equals("SSA"))
                .findFirst();
    }


}
