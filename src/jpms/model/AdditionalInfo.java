package jpms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class AdditionalInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddInfo_ID")
    private Long id;
    
    private VoiceType voice;
    
    private String functionRole;
    
    private String honor;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "additionalInfo")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoiceType getVoice() {
        return voice;
    }

    public void setVoice(VoiceType voice) {
        this.voice = voice;
    }

    public String getFunctionRole() {
        return functionRole;
    }

    public void setFunctionRole(String functionRole) {
        this.functionRole = functionRole;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }   
    
}
