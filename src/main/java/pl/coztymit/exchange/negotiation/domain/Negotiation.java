package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.policy.NegotiationAutomaticApprovePolicy;

import java.util.List;

@Entity
@Table(name = "negotiations")
public class Negotiation {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "negotiation_id"))
    @EmbeddedId
    private NegotiationId negotiationId;

    @AttributeOverride(name = "identityId.uuid", column = @Column(name = "negotiator_identity_id"))
    private Negotiator negotiator;

    @AttributeOverride(name = "operatorId.uuid", column = @Column(name = "operator_id"))
    private Operator operator;

    @AttributeOverride(name = "value", column = @Column(name = "base_currency"))
    private Currency baseCurrency;

    @AttributeOverride(name = "value", column = @Column(name = "target_currency"))
    private Currency targetCurrency;

    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "proposed_exchange_amount")),
            @AttributeOverride(name = "currency.value", column = @Column(name = "proposed_exchange_currency"))
    })
    private ProposedExchangeAmount proposedExchangeAmount;

    @AttributeOverrides({
            @AttributeOverride(name = "proposedRate", column = @Column(name = "propose_exchange_rate")),
            @AttributeOverride(name = "baseExchangeRate", column = @Column(name = "base_exchange_rate")),
            @AttributeOverride(name = "differenceInPercentage", column = @Column(name = "difference_in_percentage"))
    })
    private NegotiationRate negotiationRate;

    @AttributeOverride(name = "status", column = @Column(name = "status"))
    private Status status;

    @AttributeOverride(name = "expirationDate", column = @Column(name = "expiration_date"))
    private ExpirationDate expirationDate;

    private Negotiation() {
    }

    public Negotiation(Negotiator negotiator, ProposedExchangeAmount proposedExchangeAmount, Currency baseCurrency, Currency targetCurrency, NegotiationRate  negotiationRate) {
        this.negotiationId = NegotiationId.generate();
        this.negotiator = negotiator;
        this.proposedExchangeAmount = proposedExchangeAmount;
        this.status = Status.PENDING;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.negotiationRate = negotiationRate;
    }

    public AutomaticNegotiationStatus tryAutomaticApprove(List<NegotiationAutomaticApprovePolicy> negotiationAmountAutomaticApprovePolicies) {
        negotiationAmountAutomaticApprovePolicies.stream()
                .filter(policy -> policy.isApplicable(proposedExchangeAmount))
                .filter(policy -> policy.shouldApprove(proposedExchangeAmount, this.negotiationRate.differenceInPercentage()))
                .findFirst()
                .ifPresent(policy -> {
                    this.status = Status.APPROVED;
                    this.expirationDate = ExpirationDate.oneHourExpirationDate();
                });

            return status.isApproved() ? AutomaticNegotiationStatus.APPROVED : AutomaticNegotiationStatus.PENDING;
    }

    public void approve(OperatorId operatorId) {
        this.operator = new Operator(operatorId);
        this.status = Status.APPROVED;
        this.expirationDate = ExpirationDate.oneHourExpirationDate();
    }

    public void reject(OperatorId operatorId) {
        this.operator = new Operator(operatorId);
        this.status = Status.REJECTED;
        this.expirationDate = ExpirationDate.expiredDate();
    }

    public boolean isApproved() {
        return status.isApproved();
    }

    NegotiationId negotiationId() {
        return this.negotiationId;
    }

    ProposedExchangeAmount proposedExchangeAmount() {
        return proposedExchangeAmount;
    }

    Negotiator negotiator() {
        return negotiator;
    }
}
