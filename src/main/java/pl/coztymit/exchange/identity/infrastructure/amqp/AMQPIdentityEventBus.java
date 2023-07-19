package pl.coztymit.exchange.identity.infrastructure.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.identity.domain.IdentityDomainEventBus;
import pl.coztymit.exchange.identity.domain.event.IdentityActivated;
import pl.coztymit.exchange.identity.domain.event.IdentityCreated;
import pl.coztymit.exchange.identity.domain.event.IdentityDeactivated;
import pl.coztymit.exchange.infrastructure.amqp.identity.IdentityRabbitConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class AMQPIdentityEventBus implements IdentityDomainEventBus {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AMQPIdentityEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void post(IdentityCreated event) {
        try {
            String jsonString = mapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(IdentityRabbitConfiguration.identityCreatedExchange, Strings.EMPTY, jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void post(IdentityDeactivated event) {
        try {
            String jsonString = mapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(IdentityRabbitConfiguration.identityDeactivatedExchange, Strings.EMPTY, jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void post(IdentityActivated event) {
        try {
            String jsonString = mapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(IdentityRabbitConfiguration.identityActivatedExchange, Strings.EMPTY, event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
