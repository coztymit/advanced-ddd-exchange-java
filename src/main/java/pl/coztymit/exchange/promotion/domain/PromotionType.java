package pl.coztymit.exchange.promotion.domain;

import jakarta.persistence.Embeddable;


@Embeddable
public class PromotionType {

    public static final PromotionType NEW_TRADER = new PromotionType("NEW_TRADER");
    public static final PromotionType LOYAL_CUSTOMER = new PromotionType("LOYAL_CUSTOMER");
    public static final PromotionType VIP_CUSTOMER = new PromotionType("VIP_CUSTOMER");

    private String promotionType;

    private PromotionType(){

    }

    private PromotionType(String promotionType){
        this.promotionType = promotionType;
    }

}
