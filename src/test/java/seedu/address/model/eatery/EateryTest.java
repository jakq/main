package seedu.address.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEateries.ALICE;
import static seedu.address.testutil.TypicalEateries.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EateryBuilder;

public class EateryTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Eatery eatery = new EateryBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> eatery.getTags().remove(0));
    }

    @Test
    public void isSameEatery() {
        // same object -> returns true
        assertTrue(ALICE.isSameEatery(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEatery(null));

        // different phone and email -> returns false
        Eatery editedAlice = new EateryBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameEatery(editedAlice));

        // different name -> returns false
        editedAlice = new EateryBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameEatery(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new EateryBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEatery(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new EateryBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEatery(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new EateryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEatery(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Eatery aliceCopy = new EateryBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different eatery -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Eatery editedAlice = new EateryBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EateryBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EateryBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EateryBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EateryBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
