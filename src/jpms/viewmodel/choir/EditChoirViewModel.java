package jpms.viewmodel.choir;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.PersonGroup;
import jpms.services.IChoirService;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class EditChoirViewModel extends AbstractBaseViewModel {
    
    @Inject
    private IChoirService choirService;
    
    private final StringProperty newChoirName = new SimpleStringProperty();
    private final BooleanProperty isChoirNameTaken = new SimpleBooleanProperty();
    private final StringProperty oldChoirName = new SimpleStringProperty();
    
    private PersonGroup selectedChoir;
    private ObservableList<PersonGroup> choirlist;
    
    public void checkChoirName(){
        boolean isTaken = choirService.checkChoirName(newChoirName.get());
        
        if(isTaken){
            isChoirNameTaken.set(true);
        }
        else{
            isChoirNameTaken.set(false);
        }
    }
    
    public boolean rename(){
        return choirService.rename(selectedChoir.getId(), newChoirName.get());
    }
    
    public void reset(){
        newChoirName.setValue("");
    }
    
    public String getNewChoirName() {
        return newChoirName.get();
    }

    public void setNewChoirName(String value) {
        newChoirName.set(value);
    }

    public StringProperty newChoirNameProperty() {
        return newChoirName;
    }
    
    public boolean isIsChoirNameTaken() {
        return isChoirNameTaken.get();
    }

    public void setIsChoirNameTaken(boolean value) {
        isChoirNameTaken.set(value);
    }

    public BooleanProperty isChoirNameTakenProperty() {
        return isChoirNameTaken;
    }
    
    public String getOldChoirName() {
        return oldChoirName.get();
    }

    public void setOldChoirName(String value) {
        oldChoirName.set(value);
    }

    public StringProperty oldChoirNameProperty() {
        return oldChoirName;
    }
    
    public ObservableList getChoirlist() {
        reloadChoirList();
        
        return choirlist;
    }

    public PersonGroup getSelectedChoir() {
        return selectedChoir;
    }

    public void setSelectedChoir(PersonGroup selectedChoir) {
        if(selectedChoir != null){
            oldChoirName.setValue(selectedChoir.getName());
        }
        else {
            oldChoirName.setValue("");
        }
        
        this.selectedChoir = selectedChoir;
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
    
}
