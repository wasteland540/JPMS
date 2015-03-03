package jpms.viewmodel;

import com.google.inject.Singleton;
import jpms.view.account.ChangePasswordView;
import jpms.view.account.DeleteUserView;
import jpms.view.account.NewUserView;

/**
 *
 * @author m.elz
 */
@Singleton
public class MainViewModel extends AbstractBaseViewModel {
    
    private final String newUserStageKey = "newUserStage";
    private final String deleteUserStageKey = "deleteUserStage";
    private final String changePasswordStageKey = "changePasswordStageKey";
            
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
        
    }
    
    public void editChoir(){
        
    }
    
    public void deleteChoir(){
        
    }
    
}
