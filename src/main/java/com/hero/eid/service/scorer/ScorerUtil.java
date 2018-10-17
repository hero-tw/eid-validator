package com.hero.eid.service.scorer;

import com.hero.eid.model.Name;

import static java.util.Optional.ofNullable;

public class ScorerUtil {
    public static boolean compareName(Name query, Name match) {
        if(query == null || match == null){
            return false;
        }
        boolean result = ofNullable(query.getGivenName())
                .filter(n->n.equals(match.getGivenName())).isPresent();
        result &= ofNullable(query.getSurname())
                .filter(n->n.equals(match.getSurname())).isPresent();

        result &= doesMatchIfPresentOrMatchIsNull(query.getPrefix(), match.getPrefix());

        result &= doesMatchIfPresentOrMatchIsNull(query.getSecondaryName(), match.getSecondaryName());

        result &= doesMatchIfPresentOrMatchIsNull(query.getNickname(), match.getNickname());

        result &= doesMatchIfPresentOrMatchIsNull(query.getSuffix(), match.getSuffix());

        return result;

    }

    public static boolean doesMatchIfPresentOrMatchIsNull(Object query, Object match){
        return ofNullable(query)
                .map(q-> match == null ||  q.equals(match))
                .orElse(true);
    }

    public static boolean anyNull(Object... values){
        for(Object o : values){
            if(o == null){
                return true;
            }
        }
        return false;
    }
}
