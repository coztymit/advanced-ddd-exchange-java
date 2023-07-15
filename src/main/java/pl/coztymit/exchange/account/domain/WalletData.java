package pl.coztymit.exchange.account.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletData(UUID walletId, BigDecimal fundsValue, String fundsCurrency) {
}
