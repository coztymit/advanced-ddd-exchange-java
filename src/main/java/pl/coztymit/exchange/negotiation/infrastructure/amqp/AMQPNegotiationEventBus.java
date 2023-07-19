package pl.coztymit.exchange.negotiation.infrastructure.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.infrastructure.amqp.negotiation.NegotiationRabbitConfiguration;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.negotiation.domain.NegotiationDomainEventBus;
import pl.coztymit.exchange.negotiation.domain.event.NegotiationApproved;
import pl.coztymit.exchange.negotiation.domain.event.NegotiationCreated;

import java.util.function.Function;

@Component
public class AMQPNegotiationEventBus implements NegotiationDomainEventBus {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AMQPNegotiationEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void post(NegotiationCreated event) {
        IdentityId identityId = event.negotiator().identity(Function.identity());
        rabbitTemplate.convertAndSend(NegotiationRabbitConfiguration.negotiationCreatedExchange, Strings.EMPTY, identityId);
    }

    @Override
    public void post(NegotiationApproved event) {
    }
}
