package jpms.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import jpms.model.PmsUser;
import jpms.security.PBKDF2WithHmacSHA1;

/**
 *
 * @author m.elz
 */
public class UserService extends AbstractService implements IUserService {
    
    @Override
    public boolean exsistUser(String loginName) {
        boolean exsistsUser = false;
        
        startTransaction();
        
        Query query = em.createNamedQuery(PmsUser.findUserByLoginname);
        query.setParameter("loginName", loginName);
        
        List<PmsUser> users = query.getResultList();
        
        if(users.size() == 1){
            exsistsUser = true;
        }       
        
        endTransaction();
        
        return exsistsUser;
    }
    
    @Override
    public boolean createUser(String loginName, String password) {
        boolean created = false;       
        
        String encryptedPassword = null;
        
        try {
            encryptedPassword = PBKDF2WithHmacSHA1.generateStorngPasswordHash(password);            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(encryptedPassword != null){
            if(!exsistUser(loginName)){
                //create only, if the encryption was successful!

                startTransaction();
                
                PmsUser user = new PmsUser();
                user.setLoginName(loginName);
                user.setPassword(encryptedPassword);

                em.persist(user);

                created = true;
                
                endTransaction();
            }
        }      
        
        return created;
    }

    @Override
    public boolean checkLogin(String loginName, String password) {
        boolean isOk = false;
        String storedPassword = "";
        
        startTransaction();
        
        Query query = em.createNamedQuery(PmsUser.findUserByLoginname);
        query.setParameter("loginName", loginName);
        
        List<PmsUser> users = query.getResultList();
        
        if(users.size() == 1){
            PmsUser user = users.get(0);
            
            storedPassword = user.getPassword();
        }   
        
        endTransaction();
        
        try {
            isOk = PBKDF2WithHmacSHA1.validatePassword(password, storedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isOk;
    }

    @Override
    public boolean changePassword(String loginName, String oldPassword, String newPassword) {
        boolean changed = false;
        String encrypedNewPassword = null;
        
        try {
            encrypedNewPassword = PBKDF2WithHmacSHA1.generateStorngPasswordHash(newPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(encrypedNewPassword != null){
            if(checkLogin(loginName, oldPassword)){
                startTransaction();

                Query query = em.createNamedQuery(PmsUser.findUserByLoginname);
                query.setParameter("loginName", loginName);

                List<PmsUser> users = query.getResultList();

                if(users.size() == 1){
                    PmsUser user = users.get(0);

                    user.setPassword(encrypedNewPassword);

                    em.persist(user);
                    
                    changed = true;
                }  

                endTransaction();
            }
        }
        
        return changed;
    }
    
    @Override
    public List<PmsUser> getUsers() {
        List<PmsUser> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(PmsUser.getUsers);
        resultList = query.getResultList();
        
        endTransaction();
        
        return resultList;
    }
    
    @Override
    public void deleteUser(Long id) {
        startTransaction();
        
        Query query = em.createNamedQuery(PmsUser.findUserById);
        query.setParameter("id", id);
        
        PmsUser user = (PmsUser) query.getResultList().get(0);
        
        if(user != null){
            em.remove(user);
        }
        
        endTransaction();
    }

}
