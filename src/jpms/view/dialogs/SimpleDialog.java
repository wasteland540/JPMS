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

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class SimpleDialog implements Initializable, IBasicDialog {

    @FXML
    private Node view;
    
    @FXML
    private Label messageLbl;
    
    @FXML
    private ImageView iconImageView;
    
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
        
        if(icon != null){
            InputStream iconStream = null;
            
            if(icon == DialogIcon.INFO){
                iconStream = getClass().getResourceAsStream("/resources/images/about.png");
            }
            else if(icon == DialogIcon.WARN){
                iconStream = getClass().getResourceAsStream("/resources/images/warning.png");
            }
            else if(icon == DialogIcon.ERROR){
                iconStream = getClass().getResourceAsStream("/resources/images/stop.png");
            }
            
            if(iconStream != null){
                iconImageView.setImage(new Image(iconStream));
            }
        }
    }
    
    @FXML
    private void handleOkBtnAction(ActionEvent event){
         Stage stage  = (Stage) messageLbl.getScene().getWindow();
         stage.close();
    }


}
