package jpms.view.choir;

import com.google.inject.Inject;
import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.choir.NewChoirViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class NewChoirView extends AbstractView implements Initializable, IBasicView {

    private final String infoDialogKey = "NewChoirView.infoDialogKey";
    private final String warnDialogKey = "NewChoirView.warnDialogKey";
    
    @Inject
    private NewChoirViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private TextField choirNameField;
    
    @FXML
    private Label errorMsgLbl;
    
    @FXML
    private Button createBtn;
    
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
        viewModel.choirNameProperty().bindBidirectional(choirNameField.textProperty());
        
        errorMsgLbl.visibleProperty().bind(viewModel.isChoirNameTakenProperty());
        createBtn.disableProperty().bind(errorMsgLbl.visibleProperty());
        
        setupListeners();
    }

    @Override
    protected void setupListeners() {
        //choirNameTextChanged
        choirNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.checkChoirName();
            }
        });
    }
    
    @FXML
    private void handleCreateBtnAction(ActionEvent event) throws IOException{
        boolean isCreated = viewModel.create();
        
        if(isCreated){
            //clear field, for reuse
            viewModel.reset();
            
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.INFO, "Choir was created!", infoDialogKey);
            
            // close the dialog.
            Stage stage  = (Stage) getView().getScene().getWindow();
            stage.close();   
        }
        else {
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.WARN, "Ups, sorry! Something went wrong. Please try again!", warnDialogKey);
        }
    }
}
