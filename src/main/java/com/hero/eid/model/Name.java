package com.hero.eid.model;

import java.util.Objects;
import javax.persistence.Embeddable;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
@ToString
@NoArgsConstructor
public class Name {
    private String givenName;
    private String secondaryName;
    private String nickname;
    private String surname;
    private String prefix;
    private String suffix;

    public Name givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public Name secondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
        return this;
    }

    public Name nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Name surname(String surname) {
        this.surname = surname;
        return this;
    }

    public Name prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Name suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(getGivenName(), name.getGivenName()) &&
                Objects.equals(getSecondaryName(), name.getSecondaryName()) &&
                Objects.equals(getNickname(), name.getNickname()) &&
                Objects.equals(getSurname(), name.getSurname()) &&
                Objects.equals(getPrefix(), name.getPrefix()) &&
                Objects.equals(getSuffix(), name.getSuffix());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getGivenName(), getSecondaryName(), getNickname(), getSurname(), getPrefix(), getSuffix());
    }
}