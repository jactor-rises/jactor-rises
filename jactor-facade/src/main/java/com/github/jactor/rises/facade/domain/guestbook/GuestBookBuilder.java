package com.github.jactor.rises.facade.domain.guestbook;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;
import com.github.jactor.rises.commons.dto.GuestBookDto;
import com.github.jactor.rises.commons.dto.UserDto;
import com.github.jactor.rises.facade.domain.user.UserDomain;

import java.util.Optional;

public final class GuestBookBuilder extends AbstractBuilder<GuestBookDomain> {
    private final GuestBookDto guestBookDto = new GuestBookDto();

    GuestBookBuilder() {
        super(GuestBookBuilder::validateInstance);
    }

    public GuestBookBuilder withTitle(String title) {
        guestBookDto.setTitle(title);
        return this;
    }

    public GuestBookBuilder with(UserDto userDto) {
        guestBookDto.setUser(userDto);
        return this;
    }

    public GuestBookBuilder with(UserDomain userDomain) {
        with(userDomain.getDto());
        return this;
    }

    @Override protected GuestBookDomain buildBean() {
        return new GuestBookDomain(guestBookDto);
    }

    private static Optional<MissingFields> validateInstance(GuestBookDomain guestBookDomain, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenNoValue(GuestBookDomain.class.getSimpleName(), "title", guestBookDomain.getTitle());
        missingFields.addInvalidFieldWhenNoValue(GuestBookDomain.class.getSimpleName(), "user", guestBookDomain.getUser());

        return missingFields.presentWhenFieldsAreMissing();
    }

    public static GuestBookDomain build(GuestBookDto guestBook) {
        return new GuestBookDomain(guestBook);
    }
}
