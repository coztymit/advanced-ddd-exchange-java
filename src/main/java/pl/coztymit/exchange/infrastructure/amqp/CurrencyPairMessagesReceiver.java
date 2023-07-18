package pl.coztymit.exchange.infrastructure.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.negotiation.domain.NegotiationAcceptanceService;

@Component
public class CurrencyPairMessagesReceiver {

    @Autowired
    private NegotiationAcceptanceService negotiationAcceptanceService;

    @RabbitListener(queues = RabbitConfiguration.currencyPairCreatedQueue)
    public void currencyPairCreatedForNegotiationMessage(String message) {

    }

    @RabbitListener(queues = RabbitConfiguration.currencyPairCreatedQueue)
    public void currencyPairCreatedForQuotingMessage(String message) {

    }
}