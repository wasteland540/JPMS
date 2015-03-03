package jpms.viewmodel.account;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpms.callback.MessageCallBack;
import jpms.messaging.ViewModelMessage;
import jpms.messaging.ViewModelQueue;
import jpms.model.PmsUser;
import jpms.services.IUserService;
import jpms.viewmodel.AbstractBaseViewModel;

/**
 *
 * @author m.elz
 */
@Singleton
public class DeleteUserViewModel extends AbstractBaseViewModel implements MessageCallBack {
    
    @Inject
    private IUserService userService;
    private PmsUser selectedUser;
    private ObservableList<PmsUser> userlist;
    
    public DeleteUserViewModel() throws IOException, InterruptedException{
        receiveMessage(ViewModelQueue.NEW_USER_ADDED_QUEUE.name(), this);
    }
        
    public void deleteUser(){
        if(selectedUser != null){
            userService.deleteUser(selectedUser.getId());
            
            userlist.remove(selectedUser);
        }
    }
    
    public ObservableList getUserlist() {
        if(userlist == null){
            reloadUserlist();
        }
        
        return userlist;
    }

    public void setSelectedUser(PmsUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    @Override
    public void execute(ViewModelMessage message) {
        if(message == ViewModelMessage.NEW_USER_ADDED){
            reloadUserlist();
        }
    }
    
    private void reloadUserlist(){
        List<PmsUser> users = userService.getUsers();
        userlist = FXCollections.observableArrayList(users);
    }

}
