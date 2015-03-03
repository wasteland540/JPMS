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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import jpms.model.PmsUser;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.viewmodel.account.DeleteUserViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class DeleteUserView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private DeleteUserViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ListView<PmsUser> userlistListView;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //note: do nothing here! because we get wrong handling by guice...
    }    

    @Override
    public void payloadBindings() {
        userlistListView.setItems(viewModel.getUserlist());
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        userlistListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        userlistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PmsUser>() {
            @Override
            public void changed(ObservableValue<? extends PmsUser> ov, PmsUser oldValue, PmsUser newValue) {
                viewModel.setSelectedUser(newValue);
            }
        });
    }

    @Override
    public Node getView() {
        return view;
    }

    @FXML
    private void handleDeleteBtnAction(ActionEvent event){
        viewModel.deleteUser();
    }
    
    
}