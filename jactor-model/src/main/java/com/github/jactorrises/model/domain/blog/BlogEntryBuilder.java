package com.github.jactorrises.model.domain.blog;

import com.github.jactorrises.model.Builder;
import com.github.jactorrises.model.persistence.entity.blog.BlogEntity;
import com.github.jactorrises.model.persistence.entity.blog.BlogEntityBuilder;
import com.github.jactorrises.model.persistence.entity.blog.BlogEntryEntityBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.github.jactorrises.model.persistence.entity.blog.BlogEntryEntity.aBlogEntry;
import static java.util.Arrays.asList;

public final class BlogEntryBuilder extends Builder<BlogEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_BLOG = "The blog entry must belong to a blog";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry field cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private final BlogEntryEntityBuilder blogEntryEntityBuilder = aBlogEntry();

    BlogEntryBuilder() {
        super(asList(
                domain -> StringUtils.isNotBlank(domain.getEntry()) ? Optional.empty() : Optional.of(THE_ENTRY_CANNOT_BE_EMPTY),
                domain -> domain.getCreatorName() != null ? Optional.empty() : Optional.of(THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE),
                domain -> domain.getBlog() != null ? Optional.empty() : Optional.of(THE_ENTRY_MUST_BELONG_TO_A_BLOG)
        ));
    }

    BlogEntryBuilder withEntry(String entry, String creator) {
        blogEntryEntityBuilder
                .withEntry(entry)
                .withCreatorName(creator);

        return this;
    }

    public BlogEntryBuilder with(BlogEntity blogEntity) {
        blogEntryEntityBuilder.with(blogEntity);
        return this;
    }

    public BlogEntryBuilder with(BlogEntityBuilder blogEntityBuilder) {
        return with(blogEntityBuilder.build());
    }

    @Override protected BlogEntryDomain buildBean() {
        return new BlogEntryDomain(blogEntryEntityBuilder.build());
    }
}