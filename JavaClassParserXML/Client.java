package JavaClassParserXML;

import javax.persistence.*;

@Entity
public class Client {
    private String firstName;
    private String lastName;
    private String middleName;
    @Id
    private String inn ;


    public Client() {
    }

    public Client(String firstName, String lastName, String middleName, String inn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.inn = inn;
    }


    public String getInn() {
        return inn;
    }
}

