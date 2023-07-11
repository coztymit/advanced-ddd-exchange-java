package pl.coztymit.exchange.negotiation.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class Operator {
    private OperatorId operatorId;

    private Operator(){
    }

    public Operator(OperatorId operatorId) {
        this.operatorId = operatorId;
    }
}
