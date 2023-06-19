package pl.coztymit.exchange.identity.application;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.identity.domain.*;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityApplicationService {
    private IdentityFactory identityFactory = new IdentityFactory();
    @Autowired
    private IdentityRepository identityRepository;

    @Transactional
    public IdentityId createIdentity(String pesel, String firstName, String surname, String email) {
        Identity identity = identityFactory.create(pesel, firstName, surname, email);
        identityRepository.save(identity);
        return identity.getIdentityId();

    }

    public List<IdentityId> getAllIdentityIds() {
        return identityRepository.findIdentityIds();
    }

    public IdentityResponse getIdentity(IdentityId identityId) {
        Optional<IdentityData> optionalIdentity = identityRepository.findByIdentityId(identityId);
        IdentityData identityData = optionalIdentity.orElseThrow(() -> new RuntimeException("Identity not found"));
        return new IdentityResponse(identityData.getIdentityId(), identityData.getFirstName(), identityData.getSurname(), identityData.getPesel(), identityData.getEmail());
    }
}
