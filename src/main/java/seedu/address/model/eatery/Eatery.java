package seedu.address.model.eatery;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Eatery in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Eatery {

    // Identity fields
    private final Name name;
    private final boolean isOpen;
    // Data fields
    private final Address address;
    private final Category category;
    private Set<Review> reviews = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * Used when adding a eatery for the first time
     */
    public Eatery(Name name, Address address, Category category, Set<Tag> tags) {
        requireAllNonNull(name, address, category, tags);
        this.name = name;
        this.isOpen = true;
        this.address = address;
        this.category = category;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * Used for editing open or close
     */
    public Eatery(Name name, boolean isOpen, Address address, Category category, Set<Tag> tags) {
        requireAllNonNull(name, address, category);
        this.name = name;
        this.isOpen = isOpen;
        this.address = address;
        this.category = category;
        this.tags = tags;
    }

    public Name getName() {
        return name;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public Address getAddress() {
        return address;
    }

    public Category getCategory() {
        return category;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews.clear();
        for (Review r : reviews) {
            this.addReview(r);
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both eateries of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two eateries.
     */
    public boolean isSameEatery(Eatery otherEatery) {
        if (otherEatery == this) {
            return true;
        }

        return otherEatery != null
                && otherEatery.getName().equals(getName())
                && otherEatery.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both eateries have the same identity and data fields.
     * This defines a stronger notion of equality between two eateries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Eatery)) {
            return false;
        }

        Eatery otherEatery = (Eatery) other;
        return otherEatery.getName().equals(getName())
                && otherEatery.getIsOpen() == (getIsOpen())
                && otherEatery.getAddress().equals(getAddress())
                && otherEatery.getCategory().equals(getCategory())
                && otherEatery.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, isOpen, address, reviews, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Address: ")
                .append(getAddress())
                .append(" Category: ")
                .append(getCategory())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
