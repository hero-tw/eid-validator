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


    public String getId() {
        return id;
    }

    public Address id(String id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Address number(String number) {
        this.number = number;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Address unit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getAdditional() {
        return additional;
    }

    public Address additional(String additional) {
        this.additional = additional;
        return this;
    }

    public String getCityRegion() {
        return cityRegion;
    }

    public Address cityRegion(String cityRegion) {
        this.cityRegion = cityRegion;
        return this;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public Address stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Address countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Address startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Address endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

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
