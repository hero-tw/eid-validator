package com.hero.eid.service.scorer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import com.hero.eid.model.Address;
import com.hero.eid.model.Identity;
import com.hero.eid.service.Score;
import com.hero.eid.service.Scorer;

import static com.hero.eid.service.scorer.ScorerUtil.doesMatchIfPresentOrMatchIsNull;

public class AddressMatch implements Scorer {
    @Override
    public Optional<Score> computeScore(Identity query, Identity match) {
        int score = 0;
        ArrayList<Address> matchAddresses = new ArrayList<>(match.getAddresses());
        ArrayList<Address> matched = new ArrayList<>();
        for (Address queryAddress : query.getAddresses()) {
            for (Address matchAddress : matchAddresses) {
                if (compareAddresses(queryAddress, matchAddress)) {
                    matched.add(matchAddress);
                }
            }
            matchAddresses.removeAll(matched);
            score += matched.size() * 5;
            matched.clear();
        }
        return score > 0 ? Optional.of(new Score(score, false)) : Optional.empty();
    }


    public boolean compareAddresses(Address query, Address match) {

        return
                Objects.equals(query.getNumber(), match.getNumber()) &&
                        Objects.equals(query.getStreet(), match.getStreet()) &&
                        doesMatchIfPresentOrMatchIsNull(query.getUnit(), match.getUnit()) &&
                        Objects.equals(query.getStateProvince(), match.getStateProvince()) &&
                        Objects.equals(query.getCityRegion(), match.getCityRegion()) &&
                        Objects.equals(query.getPostalCode(), match.getPostalCode()) &&
                        doesMatchIfPresentOrMatchIsNull(query.getCountryCode(), match.getCountryCode()) &&
                        doesMatchIfPresentOrMatchIsNull(query.getStartDate(), match.getStartDate()) &&
                        doesMatchIfPresentOrMatchIsNull(query.getEndDate(), match.getEndDate());
    }
}
