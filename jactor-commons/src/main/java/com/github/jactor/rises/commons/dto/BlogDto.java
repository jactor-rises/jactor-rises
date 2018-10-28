package com.github.jactor.rises.commons.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class BlogDto extends PersistentDto<Long> implements Serializable {
    private LocalDate created;
    private String title;
    private UserDto user;

    public BlogDto() {
        // empty, if usage of setters
    }

    public BlogDto(BlogDto blogDto) {
        super(blogDto);
        created = blogDto.getCreated();
        title = blogDto.getTitle();
        user = blogDto.getUser();
    }

    public LocalDate getCreated() {
        return created;
    }

    public String getTitle() {
        return title;
    }

    public UserDto getUser() {
        return user;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public static BlogDtoBuilder aBlog() {
        return new BlogDtoBuilder(LocalDate.now());
    }

    public static class BlogDtoBuilder {
        private final LocalDate created;
        private String title;
        private UserDto userDto;

        BlogDtoBuilder(LocalDate created) {
            this.created = created;
        }

        public BlogDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BlogDtoBuilder with(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public BlogDto build() {
            BlogDto blogDto = new BlogDto();
            blogDto.setCreated(created);
            blogDto.setTitle(title);
            blogDto.setUser(userDto);

            return blogDto;
        }
    }
}
