package com.github.jactor.rises.facade.domain.blog;

import com.github.jactor.rises.commons.dto.BlogDto;
import com.github.jactor.rises.facade.domain.PersistentDomain;
import com.github.jactor.rises.facade.domain.user.UserDomain;

import java.time.LocalDate;
import java.util.Optional;

public class BlogDomain extends PersistentDomain {

    private final BlogDto blogDto;

    BlogDomain(BlogDto blogDto) {
        this.blogDto = blogDto;
    }

    String getTitle() {
        return blogDto.getTitle();
    }

    public UserDomain getUser() {
        return Optional.ofNullable(blogDto.getUser()).map(UserDomain::new).orElse(null);
    }

    LocalDate getCreated() {
        return getDto().getCreated();
    }

    @Override public BlogDto getDto() {
        return blogDto;
    }

    static BlogBuilder aBlog() {
        return new BlogBuilder();
    }
}
