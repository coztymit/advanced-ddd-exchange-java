package pl.coztymit.exchange.account.application;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferFundsBetweenAccountCommand (UUID fromAccountId, UUID toAccountId, BigDecimal fundsToTransfer, String currency){
}
