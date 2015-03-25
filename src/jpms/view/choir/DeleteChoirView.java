package jpms.view.choir;

import com.google.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.util.dialogs.RefreshMainViewAction;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.view.IMainViewRefreshable;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.choir.DeleteChoirViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class DeleteChoirView extends AbstractView implements Initializable, IBasicView, IMainViewRefreshable {
     
    @Inject
    private DeleteChoirViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ListView<PersonGroup> choirListView;
    
    @FXML
    private ListView<Person> personListView;
    
    @FXML
    private Button deleteBtn;
    
    private RefreshMainViewAction refreshMainViewAction;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note: do nothing here! because we get wrong handling by guice...
    }    

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void payloadBindings() {
        deleteBtn.disableProperty().bind(viewModel.hasLinkedPersonsProperty());
        
        choirListView.setItems(viewModel.getChoirlist());
        
        List<Person> persons = new ArrayList<>();
        personListView.setItems(FXCollections.observableArrayList(persons));
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        //choir list selection
        choirListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        choirListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonGroup>() {
            @Override
            public void changed(ObservableValue<? extends PersonGroup> ov, PersonGroup oldValue, PersonGroup newValue) {
                viewModel.setSelectedChoir(newValue);
                
                if(viewModel.getSelectedChoir() != null){                    
                    List<Person> linkedPersons = viewModel.getSelectedChoir().getPersons();

                    personListView.getItems().clear();
                    personListView.getItems().addAll(linkedPersons);
                }
            }
        });
    }

    @FXML
    private void handleDeleteBtnAction(ActionEvent event) throws IOException{
        boolean deleted = viewModel.delete();
        
        if(deleted){
            viewModel.reloadChoirList();
            
            refreshMainViewAction.Refresh();
            
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.INFO, "Choir deleted!");
        }
        else {
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.WARN, "Ups, sorry! Something went wrong. Please try again!");
        }
    }

    @Override
    public void setRefreshAction(RefreshMainViewAction refreshMainViewAction) {
        this.refreshMainViewAction = refreshMainViewAction;
    }
}
