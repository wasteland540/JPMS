package jpms.view;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jpms.model.PersonGroup;
import jpms.viewmodel.MainViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class MainView implements Initializable, IBasicView {

    @Inject
    private MainViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private TextField searchFiel;
    
    @FXML
    private TableView membersTable;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note: do nothing here! because we get wrong handling by guice...
    }    
    
    @Override
    public void payloadBindings() {
        
    }
    
    @Override
    public Node getView(){
        return view;
    }

    @FXML
    private void handleSearchTextChangedAction(Event event){
        
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
    private void handleMembersDeleteMenuAction(ActionEvent event){

    }
    
    @FXML
    private void handleDuesNewMenuAction(ActionEvent event){

    }
    
    @FXML
    private void handleDuesEditMenuAction(ActionEvent event){

    }
    
    @FXML
    private void handleDuesDeleteMenuAction(ActionEvent event){

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
