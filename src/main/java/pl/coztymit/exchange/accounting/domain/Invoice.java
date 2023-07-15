package pl.coztymit.exchange.accounting.domain;

import pl.coztymit.exchange.accounting.domain.exception.CannotAddLineToApprovedInvoiceException;
import pl.coztymit.exchange.accounting.domain.policy.LineLimitPolicy;
import pl.coztymit.exchange.kernel.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Aggregate
public class Invoice {

    private Number number;
    private ApproveStatus approveStatus = ApproveStatus.DRAFT;
    private Money lineValue = Money.ZERO_PLN;

    private List<Line> lines = new ArrayList<>();

    private List<Correction> correction;
    private Seller seller;
    private Buyer buyer;

    private List<Correction> corrections = new ArrayList<>();

    //TO można zmienić na politykę
    private static Money lineValueLimit = new Money(new BigDecimal("10000"));
    //TO należy zamienić na politykę
    private static int lineLimit = 10;


    Invoice(Seller seller, Buyer buyer) {
        this.seller = seller;
        this.buyer = buyer;
        this.number = new Number();

    }

    Invoice(Number number, Seller seller, Buyer buyer) {
        this.seller = seller;
        this.buyer = buyer;
        this.number = number;
    }

    public ApproveStatus approve() {
        this.approveStatus = ApproveStatus.APPROVED;
        return ApproveStatus.APPROVED;
    }

    void addLine(Line line, LineLimitPolicy lineLimitPolicy) {

        if (!lineLimitPolicy.lessOrEqualsLimit(this.lines.size() + 1)) {

            throw new RuntimeException();
        }

        if (approveStatus.equals(ApproveStatus.DRAFT)) {
            lines.add(line);
            lineValue = lineValue.add(line.lineValue());
        }
    }

    void addLines(List<Line> lines) {
        //sprawdzenie invariant

        if (approveStatus.equals(ApproveStatus.DRAFT)) {
            lines.forEach(line ->
            {
                this.lineValue = lineValue.add(line.lineValue());
                this.lines.add(line);
            });
        } else {
            throw new CannotAddLineToApprovedInvoiceException();
        }
    }


    //invariant
    private boolean lessOrEqualsLineLimit(int newLineCount) {
        if (this.lines.size() + newLineCount > 10) {
            return false;
        }
        return true;
    }

    //invariant
    private boolean valueLessOrEqualsMoneyLimit(Money newLineValue) {
        var oldAndNewLineValue = lineValue.add(newLineValue);
        if (oldAndNewLineValue.lessThan(lineValueLimit)) {
            return false;
        }
        return true;
    }

    private void invariantCheck(Line line) {
        if (!lessOrEqualsLineLimit(1)) {
            throw new RuntimeException();
        }
        if (!valueLessOrEqualsMoneyLimit(line.lineValue())) {
            throw new RuntimeException();
        }
    }

    public String invoiceNumber() {
        return this.number.toString();
    }

    public String toString() {
        return number.toString() + " : " + approveStatus.toString() + " : "
                + lineValue.toString() + " : " + seller.toString();
    }
}
