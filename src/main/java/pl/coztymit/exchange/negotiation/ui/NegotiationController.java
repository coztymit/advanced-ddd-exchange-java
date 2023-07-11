package pl.coztymit.exchange.negotiation.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.application.CreateNegotiationCommand;
import pl.coztymit.exchange.negotiation.application.CreateNegotiationStatus;
import pl.coztymit.exchange.negotiation.application.NegotiationRateResponse;
import pl.coztymit.exchange.negotiation.application.NegotiationService;
import pl.coztymit.exchange.negotiation.domain.Negotiation;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/negotiations")
public class NegotiationController {

    private final NegotiationService negotiationService;

    @Autowired
    public NegotiationController(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    @PostMapping("/create")
    public CreateNegotiationStatus createNegotiation(@RequestBody NegotiationRequest request) {
        CreateNegotiationCommand createNegotiationCommand = new CreateNegotiationCommand(
                new TraderNumber(request.getTraderNumber()),
                new Currency(request.getBaseCurrency()),
                new Currency(request.getTargetCurrency()),
                new Money(request.getProposedExchangeAmount(), new Currency(request.getProposedExchangeCurrency())),
                request.getProposedRate());

        return negotiationService.createNegotiation(createNegotiationCommand);
    }

    @PutMapping("/{negotiationId}/approve")
    public ResponseEntity approveNegotiation(@PathVariable UUID negotiationId, @RequestParam UUID operatorId) {
        negotiationService.approveNegotiation(negotiationId, operatorId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{negotiationId}/reject")
    public ResponseEntity rejectNegotiation(@PathVariable UUID negotiationId, @RequestParam UUID operatorId) {
        negotiationService.rejectNegotiation(negotiationId, operatorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public NegotiationRateResponse getNegotiation(@PathVariable("id") UUID negotiationId)  {
        return negotiationService.getNegotiationRateIfApproved(negotiationId);
    }

    @GetMapping("/find-approved")
    public NegotiationRateResponse findAcceptedNegotiation(@RequestParam String traderID,
                                               @RequestParam String baseCurrency,
                                               @RequestParam String targetCurrency,
                                               @RequestParam BigDecimal proposedExchangeAmount,
                                               @RequestParam String proposedExchangeCurrency) {

        return negotiationService.findAcceptedActiveNegotiationRate(
                new TraderNumber(traderID),
                new Currency(baseCurrency),
                new Currency(targetCurrency),
                new Money(proposedExchangeAmount, new Currency(proposedExchangeCurrency)));
    }
}
