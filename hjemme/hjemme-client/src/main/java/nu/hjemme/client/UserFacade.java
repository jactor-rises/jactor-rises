package nu.hjemme.client;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

/** @author Tor Egil Jacobsen */
public interface UserFacade {

    User retrieveBy(UserName userName);
}
