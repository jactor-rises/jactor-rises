package com.gitlab.jactor.rises.facade.domain.blog;

import com.gitlab.jactor.rises.facade.domain.Blog;
import com.gitlab.jactor.rises.commons.dto.BlogDto;
import com.gitlab.jactor.rises.facade.domain.PersistentDomain;
import com.gitlab.jactor.rises.facade.domain.user.UserDomain;

import java.time.LocalDate;
import java.util.Optional;

public class BlogDomain extends PersistentDomain implements Blog {

    private final BlogDto blogDto;

    BlogDomain(BlogDto blogDto) {
        this.blogDto = blogDto;
    }

    @Override public String getTitle() {
        return blogDto.getTitle();
    }

    @Override public UserDomain getUser() {
        return Optional.ofNullable(blogDto.getUser()).map(UserDomain::new).orElse(null);
    }

    @Override public LocalDate getCreated() {
        return getDto().getCreated();
    }

    @Override public BlogDto getDto() {
        return blogDto;
    }

    static BlogBuilder aBlog() {
        return new BlogBuilder();
    }
}
