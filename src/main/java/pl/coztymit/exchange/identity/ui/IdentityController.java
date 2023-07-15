package pl.coztymit.exchange.identity.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.identity.application.CreateIdentityCommand;
import pl.coztymit.exchange.identity.application.CreateIdentityStatus;
import pl.coztymit.exchange.identity.application.IdentityApplicationService;
import pl.coztymit.exchange.identity.application.IdentityResponse;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/identity")
public class IdentityController {

    private final IdentityApplicationService identityApplicationService;

    @Autowired
    public IdentityController(IdentityApplicationService identityApplicationService) {
        this.identityApplicationService = identityApplicationService;
    }

    //curl -X POST -H "Content-Type: application/json" -d '{"pesel": "90090503657", "firstName": "Jan", "surname": "Kowalski", "email": "kontakt@coztymit.pl"}' http://localhost:8080/identity
    @PostMapping
    public CreateIdentityStatus createIdentity(@RequestBody IdentityRequest request) {
        CreateIdentityCommand createIdentityCommand = new CreateIdentityCommand(request.pesel(), request.firstName(), request.surname(), request.email());
        return identityApplicationService.createIdentity(createIdentityCommand);
    }

    //curl -X GET http://localhost:8080/identity/all
    @GetMapping("/all")
    public List<IdentityId> getAllIdentityIds() {
        return identityApplicationService.getAllIdentityIds();
    }

    //curl -X GET http://localhost:8080/identity/<tu-wstaw-UUID>
    @GetMapping("/{id}")
    public IdentityResponse getIdentity(@PathVariable UUID id) {
        return identityApplicationService.getIdentity(new IdentityId(id));
    }

}
