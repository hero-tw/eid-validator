package com.hero.eid.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(number, address.number) &&
                Objects.equals(street, address.street) &&
                Objects.equals(unit, address.unit) &&
                Objects.equals(additional, address.additional) &&
                Objects.equals(cityRegion, address.cityRegion) &&
                Objects.equals(stateProvince, address.stateProvince) &&
                Objects.equals(countryCode, address.countryCode) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(startDate, address.startDate) &&
                Objects.equals(endDate, address.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, street, unit, additional, cityRegion, stateProvince, countryCode, postalCode, startDate, endDate);
    }
}
