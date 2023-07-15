package pl.coztymit.exchange.negotiation.ui;

import pl.coztymit.exchange.kernel.IdentityId;

import java.math.BigDecimal;

public class NegotiationRequest {
    private IdentityId identityId;
    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal proposedExchangeAmount;
    private String proposedExchangeCurrency;
    private BigDecimal proposedRate;

    public NegotiationRequest() {
    }

    public NegotiationRequest(IdentityId identityId, String baseCurrency, String targetCurrency, BigDecimal proposedExchangeAmount, BigDecimal proposedRate, String proposedExchangeCurrency) {
        this.identityId = identityId;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.proposedExchangeAmount = proposedExchangeAmount;
        this.proposedRate = proposedRate;
        this.proposedExchangeCurrency = proposedExchangeCurrency;
    }

    public IdentityId getIdentityId() {
        return identityId;
    }

    public void setIdentityId(IdentityId identityId) {
        this.identityId = identityId;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String  getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String  targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getProposedExchangeAmount() {
        return proposedExchangeAmount;
    }

    public void setProposedExchangeAmount(BigDecimal proposedExchangeAmount) {
        this.proposedExchangeAmount = proposedExchangeAmount;
    }

    public BigDecimal getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(BigDecimal proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getProposedExchangeCurrency() {
        return proposedExchangeCurrency;
    }

    public void setProposedExchangeCurrency(String proposedExchangeCurrency) {
        this.proposedExchangeCurrency = proposedExchangeCurrency;
    }

}
