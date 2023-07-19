package pl.coztymit.exchange.account.infrastructure.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.account.domain.AccountDomainEventBus;
import pl.coztymit.exchange.account.domain.events.AccountActivated;
import pl.coztymit.exchange.infrastructure.amqp.account.AccountRabbitConfiguration;

@Component
public class AMQPAccountDomainEventBus implements AccountDomainEventBus {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AMQPAccountDomainEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void post(AccountActivated accountActivated) {
        try {
            String jsonString = mapper.writeValueAsString(accountActivated);
            rabbitTemplate.convertAndSend(AccountRabbitConfiguration.accountActivatedExchange, Strings.EMPTY, jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
