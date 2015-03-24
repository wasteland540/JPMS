package jpms;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jpms.guice.module.MasterModule;
import jpms.view.IBasicView;
import jpms.view.LoginView;

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
