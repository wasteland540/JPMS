package jpms.view.person;

import com.google.inject.Inject;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jpms.model.Communication;
import jpms.model.CommunicationTyp;
import jpms.model.PersonGroup;
import jpms.model.SalutationType;
import jpms.model.VoiceType;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.person.NewPersonViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class NewPersonView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private NewPersonViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ComboBox<SalutationType> salutationCb;
    
    @FXML
    private TextField firstnameField;
    
    @FXML
    private TextField lastnameField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private ComboBox<PersonGroup> choirCb;
    
    @FXML
    private TextField birthdayField;
    
    @FXML
    private TextField enterDateField;
    
    @FXML
    private TextField streetField;
    
    @FXML
    private TextField zipcodeField;
    
    @FXML
    private TextField cityField;
    
    @FXML
    private TextField functionField;
    
    @FXML
    private TextField honorField;
    
    @FXML
    private ComboBox<VoiceType> voiceCb;
    
    @FXML
    private ListView<Communication> contactListView;
        
    @FXML
    private TextField contactValueField;
    
    @FXML
    private ComboBox<CommunicationTyp> contactTypeCb;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Label birthdayErrorMsg;
    
    @FXML
    private Label enterDateErrorMsg;
    
    @FXML
    private Label zipcodeErrorMsg;
    
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
        //enums
        salutationCb.getItems().setAll(SalutationType.values());
        voiceCb.getItems().setAll(VoiceType.values());
        contactTypeCb.getItems().setAll(CommunicationTyp.values());
        
        //bindings
        viewModel.firstnameProperty().bindBidirectional(firstnameField.textProperty());
        viewModel.lastnameProperty().bindBidirectional(lastnameField.textProperty());
        viewModel.ageProperty().bindBidirectional(ageField.textProperty());
        viewModel.birthdayProperty().bindBidirectional(birthdayField.textProperty());
        viewModel.enterDateProperty().bindBidirectional(enterDateField.textProperty());
        viewModel.streetProperty().bindBidirectional(streetField.textProperty());
        viewModel.zipcodeProperty().bindBidirectional(zipcodeField.textProperty());
        viewModel.cityProperty().bindBidirectional(cityField.textProperty());
        viewModel.functionRoleProperty().bindBidirectional(functionField.textProperty());
        viewModel.honorProperty().bindBidirectional(honorField.textProperty());
        viewModel.communicationValueProperty().bindBidirectional(contactValueField.textProperty());
                
        birthdayErrorMsg.visibleProperty().bind(viewModel.isBirthdayDateVaildProperty().not());
        enterDateErrorMsg.visibleProperty().bind(viewModel.isEnterDateVaildProperty().not());
        zipcodeErrorMsg.visibleProperty().bind(viewModel.isZipcodeVaildProperty().not());
        saveBtn.disableProperty().bind(Bindings.or(birthdayErrorMsg.visibleProperty(), enterDateErrorMsg.visibleProperty()).or(zipcodeErrorMsg.visibleProperty()));
        
        contactListView.setItems(viewModel.getCommunications());
        
        //dynamic list items from database
        choirCb.getItems().setAll(viewModel.getChoirlist());
        
        setupListeners();
    }

    @Override
    protected void setupListeners() {
        //salutation changed
        salutationCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalutationType>() {
            @Override
            public void changed(ObservableValue<? extends SalutationType> ov, SalutationType oldValue, SalutationType newValue) {
                viewModel.setSalutation(newValue);
            }
        });
        
        //voice changed
        voiceCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<VoiceType>() {
            @Override
            public void changed(ObservableValue<? extends VoiceType> ov, VoiceType oldValue, VoiceType newValue) {
                viewModel.setVoice(newValue);
            }
        });
        
        //choir changed
        choirCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonGroup>() {
            @Override
            public void changed(ObservableValue<? extends PersonGroup> ov, PersonGroup oldValue, PersonGroup newValue) {
                viewModel.setChoir(newValue);
            }
        });
        
        //communicationtyp changed
        contactTypeCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CommunicationTyp>() {
            @Override
            public void changed(ObservableValue<? extends CommunicationTyp> ov, CommunicationTyp oldValue, CommunicationTyp newValue) {
                viewModel.setCommunicationTyp(newValue);
            }
        });
        
        //birthday date changed
        birthdayField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                viewModel.isVaildDate(NewPersonViewModel.DateType.BIRTHDAY);
            }
        });
        
        //enter date changed
        enterDateField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                viewModel.isVaildDate(NewPersonViewModel.DateType.ENTER_DATE);
            }
        });
        
        //zipcode changed
        zipcodeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                viewModel.vaildateZipcode();
            }
        });
        
        //birthday focus lost
        birthdayField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    //focus lost
                    try {
                        viewModel.calculateAge();
                    } catch (ParseException ex) {
                        Logger.getLogger(NewPersonView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    @FXML
    private void handleAddContactBtnAction(ActionEvent event){
        viewModel.addContact();
    }
    
    @FXML
    private void handleSaveBtnAction(ActionEvent event) throws ParseException{
        boolean isCreated = viewModel.save();
        
        if(isCreated){
            //clear field, for reuse
            viewModel.reset();
            
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.INFO, "Member was saved!");
            
            // close the dialog.
            Stage stage  = (Stage) getView().getScene().getWindow();
            stage.close();   
        }
        else {
            //notify user
            viewModel.showDialog(SimpleDialog.class, DialogIcon.WARN, "Ups, sorry! Something went wrong. Please try again!");
        }
    }
}
