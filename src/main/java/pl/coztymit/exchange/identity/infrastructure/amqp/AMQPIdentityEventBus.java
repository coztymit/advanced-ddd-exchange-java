package pl.coztymit.exchange.identity.infrastructure.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AMQPIdentityEventBus {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AMQPIdentityEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    //Do użycia w metodzie post z klasy IdentityDomainEventBus
    //String jsonString = mapper.writeValueAsString(event);
    //rabbitTemplate.convertAndSend(IdentityRabbitConfiguration.identityCreatedExchange, Strings.EMPTY, jsonString);

}
