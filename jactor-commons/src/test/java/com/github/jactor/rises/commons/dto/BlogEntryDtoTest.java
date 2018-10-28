package com.github.jactor.rises.commons.dto;

import com.github.jactor.rises.commons.dto.BlogDto;
import com.github.jactor.rises.commons.dto.BlogEntryDto;
import com.github.jactor.rises.commons.dto.PersistentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A BlogEntryDto")
class BlogEntryDtoTest {

    @DisplayName("should have a copy constructor")
    @Test
    void shouldHaveCopyConstructor() {
        BlogEntryDto blogEntryDto = new BlogEntryDto();
        blogEntryDto.setBlog(new BlogDto());
        blogEntryDto.setCreationTime(LocalDateTime.now());
        blogEntryDto.setCreatorName("someone");
        blogEntryDto.setEntry("entry");

        BlogEntryDto copied = new BlogEntryDto(blogEntryDto);

        assertAll(
                () -> assertThat(copied.getBlog()).as("blog").isEqualTo(blogEntryDto.getBlog()),
                () -> assertThat(copied.getCreationTime()).as("creation time").isEqualTo(blogEntryDto.getCreationTime()),
                () -> assertThat(copied.getCreatorName()).as("creator name").isEqualTo(blogEntryDto.getCreatorName()),
                () -> assertThat(copied.getEntry()).as("entry").isEqualTo(blogEntryDto.getEntry())
        );
    }

    @DisplayName("should give values to PersistentDto")
    @Test
    void shouldGiveValuesToPersistentDto() {
        BlogEntryDto persistentDto = new BlogEntryDto();
        persistentDto.setCreatedBy("jactor");
        persistentDto.setCreationTime(LocalDateTime.now());
        persistentDto.setId(1L);
        persistentDto.setUpdatedBy("tip");
        persistentDto.setUpdatedTime(LocalDateTime.now());

        PersistentDto copied = new BlogEntryDto(persistentDto);

        assertAll(
                () -> assertThat(copied.getCreatedBy()).as("created by").isEqualTo(persistentDto.getCreatedBy()),
                () -> assertThat(copied.getCreationTime()).as("creation time").isEqualTo(persistentDto.getCreationTime()),
                () -> assertThat(copied.getId()).as("id").isEqualTo(persistentDto.getId()),
                () -> assertThat(copied.getUpdatedBy()).as("updated by").isEqualTo(persistentDto.getUpdatedBy()),
                () -> assertThat(copied.getUpdatedTime()).as("updated time").isEqualTo(persistentDto.getUpdatedTime())
        );
    }
}
