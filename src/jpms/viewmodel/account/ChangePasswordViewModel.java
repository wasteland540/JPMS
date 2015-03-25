package jpms.viewmodel.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jpms.application.IAppInfo;
import jpms.services.IUserService;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.SimpleDialog;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class ChangePasswordViewModel extends AbstractBaseViewModel {
        
    @Inject
    private IAppInfo appInfo;
    
    @Inject
    private IUserService userService;
    
    private final StringProperty oldPassword = new SimpleStringProperty();
    private final StringProperty newPassword = new SimpleStringProperty();
    private final StringProperty newPassword2 = new SimpleStringProperty();
    private final BooleanProperty isNewPasswordEqual = new SimpleBooleanProperty(true);

    public boolean save(){
        String loginname = appInfo.getLoginname();
        String oldPass = oldPassword.get();
        String newPass = newPassword.get();
        
        boolean changed = userService.changePassword(loginname, oldPass, newPass);
        
        return changed;
    }
    
    public void comparePasswords(){
        if(newPassword.get().equals(newPassword2.get())){
            isNewPasswordEqual.set(false);
        }
        else {
            isNewPasswordEqual.set(true);
        }
    }
    
    public void reset(){
        //use setValue method, cause they are bound!
        oldPassword.setValue("");
        newPassword.setValue("");
        newPassword2.setValue("");
        
        isNewPasswordEqual.setValue(Boolean.TRUE);
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
        
}
