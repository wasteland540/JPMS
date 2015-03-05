package jpms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author m.elz
 */
@Entity
public class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pers_ID")
    private Long id;
    
    private String salutation;
    private String lastname;
    private String firstname;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    private int age;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfEnters;
    
    private boolean activ = true;
    
    //relationships:
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    private List<Communication> communications;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Adress_ID")
    private Adress adress;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "person")
    private List<Fee> fees;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AddInfo_ID")
    private AdditionalInfo additionalInfo;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PERS_PERSGROUP", 
                joinColumns = {@JoinColumn(name="Pers_ID", referencedColumnName = "Pers_ID")},
                inverseJoinColumns = {@JoinColumn(name = "PersGroup_ID", referencedColumnName = "PersGroup_ID")}
    )
    private List<PersonGroup> personGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfEnters() {
        return dateOfEnters;
    }

    public void setDateOfEnters(Date dateOfEnters) {
        this.dateOfEnters = dateOfEnters;
    }

    public boolean isActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }
    
    public List<Communication> getCommunications() {
        if(communications == null){
            communications = new ArrayList<>();
        }
        
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }
    
    public Adress getAdress() {        
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public List<Fee> getFees() {
        if(fees == null){
            fees = new ArrayList<>();
        }
        
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }    

    public List<PersonGroup> getPersonGroups() {
        if(personGroups == null){
            personGroups = new ArrayList<>();
        }
        
        return personGroups;
    }

    public void setPersonGroups(List<PersonGroup> personGroups) {
        this.personGroups = personGroups;
    }   
    
    @Override
    public String toString(){
        return String.format("%s, %s", this.lastname, this.firstname);
    }
    
}
