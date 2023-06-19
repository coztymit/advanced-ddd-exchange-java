package pl.coztymit.exchange.accounting.application;

public interface NotificationSender {
    void sendNotification(String invoiceNumber);
}
