package pl.coztymit.exchange.identity.infrastructure;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coztymit.exchange.identity.domain.Identity;
import pl.coztymit.exchange.identity.domain.IdentityData;
import pl.coztymit.exchange.identity.domain.IdentityFactory;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HibernateIdentityRepositoryTest {
    @Autowired
    private HibarnateIdentityRepository repository;
    private IdentityFactory identityFactory;
    private Identity identity;
    @BeforeEach
    public void setUp() {
        identityFactory = new IdentityFactory();
        String pesel = "55072993619";
        String firstName = "John";
        String surname = "Doe";
        String email = "kontakt@coztymit.pl";
        identity = identityFactory.create(pesel, firstName, surname, email);
    }

    @Test
    public void testSaveAndFindIdentityIds() {

        repository.save(identity);

        List<IdentityId> identities = repository.findIdentityIds();

        // Sprawdzanie czy nowa tożsamość jest w liście
        assertTrue(identities.contains(identity.getIdentityId()));

    }

    @Test
    public void testFindIdentityById() {
        // given
        repository.save(identity);
        IdentityId identityId = identity.getIdentityId();

        // when
        Optional<IdentityData> identityDataOptional = repository.findByIdentityId(identityId);

        // then
        assertTrue(identityDataOptional.isPresent());
        assertTrue(identityId.equals(identityDataOptional.get().getIdentityId()));
    }

}