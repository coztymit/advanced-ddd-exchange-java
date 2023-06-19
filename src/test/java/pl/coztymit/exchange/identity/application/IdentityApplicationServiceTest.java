package pl.coztymit.exchange.identity.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.coztymit.exchange.identity.domain.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
class IdentityApplicationServiceTest {

    @InjectMocks
    private IdentityApplicationService identityApplicationService;

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private IdentityFactory identityFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAndSaveIdentity() {
        // given
        String pesel = "55072993619";
        String firstName = "John";
        String surname = "Doe";
        String email = "test@coztymit.pl";
        Identity identity = new IdentityFactory().create(pesel, firstName, surname, email);
        Mockito.when(identityFactory.create(pesel, firstName, surname, email)).thenReturn(identity);

        // when
        identityApplicationService.createIdentity(pesel, firstName, surname, email);

        // then
        verify(identityFactory, times(1)).create(pesel, firstName, surname, email);
        verify(identityRepository, times(1)).save(identity);
    }
}