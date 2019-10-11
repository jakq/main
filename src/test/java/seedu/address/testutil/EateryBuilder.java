package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.eatery.Address;
import seedu.address.model.eatery.Eatery;
import seedu.address.model.eatery.Email;
import seedu.address.model.eatery.Name;
import seedu.address.model.eatery.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Eatery objects.
 */
public class EateryBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    public EateryBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EateryBuilder with the data of {@code eateryToCopy}.
     */
    public EateryBuilder(Eatery eateryToCopy) {
        name = eateryToCopy.getName();
        phone = eateryToCopy.getPhone();
        email = eateryToCopy.getEmail();
        address = eateryToCopy.getAddress();
        tags = new HashSet<>(eateryToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Eatery} that we are building.
     */
    public EateryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Eatery} that we are building.
     */
    public EateryBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Eatery build() {
        return new Eatery(name, phone, email, address, tags);
    }

}
