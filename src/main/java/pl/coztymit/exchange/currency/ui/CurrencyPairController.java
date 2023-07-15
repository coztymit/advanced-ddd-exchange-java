package pl.coztymit.exchange.currency.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coztymit.exchange.currency.application.*;
import pl.coztymit.exchange.kernel.Currency;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/currency-pair")
public class CurrencyPairController {
    private final CurrencyPairApplicationService currencyPairApplicationService;

    @Autowired
    public CurrencyPairController(CurrencyPairApplicationService currencyPairApplicationService) {
        this.currencyPairApplicationService = currencyPairApplicationService;
    }

    @PostMapping("/add")
    public AddCurrencyPairStatus addCurrencyPair(@RequestBody CurrencyPairRequest currencyPairRequest) {
        return currencyPairApplicationService.addCurrencyPair(currencyPairRequest.getBaseCurrency(), currencyPairRequest.getTargetCurrency());
    }
    @PostMapping("/add-with-rate")
    public AddCurrencyPairWithRateResponse addCurrencyPairWithRate(@RequestBody CurrencyPairWithRateRequest currencyPairWithRateRequest) {
        return currencyPairApplicationService.addCurrencyPairWithRate(
                currencyPairWithRateRequest.getAdjustedRate(),
                currencyPairWithRateRequest.getBaseCurrency(),
                currencyPairWithRateRequest.getTargetCurrency());
    }
    @PutMapping("/update-rate")
    public UpdateCurrencyPairRateStatus updateCurrencyPairRate(@RequestBody CurrencyPairWithRateRequest currencyPairWithRateRequest) {
        return currencyPairApplicationService.updateCurrencyPairRate(
                currencyPairWithRateRequest.getBaseCurrency(),
                currencyPairWithRateRequest.getTargetCurrency(),
                currencyPairWithRateRequest.getAdjustedRate());
    }

    @PostMapping("/deactivate")
    public DeactivateCurrencyPairStatus deactivateCurrencyPair(@RequestParam UUID currencyPairId) {
        return currencyPairApplicationService.deactivateCurrencyPair(currencyPairId);
    }

    @GetMapping("/all")
    public List<CurrencyPairResponse> getAllCurrencyPairs() {
        return currencyPairApplicationService.getAllCurrencyPairs();
    }

    @GetMapping("/{baseCurrency}/{targetCurrency}")
    public CurrencyPairResponse getCurrencyPair(@PathVariable("baseCurrency") String baseCurrency,
                                                @PathVariable("targetCurrency") String targetCurrency) {
       return currencyPairApplicationService.getCurrencyPair(new Currency(baseCurrency.toUpperCase()), new Currency(targetCurrency.toUpperCase()));
    }

}
