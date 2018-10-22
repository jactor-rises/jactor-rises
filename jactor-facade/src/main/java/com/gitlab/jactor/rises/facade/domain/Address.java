package com.gitlab.jactor.rises.facade.domain;

import com.gitlab.jactor.rises.commons.datatype.Country;

public interface Address extends Persistent {

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    Country getCountry();

    Integer getZipCode();
}
