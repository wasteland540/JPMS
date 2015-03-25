package jpms.view.dialogs;

import javafx.scene.Node;

/**
 *
 * @author m.elz
 */
public interface IBasicDialog {
    
    Node getView();
    
    void setMessage(DialogIcon icon, String message);   
    
}
