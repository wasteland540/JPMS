package jpms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author m.elz
 */
@Entity
public class Adress implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Adress_ID")
    private Long id;
    
    private String street;
    
    private int zipcode;
    
    private String city;
    
    @Enumerated(EnumType.ORDINAL)
    private CountryTyp country = CountryTyp.Germany;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adress")
    private Person person;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryTyp getCountry() {
        return country;
    }

    public void setCountry(CountryTyp country) {
        this.country = country;
    }  
  
    public Person getPerson() {        
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
