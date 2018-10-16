package com.hero.eid.service;

import com.hero.eid.model.Identity;

public class QueryResult {

    private int score;
    private Identity identity;

    public QueryResult(int score, Identity identity){
        this.score = score;
        this.identity = identity;
    }

    public int getScore() {
        return score;
    }

    public QueryResult score(int score) {
        this.score = score;
        return this;
    }

    public Identity getIdentity() {
        return identity;
    }

    public QueryResult identity(Identity identity) {
        this.identity = identity;
        return this;
    }
}
