package jpms.view.account;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.account.ChangePasswordViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class ChangePasswordView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private ChangePasswordViewModel viewModel;
    
    @FXML 
    private Node view;
    
    @FXML
    private PasswordField oldPasswordField;
    
    @FXML
    private PasswordField newPasswordField;
    
    @FXML
    private PasswordField newPasswordField2;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Label errorMsgLbl;

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
        viewModel.oldPasswordProperty().bindBidirectional(oldPasswordField.textProperty());
        viewModel.newPasswordProperty().bindBidirectional(newPasswordField.textProperty());
        viewModel.newPassword2Property().bindBidirectional(newPasswordField2.textProperty());
        
        errorMsgLbl.visibleProperty().bind(viewModel.isNewPasswordEqualProperty());
        saveBtn.disableProperty().bind(viewModel.isNewPasswordEqualProperty());
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {        
        //newPassword1TextChanged
        newPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.comparePasswords();
            }
        });
        
        //newPassword2TextChanged
        newPasswordField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.comparePasswords();
            }
        });
    }
    
    @FXML
    private void handleSaveBtnAction(ActionEvent event){
        boolean changed = viewModel.save();
        
        if(changed){            
            //clear field, for reuse
            viewModel.reset();
            
            // close the dialog.
            Stage stage  = (Stage) getView().getScene().getWindow();
            stage.close();            
        }
        else {
            viewModel.showDialog(SimpleDialog.class, DialogIcon.WARN, "Password not changed! Maybe your old password is wrong?");
        }
    }
    
}
