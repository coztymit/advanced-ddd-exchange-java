package pl.coztymit.exchange.infrastructure.amqp.identity;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class IdentityRabbitConfiguration {

    // queues
    public static final String identityCreatedQueueForAccount = "identityCreatedQueue.account";
    public static final String identityActivatedQueueForAccount = "identityActivatedQueue.account";
    public static final String identityDeactivatedQueueForAccount = "identityDeactivatedQueue.account";

    public static final String identityCreatedQueueForPromotion = "identityCreatedQueue.promotion";

    // exchanges
    public static final String identityCreatedExchange = "identityCreatedExchange";
    public static final String identityDeactivatedExchange = "identityDeactivatedExchange";
    public static final String identityActivatedExchange = "identityActivatedExchange";

    @Bean
    public Queue identityCreatedQueueForAccount() {
        return new Queue(identityCreatedQueueForAccount);
    }
    @Bean
    public Queue identityActivatedQueueForAccount() {
        return new Queue(identityActivatedQueueForAccount);
    }
    @Bean
    public Queue identityDeactivatedQueueForAccount() {
        return new Queue(identityDeactivatedQueueForAccount);
    }

    @Bean
    public Queue identityCreatedQueueForPromotion() {
        return new Queue(identityCreatedQueueForPromotion);
    }

    @Bean
    public FanoutExchange identityCreatedExchange() {
        return new FanoutExchange(identityCreatedExchange);
    }

    @Bean
    public FanoutExchange identityActivatedExchange() {
        return new FanoutExchange(identityActivatedExchange);
    }
    @Bean
    public FanoutExchange identityDeactivatedExchange() {
        return new FanoutExchange(identityDeactivatedExchange);
    }


    // binding for account
    @Bean
    public Binding identityCreatedToAccount(Queue identityCreatedQueueForAccount, FanoutExchange identityCreatedExchange) {
        return BindingBuilder.bind(identityCreatedQueueForAccount).to(identityCreatedExchange);
    }

    @Bean
    public Binding identityActivatedToAccount(Queue identityActivatedQueueForAccount, FanoutExchange identityActivatedExchange) {
        return BindingBuilder.bind(identityActivatedQueueForAccount).to(identityActivatedExchange);
    }

    @Bean
    public Binding identityDeactivatedToAccount(Queue identityDeactivatedQueueForAccount, FanoutExchange identityDeactivatedExchange) {
        return BindingBuilder.bind(identityDeactivatedQueueForAccount).to(identityDeactivatedExchange);
    }

    @Bean
    public Binding identityCreatedToPromotion(Queue identityCreatedQueueForPromotion, FanoutExchange identityCreatedExchange) {
        return BindingBuilder.bind(identityCreatedQueueForPromotion).to(identityCreatedExchange);
    }
}
