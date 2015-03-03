package jpms.services;

import java.util.List;
import jpms.model.PmsUser;

/**
 *
 * @author m.elz
 */
public interface IUserService {

    boolean exsistUser(String loginName);
    
    boolean createUser(String loginName, String password);
    
    boolean checkLogin(String loginName, String password);
    
    boolean changePassword(String loginName, String oldPassword, String newPassword);
    
    List<PmsUser> getUsers();
    
    void deleteUser(Long id);
}
