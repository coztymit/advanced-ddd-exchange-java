package pl.coztymit.exchange.infrastructure.amqp.account;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRabbitConfiguration {

    public static final String accountActivatedQueueForPromotion = "accountActivatedQueue.promotion";

    // exchanges
    public static final String accountActivatedExchange = "accountActivatedExchange";

    @Bean
    public Queue accountActivatedQueueForPromotion() {
        return new Queue(accountActivatedQueueForPromotion);
    }

    @Bean
    public FanoutExchange accountActivatedExchange() {
        return new FanoutExchange(accountActivatedExchange);
    }

    @Bean
    public Binding accountActivatedBinding(Queue accountActivatedQueueForPromotion, FanoutExchange accountActivatedExchange) {
        return BindingBuilder.bind(accountActivatedQueueForPromotion).to(accountActivatedExchange);
    }
}
