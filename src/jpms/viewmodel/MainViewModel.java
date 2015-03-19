package jpms.viewmodel;

import com.google.inject.Singleton;
import jpms.view.account.ChangePasswordView;
import jpms.view.account.DeleteUserView;
import jpms.view.account.NewUserView;
import jpms.view.choir.DeleteChoirView;
import jpms.view.choir.EditChoirView;
import jpms.view.choir.NewChoirView;
import jpms.view.dues.DuesView;
import jpms.view.person.EditPersonView;
import jpms.view.person.NewPersonView;
import jpms.view.person.SetPassivPersonView;

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
    private final String newPersonStageKey = "newPersonStageKey";
    private final String editPersonStageKey = "editPersonStageKey";
    private final String setPassivePersonStageKey = "setPassivePersonStageKey";
    private final String duesStageKey = "duesStageKey";
            
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
    
    public void newMember(){
        showModualStage(NewPersonView.class, newPersonStageKey, "New Member");
    }

    public void editMember() {
        showModualStage(EditPersonView.class, editPersonStageKey, "Edit Member");
    }
    
    public void setMemberPassive(){
        showModualStage(SetPassivPersonView.class, setPassivePersonStageKey, "Set Member Passive");
    }
    
    public void editDues() {
        showModualStage(DuesView.class, duesStageKey, "Dues");
    }
    
}
