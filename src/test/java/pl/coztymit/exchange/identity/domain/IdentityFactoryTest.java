package pl.coztymit.exchange.identity.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coztymit.exchange.kernel.IdentityId;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFactoryTest {

    private IdentityFactory identityFactory;

    @BeforeEach
    public void setUp() {
        identityFactory = new IdentityFactory();
    }

    @Test
    public void shouldCreateIdentityWithGivenValues() {
        // given
        String pesel = "55072993619";
        String firstName = "John";
        String surname = "Doe";
        String email = "test@coztymit.pl";

        // when
        Identity identity = identityFactory.create(pesel, firstName, surname, email);

        // then
        IdentityId createdIdentityId = (IdentityId) ReflectionTestUtils.getField(identity, "identityId");
        PESEL createdPesel = (PESEL) ReflectionTestUtils.getField(identity, "pesel");
        FirstName createdName = (FirstName) ReflectionTestUtils.getField(identity, "firstName");
        Surname createdSurname = (Surname) ReflectionTestUtils.getField(identity, "surname");
        Email createdEmail = (Email) ReflectionTestUtils.getField(identity, "email");

        assertNotNull(identity);
        assertNotNull(createdIdentityId);
        assertTrue(createdPesel.equals(pesel));
        assertTrue(createdName.equals(firstName));
        assertTrue(createdSurname.equals(surname));
        assertTrue(createdEmail.equals(email));

    }
}