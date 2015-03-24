package jpms.viewmodel.choir;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jpms.services.IChoirService;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class NewChoirViewModel extends AbstractBaseViewModel {
    
    @Inject
    private IChoirService choirService;
    
    private final StringProperty choirName = new SimpleStringProperty();
    private final BooleanProperty isChoirNameTaken = new SimpleBooleanProperty();

    public void checkChoirName(){
        boolean isTaken = choirService.checkChoirName(choirName.get());
        
        if(isTaken){
            isChoirNameTaken.set(true);
        }
        else{
            isChoirNameTaken.set(false);
        }
    }
    
    public boolean create() throws IOException{
        boolean created = choirService.createChoir(choirName.get());
        
        return created;
    }
    
    public void reset(){
        choirName.setValue("");
        
        isChoirNameTaken.setValue(Boolean.FALSE);
    }
    
    public String getChoirName() {
        return choirName.get();
    }

    public void setChoirName(String value) {
        choirName.set(value);
    }

    public StringProperty choirNameProperty() {
        return choirName;
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
}
