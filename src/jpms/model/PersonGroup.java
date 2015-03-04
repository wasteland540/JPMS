package jpms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author m.elz
 */
@Entity
@NamedQueries({@NamedQuery(name = PersonGroup.findGroupByName,
                           query = "SELECT pg FROM PersonGroup pg WHERE pg.name = :groupName")})
public class PersonGroup implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final String findGroupByName = "findGroupByName";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersGroup_ID")
    private Long id;

    private String name;
    
    @ManyToMany(mappedBy = "personGroups")
    private List<Person> persons;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        if(persons == null){
            persons = new ArrayList<>();
        }
        
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
}
