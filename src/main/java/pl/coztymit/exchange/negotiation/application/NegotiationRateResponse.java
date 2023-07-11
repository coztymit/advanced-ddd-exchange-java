package pl.coztymit.exchange.negotiation.application;

import java.math.BigDecimal;

public class NegotiationRateResponse {
    private BigDecimal rate;
    private String status;

    private NegotiationRateResponse() {
        this.status = "CANNOT_FIND_RATE";
    }

    public NegotiationRateResponse(BigDecimal rate) {
        if (rate == null) {
            this.status = "CANNOT_FIND_RATE";
        }else{
            this.rate = rate;
            this.status = "SUCCESS";
        }
    }

    public static NegotiationRateResponse failed() {
        return new NegotiationRateResponse();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
