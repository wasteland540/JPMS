package jpms.viewmodel.person;

import java.text.ParseException;
import jpms.model.AdditionalInfo;
import jpms.model.Adress;
import jpms.model.Communication;
import jpms.model.Person;
import jpms.view.person.AbstractPersonBaseViewModel;

/**
 *
 * @author m.elz
 */
public class NewPersonViewModel extends AbstractPersonBaseViewModel {
        
    public void addContact(){
        Communication communication = new Communication();
        communication.setCommunicationTyp(getCommunicationTyp());
        communication.setCommunicationValue(getCommunicationValue());
        
        getCommunications().add(communication);
        
        setCommunicationValue("");
    }
    
    public boolean save() throws ParseException{
        Person member = createPersonWithAllRelationships();
        
        return personService.save(member);
    }
    
    public void reset(){
        getCommunications().clear();
        
        setFirstname("");
        setLastname("");
        setAge("");
        setBirthday("");
        setEnterDate("");
        setStreet("");
        setZipcode("");
        setCity("");
        setFunctionRole("");
        setHonor("");
        setIsBirthdayDateVaild(Boolean.TRUE);
        setIsEnterDateVaild(Boolean.TRUE);
        setIsZipcodeVaild(Boolean.TRUE);
    }
    
    private Person createPersonWithAllRelationships() throws ParseException{
        Person person = new Person();
        person.setFirstname(getFirstname());
        person.setLastname(getLastname());
        
        if(getAge().equals("")){
            setAge("0");
        }
        
        person.setAge(Integer.parseInt(getAge()));
        person.setActiv(true);
        person.setBirthday(dateFormat.parse(getBirthday()));
        person.setDateOfEnters(dateFormat.parse(getEnterDate()));
        person.setSalutation(getSalutation());
        
        Adress address = new Adress();
        address.setStreet(getStreet());
        address.setZipcode(Integer.parseInt(getZipcode()));
        address.setCity(getCity());
        address.setPerson(person);
        person.setAdress(address);
        
        AdditionalInfo addInfo = new AdditionalInfo();
        addInfo.setFunctionRole(getFunctionRole());
        addInfo.setHonor(getHonor());
        addInfo.setVoice(getVoice());
        addInfo.setPerson(person);
        person.setAdditionalInfo(addInfo);
        
        for(Communication comm : getCommunications()){
            comm.setPerson(person);
        }
        person.getCommunications().addAll(getCommunications());
                              
        getChoir().getPersons().add(person);
        person.getPersonGroups().add(getChoir());
                
        return person;
    }
        
}
