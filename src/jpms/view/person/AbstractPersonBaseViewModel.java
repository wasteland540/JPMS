package jpms.view.person;

import com.google.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.Communication;
import jpms.model.CommunicationTyp;
import jpms.model.PersonGroup;
import jpms.model.SalutationType;
import jpms.model.VoiceType;
import jpms.services.IChoirService;
import jpms.services.IPersonService;
import jpms.util.RegExValidator;
import jpms.viewmodel.AbstractBaseViewModel;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 *
 * @author m.elz
 */
public class AbstractPersonBaseViewModel extends AbstractBaseViewModel {
    
    public enum DateType {
        BIRTHDAY,
        ENTER_DATE;
    }
    
    @Inject
    protected IChoirService choirService;
    
    @Inject
    protected IPersonService personService;
    
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty birthday = new SimpleStringProperty();
    private final StringProperty enterDate = new SimpleStringProperty();
    private final StringProperty street = new SimpleStringProperty();
    private final StringProperty zipcode = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty functionRole = new SimpleStringProperty();
    private final StringProperty honor = new SimpleStringProperty();
    private final StringProperty communicationValue = new SimpleStringProperty();
    private final BooleanProperty isBirthdayDateVaild = new SimpleBooleanProperty(false);
    private final BooleanProperty isEnterDateVaild = new SimpleBooleanProperty(false);
    private final BooleanProperty isZipcodeVaild = new SimpleBooleanProperty(false);

    private SalutationType salutation;
    private VoiceType voice;
    private PersonGroup choir;
    private CommunicationTyp communicationTyp;
    
    private ObservableList<PersonGroup> choirlist;
    private ObservableList<Communication> communications = FXCollections.observableArrayList();
    
    protected final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    
    public void isVaildDate(DateType type){
        boolean isVaild;
                
        switch(type){
            case BIRTHDAY:
                isVaild = RegExValidator.isVaildDate(birthday.get());
                isBirthdayDateVaild.setValue(isVaild);
                break;
                
            case ENTER_DATE:
                isVaild = RegExValidator.isVaildDate(enterDate.get());
                isEnterDateVaild.setValue(isVaild);
                break;
        }
    }
    
    public void vaildateZipcode(){
        boolean isVaild = RegExValidator.isVaildZipcode(zipcode.get());
        
        isZipcodeVaild.setValue(isVaild);
    }
    
    public void calculateAge() throws ParseException{
        if(birthday.get() != null  && !birthday.get().equals("")){
            Date date = dateFormat.parse(birthday.get());

            LocalDate birthdate = new LocalDate(date.getTime());
            LocalDate sysDate = new LocalDate();

            Years ageObj = Years.yearsBetween(birthdate, sysDate);

            age.setValue("" + ageObj.getYears());
        }
    }
    
    public ObservableList<PersonGroup> getChoirlist() {
        reloadChoirList();
        
        return choirlist;
    }
    
    public void reloadChoirList(){
        List<PersonGroup> choirs = choirService.getChoirs();
        
        if(choirlist != null){
            choirlist.clear();
            choirlist.addAll(choirs);
        }
        else{
            choirlist = FXCollections.observableArrayList(choirs);
        }
    }
    
    public ObservableList<Communication> getCommunications(){        
        return communications;
    }
    
    public void setCommunications(ObservableList<Communication> communications){        
        this.communications = communications;
    }
    
    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String value) {
        firstname.set(value);
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }
    
    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String value) {
        lastname.set(value);
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }
    
    public String getAge() {
        return age.get();
    }

    public void setAge(String value) {
        age.set(value);
    }

    public StringProperty ageProperty() {
        return age;
    }
    
    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String value) {
        birthday.set(value);
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }
    
    public String getEnterDate() {
        return enterDate.get();
    }

    public void setEnterDate(String value) {
        enterDate.set(value);
    }

    public StringProperty enterDateProperty() {
        return enterDate;
    }
    
    public String getStreet() {
        return street.get();
    }

    public void setStreet(String value) {
        street.set(value);
    }

    public StringProperty streetProperty() {
        return street;
    }
    
    public String getZipcode() {
        return zipcode.get();
    }

    public void setZipcode(String value) {
        zipcode.set(value);
    }

    public StringProperty zipcodeProperty() {
        return zipcode;
    }
    
    public String getCity() {
        return city.get();
    }

    public void setCity(String value) {
        city.set(value);
    }

    public StringProperty cityProperty() {
        return city;
    }
    
    public String getFunctionRole() {
        return functionRole.get();
    }

    public void setFunctionRole(String value) {
        functionRole.set(value);
    }

    public StringProperty functionRoleProperty() {
        return functionRole;
    }
    
    public String getHonor() {
        return honor.get();
    }

    public void setHonor(String value) {
        honor.set(value);
    }

    public StringProperty honorProperty() {
        return honor;
    }
    public String getCommunicationValue() {
        return communicationValue.get();
    }

    public void setCommunicationValue(String value) {
        communicationValue.set(value);
    }

    public StringProperty communicationValueProperty() {
        return communicationValue;
    }
    
    public boolean isIsBirthdayDateVaild() {
        return isBirthdayDateVaild.get();
    }

    public void setIsBirthdayDateVaild(boolean value) {
        isBirthdayDateVaild.set(value);
    }

    public BooleanProperty isBirthdayDateVaildProperty() {
        return isBirthdayDateVaild;
    }
    
    public boolean isIsEnterDateVaild() {
        return isEnterDateVaild.get();
    }

    public void setIsEnterDateVaild(boolean value) {
        isEnterDateVaild.set(value);
    }

    public BooleanProperty isEnterDateVaildProperty() {
        return isEnterDateVaild;
    }
    
    public boolean isIsZipcodeVaild() {
        return isZipcodeVaild.get();
    }

    public void setIsZipcodeVaild(boolean value) {
        isZipcodeVaild.set(value);
    }

    public BooleanProperty isZipcodeVaildProperty() {
        return isZipcodeVaild;
    }
    
    public SalutationType getSalutation() {
        return salutation;
    }

    public void setSalutation(SalutationType salutation) {
        this.salutation = salutation;
    }

    public VoiceType getVoice() {
        return voice;
    }

    public void setVoice(VoiceType voice) {
        this.voice = voice;
    }

    public PersonGroup getChoir() {
        return choir;
    }

    public void setChoir(PersonGroup choir) {
        this.choir = choir;
    }
    
    public CommunicationTyp getCommunicationTyp() {
        return communicationTyp;
    }

    public void setCommunicationTyp(CommunicationTyp communicationTyp) {
        this.communicationTyp = communicationTyp;
    }
    
}
