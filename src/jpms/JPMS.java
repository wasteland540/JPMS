package jpms;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jpms.guice.module.MasterModule;
import jpms.view.IBasicView;
import jpms.view.LoginView;
import jpms.viewmodel.account.DeleteUserViewModel;

/**
 *
 * @author m.elz
 */
public class JPMS extends Application {
        
    private static Injector injector;
    private static Stage primaryStage; 
    
    //for reusing stages
    private static final Map<String, Stage> stageMap = new HashMap<>();
        
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        
        //DI-container
        injector = Guice.createInjector(new MasterModule());        
        
        //we have to create the view instances with the injector, so we can inject another classes there!
        IBasicView loginView = injector.getInstance(LoginView.class);
                       
        Parent root = (Parent) loginView.getView();
        loginView.payloadBindings();
        
        Scene scene = new Scene(root);
        
        //for the login view, no resizeing allowed
        stage.setResizable(false);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop(){
        //TODO: every class with starts a consumer, from rabbitMQ, have to close there channel and connection on application exit!
        //note: this only works, because all viewmodels will be singletons..
        //note 2: --> every class with will start a consumer have to be a singleton!
        DeleteUserViewModel viewModel = injector.getInstance(DeleteUserViewModel.class);
        try {
            viewModel.getChannel().close();
            viewModel.getConnection().close();
        } catch (IOException ex) {
            Logger.getLogger(JPMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    public static Injector getInjector(){
        return injector;
    }
    
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public static Map<String, Stage> getStageMap(){
        return stageMap;
    }
}
