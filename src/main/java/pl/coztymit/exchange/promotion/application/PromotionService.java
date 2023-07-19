package pl.coztymit.exchange.promotion.application;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coztymit.exchange.kernel.IdentityId;
import pl.coztymit.exchange.promotion.domain.Promotion;
import pl.coztymit.exchange.promotion.domain.PromotionRepository;
import pl.coztymit.exchange.promotion.domain.PromotionType;
import pl.coztymit.exchange.promotion.domain.TargetCustomer;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Transactional
    public void createNewTraderPromotion(IdentityId identityId) {
        Promotion promotion = new Promotion(new TargetCustomer(identityId), PromotionType.NEW_TRADER);
        promotionRepository.save(promotion);
    }
}
