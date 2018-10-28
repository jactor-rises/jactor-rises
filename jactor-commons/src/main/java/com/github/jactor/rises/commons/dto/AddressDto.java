package com.github.jactor.rises.commons.dto;

import java.io.Serializable;

public class AddressDto extends PersistentDto<Long> implements Serializable {
    private Integer zipCode;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String country;

    public AddressDto() {
        // empty, use setters
    }

    AddressDto(AddressDto addressDto) {
        super(addressDto);
        this.addressLine1 = addressDto.getAddressLine1();
        this.addressLine2 = addressDto.getAddressLine2();
        this.addressLine3 = addressDto.getAddressLine3();
        this.city = addressDto.getCity();
        this.country = addressDto.getCountry();
        this.zipCode = addressDto.getZipCode();
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public static AddressDtoBuilder anAddress() {
        return new AddressDtoBuilder();
    }

    public static class AddressDtoBuilder {
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String city;
        private String country;
        private int zipCode;

        public AddressDtoBuilder withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public AddressDtoBuilder withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public AddressDtoBuilder withAddressLine3(String addressLine3) {
            this.addressLine3 = addressLine3;
            return this;
        }

        public AddressDtoBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressDtoBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressDtoBuilder withZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressDto build() {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressLine1(addressLine1);
            addressDto.setAddressLine2(addressLine2);
            addressDto.setAddressLine3(addressLine3);
            addressDto.setCity(city);
            addressDto.setCountry(country);
            addressDto.setZipCode(zipCode);

            return addressDto;
        }
    }
}
