package jpms.viewmodel;

import com.google.inject.Singleton;
import jpms.view.account.ChangePasswordView;
import jpms.view.account.DeleteUserView;
import jpms.view.account.NewUserView;
import jpms.view.choir.DeleteChoirView;
import jpms.view.choir.EditChoirView;
import jpms.view.choir.NewChoirView;

/**
 *
 * @author m.elz
 */
@Singleton
public class MainViewModel extends AbstractBaseViewModel {
    
    private final String newUserStageKey = "newUserStage";
    private final String deleteUserStageKey = "deleteUserStage";
    private final String changePasswordStageKey = "changePasswordStageKey";
    private final String newChoirStageKey = "newChoirStage";
    private final String editChoirStageKey = "editChoirStageKey";
    private final String deleteChoirStageKey = "deleteChoirStageKey";
            
    public void showNewUserView(){
        showModualStage(NewUserView.class, newUserStageKey, "New User");
    }
    
    public void showDeleteUserView(){
        showModualStage(DeleteUserView.class, deleteUserStageKey, "Delete User");
    }
    
    public void showChangePasswordView(){
        showModualStage(ChangePasswordView.class, changePasswordStageKey, "Change Password");
    }
    
    public void newChoir(){
        showModualStage(NewChoirView.class, newChoirStageKey, "New Choir");
    }
    
    public void editChoir(){
        showModualStage(EditChoirView.class, editChoirStageKey, "Rename Choir");
    }
    
    public void deleteChoir(){
        showModualStage(DeleteChoirView.class, deleteChoirStageKey, "Delete Choir");
    }
    
}
