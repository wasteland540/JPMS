package jpms.viewmodel.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.model.PmsUser;
import jpms.services.IUserService;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class DeleteUserViewModel extends AbstractBaseViewModel {
    
    @Inject
    private IUserService userService;
    private PmsUser selectedUser;
    private ObservableList<PmsUser> userlist;
        
    public void deleteUser(){
        if(selectedUser != null){
            userService.deleteUser(selectedUser.getId());
            
            userlist.remove(selectedUser);
        }
    }
    
    public ObservableList getUserlist() {
        reloadUserlist();
                
        return userlist;
    }

    public void setSelectedUser(PmsUser selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    private void reloadUserlist(){
        List<PmsUser> users = userService.getUsers();
        
        if(userlist != null){
            userlist.clear();
            userlist.addAll(users);
        }
        else {
            userlist = FXCollections.observableArrayList(users);
        }
    }

}
