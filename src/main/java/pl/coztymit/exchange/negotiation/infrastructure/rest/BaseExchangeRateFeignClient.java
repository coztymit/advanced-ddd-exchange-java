package pl.coztymit.exchange.negotiation.infrastructure.rest;


import feign.Param;
import feign.RequestLine;

import java.math.BigDecimal;

public interface BaseExchangeRateFeignClient {

    @RequestLine("GET /currency-pair/{baseCurrency}/{targetCurrency}")
    CurrencyPairResponse getCurrencyPair(@Param("baseCurrency") String baseCurrency,
                                         @Param("targetCurrency") String targetCurrency);


    // Response DTO
    class CurrencyPairResponse {
        private BigDecimal baseExchangeRate;
        private BigDecimal adjustedExchangeRate;

        // getters and setters
        public BigDecimal getBaseExchangeRate() {
            return baseExchangeRate;
        }

        public void setBaseExchangeRate(BigDecimal baseExchangeRate) {
            this.baseExchangeRate = baseExchangeRate;
        }

        public BigDecimal getAdjustedExchangeRate() {
            return adjustedExchangeRate;
        }

        public void setAdjustedExchangeRate(BigDecimal adjustedExchangeRate) {
            this.adjustedExchangeRate = adjustedExchangeRate;
        }
    }
}
