package pl.coztymit.exchange.currency.infrastructure.rest;

import feign.Param;
import feign.RequestLine;

import java.math.BigDecimal;

public interface RESTBaseCurrencyPairRateFeignClient {

    @RequestLine("GET /pair/{baseCurrency}/{targetCurrency}")
    ExchangeRateResponse getConversionRate(@Param("baseCurrency") String baseCurrency, @Param("targetCurrency") String targetCurrency);


    // Response DTO
    class ExchangeRateResponse {
        private String result;
        private String base_code;
        private String target_code;
        private BigDecimal conversion_rate;

        // getters and setters
        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getBase_code() {
            return base_code;
        }

        public void setBase_code(String base_code) {
            this.base_code = base_code;
        }

        public String getTarget_code() {
            return target_code;
        }

        public void setTarget_code(String target_code) {
            this.target_code = target_code;
        }

        public BigDecimal getConversion_rate() {
            return conversion_rate;
        }

        public void setConversion_rate(BigDecimal conversion_rate) {
            this.conversion_rate = conversion_rate;
        }
    }
}
