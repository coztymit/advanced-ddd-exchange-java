package pl.coztymit.exchange.promotion.domain;

import jakarta.persistence.*;

@Entity
@Table(name ="promotions")
public class Promotion {

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "promotion_number"))
    @EmbeddedId
    private PromotionNumber promotionNumber;

    @Embedded
    private TargetCustomer targetCustomer;

    @AttributeOverride(name = "promotionType", column = @Column(name = "promotion_type"))
    private PromotionType promotionType;

    Promotion() {
    }

    public Promotion(TargetCustomer targetCustomer, PromotionType promotionType) {
        this.promotionNumber = PromotionNumber.generateNewId();
        this.targetCustomer = targetCustomer;
        this.promotionType = promotionType;
    }
}
