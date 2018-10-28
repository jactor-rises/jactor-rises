package com.github.jactor.rises.commons.dto;

import java.io.Serializable;

public class GuestBookEntryDto extends PersistentDto<Long> implements Serializable {
    private GuestBookDto guestBook;
    private String creatorName;
    private String entry;

    public GuestBookEntryDto() {
        // empty, use setters
    }

    GuestBookEntryDto(GuestBookEntryDto guestBookEntryDto) {
        super(guestBookEntryDto);
        guestBook = guestBookEntryDto.getGuestBook();
        creatorName = guestBookEntryDto.getCreatorName();
        entry = guestBookEntryDto.getEntry();
    }

    public GuestBookDto getGuestBook() {
        return guestBook;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getEntry() {
        return entry;
    }

    public void setCreatorName(String creatorNmae) {
        this.creatorName = creatorNmae;
    }

    public void setGuestBook(GuestBookDto guestBook) {
        this.guestBook = guestBook;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
