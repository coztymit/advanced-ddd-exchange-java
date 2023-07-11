package pl.coztymit.exchange.currency.application;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.currency.domain.*;
import pl.coztymit.exchange.currency.domain.exception.CurrencyPairNotSupportedException;
import pl.coztymit.exchange.kernel.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyPairService {
    private Log LOG = LogFactory.getLog(CurrencyPairService.class);
    private final CurrencyPairRepository repository;
    private final CurrencyPairFactory factory;

    @Autowired
    public CurrencyPairService(CurrencyPairRepository repository, CurrencyPairFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Transactional
    public void addCurrencyPair(Currency baseCurrency, Currency targetCurrency) {
        if (repository.alreadyExists(baseCurrency, targetCurrency)){
            throw new RuntimeException("Currency pair already exists");
        }
        CurrencyPair currencyPair = null;
        try {
            currencyPair = factory.create(baseCurrency, targetCurrency);
        } catch (CurrencyPairNotSupportedException e) {
            LOG.error("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
            throw new RuntimeException(e);
        }
        repository.save(currencyPair);
    }

    @Transactional
    public void addCurrencyPairWithRate(BigDecimal rate, Currency baseCurrency, Currency targetCurrency) {
        if (repository.alreadyExists(baseCurrency, targetCurrency)){
            throw new RuntimeException("Currency pair already exists");
        }
        CurrencyPair currencyPair = null;
        try {
            currencyPair = factory.create(rate, baseCurrency, targetCurrency);
        } catch (CurrencyPairNotSupportedException e) {
            LOG.error("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
            throw new RuntimeException(e);
        }
        repository.save(currencyPair);
    }

    @Transactional
    public void deactivateCurrencyPair(CurrencyPairId currencyPairId) {
        CurrencyPair existingCurrencyPair = repository.findById(currencyPairId);
        existingCurrencyPair.deactivate();
        repository.save(existingCurrencyPair);
    }

    @Transactional
    public void updateCurrencyPairRate(Currency baseCurrency, Currency targetCurrency, BigDecimal adjustedRate) {
        CurrencyPair existingCurrencyPair = repository.findByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
        existingCurrencyPair.adjustExchangeRate(adjustedRate);
        repository.save(existingCurrencyPair);
    }

    public List<CurrencyPairResponse> getAllCurrencyPairs() {
        List<CurrencyPairData> currencyPairs = repository.findAll();
        return currencyPairs.stream()
                .map(pair -> new CurrencyPairResponse (
                        pair.getCurrencyPairId(),
                        pair.getBaseCurrency(),
                        pair.getTargetCurrency(),
                        pair.getBaseExchangeRate(),
                        pair.getAdjustedExchangeRate()))
                .collect(Collectors.toList());
    }

    public CurrencyPairResponse getCurrencyPair(Currency baseCurrency, Currency targetCurrency) {
        CurrencyPairData currencyPair = repository.findDataByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
        return new CurrencyPairResponse(
                currencyPair.getCurrencyPairId(),
                currencyPair.getBaseCurrency(),
                currencyPair.getTargetCurrency(),
                currencyPair.getBaseExchangeRate(),
                currencyPair.getAdjustedExchangeRate());
    }
}
