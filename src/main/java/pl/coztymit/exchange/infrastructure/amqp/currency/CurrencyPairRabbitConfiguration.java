package pl.coztymit.exchange.infrastructure.amqp.currency;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyPairRabbitConfiguration {
    // queues
    public static final String currencyPairCreatedQueueNegotiation = "currencyPairCreatedQueue.negotiation";
    public static final String currencyPairCreatedQueueQuoting = "currencyPairCreatedQueue.quoting";
    public static final String currencyPairDeactivatedQueueQuoting = "currencyPairDeactivatedQueue.quoting";
    public static final String currencyPairDeactivatedQueueNegotiation = "currencyPairDeactivatedQueue.negotiation";

    // exchanges
    public static final String currencyPairCreatedExchange = "currencyPairCreatedExchange";
    public static final String currencyPairRateAdjustedExchange = "currencyPairRateAdjustedExchange";
    public static final String currencyPairDeactivatedExchange = "currencyPairDeactivatedExchange";

    @Bean
    public Queue currencyPairDeactivatedQueueNegotiation() {
        return new Queue(currencyPairDeactivatedQueueNegotiation);
    }

    @Bean
    public Queue currencyPairCreatedQueueNegotiation() {
        return new Queue(currencyPairCreatedQueueNegotiation);
    }
    @Bean
    public Queue currencyPairCreatedQueueQuoting() {
        return new Queue(currencyPairCreatedQueueQuoting);
    }
    @Bean
    public Queue currencyPairDeactivatedQueueQuoting() {
        return new Queue(currencyPairDeactivatedQueueQuoting);
    }


    @Bean
    public FanoutExchange currencyPairCreatedExchange() {
        return new FanoutExchange(currencyPairCreatedExchange);
    }

    @Bean
    public FanoutExchange currencyPairRateAdjustedExchange() {
        return new FanoutExchange(currencyPairRateAdjustedExchange);
    }

    @Bean
    public FanoutExchange currencyPairDeactivatedExchange() {
        return new FanoutExchange(currencyPairDeactivatedExchange);
    }


    @Bean
    public Binding currencyAddToNegotiation(Queue currencyPairCreatedQueueNegotiation, FanoutExchange currencyPairCreatedExchange) {
        return BindingBuilder.bind(currencyPairCreatedQueueNegotiation).to(currencyPairCreatedExchange);
    }

    @Bean
    public Binding currencyAddToQuoting(Queue currencyPairCreatedQueueQuoting, FanoutExchange currencyPairCreatedExchange) {
        return BindingBuilder.bind(currencyPairCreatedQueueQuoting).to(currencyPairCreatedExchange);
    }

    @Bean
    public Binding currencyDeactivatedToNegotiation(Queue currencyPairDeactivatedQueueNegotiation, FanoutExchange currencyPairDeactivatedExchange) {
        return BindingBuilder.bind(currencyPairDeactivatedQueueNegotiation).to(currencyPairDeactivatedExchange);
    }

    @Bean
    public Binding currencyDeactivatedToQuoting(Queue currencyPairDeactivatedQueueQuoting, FanoutExchange currencyPairDeactivatedExchange) {
        return BindingBuilder.bind(currencyPairDeactivatedQueueQuoting).to(currencyPairDeactivatedExchange);
    }
}
