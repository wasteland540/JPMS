package jpms.view;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jpms.viewmodel.LoginViewModel;

/**
 *
 * @author m.elz
 */
public class LoginView implements Initializable, IBasicView {
    
    @Inject
    private LoginViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private TextField loginnameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorMsgLbl;
            
    @FXML
    private void handleLoginBtnAction(ActionEvent event) {        
        viewModel.login();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    //needed, because in that moment, where the login view is loaded guice, guice did not loaded the viewmodel and its null.
    //we will see, if this methode in antoher views needed...
    //i think it is so, because, all view will be loaded at the start by guice!
    @Override
    public void payloadBindings(){
        //set bindings       
        viewModel.loginnameProperty().bindBidirectional(loginnameField.textProperty());
        viewModel.passwordProperty().bindBidirectional(passwordField.textProperty());
        
        errorMsgLbl.visibleProperty().bind(viewModel.isLoginOkProperty());
    }
    
    @Override
    public Node getView(){        
        return view;
    }
    
}
