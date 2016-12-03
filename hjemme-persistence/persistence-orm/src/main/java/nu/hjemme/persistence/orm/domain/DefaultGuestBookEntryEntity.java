package nu.hjemme.persistence.orm.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.GuestBookEntry;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.GuestBookEntryEntity;
import nu.hjemme.persistence.orm.meta.GuestBookEntryMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = GuestBookEntryMetadata.GUEST_BOOK_ENTRY_TABLE)
public class DefaultGuestBookEntryEntity extends DefaultPersistentEntity implements GuestBookEntryEntity {
    @ManyToOne() @JoinColumn(name = GuestBookEntryMetadata.GUEST_BOOK_ID) private DefaultGuestBookEntity guestBookEntity;

    @Embedded @AttributeOverrides({
            @AttributeOverride(name = "createdTime", column = @Column(name = GuestBookEntryMetadata.CREATED_TIME)),
            @AttributeOverride(name = "creatorName", column = @Column(name = GuestBookEntryMetadata.GUEST_NAME)),
            @AttributeOverride(name = "entry", column = @Column(name = GuestBookEntryMetadata.ENTRY))
    }) private DefaultPersistentEntry persistentEntry;

    public DefaultGuestBookEntryEntity() {
        persistentEntry = new DefaultPersistentEntry();
    }

    public DefaultGuestBookEntryEntity(GuestBookEntry guestBookEntry) {
        guestBookEntity = castOrInitializeCopyWith(guestBookEntry.getGuestBook(), DefaultGuestBookEntity.class);
        persistentEntry = new DefaultPersistentEntry((Date) convertFrom(guestBookEntry.getCreatedTime(), LocalDateTime.class));
        persistentEntry.setCreatorName(convertFrom(guestBookEntry.getCreatorName(), Name.class));
        persistentEntry.setEntry(guestBookEntry.getEntry());
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getId(), ((DefaultGuestBookEntryEntity) o).getId()) &&
                Objects.equals(persistentEntry, ((DefaultGuestBookEntryEntity) o).persistentEntry) &&
                Objects.equals(guestBookEntity, ((DefaultGuestBookEntryEntity) o).guestBookEntity);
    }

    @Override public int hashCode() {
        return hash(guestBookEntity, persistentEntry);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(guestBookEntity).append(persistentEntry).toString();
    }

    @Override public GuestBookEntity getGuestBook() {
        return guestBookEntity;
    }

    @Override public LocalDateTime getCreatedTime() {
        return persistentEntry.getCreatedTime();
    }

    @Override public String getEntry() {
        return persistentEntry.getEntry();
    }

    @Override public Name getCreatorName() {
        return persistentEntry.getCreatorName();
    }

    @Override public void setGuestBook(GuestBookEntity guestBookEntity) {
        this.guestBookEntity = castOrInitializeCopyWith(guestBookEntity, DefaultGuestBookEntity.class);
    }

    @Override public void setEntry(String entry) {
        persistentEntry.setEntry(entry);
    }

    @Override public void setCreatorName(String creatorName) {
        persistentEntry.setCreatorName(creatorName);
    }
}