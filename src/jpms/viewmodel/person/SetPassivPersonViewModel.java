package jpms.viewmodel.person;

import com.google.inject.Inject;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.Person;
import jpms.services.IPersonService;

/**
 *
 * @author m.elz
 */
public class SetPassivPersonViewModel {
    
    @Inject
    private IPersonService personService;
    
    private Person selectedPerson;
    private ObservableList<Person> activeMemberList;
    private ObservableList<Person> passiveMemberList;
        
    public void SetPersonPassive(){
        if(getSelectedPerson() != null){
            getSelectedPerson().setActiv(false);
            
            personService.save(getSelectedPerson());
            
            reloadActiveMembers();
            reloadPassiveMembers();
        }
    }
    
    public void SetPersonAcive(){
        if(getSelectedPerson() != null){
            getSelectedPerson().setActiv(true);
            
            personService.save(getSelectedPerson());
            
            reloadActiveMembers();
            reloadPassiveMembers();
        }
    }
    
    private void reloadActiveMembers(){
        List<Person> activePersons = personService.getPersons(false);
        
        if(activeMemberList != null){
            activeMemberList.clear();
            activeMemberList.addAll(activePersons);
        }
        else{
            activeMemberList = FXCollections.observableArrayList(activePersons);
        }      
    }
    
    private void reloadPassiveMembers() {
       List<Person> passivePersons = personService.getPersons(true);
        
        if(passiveMemberList != null){
            passiveMemberList.clear();
            passiveMemberList.addAll(passivePersons);
        }
        else{
            passiveMemberList = FXCollections.observableArrayList(passivePersons);
        } 
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public ObservableList<Person> getActiveMemberList() {
        reloadActiveMembers();
        
        return activeMemberList;
    }

    public ObservableList<Person> getPassiveMemberList() {
        reloadPassiveMembers();
        
        return passiveMemberList;
    }
    
}
