package com.hero.eid.model;

import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Embeddable;

@Embeddable
public class Name {
    private String givenName;
    private String secondaryName;
    private String nickname;
    private String surname;
    private String prefix;
    private String suffix;

    public Name() {
    }

    public String getGivenName() {
        return givenName;
    }

    public Name givenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public Name secondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Name nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Name surname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Name prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public Name suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Name.class.getSimpleName() + "[", "]")
                .add("givenName='" + givenName + "'")
                .add("secondaryName='" + secondaryName + "'")
                .add("nickname='" + nickname + "'")
                .add("surname='" + surname + "'")
                .add("prefix='" + prefix + "'")
                .add("suffix='" + suffix + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(givenName, name.givenName) &&
                Objects.equals(secondaryName, name.secondaryName) &&
                Objects.equals(nickname, name.nickname) &&
                Objects.equals(surname, name.surname) &&
                Objects.equals(prefix, name.prefix) &&
                Objects.equals(suffix, name.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenName, secondaryName, nickname, surname, prefix, suffix);
    }
}
