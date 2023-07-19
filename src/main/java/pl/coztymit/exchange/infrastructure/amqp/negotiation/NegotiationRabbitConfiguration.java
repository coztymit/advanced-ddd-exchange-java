package pl.coztymit.exchange.infrastructure.amqp.negotiation;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NegotiationRabbitConfiguration {
    public static final String negotiationCreatedQueueForPromotion = "negotiationCreatedQueue.promotion";

    // exchanges
    public static final String negotiationCreatedExchange = "negotiationCreatedExchange";

    @Bean
    public Queue negotiationCreatedQueueForPromotion() {
        return new Queue(negotiationCreatedQueueForPromotion);
    }

    @Bean
    public FanoutExchange negotiationCreatedExchange() {
        return new FanoutExchange(negotiationCreatedExchange);
    }

    @Bean
    public Binding negotiationCreatedToPromotion(Queue negotiationCreatedQueueForPromotion, FanoutExchange negotiationCreatedExchange) {
        return BindingBuilder.bind(negotiationCreatedQueueForPromotion).to(negotiationCreatedExchange);
    }

}
