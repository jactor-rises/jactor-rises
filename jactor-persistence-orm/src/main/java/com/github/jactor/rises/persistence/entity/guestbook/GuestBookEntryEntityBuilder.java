package com.github.jactor.rises.persistence.entity.guestbook;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;

import java.util.Optional;

public class GuestBookEntryEntityBuilder extends AbstractBuilder<GuestBookEntryEntity> {
    private GuestBookEntity guestBookEntity;
    private String creatorName;
    private String entry;

    GuestBookEntryEntityBuilder() {
        super(GuestBookEntryEntityBuilder::validate);
    }

    public GuestBookEntryEntityBuilder with(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = guestBookEntity;
        return this;
    }

    public GuestBookEntryEntityBuilder with(GuestBookEntityBuilder guestBookEntityBuilder) {
        return with(guestBookEntityBuilder.build());
    }

    public GuestBookEntryEntityBuilder withEntry(String entry) {
        this.entry = entry;
        return this;
    }

    public GuestBookEntryEntityBuilder withCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    @Override protected GuestBookEntryEntity buildBean() {
        GuestBookEntryEntity guestBookEntryEntity = new GuestBookEntryEntity();
        guestBookEntryEntity.create(entry);
        guestBookEntryEntity.setCreatorName(creatorName);
        guestBookEntryEntity.setGuestBook(guestBookEntity);

        return guestBookEntryEntity;
    }

    private static Optional<MissingFields> validate(GuestBookEntryEntity guestBookEntryEntity, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenNoValue(GuestBookEntryEntity.class.getSimpleName(), "entry", guestBookEntryEntity.getEntry());
        missingFields.addInvalidFieldWhenNoValue(GuestBookEntryEntity.class.getSimpleName(), "name", guestBookEntryEntity.getCreatorName());
        missingFields.addInvalidFieldWhenNoValue(GuestBookEntryEntity.class.getSimpleName(), "guestBook", guestBookEntryEntity.getGuestBook());

        return missingFields.presentWhenFieldsAreMissing();
    }
}
