package jpms.view.dues;

import com.google.inject.Inject;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jpms.listeners.changelistener.OnlyNumberTextFieldFilter;
import jpms.model.Fee;
import jpms.model.Person;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.viewmodel.dues.DuesViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class DuesView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private DuesViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ListView<Person> memberListView;
    
    @FXML
    private ListView<Fee> duesListView;
    
    @FXML
    private TextField amountField;
    
    @FXML
    private TextField dateField;
    
    @FXML
    private Button addBtn;
    
    @FXML
    private Label dateErrorMsg;

    private ObservableList<Fee> selectedPersonDues;
    
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
        amountField.textProperty().bindBidirectional(viewModel.amountProperty());
        dateField.textProperty().bindBidirectional(viewModel.settledAtProperty());
        dateErrorMsg.visibleProperty().bind(viewModel.isDateVaildProperty().not());
        
        addBtn.disableProperty().bind(dateErrorMsg.visibleProperty());
        
        memberListView.setItems(viewModel.getMemberList());
                
        setupListeners();
    }
    
    @Override
    protected void setupListeners() {
        //selected member changed
        memberListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        memberListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> ov, Person oldValue, Person newValue) {
                viewModel.setSelectedPerson(newValue);
        
                //refresh works only at this place.
                //if we set this in de payloadedBindings method, there is no refresh, even if the observableList changes...
                duesListView.setItems(viewModel.getFeeList());
            }
        });
        
        //logic outsource for reuse in another projects
        amountField.lengthProperty().addListener(new OnlyNumberTextFieldFilter(amountField));
        
        //settledAt changed
        dateField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                viewModel.validateDate();
            }
        });
    }
    
    @FXML
    private void handleAddBtnAction() throws ParseException {
        viewModel.addFee();
        
        //getSelectedPersonDues();
    }

    public ObservableList<Fee> getSelectedPersonDues() {
        if(viewModel.getSelectedPerson() != null){
            if(selectedPersonDues == null){
                selectedPersonDues = FXCollections.observableArrayList(viewModel.getSelectedPerson().getFees());
            }
            else {
                selectedPersonDues.clear();
                selectedPersonDues.addAll(viewModel.getSelectedPerson().getFees());
            }
        }
        
        return selectedPersonDues;
    }
    
    
}
