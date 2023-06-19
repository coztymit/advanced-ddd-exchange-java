package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.kernel.Money;

class Line {
    //nazwa nie przypadkowa ale nie do realnego systemu
    //pojawił się obcy kontekst - PRODUCT
    private PositionBusinessId id;
    private ProductNumber productNumber;
    private Money productValue;

    Line(ProductNumber productNumber, Money productValue) {
        id = new PositionBusinessId();
        this.productNumber = productNumber;
        this.productValue = productValue;
    }


    public Money lineValue() {
        return productValue;
    }
}
