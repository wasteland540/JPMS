package jpms.viewmodel.dues;

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
import jpms.model.Fee;
import jpms.model.Person;
import jpms.services.IDuesService;
import jpms.services.IPersonService;
import jpms.util.RegExValidator;

/**
 *
 * @author m.elz
 */
public class DuesViewModel {
    
    @Inject
    private IPersonService personService; 
    
    @Inject
    private IDuesService duesService;
    
    private final StringProperty amount = new SimpleStringProperty();
    private final StringProperty settledAt = new SimpleStringProperty();
    private final BooleanProperty isDateVaild = new SimpleBooleanProperty();
    
    private Person selectedPerson;
    private ObservableList<Person> memberList;
    private Fee selectedFee;
    private ObservableList<Fee> feeList;
    
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
    public void validateDate(){
        boolean isVaild;
               
        isVaild = RegExValidator.isVaildDate(settledAt.get());
        isDateVaild.setValue(isVaild);
    }
    
    public void addFee() throws ParseException {
        if(getSelectedPerson() != null){
            double amountVal = Double.parseDouble(amount.get().replace(',', '.'));
            Date settledAtVal = dateFormat.parse(settledAt.get());

            Fee fee = new Fee();
            fee.setAmount(amountVal);
            fee.setSettledAt(settledAtVal);
            fee.setPerson(getSelectedPerson());
            
            duesService.addFee(fee);
            
            amount.setValue("");
            settledAt.setValue("");
            
            reloadFeeList();
        }
    }
    
    public void removeFee(){
        if(getSelectedFee() != null){
            duesService.deleteFee(getSelectedFee());
            
            reloadFeeList();
        }
    }
    
    private void reloadMemberList(){
        List<Person> persons = personService.getPersons(false);
        
        if(memberList == null){
            memberList = FXCollections.observableArrayList(persons);
        }
        else {
           memberList.clear();
           memberList.addAll(persons);
        }
    }
    
    private void reloadFeeList() {
        if(getSelectedPerson() != null){
            List<Fee> dues = duesService.getDues(getSelectedPerson().getId());
            
            if(feeList == null){
                feeList = FXCollections.observableArrayList(dues);
            }
            else {
                feeList.clear();
                feeList.addAll(dues);
            }
        }
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
        
        reloadFeeList();
    }

    public Fee getSelectedFee() {
        return selectedFee;
    }

    public void setSelectedFee(Fee selectedFee) {
        this.selectedFee = selectedFee;
    }

    public ObservableList<Fee> getFeeList() {
        reloadFeeList();
        
        return feeList;
    }
    
    public ObservableList<Person> getMemberList() {
        reloadMemberList();
        
        return memberList;
    }    
    
    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String value) {
        amount.set(value);
    }

    public StringProperty amountProperty() {
        return amount;
    }
    
    public String getSettledAt() {
        return settledAt.get();
    }

    public void setSettledAt(String value) {
        settledAt.set(value);
    }

    public StringProperty settledAtProperty() {
        return settledAt;
    }
    
    public boolean isIsDateVaild() {
        return isDateVaild.get();
    }

    public void setIsDateVaild(boolean value) {
        isDateVaild.set(value);
    }

    public BooleanProperty isDateVaildProperty() {
        return isDateVaild;
    }
}
