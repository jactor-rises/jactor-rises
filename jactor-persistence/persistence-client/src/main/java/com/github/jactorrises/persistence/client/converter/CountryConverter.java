package com.github.jactorrises.persistence.client.converter;

import com.github.jactorrises.client.datatype.Country;

public class CountryConverter implements TypeConverter<Country, String> {

    @Override public Country convertTo(String country) {
        return country != null ? new Country(country) : null;
    }

    @Override public String convertFrom(Country country) {
        return country != null ? country.getCountryCode() : null;
    }
}
