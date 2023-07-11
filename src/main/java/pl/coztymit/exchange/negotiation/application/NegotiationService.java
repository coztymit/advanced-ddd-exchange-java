package pl.coztymit.exchange.negotiation.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.account.domain.trader.TraderNumber;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.kernel.Money;
import pl.coztymit.exchange.negotiation.domain.*;
import pl.coztymit.exchange.negotiation.domain.exception.NegotiationNotFoundException;
import pl.coztymit.exchange.negotiation.domain.policy.NegotiationAutomaticApprovePolicy;
import pl.coztymit.exchange.quoting.application.QuoteApplicationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class NegotiationService {
    private Log LOG = LogFactory.getLog(NegotiationService.class);

    private final NegotiationRepository negotiationRepository;
    private final ManualNegotiationApproveNotifier manualNegotiationApproveNotifier;
    private List<NegotiationAutomaticApprovePolicy> negotiationAmountAutomaticApprovePolicies;

    @Autowired
    public NegotiationService(NegotiationRepository negotiationRepository, ManualNegotiationApproveNotifier manualNegotiationApproveNotifier, List<NegotiationAutomaticApprovePolicy> negotiationAmountAutomaticApprovePolicies) {
        this.negotiationRepository = negotiationRepository;
        this.manualNegotiationApproveNotifier = manualNegotiationApproveNotifier;
        this.negotiationAmountAutomaticApprovePolicies = negotiationAmountAutomaticApprovePolicies;
    }

    @Transactional
    public CreateNegotiationStatus createNegotiation(CreateNegotiationCommand command) {

        if(negotiationRepository.alreadyExistsActiveNegotiationForTrader(command.traderNumber(),
                command.baseCurrency(), command.targetCurrency(),
                command.proposedRate(), command.proposedExchangeAmount())){
            return CreateNegotiationStatus.ALREADY_EXISTS;
        }
        //TODO trzeba dostarczyć obecny kurs waluty
        //zadanie
        BigDecimal baseExchangeRate = new BigDecimal("4.5");

        Negotiation negotiation = new Negotiation(
                command.traderNumber(),
                command.proposedExchangeAmount(),
                command.baseCurrency(),
                command.targetCurrency(),
                new NegotiationRate(command.proposedRate(),baseExchangeRate)
              );
        negotiationRepository.save(negotiation);
        AutomaticNegotiationStatus status = negotiation.tryAutomaticApprove(negotiationAmountAutomaticApprovePolicies);

        if(status.isApproved()){
            return CreateNegotiationStatus.APPROVED;
        }
        manualNegotiationApproveNotifier.notifyManualApprovalRequired();
        return CreateNegotiationStatus.PENDING;
    }

    @Transactional
    public void approveNegotiation(UUID negotiationId, UUID operatorId) {
        try {
            //TODO weryfikacja operatora - czy mam możliwość wykonania operacji zatwierdzenia
            Negotiation negotiation = negotiationRepository.findById(new NegotiationId(negotiationId));
            negotiation.approve(new OperatorId(operatorId));
            negotiationRepository.save(negotiation);
            manualNegotiationApproveNotifier.notifyNegotiationApproved(negotiationId.toString());
        } catch (NegotiationNotFoundException e) {
            LOG.error("Negotiation not found", e);
        }
    }

    @Transactional
    public void rejectNegotiation(UUID negotiationId, UUID operatorId) {
        try {
            //TODO weryfikacja operatora - czy mam możliwość wykonania operacji zatwierdzenia
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

    public NegotiationRateResponse findAcceptedActiveNegotiationRate(TraderNumber traderNumber, Currency baseCurrency, Currency targetCurrency, Money proposedExchangeAmount) {
        try {
            return new NegotiationRateResponse(negotiationRepository.findAcceptedActiveNegotiation(traderNumber, baseCurrency, targetCurrency, proposedExchangeAmount));
        } catch (NegotiationNotFoundException e) {
            LOG.error("Negotiation not found", e);
            return NegotiationRateResponse.failed();
        }
    }
}
