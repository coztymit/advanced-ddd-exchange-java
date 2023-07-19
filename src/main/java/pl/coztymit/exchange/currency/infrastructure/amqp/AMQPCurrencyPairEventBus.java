package pl.coztymit.exchange.currency.infrastructure.amqp;

import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.currency.domain.event.*;
import pl.coztymit.exchange.infrastructure.amqp.currency.CurrencyPairRabbitConfiguration;

@Component
public class AMQPCurrencyPairEventBus implements CurrencyPairDomainEventBus {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AMQPCurrencyPairEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void post(CurrencyPairCreated currencyPairCreated) {
        rabbitTemplate.convertAndSend(CurrencyPairRabbitConfiguration.currencyPairCreatedExchange, Strings.EMPTY, currencyPairCreated);
    }

    @Override
    public void post(CurrencyPairExchangeRateAdjusted currencyPairExchangeRateAdjusted) {
        rabbitTemplate.convertAndSend(CurrencyPairRabbitConfiguration.currencyPairRateAdjustedExchange, Strings.EMPTY, currencyPairExchangeRateAdjusted);
    }

    @Override
    public void post(CurrencyPairDeactivated currencyPairDeactivated) {
        rabbitTemplate.convertAndSend(CurrencyPairRabbitConfiguration.currencyPairDeactivatedExchange,  Strings.EMPTY, currencyPairDeactivated);
    }

    @Override
    public void post(CurrencyPairActivated currencyPairActivated) {

    }

}
