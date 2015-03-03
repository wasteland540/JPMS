package jpms.viewmodel.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jpms.application.IAppInfo;

/**
 *
 * @author m.elz
 */
@Singleton
public class ChangePasswordViewModel {
    
    @Inject
    private IAppInfo appInfo;
    
    private final StringProperty oldPassword = new SimpleStringProperty();
    private final StringProperty newPassword = new SimpleStringProperty();
    private final StringProperty newPassword2 = new SimpleStringProperty();
    private final BooleanProperty isNewPasswordEqual = new SimpleBooleanProperty();
    private final BooleanProperty isOldPasswordOk = new SimpleBooleanProperty();

 
    public void save(){
        //TODO: userServie...
        String loginname =appInfo.getLoginname();
        
        int a = 12;
    }
    
    public void checkOldPassword(){
        //TODO: check old password...
        String loginname =appInfo.getLoginname();
        
        
        
        
        
        
        
        
        
    }
    
    public void comparePasswords(){
        if(newPassword.get().equals(newPassword2.get())){
            isNewPasswordEqual.set(false);
        }
        else {
            isNewPasswordEqual.set(true);
        }
    }
    
    public String getOldPassword() {
        return oldPassword.get();
    }

    public void setOldPassword(String value) {
        oldPassword.set(value);
    }

    public StringProperty oldPasswordProperty() {
        return oldPassword;
    }
    
    public String getNewPassword() {
        return newPassword.get();
    }

    public void setNewPassword(String value) {
        newPassword.set(value);
    }

    public StringProperty newPasswordProperty() {
        return newPassword;
    }
    
    public String getNewPassword2() {
        return newPassword2.get();
    }

    public void setNewPassword2(String value) {
        newPassword2.set(value);
    }

    public StringProperty newPassword2Property() {
        return newPassword2;
    }
    
    public boolean isIsNewPasswordEqual() {
        return isNewPasswordEqual.get();
    }

    public void setIsNewPasswordEqual(boolean value) {
        isNewPasswordEqual.set(value);
    }

    public BooleanProperty isNewPasswordEqualProperty() {
        return isNewPasswordEqual;
    }
    
    public boolean isIsOldPasswordOk() {
        return isOldPasswordOk.get();
    }

    public void setIsOldPasswordOk(boolean value) {
        isOldPasswordOk.set(value);
    }

    public BooleanProperty isOldPasswordOkProperty() {
        return isOldPasswordOk;
    }
    
}
