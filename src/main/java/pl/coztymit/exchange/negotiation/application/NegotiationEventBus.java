package pl.coztymit.exchange.negotiation.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.negotiation.domain.NegotiationDomainEventBus;
import pl.coztymit.exchange.negotiation.domain.event.NegotiationApproved;
import pl.coztymit.exchange.negotiation.domain.event.NegotiationCreated;

@Component
public class NegotiationEventBus implements NegotiationDomainEventBus {

    @Autowired
    private RiskAssessmentApplicationService riskAssessmentApplicationService;

    @Override
    public void post(NegotiationCreated event) {
    }

    @Override
    public void post(NegotiationApproved event) {
        riskAssessmentApplicationService.negotiationApproved(event.getNegotiationId(), event.getNegotiator(), event.getProposedExchangeAmount());
    }
}
