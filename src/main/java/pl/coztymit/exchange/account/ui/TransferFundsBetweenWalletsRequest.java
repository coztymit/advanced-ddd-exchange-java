package pl.coztymit.exchange.account.ui;

import java.math.BigDecimal;

public record TransferFundsBetweenWalletsRequest(BigDecimal moneyToBuyValue, String moneyToBuyCurrency, BigDecimal moneyToSellValue, String moneyToSellCurrency) {
}
