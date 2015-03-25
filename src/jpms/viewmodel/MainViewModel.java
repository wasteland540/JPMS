package jpms.viewmodel;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.Communication;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.services.IChoirService;
import jpms.services.IPersonService;
import jpms.util.reports.DefaultMemberlistReport;
import jpms.util.reports.helperbeans.ReportContact;
import jpms.util.reports.helperbeans.ReportPerson;
import jpms.view.account.ChangePasswordView;
import jpms.view.account.DeleteUserView;
import jpms.view.account.NewUserView;
import jpms.view.choir.DeleteChoirView;
import jpms.view.choir.EditChoirView;
import jpms.view.choir.NewChoirView;
import jpms.view.dues.DuesView;
import jpms.view.person.EditPersonView;
import jpms.view.person.NewPersonView;
import jpms.view.person.SetPassivPersonView;

/**
 *
 * @author m.elz
 */
@Singleton
public class MainViewModel extends AbstractBaseViewModel {
    
    @Inject
    private IChoirService choirService;
    
    @Inject
    private IPersonService personService;
    
    private final String newUserStageKey = "newUserStage";
    private final String deleteUserStageKey = "deleteUserStage";
    private final String changePasswordStageKey = "changePasswordStageKey";
    private final String newChoirStageKey = "newChoirStage";
    private final String editChoirStageKey = "editChoirStageKey";
    private final String deleteChoirStageKey = "deleteChoirStageKey";
    private final String newPersonStageKey = "newPersonStageKey";
    private final String editPersonStageKey = "editPersonStageKey";
    private final String setPassivePersonStageKey = "setPassivePersonStageKey";
    private final String duesStageKey = "duesStageKey";
    
    private PersonGroup selectedChoir;
    private ObservableList<PersonGroup> choirs;
    private Person selectedPerson;
    private ObservableList<Person> persons;
    
    private final StringProperty searchText = new SimpleStringProperty();
    private final StringProperty selectedZipcode = new SimpleStringProperty();
    private final StringProperty selectedStreet = new SimpleStringProperty();
    private final StringProperty selectedCity = new SimpleStringProperty();
    private final StringProperty selectedVoice = new SimpleStringProperty();
    private final StringProperty selectedFunction = new SimpleStringProperty();
    private final StringProperty selectedHonor = new SimpleStringProperty();
    private final StringProperty selectedBirthday = new SimpleStringProperty();
    private final StringProperty selectedEntersDate = new SimpleStringProperty();
    
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public void showNewUserView(){
        showModualStage(NewUserView.class, newUserStageKey, "New User");
    }
    
    public void showDeleteUserView(){
        showModualStage(DeleteUserView.class, deleteUserStageKey, "Delete User");
    }
    
    public void showChangePasswordView(){
        showModualStage(ChangePasswordView.class, changePasswordStageKey, "Change Password");
    }
    
    public void newChoir(){
        showModualStage(NewChoirView.class, newChoirStageKey, "New Choir");
    }
    
    public void editChoir(){
        showModualStage(EditChoirView.class, editChoirStageKey, "Rename Choir");
    }
    
    public void deleteChoir(){
        showModualStage(DeleteChoirView.class, deleteChoirStageKey, "Delete Choir");
    }
    
    public void newMember(){
        showModualStage(NewPersonView.class, newPersonStageKey, "New Member");
    }

    public void editMember() {
        showModualStage(EditPersonView.class, editPersonStageKey, "Edit Member");
    }
    
    public void setMemberPassive(){
        showModualStage(SetPassivPersonView.class, setPassivePersonStageKey, "Set Member Passive");
    }
    
    public void editDues() {
        showModualStage(DuesView.class, duesStageKey, "Dues");
    }
        
    public void showPdf(){
        List<Person> allPersons = personService.getPersons(false);
        List<ReportPerson> memberlistForReport = createReportList(allPersons);
        
        DefaultMemberlistReport report = new DefaultMemberlistReport();
        report.show(memberlistForReport);
    }
    
    public void filterPersons(String searchText){
        //filter about firstname, lastname, zipcode, city
                
        if(persons != null && getSelectedChoir() != null){
            List<Person> filterdPersons = personService.getPersons(false, getSelectedChoir().getId(), searchText);
            
            persons.clear();
            persons.addAll(filterdPersons);
        }
    }

    public PersonGroup getSelectedChoir() {
        return selectedChoir;
    }

    public void setSelectedChoir(PersonGroup selectedChoir) {
        this.selectedChoir = selectedChoir;        
    }

    public ObservableList<PersonGroup> getChoirs() {
        reloadChoirs();
        
        return choirs;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
        
        refreshSelectedValues();
    }

    public ObservableList<Person> getPersons() {
        loadPersonlistByChoir();
        
        return persons;
    }
    
    public String getSearchText() {
        return searchText.get();
    }

    public void setSearchText(String value) {
        searchText.set(value);
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }
    
    public String getSelectedEntersDate() {
        return selectedEntersDate.get();
    }

    public void setSelectedEntersDate(String value) {
        selectedEntersDate.set(value);
    }

    public StringProperty selectedEntersDateProperty() {
        return selectedEntersDate;
    }

    public String getSelectedBirthday() {
        return selectedBirthday.get();
    }

    public void setSelectedBirthday(String value) {
        selectedBirthday.set(value);
    }

    public StringProperty selectedBirthdayProperty() {
        return selectedBirthday;
    }

    public String getSelectedHonor() {
        return selectedHonor.get();
    }

    public void setSelectedHonor(String value) {
        selectedHonor.set(value);
    }

    public StringProperty selectedHonorProperty() {
        return selectedHonor;
    }

    public String getSelectedFunction() {
        return selectedFunction.get();
    }

    public void setSelectedFunction(String value) {
        selectedFunction.set(value);
    }

    public StringProperty selectedFunctionProperty() {
        return selectedFunction;
    }

    public String getSelectedVoice() {
        return selectedVoice.get();
    }

    public void setSelectedVoice(String value) {
        selectedVoice.set(value);
    }

    public StringProperty selectedVoiceProperty() {
        return selectedVoice;
    }

    public String getSelectedCity() {
        return selectedCity.get();
    }

    public void setSelectedCity(String value) {
        selectedCity.set(value);
    }

    public StringProperty selectedCityProperty() {
        return selectedCity;
    }

    public String getSelectedStreet() {
        return selectedStreet.get();
    }

    public void setSelectedStreet(String value) {
        selectedStreet.set(value);
    }

    public StringProperty selectedStreetProperty() {
        return selectedStreet;
    }

    public String getSelectedZipcode() {
        return selectedZipcode.get();
    }

    public void setSelectedZipcode(String value) {
        selectedZipcode.set(value);
    }

    public StringProperty selectedZipcodeProperty() {
        return selectedZipcode;
    }
    
    private void reloadChoirs(){
        List<PersonGroup> choirList = choirService.getChoirs();
        
        if(choirs == null){
            choirs = FXCollections.observableArrayList(choirList);
        }
        else {
            choirs.clear();
            choirs.addAll(choirList);
        }
    }
    
    private void loadPersonlistByChoir(){
        if(getSelectedChoir() != null){
            List<Person> personList = personService.getPersons(false, getSelectedChoir().getId());
            
            if(persons == null){
                persons = FXCollections.observableArrayList(personList);
            }
            else {
                persons.clear();
                persons.addAll(personList);
            }
        }
    }
    
    private void refreshSelectedValues() {
        if(getSelectedPerson() != null){
            selectedBirthday.setValue(dateFormat.format(getSelectedPerson().getBirthday()));
            selectedEntersDate.setValue(dateFormat.format(getSelectedPerson().getDateOfEnters()));
            selectedZipcode.setValue("" + getSelectedPerson().getAdress().getZipcode());
            selectedStreet.setValue(getSelectedPerson().getAdress().getStreet());
            selectedCity.setValue(getSelectedPerson().getAdress().getCity());
            selectedVoice.setValue(getSelectedPerson().getAdditionalInfo().getVoice().name());
            selectedFunction.setValue(getSelectedPerson().getAdditionalInfo().getFunctionRole());
            selectedHonor.setValue(getSelectedPerson().getAdditionalInfo().getHonor());
        }
    }
       
    private List<ReportPerson> createReportList(List<Person> personList){
        List<ReportPerson> reportList = new ArrayList<>();
        
        for(Person p : personList){
            List<ReportContact> contacts = new ArrayList<>();
            
            for(Communication c : p.getCommunications()){
                contacts.add(new ReportContact(c.getCommunicationTyp().name(), c.getCommunicationValue()));
            }
            
            reportList.add(new ReportPerson(p.getPersonGroups().get(0).getName(), p.getFirstname(), p.getLastname(), "" + p.getAge(), dateFormat.format(p.getBirthday()), dateFormat.format(p.getDateOfEnters()), 
                                            p.getAdress().getStreet(), "" + p.getAdress().getZipcode(), p.getAdress().getCity(), 
                                            p.getAdditionalInfo().getVoice().name(), p.getAdditionalInfo().getFunctionRole(), p.getAdditionalInfo().getHonor(), 
                                            contacts));
        }
        
        return reportList;
    }
}
