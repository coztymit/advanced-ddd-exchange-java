package pl.coztymit.exchange.negotiation.ui;

import java.math.BigDecimal;

public class NegotiationResponse {
   private BigDecimal rate;
   private String status;

   private NegotiationResponse() {
   }

   public NegotiationResponse(BigDecimal rate) {
        if (rate == null) {
            this.status = "CANNOT_FIND_RATE";
        }else{
            this.rate = rate;
            this.status = "SUCCESS";
        }
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
