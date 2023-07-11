package pl.coztymit.exchange.negotiation.infrastructure.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coztymit.exchange.negotiation.application.ManualNegotiationApproveNotifier;

@Component
public class EmailManualNegotiationApproveNotifier implements ManualNegotiationApproveNotifier {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void notifyManualApprovalRequired() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@coztymit.pl");
        message.setTo("operator@coztymit.pl");
        message.setSubject("Manual Approval Required");
        message.setText("Manual approval is required for a negotiation.");
        javaMailSender.send(message);

    }

    @Override
    public void notifyNegotiationApproved(String negotiationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@coztymit.pl");
        message.setTo("trader@coztymit.pl");
        message.setSubject("You negotiation has been approved");
        message.setText("Your negotiation has been approved. Negotiation number: " + negotiationId);
        javaMailSender.send(message);
    }

    @Override
    public void notifyNegotiationRejected(String negotiationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@coztymit.pl");
        message.setTo("trader@coztymit.pl");
        message.setSubject("You negotiation has been rejected");
        message.setText("Your negotiation has been rejected. Negotiation number: " + negotiationId);
        javaMailSender.send(message);
    }
}
