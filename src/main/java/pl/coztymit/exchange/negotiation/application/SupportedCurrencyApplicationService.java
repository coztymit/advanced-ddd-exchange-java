package pl.coztymit.exchange.negotiation.application;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.Currency;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.Rate;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.SupportedCurrency;
import pl.coztymit.exchange.negotiation.domain.supportedcurrency.SupportedCurrencyRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SupportedCurrencyApplicationService {

    @Autowired
    private SupportedCurrencyRepository repository;

    @Transactional
    public void addSupportedCurrency(Currency baseCurrency, Currency targetCurrency, BigDecimal rate){
        repository.save(new SupportedCurrency(baseCurrency,targetCurrency, new Rate(rate)));
    }

    @Transactional
    public void adjustCurrencyPair(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
        Optional<SupportedCurrency> optionalSupportedCurrency = repository.findByCurrency(baseCurrency, targetCurrency);
        SupportedCurrency supportedCurrency = optionalSupportedCurrency.orElseThrow(RuntimeException::new);
        supportedCurrency.setRate(new Rate(rate));
        repository.save(supportedCurrency);
    }

    @Transactional
    public void deactivateCurrencyPair(Currency baseCurrency, Currency targetCurrency) {
        Optional<SupportedCurrency> optionalSupportedCurrency = repository.findByCurrency(baseCurrency, targetCurrency);
        SupportedCurrency supportedCurrency = optionalSupportedCurrency.orElseThrow(RuntimeException::new);
        supportedCurrency.deactivate();
        repository.save(supportedCurrency);
    }

    @Transactional
    public void activateCurrencyPair(Currency baseCurrency, Currency targetCurrency) {
        Optional<SupportedCurrency> optionalSupportedCurrency = repository.findByCurrency(baseCurrency, targetCurrency);
        SupportedCurrency supportedCurrency = optionalSupportedCurrency.orElseThrow(RuntimeException::new);
        supportedCurrency.activate();
        repository.save(supportedCurrency);
    }
}
