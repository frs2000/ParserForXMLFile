package JavaClassParserXML;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TableAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String place;
    private String amount;
    private String currency;
    private String card;
    private String firstName;
    private String lastName;
    private String middleName;
    private String inn;

    public TableAll() {
    }

    public TableAll(String place, String amount, String currency, String card, String firstName, String lastName, String middleName, String inn) {
        this.place = place;
        this.amount = amount;
        this.currency = currency;
        this.card = card;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.inn = inn;
    }
}
