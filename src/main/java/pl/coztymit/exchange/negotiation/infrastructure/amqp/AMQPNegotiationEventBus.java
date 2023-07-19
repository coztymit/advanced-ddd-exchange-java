package pl.coztymit.exchange.negotiation.infrastructure.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AMQPNegotiationEventBus {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AMQPNegotiationEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*@Override
    public void post(NegotiationCreated event) {
        IdentityId identityId = event.negotiator().identity(Function.identity());
        rabbitTemplate.convertAndSend(NegotiationRabbitConfiguration.negotiationCreatedExchange, Strings.EMPTY, identityId);
    }

    @Override
    public void post(NegotiationApproved event) {
    }*/
}
