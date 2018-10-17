package com.hero.eid.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@SuppressWarnings({"unused", "WeakerAccess"})
@ToString
@Getter
@NoArgsConstructor
public class StateIdentification {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String issuer;
    private String number;
    private LocalDate expiration;

    public String getId() {
        return id;
    }

    public StateIdentification id(String id) {
        this.id = id;
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public StateIdentification issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public StateIdentification number(String number) {
        this.number = number;
        return this;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public StateIdentification expiration(LocalDate expiration) {
        this.expiration = expiration;
        return this;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateIdentification)) return false;
        StateIdentification that = (StateIdentification) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getIssuer(), that.getIssuer()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getExpiration(), that.getExpiration());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIssuer(), getNumber(), getExpiration());
    }
}
