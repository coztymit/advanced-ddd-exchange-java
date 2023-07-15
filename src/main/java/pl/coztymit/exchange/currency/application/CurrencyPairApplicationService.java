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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CurrencyPairApplicationService {
    private Log LOG = LogFactory.getLog(CurrencyPairApplicationService.class);
    private final CurrencyPairRepository repository;
    private final CurrencyPairFactory factory;

    @Autowired
    public CurrencyPairApplicationService(CurrencyPairRepository repository, CurrencyPairFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Transactional
    public AddCurrencyPairStatus addCurrencyPair(Currency baseCurrency, Currency targetCurrency) {
        if (repository.alreadyExists(baseCurrency, targetCurrency)){
            return AddCurrencyPairStatus.createCurrencyPairAlreadyExistsStatus();
        }

        try {
            CurrencyPair currencyPair = factory.create(baseCurrency, targetCurrency);
            repository.save(currencyPair);
            return AddCurrencyPairStatus.createSuccessStatus(currencyPair.currencyPairId());
        } catch (CurrencyPairNotSupportedException e) {
            LOG.error("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
            return AddCurrencyPairStatus.createFailureStatus("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
        }
    }

    @Transactional
    public AddCurrencyPairWithRateResponse addCurrencyPairWithRate(BigDecimal rate, Currency baseCurrency, Currency targetCurrency) {
        if (repository.alreadyExists(baseCurrency, targetCurrency)){
            return AddCurrencyPairWithRateResponse.createAlreadyExistsStatus();
        }
        try {
            CurrencyPair currencyPair = factory.create(rate, baseCurrency, targetCurrency);
            repository.save(currencyPair);
            return AddCurrencyPairWithRateResponse.createSuccessStatus();
        } catch (CurrencyPairNotSupportedException e) {
            LOG.error("Currency pair not supported: " + baseCurrency + " -> " + targetCurrency);
            return AddCurrencyPairWithRateResponse.createNorSupportedStatus();
        }
    }

    @Transactional
    public DeactivateCurrencyPairStatus deactivateCurrencyPair(UUID currencyPairId) {
        Optional<CurrencyPair> existingCurrencyPair = repository.findById(new CurrencyPairId(currencyPairId));
        try{
            CurrencyPair currencyPair = existingCurrencyPair.orElseThrow();
            currencyPair.deactivate();
            repository.save(currencyPair);
            return DeactivateCurrencyPairStatus.createSuccessStatus();
        }catch (Exception e){
            return DeactivateCurrencyPairStatus.createCurrencyPairNotFoundStatus();
        }
    }

    @Transactional
    public UpdateCurrencyPairRateStatus updateCurrencyPairRate(Currency baseCurrency, Currency targetCurrency, BigDecimal adjustedRate) {
        Optional<CurrencyPair> optionalExistingCurrencyPair = repository.findByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
        try{
            CurrencyPair currencyPair = optionalExistingCurrencyPair.orElseThrow();
            currencyPair.adjustExchangeRate(adjustedRate);
            repository.save(currencyPair);
            return UpdateCurrencyPairRateStatus.createSuccessStatus();
        }catch (Exception e){
            return UpdateCurrencyPairRateStatus.createCurrencyPairNotFoundStatus();
        }
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
        Optional<CurrencyPairData> optionalCurrencyPairData = repository.findDataByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);

        return optionalCurrencyPairData.map(
                pair -> new CurrencyPairResponse (
                    pair.getCurrencyPairId(),
                    pair.getBaseCurrency(),
                    pair.getTargetCurrency(),
                    pair.getBaseExchangeRate(),
                    pair.getAdjustedExchangeRate()))
                .orElseGet(CurrencyPairResponse::failure);
    }
}
