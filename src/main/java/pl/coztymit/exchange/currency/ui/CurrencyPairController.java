package pl.coztymit.exchange.currency.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.currency.application.CurrencyPairResponse;
import pl.coztymit.exchange.currency.application.CurrencyPairService;
import pl.coztymit.exchange.currency.domain.CurrencyPair;
import pl.coztymit.exchange.currency.domain.CurrencyPairData;
import pl.coztymit.exchange.currency.domain.CurrencyPairId;
import pl.coztymit.exchange.kernel.Currency;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/currency-pair")
public class CurrencyPairController {
    private final CurrencyPairService currencyPairService;

    @Autowired
    public CurrencyPairController(CurrencyPairService currencyPairService) {
        this.currencyPairService = currencyPairService;
    }

    @PostMapping("/add")
    public void addCurrencyPair(@RequestBody CurrencyPairRequest currencyPairRequest) {
        currencyPairService.addCurrencyPair(currencyPairRequest.getBaseCurrency(), currencyPairRequest.getTargetCurrency());
    }
    @PostMapping("/add-with-rate")
    public void addCurrencyPairWithRate(@RequestBody CurrencyPairWithRateRequest currencyPairWithRateRequest) {
        currencyPairService.addCurrencyPairWithRate(
                currencyPairWithRateRequest.getAdjustedRate(),
                currencyPairWithRateRequest.getBaseCurrency(),
                currencyPairWithRateRequest.getTargetCurrency());
    }
    @PutMapping("/update-rate")
    public void updateCurrencyPairRate(@RequestBody CurrencyPairWithRateRequest currencyPairWithRateRequest) {
        currencyPairService.updateCurrencyPairRate(
                currencyPairWithRateRequest.getBaseCurrency(),
                currencyPairWithRateRequest.getTargetCurrency(),
                currencyPairWithRateRequest.getAdjustedRate());
    }

    @PostMapping("/deactivate")
    public void deactivateCurrencyPair(@RequestBody CurrencyPairId currencyPairId) {
        currencyPairService.deactivateCurrencyPair(currencyPairId);
    }

    @GetMapping("/all")
    public List<CurrencyPairResponse> getAllCurrencyPairs() {
        return currencyPairService.getAllCurrencyPairs();
    }

    @GetMapping("/{baseCurrency}/{targetCurrency}")
    public CurrencyPairResponse getCurrencyPair(@PathVariable("baseCurrency") String baseCurrency,
                                                @PathVariable("targetCurrency") String targetCurrency) {
       return currencyPairService.getCurrencyPair(new Currency(baseCurrency.toUpperCase()), new Currency(targetCurrency.toUpperCase()));
    }

}
