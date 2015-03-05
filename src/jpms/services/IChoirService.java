package jpms.services;

import java.util.List;
import jpms.model.PersonGroup;

/**
 *
 * @author m.elz
 */
public interface IChoirService {
    
    boolean checkChoirName(String choirName);
    
    boolean createChoir(String choirName);
    
    List<PersonGroup> getChoirs();
    
    boolean rename(Long id, String newChoirName);
    
    boolean delete(Long id);
    
}
