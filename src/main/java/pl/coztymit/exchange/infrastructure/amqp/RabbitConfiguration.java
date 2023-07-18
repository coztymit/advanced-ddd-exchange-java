package pl.coztymit.exchange.infrastructure.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfiguration {
    public static final String currencyPairCreatedQueue= "currencyPairCreatedQueue";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue myQueue() {
        return new Queue("currencyPairCreatedQueue", true);
    }

    @Bean
    public TopicExchange myExchange() {
        return new TopicExchange("currencyPairCreatedExchange");
    }

    @Bean
    public Binding binding(Queue myQueue, TopicExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with("routingKey");
    }

}
