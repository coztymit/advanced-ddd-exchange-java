package pl.coztymit.exchange.negotiation.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.*;
import pl.coztymit.exchange.negotiation.domain.exception.NegotiationNotFoundException;
import pl.coztymit.exchange.negotiation.domain.policy.NegotiationAutomaticApprovePolicy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NegotiationApplicationService {
    private Log LOG = LogFactory.getLog(NegotiationApplicationService.class);

    private final NegotiationRepository negotiationRepository;
    private final ManualNegotiationApproveNotifier manualNegotiationApproveNotifier;
    private final List<NegotiationAutomaticApprovePolicy> negotiationAmountAutomaticApprovePolicies;
    private final BaseExchangeRateAdvisor baseExchangeRateAdvisor;
    private final NegotiationAcceptanceService negotiationAcceptanceService;


    @Autowired
    public NegotiationApplicationService(NegotiationRepository negotiationRepository,
                                         ManualNegotiationApproveNotifier manualNegotiationApproveNotifier,
                                         List<NegotiationAutomaticApprovePolicy> negotiationAmountAutomaticApprovePolicies,
                                         BaseExchangeRateAdvisor baseExchangeRateAdvisor, NegotiationAcceptanceService negotiationAcceptanceService) {
        this.negotiationRepository = negotiationRepository;
        this.manualNegotiationApproveNotifier = manualNegotiationApproveNotifier;
        this.negotiationAmountAutomaticApprovePolicies = negotiationAmountAutomaticApprovePolicies;
        this.baseExchangeRateAdvisor = baseExchangeRateAdvisor;
        this.negotiationAcceptanceService = negotiationAcceptanceService;
    }

    @Transactional
    public CreateNegotiationStatus createNegotiation(CreateNegotiationCommand command) {
        Negotiator negotiator = new Negotiator(command.identityId());
        ProposedExchangeAmount proposedExchangeAmount = new ProposedExchangeAmount(command.proposedExchangeAmount(), new Currency(command.proposedExchangeCurrency()));

        if(negotiationRepository.alreadyExistsActiveNegotiationForNegotiator(
                negotiator,
                command.baseCurrency(),
                command.targetCurrency(),
                command.proposedRate(),
                proposedExchangeAmount
               )){
            return CreateNegotiationStatus.ALREADY_EXISTS;
        }

        Optional<BigDecimal> optionalBaseExchangeRate = baseExchangeRateAdvisor.baseExchangeRate(command.baseCurrency(), command.targetCurrency());

        if(!optionalBaseExchangeRate.isPresent()){
            return CreateNegotiationStatus.CURRENCY_PAIR_NOT_SUPPORTED;
        }

        BigDecimal baseExchangeRate = optionalBaseExchangeRate.get();


        Negotiation negotiation = new Negotiation(
            negotiator,
            proposedExchangeAmount,
            command.baseCurrency(),
            command.targetCurrency(),
            new NegotiationRate(command.proposedRate(), baseExchangeRate)
        );

        AutomaticNegotiationStatus status = negotiation.tryAutomaticApprove(negotiationAmountAutomaticApprovePolicies);
        negotiationRepository.save(negotiation);

        if(status.isApproved()){
            negotiationAcceptanceService.negotiationAccepted(negotiation);
            return CreateNegotiationStatus.APPROVED;

        }
        manualNegotiationApproveNotifier.notifyManualApprovalRequired();
        return CreateNegotiationStatus.PENDING;
    }

    @Transactional
    public void approveNegotiation(UUID negotiationId, UUID operatorId) {
        try {
            //TODO operator verification
            Negotiation negotiation = negotiationRepository.findById(new NegotiationId(negotiationId));
            negotiation.approve(new OperatorId(operatorId));
            negotiationRepository.save(negotiation);
            manualNegotiationApproveNotifier.notifyNegotiationApproved(negotiationId.toString());
            negotiationAcceptanceService.negotiationAccepted(negotiation);
        } catch (NegotiationNotFoundException e) {
            LOG.error("Negotiation not found", e);
        }
    }

    @Transactional
    public void rejectNegotiation(UUID negotiationId, UUID operatorId) {
        try {
            Negotiation negotiation = negotiationRepository.findById(new NegotiationId(negotiationId));
            negotiation.reject(new OperatorId(operatorId));
            negotiationRepository.save(negotiation);
            manualNegotiationApproveNotifier.notifyNegotiationRejected(negotiationId.toString());
        } catch (NegotiationNotFoundException e) {
            LOG.error("Negotiation not found", e);
        }
    }

    public NegotiationRateResponse getNegotiationRateIfApproved(UUID negotiationId) {
        //TODO QueryStack
        try {
            return new NegotiationRateResponse(negotiationRepository.findApprovedRateById(new NegotiationId(negotiationId)));
        } catch (NegotiationNotFoundException e) {
            LOG.error("Negotiation not found", e);
            return NegotiationRateResponse.failed();
        }
    }

    public NegotiationRateResponse findAcceptedActiveNegotiationRate(FindAcceptedActiveNegotiationRateCommand command) {
        ProposedExchangeAmount proposedExchangeAmount = new ProposedExchangeAmount(command.proposedExchangeAmount(), new Currency(command.proposedExchangeCurrency()));
        Optional<BigDecimal> acceptedActiveNegotiation = negotiationRepository.findAcceptedActiveNegotiation(
                new Negotiator(command.identityId()),
                command.baseCurrency(),
                command.targetCurrency(),
                proposedExchangeAmount);

        return acceptedActiveNegotiation.map(NegotiationRateResponse::new).orElse(NegotiationRateResponse.failed());
    }
}
