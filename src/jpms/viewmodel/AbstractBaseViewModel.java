package jpms.viewmodel;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jpms.util.dialogs.ConfirmDialogAction;
import jpms.util.dialogs.RefreshMainViewAction;
import jpms.view.IBasicView;
import jpms.view.IMainViewRefreshable;
import jpms.view.dialogs.DialogIcon;
import jpms.view.dialogs.IBasicDialog;
import jpms.view.dialogs.IConfirmDialog;

/**
 *
 * @author m.elz
 */
public abstract class AbstractBaseViewModel {
    
    //TODO: showModualStage and showDialog method refactor
    
    public void showModualStage(Class<?> view, String stageKey, String windowTitle){
        IBasicView basicView = (IBasicView) jpms.JPMS.getInjector().getInstance(view);
        basicView.payloadBindings();
        
        Parent root = (Parent) basicView.getView();     
        
        //this is needed, because guice hold an instance of xyzView.class. so it had also one reference to a scene/stage.
        //A root can only have one refernce to a scene/stage!
        //we can reuse our view simple with the .show() method.
        if(root.getScene() != null){
            Stage dialogStage = jpms.JPMS.getStageMap().get(stageKey);
            dialogStage.show();
        }
        else{
            Scene scene = new Scene(root);
                        
            Stage dialogStage = new Stage();
            dialogStage.setTitle(windowTitle);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(jpms.JPMS.getPrimaryStage());

            jpms.JPMS.getStageMap().put(stageKey, dialogStage);

            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }
    
    public void showModualStage(Class<?> view, String stageKey, String windowTitle, RefreshMainViewAction refreshMainViewAction){
        IBasicView basicView = (IBasicView) jpms.JPMS.getInjector().getInstance(view);
        basicView.payloadBindings();
        
        IMainViewRefreshable refreshable = (IMainViewRefreshable) basicView;
        refreshable.setRefreshAction(refreshMainViewAction);
        
        Parent root = (Parent) basicView.getView();     
        
        //this is needed, because guice hold an instance of xyzView.class. so it had also one reference to a scene/stage.
        //A root can only have one refernce to a scene/stage!
        //we can reuse our view simple with the .show() method.
        if(root.getScene() != null){
            Stage dialogStage = jpms.JPMS.getStageMap().get(stageKey);
            dialogStage.show();
        }
        else{
            Scene scene = new Scene(root);
                        
            Stage dialogStage = new Stage();
            dialogStage.setTitle(windowTitle);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(jpms.JPMS.getPrimaryStage());

            jpms.JPMS.getStageMap().put(stageKey, dialogStage);

            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }
    
    public void showDialog(Class<?> dialog, DialogIcon icon, String message){
        String stageKey = dialog.getName();        
        
        IBasicDialog basicDialog = (IBasicDialog) jpms.JPMS.getInjector().getInstance(dialog);
        basicDialog.setMessage(icon, message);       
        
        Parent root = (Parent) basicDialog.getView();     
        
        //this is needed, because guice hold an instance of xyzView.class. so it had also one reference to a scene/stage.
        //A root can only have one refernce to a scene/stage!
        //we can reuse our view simple with the .show() method.
        if(root.getScene() != null){
            Stage dialogStage = jpms.JPMS.getStageMap().get(stageKey);
            dialogStage.show();
        }
        else{
            Scene scene = new Scene(root);
                        
            Stage dialogStage = new Stage();
            //dialogStage.setTitle(windowTitle);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(jpms.JPMS.getPrimaryStage());

            jpms.JPMS.getStageMap().put(stageKey, dialogStage);

            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }
    
    public void showDialog(Class<?> dialog, DialogIcon icon, String message, ConfirmDialogAction confirmDialogAction){
        String stageKey = dialog.getName();        
        
        IBasicDialog basicDialog = (IBasicDialog) jpms.JPMS.getInjector().getInstance(dialog);
        basicDialog.setMessage(icon, message);
        
        IConfirmDialog confirmDialog = (IConfirmDialog) basicDialog;
        confirmDialog.setConfirmAction(confirmDialogAction);
        
        Parent root = (Parent) basicDialog.getView();     
        
        //this is needed, because guice hold an instance of xyzView.class. so it had also one reference to a scene/stage.
        //A root can only have one refernce to a scene/stage!
        //we can reuse our view simple with the .show() method.
        if(root.getScene() != null){
            Stage dialogStage = jpms.JPMS.getStageMap().get(stageKey);
            dialogStage.show();
        }
        else{
            Scene scene = new Scene(root);
                        
            Stage dialogStage = new Stage();
            //dialogStage.setTitle(windowTitle);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(jpms.JPMS.getPrimaryStage());

            jpms.JPMS.getStageMap().put(stageKey, dialogStage);

            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }
    
}
