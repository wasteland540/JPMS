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
        
    public void showModualStage(Class<?> view, String stageKey, String windowTitle){
        IBasicView basicView = loadView(view);
        
        showStage(basicView, stageKey, windowTitle);
    }
    
    public void showModualStage(Class<?> view, String stageKey, String windowTitle, RefreshMainViewAction refreshMainViewAction){
        IBasicView basicView = loadView(view);
        
        IMainViewRefreshable refreshable = (IMainViewRefreshable) basicView;
        refreshable.setRefreshAction(refreshMainViewAction);
        
        showStage(basicView, stageKey, windowTitle);
    }
    
    public void showDialog(Class<?> dialog, DialogIcon icon, String message){
        String stageKey = dialog.getName();        
        
        IBasicDialog basicDialog = loadDialog(dialog, icon, message);
        
        showDialog(basicDialog, stageKey);
    }
    
    public void showDialog(Class<?> dialog, DialogIcon icon, String message, ConfirmDialogAction confirmDialogAction){
        String stageKey = dialog.getName();        
        
        IBasicDialog basicDialog = loadDialog(dialog, icon, message);
        
        IConfirmDialog confirmDialog = (IConfirmDialog) basicDialog;
        confirmDialog.setConfirmAction(confirmDialogAction);
        
        showDialog(basicDialog, stageKey);
    }
    
    private IBasicView loadView(Class<?> view){
        IBasicView basicView = (IBasicView) jpms.JPMS.getInjector().getInstance(view);
        basicView.payloadBindings();
        
        return basicView;
    }
    
    private IBasicDialog loadDialog(Class<?> dialog, DialogIcon icon, String message){
        IBasicDialog basicDialog = (IBasicDialog) jpms.JPMS.getInjector().getInstance(dialog);
        basicDialog.setMessage(icon, message);  
        
        return basicDialog;
    }
    
    private void showStage(IBasicView basicView, String stageKey, String windowTitle){
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
    
    private void showDialog(IBasicDialog basicDialog, String stageKey){
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
