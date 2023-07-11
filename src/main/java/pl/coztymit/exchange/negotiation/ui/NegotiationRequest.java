package pl.coztymit.exchange.negotiation.ui;

import java.math.BigDecimal;

public class NegotiationRequest {
    private String traderNumber;
    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal proposedExchangeAmount;
    private String proposedExchangeCurrency;
    private BigDecimal proposedRate;

    public NegotiationRequest() {
    }

    public NegotiationRequest(String traderNumber, String baseCurrency, String targetCurrency, BigDecimal proposedExchangeAmount, BigDecimal proposedRate, String proposedExchangeCurrency) {
        this.traderNumber = traderNumber;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.proposedExchangeAmount = proposedExchangeAmount;
        this.proposedRate = proposedRate;
        this.proposedExchangeCurrency = proposedExchangeCurrency;
    }

    public String getTraderNumber() {
        return traderNumber;
    }

    public void setTraderNumber(String traderNumber) {
        this.traderNumber = traderNumber;
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
