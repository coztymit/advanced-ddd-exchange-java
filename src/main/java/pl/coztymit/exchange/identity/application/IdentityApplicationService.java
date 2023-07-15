package pl.coztymit.exchange.identity.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.identity.domain.*;
import pl.coztymit.exchange.identity.domain.exception.IdentityAlreadyExistsException;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityApplicationService {
    private Log LOG = LogFactory.getLog(IdentityApplicationService.class);

    @Autowired
    private IdentityFactory identityFactory;

    @Autowired
    private IdentityRepository identityRepository;

    @Transactional
    public CreateIdentityStatus createIdentity(CreateIdentityCommand command) {
        try {
            Identity identity = identityFactory.create(
                    new PESEL(command.pesel()),
                    new FirstName(command.firstName()),
                    new Surname(command.surname()),
                    new Email(command.email()));
            identityRepository.save(identity);
            return CreateIdentityStatus.prepareSuccessStatus(identity.getIdentityId());
        } catch (IdentityAlreadyExistsException e) {
            LOG.error(e);
            return CreateIdentityStatus.prepareExistsStatus();
        }
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
