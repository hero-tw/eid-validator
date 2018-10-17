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

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Getter
@ToString
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String number;
    private String street;
    private String unit;
    private String additional;
    private String cityRegion;
    private String stateProvince;
    private String countryCode;
    private String postalCode;
    private LocalDate startDate;
    private LocalDate endDate;

    public Address id(String id) {
        this.id = id;
        return this;
    }



    public Address number(String number) {
        this.number = number;
        return this;
    }



    public Address street(String street) {
        this.street = street;
        return this;
    }



    public Address unit(String unit) {
        this.unit = unit;
        return this;
    }



    public Address additional(String additional) {
        this.additional = additional;
        return this;
    }


    public Address cityRegion(String cityRegion) {
        this.cityRegion = cityRegion;
        return this;
    }


    public Address stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }


    public Address countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public Address postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }


    public Address startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }


    public Address endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) &&
                Objects.equals(getNumber(), address.getNumber()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getUnit(), address.getUnit()) &&
                Objects.equals(getAdditional(), address.getAdditional()) &&
                Objects.equals(getCityRegion(), address.getCityRegion()) &&
                Objects.equals(getStateProvince(), address.getStateProvince()) &&
                Objects.equals(getCountryCode(), address.getCountryCode()) &&
                Objects.equals(getPostalCode(), address.getPostalCode()) &&
                Objects.equals(getStartDate(), address.getStartDate()) &&
                Objects.equals(getEndDate(), address.getEndDate());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getStreet(), getUnit(), getAdditional(), getCityRegion(), getStateProvince(), getCountryCode(), getPostalCode(), getStartDate(), getEndDate());
    }
}
