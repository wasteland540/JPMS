package jpms.guice.module;

import com.google.inject.AbstractModule;
import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import jpms.view.LoginView;
import jpms.view.MainView;
import jpms.view.account.ChangePasswordView;
import jpms.view.account.DeleteUserView;
import jpms.view.account.NewUserView;
import jpms.view.choir.EditChoirView;
import jpms.view.choir.NewChoirView;
import jpms.view.dialogs.SimpleDialog;

/**
 *
 * @author m.elz
 */
public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {               
        //views:
        bind(LoginView.class).toInstance((LoginView) loadController("/jpms/fxml/LoginView.fxml"));        
        bind(MainView.class).toInstance((MainView) loadController("/jpms/fxml/MainView.fxml"));
        
        bind(NewUserView.class).toInstance((NewUserView) loadController("/jpms/fxml/account/NewUserView.fxml"));
        bind(DeleteUserView.class).toInstance((DeleteUserView) loadController("/jpms/fxml/account/DeleteUserView.fxml"));
        bind(ChangePasswordView.class).toInstance((ChangePasswordView) loadController("/jpms/fxml/account/ChangePasswordView.fxml"));
        
        bind(NewChoirView.class).toInstance((NewChoirView) loadController("/jpms/fxml/choir/NewChoirView.fxml"));
        bind(EditChoirView.class).toInstance((EditChoirView) loadController("/jpms/fxml/choir/EditChoirView.fxml"));
        //TODO: bind all views
        
        //dialogs:
        bind(SimpleDialog.class).toInstance((SimpleDialog) loadController("/jpms/fxml/dialogs/SimpleDialog.fxml"));
        
    }
    
    protected Object loadController(String url){
        InputStream fxmlStream = null;
        
        try{
            fxmlStream = getClass().getResourceAsStream(url);
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.load(fxmlStream);
            
            return fxmlLoader.getController();
        }
        catch(Exception e){
            // FXML load exceptions are really system failures, and can be treated as RuntimeExceptions
            throw new RuntimeException(String.format("Error loading FXML file '%s'", url), e);
        }
        finally {
            if(fxmlStream != null){
                try{
                    fxmlStream.close();
                }
                catch(IOException e){
                    System.out.println("Warning: failed to close FXML file");
                }
            }
        }
    }
    
}
