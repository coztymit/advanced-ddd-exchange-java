package pl.coztymit.exchange.negotiation.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.application.*;

import java.util.UUID;

@RestController
@RequestMapping("/negotiations")
public class NegotiationController {

    private final NegotiationApplicationService negotiationApplicationService;

    @Autowired
    public NegotiationController(NegotiationApplicationService negotiationApplicationService) {
        this.negotiationApplicationService = negotiationApplicationService;
    }

    @PostMapping("/create")
    public CreateNegotiationStatus createNegotiation(@RequestBody NegotiationRequest request) {
        CreateNegotiationCommand createNegotiationCommand = new CreateNegotiationCommand(
                request.getIdentityId(),
                new Currency(request.getBaseCurrency()),
                new Currency(request.getTargetCurrency()),
                request.getProposedExchangeAmount(),
                request.getProposedExchangeCurrency(),
                request.getProposedRate());

        return negotiationApplicationService.createNegotiation(createNegotiationCommand);
    }

    @PutMapping("/{negotiationId}/approve")
    public ResponseEntity approveNegotiation(@PathVariable UUID negotiationId, @RequestParam UUID operatorId) {
        negotiationApplicationService.approveNegotiation(negotiationId, operatorId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{negotiationId}/reject")
    public ResponseEntity rejectNegotiation(@PathVariable UUID negotiationId, @RequestParam UUID operatorId) {
        negotiationApplicationService.rejectNegotiation(negotiationId, operatorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public NegotiationRateResponse getNegotiation(@PathVariable("id") UUID negotiationId)  {
        return negotiationApplicationService.getNegotiationRateIfApproved(negotiationId);
    }

    @GetMapping("/find-approved")
    public NegotiationRateResponse findAcceptedNegotiation(@RequestBody FindAcceptedNegotiationRequest request) {
        FindAcceptedActiveNegotiationRateCommand findAcceptedActiveNegotiationRateCommand =
                new FindAcceptedActiveNegotiationRateCommand(
                    request.identityId(),
                    new Currency(request.baseCurrency()),
                    new Currency(request.targetCurrency()),
                    request.proposedExchangeAmount(),
                    request.proposedExchangeCurrency());

        return negotiationApplicationService.findAcceptedActiveNegotiationRate(findAcceptedActiveNegotiationRateCommand);

    }
}
