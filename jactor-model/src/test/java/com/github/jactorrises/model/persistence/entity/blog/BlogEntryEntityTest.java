package com.github.jactorrises.model.persistence.entity.blog;

import com.github.jactorrises.model.persistence.entity.NowAsPureDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jactorrises.model.persistence.entity.blog.BlogEntryEntity.aBlogEntry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A BlogEntryEntity")
class BlogEntryEntityTest {
    @BeforeEach void useNowAsPureDate() {
        NowAsPureDate.set();
    }

    @DisplayName("should have an implementation of the hash code method")
    @Test void willHaveCorrectImplementedHashCode() {
        BlogEntryEntity base = aBlogEntry()
                .with(new BlogEntity())
                .withCreatorName("some creator")
                .withEntry("some entry")
                .build();

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = aBlogEntry()
                .with(new BlogEntity())
                .withCreatorName("some other creator")
                .withEntry("some other entry")
                .build();

        assertAll(
                () -> assertThat(base.hashCode()).as("base.hashCode() is equal to equal.hashCode()").isEqualTo(equal.hashCode()),
                () -> assertThat(base.hashCode()).as("base.hashCode() is not equal to notEqual.hashCode()").isNotEqualTo(notEqual.hashCode()),
                () -> assertThat(base.hashCode()).as("base.hashCode() is a number with different value").isNotEqualTo(0),
                () -> assertThat(base).as("base is not same instance as equal").isNotSameAs(equal)
        );
    }

    @DisplayName("should have an implementation of the equals method")
    @Test void willHaveCorrectImplementedEquals() {
        BlogEntryEntity base = aBlogEntry()
                .with(new BlogEntity())
                .withCreatorName("some creator")
                .withEntry("some entry")
                .build();

        BlogEntryEntity equal = new BlogEntryEntity(base);

        BlogEntryEntity notEqual = aBlogEntry()
                .with(new BlogEntity())
                .withCreatorName("some other creator")
                .withEntry("some other entry")
                .build();

        assertAll(
                () -> assertThat(base).as("base is not equal to null").isNotEqualTo(null),
                () -> assertThat(base).as("base is equal to base").isEqualTo(base),
                () -> assertThat(base).as("base is equal to equal").isEqualTo(equal),
                () -> assertThat(base).as("base is not equal to notEqual").isNotEqualTo(notEqual),
                () -> assertThat(base).as("base is not same instance as equal").isNotSameAs(equal)
        );
    }

    @AfterEach void removeNowAsPureDate() {
        NowAsPureDate.remove();
    }
}
