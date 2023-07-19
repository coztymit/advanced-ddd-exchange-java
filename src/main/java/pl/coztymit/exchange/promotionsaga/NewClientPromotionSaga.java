package pl.coztymit.exchange.promotionsaga;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.infrastructure.amqp.account.AccountRabbitConfiguration;
import pl.coztymit.exchange.infrastructure.amqp.identity.IdentityRabbitConfiguration;
import pl.coztymit.exchange.infrastructure.amqp.negotiation.NegotiationRabbitConfiguration;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.promotion.application.PromotionService;

import java.util.Optional;

//Saga która naliczasm rabatu dla nowego klienta za rejestrację oraz zlecenie pierwszej negocjacji
// jeżeli istnieje w systemie
// aktywował konto
// zlecił pierwszą negocjacje

@Component
public class NewClientPromotionSaga {

    private final RabbitTemplate rabbitTemplate;
    private final NewClientPromotionRepository newClientPromotionSagaRepository;
    private final PromotionService promotionService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public NewClientPromotionSaga(RabbitTemplate rabbitTemplate, NewClientPromotionRepository newClientPromotionSagaRepository, PromotionService promotionService) {
        this.rabbitTemplate = rabbitTemplate;
        this.newClientPromotionSagaRepository = newClientPromotionSagaRepository;
        this.promotionService = promotionService;
    }

    @RabbitListener(queues = IdentityRabbitConfiguration.identityCreatedQueueForPromotion)
    @Transactional
    public void onIdentityCreated(String message) {

        try {
            IdentityForPromotionCreated identityCreatedEvent = mapper.readValue(message, IdentityForPromotionCreated.class);
            Optional<NewClientPromotion> newClientPromotion = newClientPromotionSagaRepository.findNewClientPromotion(identityCreatedEvent.identityId());
            if(!newClientPromotion.isPresent()) {
                newClientPromotionSagaRepository.save(new NewClientPromotion(identityCreatedEvent.identityId()));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = AccountRabbitConfiguration.accountActivatedQueueForPromotion)
    @Transactional
    public void onAccountActivated(String message) {
        try {
            AccountActivatedForPromotion identityCreatedEvent = mapper.readValue(message, AccountActivatedForPromotion.class);

            Optional<NewClientPromotion> optionalClientPromotion = newClientPromotionSagaRepository.findNewClientPromotion(identityCreatedEvent.identityId());
            if(optionalClientPromotion.isPresent()) {
                NewClientPromotion newClientPromotion = optionalClientPromotion.get();
                newClientPromotion.accountActivated();
                newClientPromotionSagaRepository.save(newClientPromotion);
                tryEndSaga(newClientPromotion);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = NegotiationRabbitConfiguration.negotiationCreatedQueueForPromotion)
    @Transactional
    public void onNegotiationCreated(IdentityId identityId)  {

            Optional<NewClientPromotion> optionalClientPromotion = newClientPromotionSagaRepository.findNewClientPromotion(identityId);
            if(optionalClientPromotion.isPresent()) {
                NewClientPromotion newClientPromotion = optionalClientPromotion.get();
                newClientPromotion.negotiationCreated();
                newClientPromotionSagaRepository.save(newClientPromotion);
                tryEndSaga(newClientPromotion);
            }

    }

    private void tryEndSaga(NewClientPromotion newClientPromotion) {
        if (newClientPromotion.isComplete()){

            promotionService.createNewTraderPromotion(newClientPromotion.identityId());

            // wersja alternatywna wysłanie widomości na kolejkę
            //NewClientPromotionCompleted newClientPromotionCompleted = new NewClientPromotionCompleted(newClientPromotion.identityId());
            //String jsonString = mapper.writeValueAsString(newClientPromotionCompleted);
            //rabbitTemplate.convertAndSend(PromotionRabbitConfiguration.Queue, Strings.EMPTY, jsonString);
        }
    }
}
