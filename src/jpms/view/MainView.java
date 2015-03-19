package jpms.view;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.viewmodel.MainViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class MainView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private MainViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private TableView<Person> membersTable;
    
    @FXML
    private ComboBox<PersonGroup> choirCb;
    
    @FXML
    private TextField selectedZipcodeField;
    
    @FXML
    private TextField selectedStreetField;
    
    @FXML
    private TextField selectedCityField;
    
    @FXML
    private TextField selectedVoiceField;
    
    @FXML
    private TextField selectedFunctionField;
    
    @FXML
    private TextField selectedHonorField;
    
    @FXML
    private TextField selectedBirthdayField;
    
    @FXML
    private TextField selectedEntersDateField;
    
    @FXML
    private TableColumn lastnameCol;
    
    @FXML
    private TableColumn firstnameCol;
    
    @FXML
    private TableColumn ageCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note: do nothing here! because we get wrong handling by guice...
    }    
    
    @Override
    public Node getView(){
        return view;
    }
    
    @Override
    public void payloadBindings() {
        //bindings
        searchField.textProperty().bindBidirectional(viewModel.searchTextProperty());
        
        //binding detail properties
        selectedBirthdayField.textProperty().bind(viewModel.selectedBirthdayProperty());
        selectedEntersDateField.textProperty().bind(viewModel.selectedEntersDateProperty());
        selectedZipcodeField.textProperty().bind(viewModel.selectedZipcodeProperty());
        selectedStreetField.textProperty().bind(viewModel.selectedStreetProperty());
        selectedCityField.textProperty().bind(viewModel.selectedCityProperty());
        selectedVoiceField.textProperty().bind(viewModel.selectedVoiceProperty());
        selectedFunctionField.textProperty().bind(viewModel.selectedFunctionProperty());
        selectedHonorField.textProperty().bind(viewModel.selectedHonorProperty());
        
        //dynamic from database
        choirCb.getItems().addAll(viewModel.getChoirs());
        
        //table cell bindings
        lastnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        //selected choir changed
        choirCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonGroup>() {
            @Override
            public void changed(ObservableValue<? extends PersonGroup> ov, PersonGroup oldValue, PersonGroup newValue) {
                viewModel.setSelectedChoir(newValue);
                                
                membersTable.setItems(viewModel.getPersons());
            }
        });
        
        //search text changed
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {             
                viewModel.filterPersons(newValue);
                
                //TODO: evtl neu stetzten
            }
        });
        
        //selected person changed
        membersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        membersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        membersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> ov, Person oldValue, Person newValue) {
                viewModel.setSelectedPerson(newValue);
            }
        });
    }
        
    @FXML
    private void handleFileCreatePdfMenuAction(ActionEvent event){
        
    }
    
    @FXML
    private void handleFileCloseMenuAction(ActionEvent event){

    }
    
    @FXML
    private void handleChoirsNewMenuAction(ActionEvent event){
        viewModel.newChoir();
    }
    
    @FXML
    private void handleChoirsEditMenuAction(ActionEvent event){
        viewModel.editChoir();
    }
    
    @FXML
    private void handleChoirsDeleteMenuAction(ActionEvent event){
        viewModel.deleteChoir();
    }
    
    @FXML
    private void handleMembersNewMenuAction(ActionEvent event){
        viewModel.newMember();
    }
    
    @FXML
    private void handleMembersEditMenuAction(ActionEvent event){
        viewModel.editMember();
    }
    
    @FXML
    private void handleMembersSetPassiveMenuAction(ActionEvent event){
        viewModel.setMemberPassive();
    }
    
    @FXML
    private void handleDuesEditMenuAction(ActionEvent event){
        viewModel.editDues();
    }
    
    @FXML
    private void handleAccountNewUserMenuAction(ActionEvent event){
        viewModel.showNewUserView();
    }
    
    @FXML
    private void handleAccountDeleteUserMenuAction(ActionEvent event){
        viewModel.showDeleteUserView();
    }
    
    @FXML
    private void handleAccountChangePasswordMenuAction(ActionEvent event){
        viewModel.showChangePasswordView();
    }


}
