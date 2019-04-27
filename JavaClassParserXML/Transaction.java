package JavaClassParserXML;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String place;
    private String amount;
    private String currency;
    private String card;
    @ManyToOne
    private Client client;

    public Transaction() {
    }

    public Transaction(String place, String amount, String currency, String card, Client client) {
        this.place = place;
        this.amount = amount;
        this.currency = currency;
        this.card = card;
        this.client = client ;
    }
}
