package pl.coztymit.exchange.account.ui.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.infrastructure.amqp.identity.IdentityRabbitConfiguration;

@Component
public class IdentityReceiver {

    @RabbitListener(queues = IdentityRabbitConfiguration.identityCreatedQueueForAccount)
    public void identityCreatedForAccount(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            IdentityCreatedEvent identityCreatedEvent = mapper.readValue(message, IdentityCreatedEvent.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
