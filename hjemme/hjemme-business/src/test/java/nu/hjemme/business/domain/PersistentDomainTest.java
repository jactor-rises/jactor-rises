package nu.hjemme.business.domain;

import nu.hjemme.persistence.orm.domain.DefaultPersistentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@DisplayName("A PersistentDomain")
class PersistentDomainTest {

    @DisplayName("Should not initialize with an entity beeing null")
    @Test void skalFeileHvisEtDomeneInstansieresMedEntitetSomErNull() {
        assertThatNullPointerException().isThrownBy(TestPersistentDomain::new)
                .withMessage(PersistentDomain.THE_ENTITY_ON_THE_DOMAIN_CANNOT_BE_NULL);
    }

    private class TestPersistentDomain extends PersistentDomain<DefaultPersistentEntity, Long> {
        private TestPersistentDomain() {
            super(null);
        }
    }
}
