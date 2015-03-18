package jpms.view.person;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import jpms.model.Person;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.viewmodel.person.SetPassivPersonViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class SetPassivPersonView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private SetPassivPersonViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ListView<Person> activeListView;
    
    @FXML
    private ListView<Person> passiveListView;

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
        activeListView.setItems(viewModel.getActiveMemberList());
        passiveListView.setItems(viewModel.getPassiveMemberList());
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        //TODO: change listeners
        activeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        activeListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> ov, Person oldValue, Person newValue) {
                viewModel.setSelectedPerson(newValue);
                passiveListView.getSelectionModel().select(-1);
            }
        });
        
        passiveListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        passiveListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> ov, Person oldValue, Person newValue) {
                viewModel.setSelectedPerson(newValue);
                activeListView.getSelectionModel().select(-1);
            }
        });
    }
    
    @FXML
    private void handleSetPassivAction(ActionEvent event){
        viewModel.SetPersonPassive();
    }
    
    @FXML
    private void handleSetActiveAction(ActionEvent event){
        viewModel.SetPersonAcive();
    }
}
