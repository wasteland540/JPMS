package jpms.view.dialogs;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jpms.util.dialogs.ConfirmDialogAction;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class ConfirmDialog implements Initializable, IBasicDialog, IConfirmDialog {

    @FXML
    private Node view;
    
    @FXML
    private Label messageLbl;
    
    @FXML
    private ImageView iconImageView;

    private ConfirmDialogAction confirmDialogAction;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setMessage(DialogIcon icon, String message) {
        messageLbl.textProperty().set(message);
        
        InputStream iconStream = null;
        
        if(icon != null){           
            if(icon == DialogIcon.INFO){
                iconStream = getClass().getResourceAsStream("/resources/images/about.png");
            }
            else if(icon == DialogIcon.WARN){
                iconStream = getClass().getResourceAsStream("/resources/images/warning.png");
            }
            else if(icon == DialogIcon.ERROR){
                iconStream = getClass().getResourceAsStream("/resources/images/stop.png");
            }
        }
        else {
            iconStream = getClass().getResourceAsStream("/resources/images/help2.png");
        }
        
        if(iconStream != null){
            iconImageView.setImage(new Image(iconStream));
        }
    }
    
    @FXML
    private void handleYesBtnAction(ActionEvent event){
        confirmDialogAction.OnConfirm();
        
        Stage stage  = (Stage) view.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handleNoBtnAction(ActionEvent event){
        confirmDialogAction.OnCancel();
        
        Stage stage  = (Stage) view.getScene().getWindow();
        stage.close();
    }

    @Override
    public void setConfirmAction(ConfirmDialogAction confirmDialogAction) {
        this.confirmDialogAction = confirmDialogAction;
    }
    
}
