package jpms.viewmodel.person;

import java.text.ParseException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.Communication;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.view.person.AbstractPersonBaseViewModel;

/**
 *
 * @author m.elz
 */
public class EditPersonViewModel extends AbstractPersonBaseViewModel {
    
    private ObservableList<Person> memberList;
    private Person selectedMember;
    private ObservableList<PersonGroup> choirs;
    private PersonGroup selectedChoir;
    private Communication selectedCommunication;
    
    public void addChoir() {
        if(getChoir() != null && getSelectedMember() != null){
            getSelectedMember().getPersonGroups().add(getChoir());           
            
            reloadChoirs();
        }
    }

    public void removeChoir() {
        if(getSelectedChoir()!= null && getSelectedMember() != null){
            getSelectedMember().getPersonGroups().remove(getSelectedChoir());
            
            reloadChoirs();
        }
    }

    public void addContact() {
        if(getCommunications()!= null && getSelectedMember() != null){
            if(getCommunicationTyp() != null && !getCommunicationValue().equals("")){
                Communication communication = new Communication();
                communication.setCommunicationTyp(getCommunicationTyp());
                communication.setCommunicationValue(getCommunicationValue());
                communication.setPerson(getSelectedMember());
                
                getSelectedMember().getCommunications().add(communication);
                
                setCommunicationValue("");
                reloadCommunications();
            }                
        }
    }

    public void removeContact() {
        if(getSelectedCommunication()!= null && getSelectedMember() != null){
            getSelectedMember().getCommunications().remove(getSelectedCommunication());
            
            reloadCommunications();
        }
    }

    public void save() throws ParseException {
        //update person data
        getSelectedMember().setAge(Integer.parseInt(ageProperty().get()));
        getSelectedMember().setBirthday(dateFormat.parse(birthdayProperty().get()));
        getSelectedMember().setDateOfEnters(dateFormat.parse(enterDateProperty().get()));
        getSelectedMember().setFirstname(firstnameProperty().get());
        getSelectedMember().setLastname(lastnameProperty().get());
        getSelectedMember().setSalutation(getSalutation());
        
        //update address data
        getSelectedMember().getAdress().setCity(cityProperty().get());
        getSelectedMember().getAdress().setStreet(streetProperty().get());
        getSelectedMember().getAdress().setZipcode(Integer.parseInt(zipcodeProperty().get()));
        
        //update additional info
        getSelectedMember().getAdditionalInfo().setVoice(getVoice());
        getSelectedMember().getAdditionalInfo().setFunctionRole(functionRoleProperty().get());
        getSelectedMember().getAdditionalInfo().setHonor(honorProperty().get());        
        
        //persit changes
        personService.save(getSelectedMember());
        
        reloadMemberList();
        
        //inform user
        showDialog(SimpleDialog.class, DialogIcon.INFO, "Changes saved!");
    }

    public ObservableList<Person> getMemberList() {
        reloadMemberList();
        
        return memberList;
    }

    public void reloadMemberList(){
        List<Person> persons = personService.getPersons();
        
        if(memberList != null){
            memberList.clear();
            memberList.addAll(persons);
        }
        else{
            memberList = FXCollections.observableArrayList(persons);
        }
    }
    
    public Person getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(Person selectedMember) {
        this.selectedMember = selectedMember;
        
        refreshSelectedValues();
    }

    public ObservableList<PersonGroup> getChoirs() {
        reloadChoirs();
        
        return choirs;
    }

    public PersonGroup getSelectedChoir() {
        return selectedChoir;
    }

    public void setSelectedChoir(PersonGroup selectedChoir) {
        this.selectedChoir = selectedChoir;
    }
    
    public Communication getSelectedCommunication() {
        return selectedCommunication;
    }

    public void setSelectedCommunication(Communication selectedCommunication) {
        this.selectedCommunication = selectedCommunication;
    }
    
    private void reloadChoirs(){
        if(selectedMember != null){
            if(choirs != null){
                choirs.clear();
                choirs.addAll(selectedMember.getPersonGroups());
            }
            else{
                choirs = FXCollections.observableArrayList(selectedMember.getPersonGroups());
            }
        }
        else {
            choirs = FXCollections.observableArrayList();
        }
    }
    
    private void reloadCommunications() {
        if(selectedMember != null){
            if(getCommunications() != null){
                getCommunications().clear();
                getCommunications().addAll(selectedMember.getCommunications());
            }
            else{
                //communications = FXCollections.observableArrayList(selectedMember.getCommunications());
                setCommunications(FXCollections.observableArrayList(selectedMember.getCommunications()));
            }
        }
        else {
            //communications = FXCollections.observableArrayList();
            //setCommunications(FXCollections.observableArrayList<Communication>());
        }
    }
    
    private void refreshSelectedValues(){
        if(selectedMember != null){
            setFirstname(selectedMember.getFirstname());
            setLastname(selectedMember.getLastname());
            setAge("" + selectedMember.getAge());
            setBirthday(dateFormat.format(selectedMember.getBirthday()));
            setEnterDate(dateFormat.format(selectedMember.getDateOfEnters()));
            setStreet(selectedMember.getAdress().getStreet());
            setZipcode("" + selectedMember.getAdress().getZipcode());
            setCity(selectedMember.getAdress().getCity());
            setFunctionRole(selectedMember.getAdditionalInfo().getFunctionRole());
            setHonor(selectedMember.getAdditionalInfo().getHonor());

            getCommunications().clear();
            getCommunications().addAll(selectedMember.getCommunications());

            reloadChoirs();
        }
        else {
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
            
            getCommunications().clear();
            choirs.clear();
            
            setCommunicationValue("");
        }
    }
}
