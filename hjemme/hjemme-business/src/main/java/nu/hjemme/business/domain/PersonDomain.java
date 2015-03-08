package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentDomain;
import nu.hjemme.business.domain.persistence.PersonEntity;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;
import nu.hjemme.client.domain.Person;

/** @author Tor Egil Jacobsen */
public class PersonDomain extends PersistentDomain<PersonEntity> implements Person {

    public PersonDomain(PersonEntity personEntity) {
        super(personEntity);
    }

    @Override
    public Name getFirstName() {
        return getEntity().getFirstName();
    }

    @Override
    public Name getLastName() {
        return getEntity().getLastName();
    }

    @Override
    public Address getAddress() {
        return new AddressDomain(getEntity().getAddress());
    }
}