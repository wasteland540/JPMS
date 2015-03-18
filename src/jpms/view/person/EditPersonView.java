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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jpms.model.Communication;
import jpms.model.CommunicationTyp;
import jpms.model.Person;
import jpms.model.PersonGroup;
import jpms.model.SalutationType;
import jpms.model.VoiceType;
import jpms.view.AbstractView;
import jpms.view.IBasicView;
import jpms.viewmodel.person.EditPersonViewModel;
import jpms.viewmodel.person.NewPersonViewModel;

/**
 * FXML Controller class
 *
 * @author m.elz
 */
public class EditPersonView extends AbstractView implements Initializable, IBasicView {

    @Inject
    private EditPersonViewModel viewModel;
    
    @FXML
    private Node view;
    
    @FXML
    private ListView<Person> memberListView;
    
    @FXML
    private ComboBox<SalutationType> salutationCb;
    
    @FXML
    private TextField firstnameField;
    
    @FXML
    private TextField lastnameField;
    
    @FXML
    private TextField birthdayField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private TextField enterdateField;
    
    @FXML
    private Label birthdayErrorMsg;
    
    @FXML
    private Label enterdateErrorMsg;
    
    @FXML
    private ListView<PersonGroup> choirListView;
    
    @FXML
    private ComboBox choirCb;
    
    @FXML
    private Button addChoirBtn;
    
    @FXML
    private Button removeChoirBtn;
    
    @FXML
    private TextField streetField;
    
    @FXML
    private TextField zipcodeField;
    
    @FXML
    private Label zipcodeErrorMsg;
    
    @FXML
    private TextField cityField;
    
    @FXML
    private ComboBox<VoiceType> voiceCb;
    
    @FXML
    private TextField functionField;
    
    @FXML
    private TextField honorField;
    
    @FXML
    private ListView<Communication> contactListView;
    
    @FXML
    private Button addContactBtn;
    
    @FXML
    private Button removeContactBtn;
    
    @FXML
    private ComboBox<CommunicationTyp> contactTypeCb;
    
    @FXML
    private TextField contactValueField;
    
    @FXML
    private Button saveBtn;
    
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
        viewModel.enterDateProperty().bindBidirectional(enterdateField.textProperty());
        viewModel.streetProperty().bindBidirectional(streetField.textProperty());
        viewModel.zipcodeProperty().bindBidirectional(zipcodeField.textProperty());
        viewModel.cityProperty().bindBidirectional(cityField.textProperty());
        viewModel.functionRoleProperty().bindBidirectional(functionField.textProperty());
        viewModel.honorProperty().bindBidirectional(honorField.textProperty());
        viewModel.communicationValueProperty().bindBidirectional(contactValueField.textProperty());
        
        birthdayErrorMsg.visibleProperty().bind(viewModel.isBirthdayDateVaildProperty().not());
        enterdateErrorMsg.visibleProperty().bind(viewModel.isEnterDateVaildProperty().not());
        zipcodeErrorMsg.visibleProperty().bind(viewModel.isZipcodeVaildProperty().not());
        saveBtn.disableProperty().bind(Bindings.or(birthdayErrorMsg.visibleProperty(), enterdateErrorMsg.visibleProperty()).or(zipcodeErrorMsg.visibleProperty()));
        
        contactListView.setItems(viewModel.getCommunications());
        choirListView.setItems(viewModel.getChoirs());
        
        //dynamic list items from database
        choirCb.getItems().setAll(viewModel.getChoirlist());
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
                viewModel.setSelectedMember(newValue);
                
                setSelectedItemsOfComboboxes();
            }
        });
        
        //selected choir changed
        choirListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        choirListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonGroup>() {
            @Override
            public void changed(ObservableValue<? extends PersonGroup> ov, PersonGroup oldValue, PersonGroup newValue) {
                viewModel.setSelectedChoir(newValue);
            }
        });
        
        //selected contact changed
        contactListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        contactListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Communication>() {
            @Override
            public void changed(ObservableValue<? extends Communication> ov, Communication oldValue, Communication newValue) {
                viewModel.setSelectedCommunication(newValue);
            }
        });
        
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
        enterdateField.textProperty().addListener(new ChangeListener<String>() {
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
    
    private void setSelectedItemsOfComboboxes(){
        //TODO: generic?!
        
        if(viewModel.getSelectedMember() != null){
            //salutation combobox
            for(SalutationType st : salutationCb.getItems()){
                if(st.equals(viewModel.getSelectedMember().getSalutation())){
                    salutationCb.setValue(st);
                    break;
                }
            }

            //voice combobox
            for(VoiceType vt : voiceCb.getItems()){
                if(vt.equals(viewModel.getSelectedMember().getAdditionalInfo().getVoice())){
                    voiceCb.setValue(vt);
                    break;
                }
            }
        }
        else{
            salutationCb.setValue(null);
            voiceCb.setValue(null);
        }       
    }
    
    @FXML
    private void handleAddChoirAction(ActionEvent event) {
        viewModel.addChoir();
    }
    
    @FXML
    private void handleRemoveChoirAction(ActionEvent event) {
        viewModel.removeChoir();
    }
    
    @FXML
    private void handleAddContactAction(ActionEvent event) {
        viewModel.addContact();
    }
    
    @FXML
    private void handleRemoveContactAction(ActionEvent event) {
        viewModel.removeContact();
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) throws ParseException {
        viewModel.save();
    }
}
