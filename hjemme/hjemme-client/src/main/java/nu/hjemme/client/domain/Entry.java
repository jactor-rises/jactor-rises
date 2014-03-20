package nu.hjemme.client.domain;

import nu.hjemme.client.datatype.Name;
import org.joda.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public interface Entry {

    /** @return creation time of an entry */
    LocalDateTime getCreationTime();

    /** @return the actual entry */
    String getEntry();

    /** @return who/what created the entry */
    Name getCreatorName();

    /** @return the {@link Person} which is the originator of the entry (if provided) */
    Person getCreator();
}