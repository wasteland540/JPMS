package jpms.viewmodel;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jpms.application.IAppInfo;
import jpms.services.IUserService;
import jpms.view.MainView;

/**
 *
 * @author m.elz
 */
@Singleton
public class LoginViewModel {
    
    @Inject
    private IAppInfo appInfo;
    
    @Inject
    private IUserService userService;
    
    private final StringProperty loginname = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final BooleanProperty isLoginOk = new SimpleBooleanProperty(false);    
    
    public void login(){
        boolean isOk = userService.checkLogin(loginname.get(), password.get());
        
        if(isOk){
            isLoginOk.set(false);
            
            appInfo.setLoginname(loginname.get());
            
            switchToMainView();
        }
        else {
            isLoginOk.set(true);
        }
    }
    
    private void switchToMainView(){
        MainView mainView = jpms.JPMS.getInjector().getInstance(MainView.class);
        mainView.payloadBindings();
        
        Parent root = (Parent) mainView.getView();     
        
        Scene scene = new Scene(root);
                
        jpms.JPMS.getPrimaryStage().setScene(scene);
        jpms.JPMS.getPrimaryStage().show();
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
    
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String value) {
        password.set(value);
    }

    public StringProperty passwordProperty() {
        return password;
    }
    
    public boolean isIsLoginOk() {
        return isLoginOk.get();
    }

    public void setIsLoginOk(boolean value) {
        isLoginOk.set(value);
    }

    public BooleanProperty isLoginOkProperty() {
        return isLoginOk;
    }
}
