package jpms.viewmodel.choir;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
public class DeleteChoirViewModel extends AbstractBaseViewModel {
    
    @Inject
    private IChoirService choirService;
    
    private PersonGroup selectedChoir;
    private ObservableList<PersonGroup> choirlist;
    
    private final BooleanProperty hasLinkedPersons = new SimpleBooleanProperty();
      
    public boolean delete() throws IOException{
        boolean deleted = choirService.delete(getSelectedChoir().getId());
        
        return deleted;
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

    public PersonGroup getSelectedChoir() {
        //refresh selected choir, to get actual person list!
        if(selectedChoir != null){
            selectedChoir = choirService.getChoirById(selectedChoir.getId());
        }
        
        return selectedChoir;
    }

    public void setSelectedChoir(PersonGroup selectedChoir) {
        checkLinkedPersons(selectedChoir);
        
        this.selectedChoir = selectedChoir;
    }
    
    public ObservableList<PersonGroup> getChoirlist() {
        reloadChoirList();
        
        return choirlist;
    }
    
    private void checkLinkedPersons(PersonGroup selectedChoir1) {
        if (selectedChoir1 != null) {
            if (selectedChoir1.getPersons() != null && selectedChoir1.getPersons().size() > 0) {
                hasLinkedPersons.setValue(Boolean.TRUE);
            } else {
                hasLinkedPersons.setValue(Boolean.FALSE);
            }
        } else {
            hasLinkedPersons.setValue(Boolean.FALSE);
        }
    }

    public void setChoirlist(ObservableList<PersonGroup> choirlist) {
        this.choirlist = choirlist;
    }
    
    public boolean isHasLinkedPersons() {
        return hasLinkedPersons.get();
    }

    public void setHasLinkedPersons(boolean value) {
        hasLinkedPersons.set(value);
    }

    public BooleanProperty hasLinkedPersonsProperty() {
        return hasLinkedPersons;
    }
    
}
