package com.github.jactor.rises.facade.domain.blog;

import com.github.jactor.rises.commons.datatype.Name;
import com.github.jactor.rises.commons.dto.BlogEntryDto;
import com.github.jactor.rises.facade.domain.PersistentDomain;

import java.util.Optional;

public class BlogEntryDomain extends PersistentDomain {

    private final BlogEntryDto blogEntryDto;

    BlogEntryDomain(BlogEntryDto blogEntryDto) {
        this.blogEntryDto = blogEntryDto;
    }

    BlogDomain getBlog() {
        return Optional.ofNullable(blogEntryDto.getBlog()).map(BlogDomain::new).orElse(null);
    }

    String getEntry() {
        return blogEntryDto.getEntry();
    }

    Name getCreatorName() {
        return Optional.ofNullable(blogEntryDto.getCreatorName()).map(Name::new).orElse(null);
    }

    @Override public BlogEntryDto getDto() {
        return blogEntryDto;
    }

    static BlogEntryBuilder aBlogEntry() {
        return new BlogEntryBuilder();
    }
}
