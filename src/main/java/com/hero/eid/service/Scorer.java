package com.hero.eid.service;

import java.util.Optional;

import com.hero.eid.model.Identity;

public interface Scorer {

    Optional<Score> computeScore(Identity query, Identity match);

}
