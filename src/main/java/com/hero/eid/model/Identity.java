package com.hero.eid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="personal_identity")
public class Identity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Embedded
    private Name name;
    private LocalDate dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StateIdentification> stateIds = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public String getId() {
        return id;
    }

    public Identity id(String id) {
        this.id = id;
        return this;
    }

    public Name getName() {
        return name;
    }

    public Identity name(Name name) {
        this.name = name;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Identity dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public List<StateIdentification> getStateIds() {
        return stateIds;
    }

    public Identity stateIds(List<StateIdentification> stateIds) {
        this.stateIds = stateIds;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Identity addresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(id, identity.id) &&
                Objects.equals(name, identity.name) &&
                Objects.equals(dateOfBirth, identity.dateOfBirth) &&
                Objects.equals(stateIds, identity.stateIds) &&
                Objects.equals(addresses, identity.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, stateIds, addresses);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Identity.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name=" + name)
                .add("dateOfBirth=" + dateOfBirth)
                .add("stateIds=" + stateIds)
                .add("addresses=" + addresses)
                .toString();
    }
}
