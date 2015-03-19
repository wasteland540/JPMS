package jpms.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author m.elz
 */
@Entity
@NamedQueries({@NamedQuery(name = Fee.getDuesByPersonId,
                          query = "SELECT f FROM Fee f WHERE f.person.id = :personId"),
               @NamedQuery(name = Fee.getFeeById,
                           query = "SELECT f FROM Fee f WHERE f.id = :id")})
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String getDuesByPersonId = "getDuesByPersonId";
    public static final String getFeeById = "getFeeById";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Fee_ID")
    private Long id;

    private double amount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date settledAt;
    
    @ManyToOne
    @JoinColumn(name="Pers_ID", nullable = false)
    private Person person;   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSettledAt() {
        return settledAt;
    }

    public void setSettledAt(Date settledAt) {
        this.settledAt = settledAt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString(){
        return String.format("%s € (%s)", this.amount, dateFormat.format(this.settledAt));
    }
}
