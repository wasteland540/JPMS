package jpms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author m.elz
 */
@Entity
public class Communication implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Comm_ID")
    private Long id;
    
    @Enumerated(EnumType.ORDINAL)
    private CommunicationTyp communicationTyp;
    
    private String communicationValue;
    
    @ManyToOne
    @JoinColumn(name="Pers_ID", nullable = false)
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommunicationTyp getCommunicationTyp() {
        return communicationTyp;
    }

    public void setCommunicationTyp(CommunicationTyp communicationTyp) {
        this.communicationTyp = communicationTyp;
    }

    public String getCommunicationValue() {
        return communicationValue;
    }

    public void setCommunicationValue(String communicationValue) {
        this.communicationValue = communicationValue;
    }  

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    @Override
    public String toString(){
        return this.communicationValue;
    }
    
}
