package com.hero.eid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="personal_identity")
@SuppressWarnings({"unused", "WeakerAccess"})
@Getter
@NoArgsConstructor
@ToString
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

    public Identity id(String id) {
        this.id = id;
        return this;
    }

    public Identity name(Name name) {
        this.name = name;
        return this;
    }

    public Identity dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Identity stateIds(List<StateIdentification> stateIds) {
        this.stateIds = stateIds;
        return this;
    }

    public Identity addresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identity)) return false;
        Identity identity = (Identity) o;
        return Objects.equals(getId(), identity.getId()) &&
                Objects.equals(getName(), identity.getName()) &&
                Objects.equals(getDateOfBirth(), identity.getDateOfBirth()) &&
                Objects.equals(new ArrayList<>(getStateIds()), new ArrayList<>(identity.getStateIds())) &&
                Objects.equals(new ArrayList<>(getAddresses()), new ArrayList<>(identity.getAddresses()));
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDateOfBirth(), getStateIds(), getAddresses());
    }
}
