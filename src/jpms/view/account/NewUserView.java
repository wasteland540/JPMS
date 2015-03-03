package jpms.view.account;

import com.google.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.viewmodel.account.NewUserViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class NewUserView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private NewUserViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private TextField loginnameField;
    
    @FXML
    private PasswordField password1Field;
    
    @FXML
    private PasswordField password2Field;
    
    @FXML
    private Label errorMsgLbl;
    
    @FXML
    private Label errorMsgLbl2;
    
    @FXML
    private Button createUserBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note: do nothing here! because we get wrong handling by guice...
    }    

    @Override
    public void payloadBindings() {
        viewModel.loginnameProperty().bindBidirectional(loginnameField.textProperty());
        viewModel.password1Property().bindBidirectional(password1Field.textProperty());
        viewModel.password2Property().bindBidirectional(password2Field.textProperty());
        
        errorMsgLbl.visibleProperty().bind(viewModel.isLoginnameTakenProperty());
        errorMsgLbl2.visibleProperty().bind(viewModel.isPasswordEqualProperty());
        createUserBtn.disableProperty().bind(
                Bindings.or(errorMsgLbl.visibleProperty(), 
                            errorMsgLbl2.visibleProperty())
        );
                
        setupListeners();
    }
    
    @Override
    public Node getView() {
        return view;
    }
    
    @Override
    protected void setupListeners(){
        //loginTextChanged
        loginnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.checkLoginname();
            }
        });
        
        //password1TextChanged
        password1Field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.comparePasswords();
            }
        });
        
        //password2TextChanged
        password2Field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.comparePasswords();
            }
        });
    }
        
    @FXML
    private void handleCreateUserBtnAction(ActionEvent event) throws IOException{
        boolean created = viewModel.createUser();
        
        if(created){            
            //clear field, for reuse
            viewModel.reset();
            
            // close the dialog.
            Stage stage  = (Stage) getView().getScene().getWindow();
            stage.close();            
        }
    }
}
