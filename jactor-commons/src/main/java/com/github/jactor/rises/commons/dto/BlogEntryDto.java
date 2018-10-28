package com.github.jactor.rises.commons.dto;

import java.io.Serializable;

public class BlogEntryDto extends PersistentDto<Long> implements Serializable {
    private BlogDto blog;
    private String creatorName;
    private String entry;

    public BlogEntryDto() {
        // empty, use setters
    }

    BlogEntryDto(BlogEntryDto blogEntryDto) {
        super(blogEntryDto);
        blog = blogEntryDto.getBlog();
        creatorName = blogEntryDto.getCreatorName();
        entry = blogEntryDto.getEntry();
    }

    public BlogDto getBlog() {
        return blog;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getEntry() {
        return entry;
    }

    public void setBlog(BlogDto blog) {
        this.blog = blog;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public static BlogEntryDtoBuilder aBlogEntry() {
        return new BlogEntryDtoBuilder();
    }

    public static class BlogEntryDtoBuilder {
        private BlogDto blogDto;
        private String entry;
        private String creatorName;

        public BlogEntryDtoBuilder with(BlogDto blogDto) {
            this.blogDto = blogDto;
            return this;
        }

        public BlogEntryDtoBuilder with(BlogDto.BlogDtoBuilder blogDtoBuilder) {
            return with(blogDtoBuilder.build());
        }

       public BlogEntryDtoBuilder withEntry(String entry) {
            this.entry = entry;
            return this;
        }

        public BlogEntryDtoBuilder withCreatorName(String creatorName) {
            this.creatorName = creatorName;
            return this;
        }

        public BlogEntryDto build() {
            BlogEntryDto blogEntryDto = new BlogEntryDto();
            blogEntryDto.setBlog(blogDto);
            blogEntryDto.setCreatorName(creatorName);
            blogEntryDto.setEntry(entry);

            return blogEntryDto;
        }
    }
}
