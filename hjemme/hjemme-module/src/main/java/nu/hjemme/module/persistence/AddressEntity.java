package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.Address;
import nu.hjemme.module.persistence.base.PersistentBean;
import nu.hjemme.module.persistence.mutable.MutableAddress;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;

import static nu.hjemme.module.persistence.meta.AddressMetadata.ADDRESS_ID;
import static nu.hjemme.module.persistence.meta.AddressMetadata.ADDRESS_LINE_1;
import static nu.hjemme.module.persistence.meta.AddressMetadata.ADDRESS_LINE_2;
import static nu.hjemme.module.persistence.meta.AddressMetadata.ADDRESS_LINE_3;
import static nu.hjemme.module.persistence.meta.AddressMetadata.CITY;
import static nu.hjemme.module.persistence.meta.AddressMetadata.COUNTRY;
import static nu.hjemme.module.persistence.meta.AddressMetadata.ZIP_CODE;

/** @author Tor Egil Jacobsen */
public class AddressEntity extends PersistentBean implements MutableAddress {

    @Id
    @Column(name = ADDRESS_ID)
    void setAddressId(Long addressId) {
        setId(addressId);
    }

    @Column(name = COUNTRY)
    private Country country;

    @Column(name = ZIP_CODE)
    private Integer zipCode;

    @Column(name = ADDRESS_LINE_1)
    private String addressLine1;

    @Column(name = ADDRESS_LINE_2)
    private String addressLine2;

    @Column(name = ADDRESS_LINE_3)
    private String addressLine3;

    @Column(name = CITY)
    private String city;

    public AddressEntity() {
    }

    /** @param address is used to create an entity */
    public AddressEntity(Address address) {
        addressLine1 = address.getAddressLine1();
        addressLine2 = address.getAddressLine2();
        addressLine3 = address.getAddressLine3();
        city = address.getCity();
        country = address.getCountry();
        zipCode = address.getZipCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressEntity that = (AddressEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getAddressLine1(), that.getAddressLine1())
                .append(getAddressLine2(), that.getAddressLine2())
                .append(getAddressLine3(), that.getAddressLine3())
                .append(getCity(), that.getCity())
                .append(getCountry(), that.getCountry())
                .append(getZipCode(), that.getZipCode())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getAddressLine1())
                .append(getAddressLine2())
                .append(getAddressLine3())
                .append(getCity())
                .append(getCountry())
                .append(getZipCode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getAddressLine1())
                .append(getAddressLine2())
                .append(getAddressLine3())
                .append(getCity())
                .append(getCountry())
                .append(getZipCode())
                .toString();
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public Integer getZipCode() {
        return zipCode;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public String getAddressLine3() {
        return addressLine3;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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

    public void setCity(String city) {
        this.city = city;
    }
}