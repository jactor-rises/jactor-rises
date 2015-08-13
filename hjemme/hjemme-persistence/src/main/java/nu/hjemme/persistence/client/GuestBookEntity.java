package nu.hjemme.persistence.client;

import nu.hjemme.client.domain.GuestBook;

/**
 * @author Tor Egil Jacobsen
 */
public interface GuestBookEntity extends GuestBook {
    void setTitle(String title);

    void setUser(UserEntity userEntity);
}
