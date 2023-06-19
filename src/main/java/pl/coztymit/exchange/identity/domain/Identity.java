package pl.coztymit.exchange.identity.domain;

import jakarta.persistence.*;
import pl.coztymit.exchange.kernel.IdentityId;


@Entity
@Table(name = "identities")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @AttributeOverride(name = "value", column = @Column(name = "firstName"))
    @Embedded
    private FirstName firstName;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "surname"))
    private Surname surname;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pesel"))
    private PESEL pesel;
    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "identityId"))
    private IdentityId identityId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    private Email email;

    Identity(IdentityId identityId, PESEL pesel, FirstName firstName, Email email, Surname surname) {
        this.identityId = identityId;
        this.pesel = pesel;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public boolean equals(Identity identity) {
        if (this == identity) return true;
        return identityId.equals(identity.identityId) &&
                pesel.equals(identity.pesel) &&
                firstName.equals(identity.firstName) &&
                email.equals(identity.email) &&
                surname.equals(identity.surname);
    }

    public IdentityId getIdentityId() {
        return identityId;
    }
}
