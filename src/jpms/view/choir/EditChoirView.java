package jpms.view.choir;

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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jpms.model.PersonGroup;
import jpms.util.dialogs.RefreshMainViewAction;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.view.IMainViewRefreshable;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.choir.EditChoirViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class EditChoirView extends AbstractView implements Initializable, IBasicView, IMainViewRefreshable {
    
    @Inject
    private EditChoirViewModel viewModel;
    
    @FXML
    private Node view;

    @FXML
    private ListView<PersonGroup> choirListView;
    
    @FXML
    private TextField oldChoirNameField;
    
    @FXML
    private TextField newChoirNameField;
    
    @FXML
    private Button renameBtn;
    
    @FXML
    private Label errorMsgLbl;
    
    private RefreshMainViewAction refreshMainViewAction;
    
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
        viewModel.newChoirNameProperty().bindBidirectional(newChoirNameField.textProperty());
        
        oldChoirNameField.textProperty().bind(viewModel.oldChoirNameProperty());
        errorMsgLbl.visibleProperty().bind(viewModel.isChoirNameTakenProperty());
        renameBtn.disableProperty().bind(errorMsgLbl.visibleProperty());
        
        choirListView.setItems(viewModel.getChoirlist());
        
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        //newChoirNameTextChanged
        newChoirNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                viewModel.checkChoirName();
            }
        });
        
        //choir list selection
        choirListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        choirListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonGroup>() {
            @Override
            public void changed(ObservableValue<? extends PersonGroup> ov, PersonGroup oldValue, PersonGroup newValue) {
                viewModel.setSelectedChoir(newValue);
            }
        });
    }   
    
    @FXML
    private void handleRenameBtnAction(ActionEvent event){        
        boolean isRenamed = viewModel.rename();
        
        if(isRenamed){
            //clear field, for reuse
            viewModel.reset();
            viewModel.reloadChoirList();
            
            refreshMainViewAction.Refresh();
            
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.INFO, "Choir renamed!");
        }
        else {
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.WARN, "Ups, sorry! Something went wrong. Please try again!");
        }
    }

    @Override
    public void setRefreshAction(RefreshMainViewAction refreshMainViewAction) {
        this.refreshMainViewAction = refreshMainViewAction;
    }
    
}
