package pl.coztymit.exchange.identity.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.identity.domain.exception.IdentityAlreadyExistsException;
import pl.coztymit.exchange.kernel.IdentityId;

@Component
public class IdentityFactory {

    private IdentityRepository identityRepository;

    @Autowired
    public IdentityFactory (IdentityRepository identityRepository){
        this.identityRepository = identityRepository;
    }
    public Identity create(PESEL pesel, FirstName firstName, Surname surname, Email email) throws IdentityAlreadyExistsException {
        if (identityRepository.existsByPesel(pesel)){
            throw new IdentityAlreadyExistsException(pesel);
        }
        return new Identity(IdentityId.generateNewId(), pesel, firstName, email, surname);
    }
}
