package com.gitlab.jactor.rises.facade.domain.guestbook;

import com.gitlab.jactor.rises.facade.domain.GuestBook;
import com.gitlab.jactor.rises.facade.domain.GuestBookEntry;
import com.gitlab.jactor.rises.commons.dto.GuestBookDto;
import com.gitlab.jactor.rises.facade.domain.PersistentDomain;
import com.gitlab.jactor.rises.facade.domain.user.UserDomain;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GuestBookDomain extends PersistentDomain implements GuestBook {

    private final GuestBookDto guestBookDto;

    public GuestBookDomain(GuestBookDto guestBookDto) {
        this.guestBookDto = guestBookDto;
    }

    @Override public String getTitle() {
        return guestBookDto.getTitle();
    }

    @Override public UserDomain getUser() {
        return Optional.ofNullable(guestBookDto.getUser()).map(UserDomain::new).orElse(null);
    }

    @Override public Set<GuestBookEntry> getEntries() {
        return guestBookDto.getEntries().stream().map(GuestBookEntryDomain::new).collect(Collectors.toSet());
    }

    @Override public GuestBookDto getDto() {
        return guestBookDto;
    }

    public static GuestBookBuilder aGuestBook() {
        return new GuestBookBuilder();
    }
}
