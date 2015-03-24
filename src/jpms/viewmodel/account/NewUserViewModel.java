package jpms.viewmodel.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jpms.services.IUserService;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class NewUserViewModel extends AbstractBaseViewModel{
           
    @Inject
    private IUserService userService;
    
    private final BooleanProperty isLoginnameTaken = new SimpleBooleanProperty();
    private final StringProperty loginname = new SimpleStringProperty();
    private final StringProperty password1 = new SimpleStringProperty();
    private final StringProperty password2 = new SimpleStringProperty();   
    private final BooleanProperty isPasswordEqual = new SimpleBooleanProperty(true);   
    
    public void checkLoginname(){        
        boolean isTaken = userService.exsistUser(loginname.get());
        
        isLoginnameTaken.set(isTaken);
    }
    
    public void comparePasswords(){
        if(password1.get().equals(password2.get())){
            isPasswordEqual.set(false);
        }
        else {
            isPasswordEqual.set(true);
        }
    }
    
    public boolean createUser() throws IOException{
        boolean isCreated;
        
        isCreated = userService.createUser(loginname.get(), password1.get());
                
        return isCreated;
    }
    
    public void reset(){
        //use setValue method, cause they are bound!
        loginname.setValue("");
        password1.setValue("");
        password2.setValue("");
        
        isLoginnameTaken.setValue(Boolean.FALSE);
        isPasswordEqual.setValue(Boolean.TRUE);
    }
    
    public boolean isIsLoginnameTaken() {
        return isLoginnameTaken.get();
    }

    public void setIsLoginnameTaken(boolean value) {
        isLoginnameTaken.set(value);
    }

    public BooleanProperty isLoginnameTakenProperty() {
        return isLoginnameTaken;
    }
    
    public String getLoginname() {
        return loginname.get();
    }

    public void setLoginname(String value) {
        loginname.set(value);
    }

    public StringProperty loginnameProperty() {
        return loginname;
    }
    
    public String getPassword1() {
        return password1.get();
    }

    public void setPassword1(String value) {
        password1.set(value);
    }

    public StringProperty password1Property() {
        return password1;
    }
    
    public String getPassword2() {
        return password2.get();
    }

    public void setPassword2(String value) {
        password2.set(value);
    }

    public StringProperty password2Property() {
        return password2;
    }
    
    public boolean isIsPasswordEqual() {
        return isPasswordEqual.get();
    }

    public void setIsPasswordEqual(boolean value) {
        isPasswordEqual.set(value);
    }

    public BooleanProperty isPasswordEqualProperty() {
        return isPasswordEqual;
    }

}
